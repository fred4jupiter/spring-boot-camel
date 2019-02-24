package de.fred4jupiter.spring.boot.camel.csv;

import org.apache.camel.Body;
import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonCreator {

    @Handler
    public void processData(@Body List<List<String>> data, Exchange exchange) {
        List<Person> persons = data.stream().map(this::createPerson).collect(Collectors.toList());
        exchange.getIn().setBody(persons);
    }

    private Person createPerson(List<String> onePerson) {
        String name = onePerson.get(0);
        Integer iq = Integer.valueOf(onePerson.get(1).trim());
        String description = onePerson.get(2).trim();
        return new Person(name, iq, description);
    }
}
