package com.Rethink.dropwizarddemo.Dropwizard;

import com.Rethink.dropwizarddemo.POJO.Donor;
import com.codahale.metrics.health.HealthCheck;

import java.util.List;

public class TemplateHealthCheck extends HealthCheck {
    private final List<Donor> defaultList;

    public TemplateHealthCheck(List<Donor> defaultList) {
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
