package com.Rethink.dropwizarddemo.Mappers;

import com.Rethink.dropwizarddemo.POJO.Donor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DonorMapper {

    int create(Donor d);

    List<Donor> findAll();

    Donor find(int id);

    int update(Donor d);

    int delete(int i);
}