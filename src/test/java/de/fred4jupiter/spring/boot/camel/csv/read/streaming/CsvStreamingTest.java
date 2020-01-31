package de.fred4jupiter.spring.boot.camel.csv.read.streaming;

import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CsvStreamingTest {

    @Value("${inbox.csv.folder}")
    private String inboxCsvFolder;

    @Value("${outbox.csv.folder}")
    private String outboxCsvFolder;

    @EndpointInject(uri = "mock:streaming-out")
    private MockEndpoint mockEndpoint;

    @Test
    public void readCsvAsStreamConvertAggregateAndWriteCsv() throws IOException, InterruptedException {
        FileUtils.copyFile(new File("src/main/resources/csv/big-sample-data.csv"), new File(inboxCsvFolder + File.separator + "stream/big-sample-data.csv"));

        mockEndpoint.setExpectedCount(1);
        mockEndpoint.assertIsSatisfied();

        File outputFile = new File(outboxCsvFolder + File.separator + "/stream/big-sample-data.csv");
        assertThat(outputFile.exists(), equalTo(true));
        List<String> resultLines = FileUtils.readLines(outputFile, "UTF-8");
        assertThat(resultLines.size(), equalTo(2021));
    }
}
