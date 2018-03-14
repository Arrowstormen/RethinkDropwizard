package com.Rethink.dropwizarddemo;

import com.codahale.metrics.health.HealthCheck;

import java.util.ArrayList;

public class TemplateHealthCheck extends HealthCheck {
    private final ArrayList<Donor> defaultList;

    public TemplateHealthCheck(ArrayList<Donor> defaultList) {
        this.defaultList = defaultList;
    }

    @Override
    protected Result check() throws Exception {
        if (!defaultList.isEmpty()) {
            return Result.unhealthy("this is not an empty list.");
        }
        return Result.healthy();
    }
}
