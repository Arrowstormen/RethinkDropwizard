package com.Rethink.dropwizarddemo;

import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.Rethink.dropwizarddemo.Guice.BasicModule;
import com.Rethink.dropwizarddemo.Health.TemplateHealthCheck;
import com.Rethink.dropwizarddemo.resources.DonorsResource;
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

    @Override
    public void run(DropwizardConfiguration configuration, Environment environment) {
        // Healthchecks
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getDefaultList());
        environment.healthChecks().register("defaultList", healthCheck);

        // GUICE dependency injection
        final Properties props = new Properties();
        props.setProperty("JDBC.username", "postgres");
        props.setProperty("JDBC.password", "blank");

        Injector injector = Guice.createInjector(
                new XMLMyBatisModule() {
                    @Override
                    protected void initialize() {
                        setEnvironmentId("development");
                        setClassPathResource("mybatis/config.xml");
                        addProperties(props);
                    }
                },
                new BasicModule()
        );

        DonorDAO donorDAO = injector.getInstance(DonorDAO.class);

        // create Resouces
        final DonorsResource donorResource = new DonorsResource(
                donorDAO
        );

        // add resources
        environment.jersey().register(donorResource);

    }

}