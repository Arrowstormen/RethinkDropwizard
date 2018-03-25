package com.Rethink.dropwizarddemo;

import com.Rethink.dropwizarddemo.POJO.Donor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class mybatisInsert {

    public static void main(String args[]) throws IOException {

        createDonor();
        List<Donor> donorList = getAllDonors();

        for (Donor x : donorList) {
            System.out.println(x.getName());
        }

    }

    public static void createDonor() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis/config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        //Create a new student object
        Donor donor = new Donor("Noma", "Refshalevej 96, 1432 København K", "René Redzepi", 12345678);

        //Insert donor data
        session.insert("DonorMapper.insert", donor);

        System.out.println("record inserted successfully");

        session.commit();
        session.close();
    }


    public static List<Donor> getAllDonors() throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis/config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession session = sqlSessionFactory.openSession();

        List<Donor> donorList = session.selectList("DonorMapper.getAll");

        session.commit();
        session.close();

        return donorList;
    }
}