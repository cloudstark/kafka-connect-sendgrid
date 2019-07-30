package solutions.cloudstark.kafka.connect.sendgrid.sink;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.Test;
import solutions.cloudstark.kafka.connect.sendgrid.config.SendGridSinkConnectorConfig;

public class SendGridSinkTaskTest {
//    @Mock
//    SendGridWriter writer;
//    @Mock
//    Logger log;
//    @Mock
//    SinkTaskContext context;
//    @InjectMocks
//    SendGridSinkTask sendGridSinkTask;

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    public void testVersion() throws Exception {
//        String result = sendGridSinkTask.version();
//        Assert.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    public void testStart() throws Exception {
        Map<String, String> props = new HashMap<>();
        props.put(SendGridSinkConnectorConfig.SENDGRID_API_KEY, "ABC");

        SendGridSinkTask task = new SendGridSinkTask();

        task.start(props);

        task.put(Collections.singleton(
                new SinkRecord("", 1, null, null, null, "", 42)
        ));
        //        sendGridSinkTask.start(new HashMap<String, String>() {{
        //    put("String", "String");
        //}});
    }

    @Test
    public void testPut() throws Exception {
        // sendGridSinkTask.put(Arrays.<SinkRecord>asList(null));
    }

    @Test
    public void testStop() throws Exception {
        //   sendGridSinkTask.stop();
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme