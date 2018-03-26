package com.Rethink.dropwizarddemo;

import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.Rethink.dropwizarddemo.Health.TemplateHealthCheck;
import com.Rethink.dropwizarddemo.resources.DonorsResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // create Resouces
        final DonorsResource donorResource = new DonorsResource(
                new DonorDAO()
        );

        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getDefaultList());
        environment.healthChecks().register("defaultList", healthCheck);

        // add resources
        environment.jersey().register(donorResource);
    }

}