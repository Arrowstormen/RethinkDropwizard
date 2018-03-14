package com.Rethink.dropwizarddemo;

import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;

@Path("/donors")
@Produces(MediaType.APPLICATION_JSON)
public class DonorsResource {
    private final ArrayList<Donor> defaultList;

    public DonorsResource(ArrayList<Donor> defaultList) {
        this.defaultList = defaultList;
    }

    @GET
    @Timed
    public ArrayList<Donor> GetAllDonors() {
        return defaultList;
    }
}
