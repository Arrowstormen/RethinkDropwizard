package com.Rethink.dropwizarddemo;


import com.Rethink.dropwizarddemo.POJO.Donor;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import java.util.ArrayList;

public class DropwizardConfiguration extends Configuration {

    //@NotEmpty
    private ArrayList<Donor> defaultList = new ArrayList<Donor>();

    @JsonProperty
    public ArrayList<Donor> getDefaultList() {
        return defaultList;
    }

    @JsonProperty
    public void setDefaultList(ArrayList<Donor> donors) {
        this.defaultList = donors;
    }
}