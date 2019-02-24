package de.fred4jupiter.spring.boot.camel.csv;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.camel.EndpointInject;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvToJsonProcessingTest {

    private static final Logger LOG = LoggerFactory.getLogger(CsvToJsonProcessingTest.class);

    @EndpointInject(uri = "mock:json-out")
    private MockEndpoint mockEndpoint;

    @Test
    public void createFileInInboxAndCheckResult() throws IOException, InterruptedException {
        FileUtils.copyFile(new File("src/main/resources/csv/sample-data.csv"), new File("csv-inbox/sample-data.csv"));

        mockEndpoint.setExpectedCount(1);
        mockEndpoint.assertIsSatisfied();

        File outboxFile = new File("json-outbox/sample-data.json");
        assertThat(outboxFile.exists(), equalTo(true));

        String content = FileUtils.readFileToString(outboxFile, "UTF-8");

        Type listType = new TypeToken<ArrayList<Person>>() {
        }.getType();
        List<Person> list = new Gson().fromJson(content, listType);
        assertNotNull(list);

        list.forEach(person -> LOG.info("person: {}", person));
    }
}
