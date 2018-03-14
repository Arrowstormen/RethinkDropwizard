package com.Rethink.dropwizarddemo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Donor {
    private int id;
    private String name;

        public Donor() {
            // Jackson deserialization
        }

        public Donor(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @JsonProperty
        public long getId() {
            return id;
        }

        @JsonProperty
        public String getName() {
            return name;
        }

}
