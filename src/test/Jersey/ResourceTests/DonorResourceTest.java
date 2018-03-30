package Jersey.ResourceTests;

import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.Rethink.dropwizarddemo.POJO.Donor;
import com.Rethink.dropwizarddemo.resources.DonorsResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class DonorResourceTest {

    // These tests tests wether the correct HTTP-response code has been returned

    private static final DonorDAO dao = mock(DonorDAO.class);
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new DonorsResource(dao))
            .build();

    private final Donor donor = new Donor(1, "n", "a", "cn", 123);

    @Before
    public void setup() {
        // Setup mock
        when(dao.find(eq(1))).thenReturn(donor);
    }

    @After
    public void tearDown() {
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(dao);
    }

    // CREATE
    @Test
    public void testCreate() {
        Response output = resources.target("/donors")
                .request()
                .post(Entity.entity(donor, MediaType.APPLICATION_JSON));

        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return donor", output.getEntity());
    }

    // READ
    @Test
    public void testFetchAll() {
        Response output = resources.target("/donors").request().get();
        assertEquals("should return status 200", 200, output.getStatus());
        assertNotNull("Should return donor list", output.getEntity());
    }

    @Test
    public void testFetchBy() {
        Response output = resources.target("/donors/1").request().get();
        assertEquals("Should return status 200", 200, output.getStatus());
        assertNotNull("Should return donor", output.getEntity());
    }

    @Test
    public void testFetchByFail_DoesNotHaveDigit() {
        Response output = resources.target("/donors/no-id-digit").request().get();
        assertEquals("Should return status 404", 404, output.getStatus());
    }

    @Test
    public void testFetchByFail_404() {
        Response output = resources.target("/donors/2").request().get();
        assertEquals("Should return status 404", 404, output.getStatus());
    }

    // UPDATE
    @Test
    public void testUpdate() {
        when(dao.update(donor)).thenReturn(1);
        Response output = resources.target("/donors")
                .request()
                .put(Entity.entity(donor, MediaType.APPLICATION_JSON));
        assertEquals("Should return status 204", 204, output.getStatus());
    }

    // DELETE
    @Test
    public void testDelete() {
        Response output = resources.target("/donors/1").request().delete();
        assertEquals("Should return status 204", 204, output.getStatus());
    }

}