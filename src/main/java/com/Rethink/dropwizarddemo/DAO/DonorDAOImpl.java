package com.Rethink.dropwizarddemo.DAO;

import com.Rethink.dropwizarddemo.Mappers.DonorMapper;
import com.Rethink.dropwizarddemo.POJO.Donor;
import com.google.inject.Inject;

import java.util.List;


public class DonorDAOImpl implements DonorDAO {

    private DonorMapper mapper;

    @Inject
    public DonorDAOImpl(DonorMapper mapper) {
        this.mapper = mapper;
    }

    public int create(Donor donor) {
        int result = mapper.create(donor);
        System.out.println("record inserted successfully");

        return result;
    }

    public int update(Donor donor) {
        //Insert donor data
        int rowsAffected = mapper.update(donor);
        System.out.println("record updated successfully");

        return rowsAffected;
    }

    public List<Donor> findAll() {
        List<Donor> resp = mapper.findAll();
        System.out.println("records gotten successfully");

        return resp;
    }

    public Donor find(int id) {
        Donor resp = mapper.find(id);
        System.out.println("record found successfully");

        return resp;
    }

    public int delete(int i) {
        int rowsAffected = mapper.delete(i);
        System.out.println("record deleted successfully");

        return rowsAffected;
    }

}