package ResourceTests;

import com.Rethink.dropwizarddemo.DAO.DonorDAO;
import com.Rethink.dropwizarddemo.POJO.Donor;
import com.Rethink.dropwizarddemo.resources.DonorsResource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class DonorResourceTest {

    private static final DonorDAO dao = mock(DonorDAO.class);
    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new DonorsResource(dao))
            .build();
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    private final Donor donor = new Donor(1, "n", "a", "cn", 123);

    @Before
    public void setup() {
        when(dao.find(eq(1))).thenReturn(donor);
    }

    @After
    public void tearDown() {
        // we have to reset the mock after each test because of the
        // @ClassRule, or use a @Rule as mentioned below.
        reset(dao);
    }

    @Test
    public void testGetDonor() {
        assertThat(resources.target("/donors/1").request().get(Donor.class).toString())
                .isEqualTo(donor.toString());
        verify(dao).find(1);
    }

    // UPDATE

    // DELETE
    // CREATE
}



