package com.Rethink.dropwizarddemo.DAO;

import com.Rethink.dropwizarddemo.Mappers.DonorMapper;
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
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int create(Donor donor) {
        SqlSession session = sqlSessionFactory.openSession();
        DonorMapper mapper = session.getMapper(DonorMapper.class);

        int result = mapper.create(donor);
        System.out.println("record inserted successfully");

        session.commit();
        session.close();
        return result;
    }

    public int update(Donor donor) {
        SqlSession session = sqlSessionFactory.openSession();
        DonorMapper mapper = session.getMapper(DonorMapper.class);

        //Insert donor data
        int rowsAffected = mapper.update(donor);
        System.out.println("record updated successfully");

        session.commit();
        session.close();
        return rowsAffected;
    }

    public List<Donor> findAll() {
        SqlSession session = sqlSessionFactory.openSession();
        DonorMapper mapper = session.getMapper(DonorMapper.class);

        List<Donor> resp = mapper.findAll();
        System.out.println("records gotten successfully");

        session.commit();
        session.close();

        return resp;
    }

    public Donor find(int id) {
        SqlSession session = sqlSessionFactory.openSession();
        DonorMapper mapper = session.getMapper(DonorMapper.class);

        Donor resp = mapper.find(id);
        System.out.println("record found successfully");

        session.commit();
        session.close();
        return resp;
    }

    public int delete(int i) {
        SqlSession session = sqlSessionFactory.openSession();
        DonorMapper mapper = session.getMapper(DonorMapper.class);

        int rowsAffected = mapper.delete(i);
        System.out.println("record deleted successfully");

        session.commit();
        session.close();
        return rowsAffected;

<<<<<<< Updated upstream
        return rowsAffected;

=======
>>>>>>> Stashed changes
    }

}
