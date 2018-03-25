package com.Rethink.dropwizarddemo;

import com.Rethink.dropwizarddemo.POJO.Donor;
import com.codahale.metrics.annotation.Timed;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/donors")
@Produces(MediaType.APPLICATION_JSON)
public class DonorsResource {

    private DonorDAO dao;
    private List<Donor> defaultList;

    public DonorsResource(List<Donor> list, DonorDAO dao) {
        this.dao = dao;
        defaultList = list;
    }

    @GET
    @Timed
    @Produces({MediaType.APPLICATION_JSON})
    public List<Donor> getAllDonors() {
        return dao.getAllDonors();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Timed
    public void CreateDonor(@Valid Donor donor) {
        dao.createDonor(donor);
    }

}
