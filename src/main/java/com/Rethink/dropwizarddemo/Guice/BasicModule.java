package com.Rethink.dropwizarddemo.Guice;

import com.Rethink.dropwizarddemo.DAO.DAO;
import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;


public class BasicModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DAO.class)
                .annotatedWith(Names.named("DonorDAO"))
                .to(DonorDAO.class);


    }
}