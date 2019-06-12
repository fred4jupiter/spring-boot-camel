package de.fred4jupiter.spring.boot.camel.csv.read;

import de.fred4jupiter.spring.boot.camel.csv.Person;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StreamProcessor implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(StreamProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        List<Person> listOfPersons = exchange.getIn().getBody(List.class);
        LOG.info("listOfPersons chunk size: {}", listOfPersons.size());
    }
}
