package MyBatis;

import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.Rethink.dropwizarddemo.Mappers.DonorMapper;
import com.Rethink.dropwizarddemo.POJO.Donor;
import com.Rethink.dropwizarddemo.resources.DonorsResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.Reader;
import java.sql.Connection;

import static org.mockito.Mockito.mock;

public class DonorMapperTest {


    private static final DonorDAO dao = mock(DonorDAO.class);
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new DonorsResource(dao))
            .build();
    private static SqlSessionFactory sqlSessionFactory;
    private final Donor donor = new Donor(1, "n", "a", "cn", 123);

    @BeforeClass
    public static void setUp() throws Exception {
        // create an SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("mybatis/config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();

        // create in-memory database
        SqlSession session = sqlSessionFactory.openSession();
        Connection conn = session.getConnection();
        reader = Resources.getResourceAsReader("liquibase/db.changelog.xml");
        ScriptRunner runner = new ScriptRunner(conn);
        runner.setSendFullScript(true);
        runner.setLogWriter(null);
        runner.runScript(reader);
        conn.close();
        reader.close();
        session.close();

    }

    @Test
    public void shouldGetAUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DonorMapper mapper = sqlSession.getMapper(DonorMapper.class);
            Donor donor = mapper.find(1);

            Assert.assertEquals("restaurant_one", donor.getName());
        } finally {
            sqlSession.close();
        }
    }

}
