package MyBatis;

import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.Rethink.dropwizarddemo.POJO.Donor;
import com.Rethink.dropwizarddemo.resources.DonorsResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class DonorDAOTest {

    // These tests wether the correct objects are returned from the DOA (and hence the mappers that DAO uses).

    // Mock database connection
    private static final DonorDAO dao = mock(DonorDAO.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new DonorsResource(dao))
            .build();

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void setUp() throws Exception {
        // create an SqlSessionFactory
        Reader reader = Resources.getResourceAsReader("mybatis/config.xml");
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        reader.close();

    }

    @Before
    public void before() {
        reset(dao);
    }

    @Test
    public void findTest() {
        // Database connection is mocked
        when(dao.find(1)).thenReturn(new Donor(1, "restaurant_one", "a", "cn", 123));

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Donor donor = resources.client().target("/donors/1").request().get(Donor.class);

            Assert.assertEquals("restaurant_one", donor.getName());
            verify(dao).find(1);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void findFailTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Response response = resources.client().target("/donors/1").request().get(Response.class);

            Assert.assertEquals(404, response.getStatus());
            verify(dao).find(1);

        } finally {
            sqlSession.close();
        }

    }

    @Test
    public void findAllTest() {
        // arrange
        Donor notUpdatedDonor = new Donor(1, "Not updated donor", "a", "cn", 123);
        Donor testDonor2 = new Donor(1, "testDonor2", "a", "cn", 123);
        List<Donor> donorList = new ArrayList<Donor>();
        donorList.add(notUpdatedDonor);
        donorList.add(testDonor2);
        when(dao.findAll()).thenReturn(donorList);

        // Database connection is mocked
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List result = resources.client().target("/donors").request().get(List.class);

            Assert.assertEquals(2, result.size());
            verify(dao).findAll();

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void findAllTestZero() {
        // Database connection is mocked
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List result = resources.client().target("/donors").request().get(List.class);

            Assert.assertEquals(0, result.size());
            verify(dao).findAll();

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void createTest() {
        // Database connection is mocked
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Donor createTest = new Donor(99, "testCreate", "a", "cn", 123);

            Donor result = resources.client().target("/donors")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(createTest, MediaType.APPLICATION_JSON_TYPE)).readEntity(Donor.class);

            Assert.assertEquals(99, result.getId());
            verify(dao).create(any(Donor.class));

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void createFailTest() {
        // Database connection is mocked
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Response result = resources.client().target("/donors")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(new Donor(), MediaType.APPLICATION_JSON_TYPE));

            Assert.assertEquals(422, result.getStatus());
            verifyZeroInteractions(dao);

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void updateTest() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Donor donor1 = new Donor(1, "test", "a", "cn", 123);
            when(dao.update(donor1)).thenReturn(1);
            Response result = resources.client().target("/donors")
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .put(Entity.entity(donor1, MediaType.APPLICATION_JSON_TYPE));

            Assert.assertEquals(204, result.getStatus());
            verify(dao).update(any(Donor.class));

        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void deleteTest() {

        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {

            Response result = resources.client().target("/donors/0")
                    .request()
                    .delete();

            Assert.assertEquals(204, result.getStatus());
            verify(dao).delete(0);

        } finally {
            sqlSession.close();
        }
    }

}
