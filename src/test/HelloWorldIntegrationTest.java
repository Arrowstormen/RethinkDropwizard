import com.Rethink.dropwizarddemo.DropwizardApplication;
import com.Rethink.dropwizarddemo.DropwizardConfiguration;
import com.Rethink.dropwizarddemo.POJO.Donor;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Rule;
import org.junit.Test;

import javax.ws.rs.client.Client;

import static org.junit.Assert.assertEquals;

public class HelloWorldIntegrationTest {

    // Starts the program before tests are run
    @Rule
    public final DropwizardAppRule<DropwizardConfiguration> RULE =
            new DropwizardAppRule<DropwizardConfiguration>(DropwizardApplication.class);

    @Test
    public void runServerTest() {
        Client client = new JerseyClientBuilder().build();

        Donor result = client.target(
                String.format("http://localhost:%d/donors/1", RULE.getLocalPort())
        ).request().get(Donor.class);

        assertEquals(result.getId(), 1);
    }


}