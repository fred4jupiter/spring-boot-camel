package de.fred4jupiter.spring.boot.camel.csv.read;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvStreamingTest {

    @Value("${inbox.csv.folder}")
    private String inboxCsvFolder;

    @EndpointInject(uri = "mock:streaming-out")
    private MockEndpoint mockEndpoint;

    @Test
    public void readCsvAsStream() throws IOException, InterruptedException {
        FileUtils.copyFile(new File("src/main/resources/csv/big-sample-data.csv"), new File(inboxCsvFolder + File.separator + "stream/big-sample-data.csv"));

        mockEndpoint.setExpectedCount(1);
        mockEndpoint.assertIsSatisfied();

        Exchange exchange = mockEndpoint.getReceivedExchanges().get(0);
        assertNotNull(exchange);

        Object body = exchange.getIn().getBody();
        assertNotNull(body);
    }
}
