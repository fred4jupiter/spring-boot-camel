package de.fred4jupiter.spring.boot.camel.simple;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleRouteTest {

    @EndpointInject(uri = "mock:out")
    private MockEndpoint mockEndpoint;

    @Test
    public void createFileInInboxAndCheckResult() throws IOException, InterruptedException {
        FileUtils.writeStringToFile(new File("inbox/Test.txt"), "Hello", "UTF-8");

        final String bodyResult = "Hello World!";

        mockEndpoint.setExpectedCount(1);
        mockEndpoint.expectedBodiesReceived(bodyResult);
        mockEndpoint.assertIsSatisfied();

        File outboxFile = new File("outbox/Test.txt");
        assertThat(outboxFile.exists(), equalTo(true));

        String content = FileUtils.readFileToString(outboxFile, "UTF-8");
        assertThat(content, equalTo(bodyResult));
    }
}
