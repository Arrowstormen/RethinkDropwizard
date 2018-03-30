package com.Rethink.dropwizarddemo.DAO;

import com.Rethink.dropwizarddemo.POJO.Donor;

import java.util.List;

public interface DAO {
    int create(Donor donor);

    int update(Donor donor);

    List<Donor> findAll();

    Donor find(int id);

    int delete(int i);
}
