package com.Rethink.dropwizarddemo.DropwizardResources;

import com.Rethink.dropwizarddemo.POJO.Donor;
import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public interface DonorsResource {

    @GET
    @Timed
    @Produces({MediaType.APPLICATION_JSON})
    List<Donor> FindAll();

    @GET
    @Timed
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    Response Find(@PathParam("id") int i);

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Timed
    Donor Create(@Valid Donor donor);

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Timed
    void Update(@Valid Donor donor);

    @Path("/{id}")
    @DELETE
    void Delete(@PathParam("id") int id);

}
