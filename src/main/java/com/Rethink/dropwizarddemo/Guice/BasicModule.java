package com.Rethink.dropwizarddemo.Guice;

import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.Rethink.dropwizarddemo.DAO.DonorDAOImpl;
import com.Rethink.dropwizarddemo.DropwizardResources.DonorsResource;
import com.Rethink.dropwizarddemo.DropwizardResources.DonorsResourceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;


public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DonorDAO.class)
                .annotatedWith(Names.named("DonorDAOImpl"))
                .to(DonorDAOImpl.class);

        bind(DonorsResource.class)
                .annotatedWith(Names.named("DonorResource"))
                .to(DonorsResourceImpl.class);

    }
}