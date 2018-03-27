import com.Rethink.dropwizarddemo.POJO.Donor;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.junit.Test;

import javax.ws.rs.client.Client;

import static org.junit.Assert.assertEquals;

public class HelloWorldIntegrationTest {
    /*
        @Rule
        public final DropwizardAppRule<DropwizardConfiguration> RULE =
                new DropwizardAppRule<DropwizardConfiguration>(DropwizardApplication.class,
                        ResourceHelpers.resourceFilePath("hello-world.yml"));
    */
    @Test
    public void runServerTest() {
        Client client = new JerseyClientBuilder().build();
        Donor result = client.target(
                String.format("http://localhost:8080/donors/1") // , RULE.getLocalPort())
        ).request().get(Donor.class);

        assertEquals(result.getId(), 1);
    }


}