package com.Rethink.dropwizarddemo;

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
    public void run(DropwizardConfiguration configuration,
                    Environment environment) {
        final DonorsResource resource = new DonorsResource(
                configuration.getDefaultList()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getDefaultList());
        environment.healthChecks().register("defaultList", healthCheck);
        environment.jersey().register(resource);
    }

}