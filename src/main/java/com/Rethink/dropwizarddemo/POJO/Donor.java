package com.Rethink.dropwizarddemo.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Donor {
    @JsonProperty
    private int id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String address;
    @JsonProperty
    private String contactName;
    @JsonProperty
    private int contactPhone;

    public Donor() {
        // Jackson deserialization
    }

    public Donor(String name, String address, String contactName, int contactPhone) {
        this.name = name;
        this.address = address;
        this.contactName = contactName;
        this.contactPhone = contactPhone;
    }

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getAddress() {
        return address;
    }

    @JsonProperty
    public String getContactName() {
        return contactName;
    }

    @JsonProperty
    public int getContactPhone() {
        return contactPhone;
    }


}