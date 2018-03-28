package Jackson.POJOTests;

import com.Rethink.dropwizarddemo.POJO.Donor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

public class DonorTest {

    // These tests ensures that the object is converted to and from JSON as expected using Jackson
    // Fixetures are used to represent objects in json. Test will fail if jsin is expended to multiple lines

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    final Donor donor = new Donor(1, "n", "a", "cn", 123);

    @Test
    public void serializeToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("Jackson/POJOTests/fixtures/donor.json"), Donor.class));
        assertThat(MAPPER.writeValueAsString(donor)).isEqualTo(expected);
    }

    @Test
    public void deserializeFromJSON() throws Exception {
        assertEquals(MAPPER.readValue(fixture("Jackson/POJOTests/fixtures/donor.json"), Donor.class).toString()
                , donor.toString());
    }
}
