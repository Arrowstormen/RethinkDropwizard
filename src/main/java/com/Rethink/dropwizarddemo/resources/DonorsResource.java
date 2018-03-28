package com.Rethink.dropwizarddemo.resources;

import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.Rethink.dropwizarddemo.POJO.Donor;
import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
        return dao.getAllDonors();
    }

    @GET
    @Timed
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Donor Find(@PathParam("id") int i) {
        return dao.find(i);
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Timed
    public Donor Create(@Valid Donor donor) {
        dao.createDonor(donor);
        return donor;
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Timed
    public void Update(@Valid Donor donor) {
        dao.updateDonor(donor);
    }

    @Path("/{id}")
    @DELETE
    public void Delete(@PathParam("id") int id) {
        dao.deleteDonor(id);
    }
}
