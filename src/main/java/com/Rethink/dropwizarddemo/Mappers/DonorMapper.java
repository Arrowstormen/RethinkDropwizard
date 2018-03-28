package com.Rethink.dropwizarddemo.Mappers;

import com.Rethink.dropwizarddemo.POJO.Donor;

import java.util.List;

public interface DonorMapper {

    void create(Donor d);

    List<Donor> findall();

    Donor find(int id);

    void update(Donor d);

    void delete(int i);

}