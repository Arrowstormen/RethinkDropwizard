package com.Rethink.dropwizarddemo.POJO;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class Donor {

    @JsonProperty
    private int id;

    @NotEmpty
    @JsonProperty
    private String name;

    @NotEmpty
    @JsonProperty
    private String address;

    @NotEmpty
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

    public Donor(int id, String name, String address, String contactName, int contactPhone) {
        this.id = id;
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
    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "id:" + id + ", " + "name:" + name + ", " + "address:" + address + ", " + "contactName:" + contactName
                + ", " + "contactPhone:" + contactPhone;
    }
}