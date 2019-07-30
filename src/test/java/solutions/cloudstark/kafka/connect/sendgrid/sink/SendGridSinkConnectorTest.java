package solutions.cloudstark.kafka.connect.sendgrid.sink;

import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import solutions.cloudstark.kafka.connect.sendgrid.config.SendGridSinkConnectorConfig;

import static org.junit.Assert.assertEquals;

public class SendGridSinkConnectorTest {

    private SendGridSinkConnector connector;

    @Before
    public void setup() {
        connector = new SendGridSinkConnector();
    }

    @Test
    public void testTaskClass() {
        assertEquals(SendGridSinkTask.class, connector.taskClass());
    }

    @Test
    public void testStartConnectionFailure() throws Exception {
        connector.start(Collections.singletonMap(SendGridSinkConnectorConfig.SENDGRID_API_KEY, "jdbc:foo"));
    }
}