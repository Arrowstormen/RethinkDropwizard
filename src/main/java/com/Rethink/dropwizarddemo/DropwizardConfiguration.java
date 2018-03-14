package com.Rethink.dropwizarddemo;


import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;

public class DropwizardConfiguration extends Configuration {

    @NotEmpty
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