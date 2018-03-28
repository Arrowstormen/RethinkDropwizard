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
        int resp = session.insert("com.Rethink.dropwizarddemo.Mappers.DonorMapper.create", donor);

        System.out.println("record inserted successfully");

        session.commit();
        session.close();

        return resp;
    }

    public void updateDonor(Donor donor) {
        SqlSession session = sqlSessionFactory.openSession();

        //Insert donor data
        int resp = session.update("com.Rethink.dropwizarddemo.Mappers.DonorMapper.update", donor);

        System.out.println("record updated successfully");

        session.commit();
        session.close();

    }

    public List<Donor> getAllDonors() {
        SqlSession session = sqlSessionFactory.openSession();

        List<Donor> resp = session.selectList("com.Rethink.dropwizarddemo.Mappers.DonorMapper.findAll");

        System.out.println("records gotten successfully");

        session.commit();
        session.close();

        return resp;
    }

    public Donor find(int id) {
        SqlSession session = sqlSessionFactory.openSession();

        Donor resp = session.selectOne("com.Rethink.dropwizarddemo.Mappers.DonorMapper.find", id);

        System.out.println("record found successfully");

        session.commit();
        session.close();

        return resp;
    }

    public int deleteDonor(int i) {
        SqlSession session = sqlSessionFactory.openSession();

        int resp = session.delete("com.Rethink.dropwizarddemo.Mappers.DonorMapper.delete", i);

        System.out.println("record deleted successfully");

        session.commit();
        session.close();

        return resp;
    }

}
