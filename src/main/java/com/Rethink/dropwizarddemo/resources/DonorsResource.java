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

    private DonorDAO dao;

    public DonorsResource(DonorDAO dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    @Produces({MediaType.APPLICATION_JSON})
    public List<Donor> getAllDonors() {
        return dao.getAllDonors();
    }

    @GET
    @Timed
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Donor getDonor(@PathParam("id") int i) {
        return dao.find(i);

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Timed
    public void CreateDonor(@Valid Donor donor) {
        Response.ok(dao.createDonor(donor)).build();
    }

    // Does not return http-success code
    @DELETE
    public void DeleteDonor(int id) {
        Response.ok(dao.deleteDonor(id)).build();
    }
}
