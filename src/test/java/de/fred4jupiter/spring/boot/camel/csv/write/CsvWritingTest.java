package de.fred4jupiter.spring.boot.camel.csv.write;

import de.fred4jupiter.spring.boot.camel.csv.Person;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsvWritingTest {

    @Autowired
    private ProducerTemplate producerTemplate;

    @EndpointInject(uri = "mock:end")
    private MockEndpoint mockEndpoint;

    @Test
    public void createDemoDataAndWriteCsvFile() {
        final List<Person> persons = createListOfPersons();

        producerTemplate.sendBody("direct:start", persons);

        File outputFile = new File("outbox/csv-write/sample-data.csv");
        assertThat(outputFile.exists(), equalTo(true));

        Exchange exchange = mockEndpoint.getReceivedExchanges().get(0);

        List<Person> personsBack = exchange.getIn().getBody(List.class);
        assertThat(persons, equalTo(personsBack));
    }

    private List<Person> createListOfPersons() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Mark", 1, "bla1"));
        persons.add(new Person("Gerrit", 2, "bla2"));
        persons.add(new Person("Hans", 3, "bla3"));
        return persons;
    }

}
