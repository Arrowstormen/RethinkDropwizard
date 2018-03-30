package com.Rethink.dropwizarddemo.resources;

import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.Rethink.dropwizarddemo.POJO.Donor;
import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/donors")
@Produces(MediaType.APPLICATION_JSON)
public class DonorsResource {

    // Would like to ensure that correct httpresponsecodes are returned
    // Jersey API

    private DonorDAO dao;

    public DonorsResource(DonorDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @Produces({MediaType.APPLICATION_JSON})
    public List<Donor> FindAll() {
        return dao.findAll();
    }

    @GET
    @Timed
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response Find(@PathParam("id") int i) {

        Donor result = dao.find(i);

        if (result != null) {
            return Response.ok(result).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Timed
    public Donor Create(@Valid Donor donor) {
        dao.create(donor);
        return donor;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Timed
    public void Update(@Valid Donor donor) {
        dao.update(donor);

    }

    @Path("/{id}")
    @DELETE
    public void Delete(@PathParam("id") int id) {
        dao.delete(id);

    }
}
