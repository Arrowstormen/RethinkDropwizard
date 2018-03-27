package com.Rethink.dropwizarddemo.DAO;

import com.Rethink.dropwizarddemo.POJO.Donor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;


public class DonorDAO {

    private SqlSessionFactory sqlSessionFactory;

    public DonorDAO() {
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader("mybatis/config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public int createDonor(Donor donor) {
        SqlSession session = sqlSessionFactory.openSession();

        //Insert donor data
        int resp = session.insert("DonorMapper.create", donor);

        System.out.println("record inserted successfully");

        session.commit();
        session.close();

        return resp;
    }

    public List<Donor> getAllDonors() {
        SqlSession session = sqlSessionFactory.openSession();

        List<Donor> donorList = session.selectList("DonorMapper.findAll");

        session.commit();
        session.close();

        return donorList;
    }

    public Donor find(int id) {
        SqlSession session = sqlSessionFactory.openSession();

        Donor donor = session.selectOne("DonorMapper.find", id);

        session.commit();
        session.close();

        return donor;
    }

    public int deleteDonor(int i) {
        SqlSession session = sqlSessionFactory.openSession();

        session.selectList("DonorMapper.delete", i);

        session.commit();
        session.close();

        return i;
    }

}
