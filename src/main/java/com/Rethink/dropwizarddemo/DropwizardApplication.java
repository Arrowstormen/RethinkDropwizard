package com.Rethink.dropwizarddemo;

import com.Rethink.dropwizarddemo.DropwizardResources.DonorsResourceImpl;
import com.Rethink.dropwizarddemo.Guice.BasicModule;
import com.Rethink.dropwizarddemo.Health.TemplateHealthCheck;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.mybatis.guice.XMLMyBatisModule;

import java.util.Properties;

public class DropwizardApplication extends Application<DropwizardConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "Rethink Food";
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap) {
        // nothing to do yet
    }

    // Dropwizard entry point
    @Override
    public void run(DropwizardConfiguration configuration, Environment environment) {
        // Healthchecks
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getDefaultList());
        environment.healthChecks().register("defaultList", healthCheck);

        // Datasource properties, used below in XMLMyBatisModule
        final Properties props = new Properties();
        props.setProperty("JDBC.username", "postgres");
        props.setProperty("JDBC.password", "blank");

        // GUICE
        Injector injector = Guice.createInjector(
                // Load MyBatis config
                new XMLMyBatisModule() {
                    @Override
                    protected void initialize() {
                        setEnvironmentId("development");
                        setClassPathResource("mybatis/config.xml");
                        addProperties(props);
                    }
                },
                // Contains dependency injection bindings
                new BasicModule()
        );

        // Instanciate DonorResource
        DonorsResourceImpl donorResource = injector.getInstance(DonorsResourceImpl.class);

        // add DropwizardResources
        environment.jersey().register(donorResource);

    }

}