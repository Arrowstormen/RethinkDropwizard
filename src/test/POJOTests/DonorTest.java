package POJOTests;

import com.Rethink.dropwizarddemo.POJO.Donor;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertEquals;

public class DonorTest {

    // These tests ensures that the object is converted to and from JSON as expected

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    final Donor donor = new Donor(1, "n", "a", "cn", 123);

    @Test
    public void serializesToJSON() throws Exception {
        final String expected = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("POJOTests/fixtures/donor.json"), Donor.class));

        assertThat(MAPPER.writeValueAsString(donor)).isEqualTo(expected);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        assertEquals(MAPPER.readValue(fixture("POJOTests/fixtures/donor.json"), Donor.class).toString()
                , donor.toString());
    }
}
