package de.fred4jupiter.spring.boot.camel.csv.read.streaming;

import de.fred4jupiter.spring.boot.camel.csv.Person;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StreamProcessor implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(StreamProcessor.class);

    public static final String NAME = "streamProcessor";

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Person> listOfPersons = exchange.getIn().getBody(List.class);
        LOG.info("listOfPersons chunk size: {}", listOfPersons.size());

        List<Greeting> greetings = listOfPersons.stream().map(this::toGreeting).collect(Collectors.toList());
        exchange.getIn().setBody(greetings);
    }

    private Greeting toGreeting(Person person) {
        Greeting greeting = new Greeting();
        greeting.setGreetingText("Hello " + person.getName());
        return greeting;
    }
}
