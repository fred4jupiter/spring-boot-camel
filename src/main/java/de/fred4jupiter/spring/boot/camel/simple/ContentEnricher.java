package de.fred4jupiter.spring.boot.camel.simple;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("contentEnricher")
public class ContentEnricher {

    private static final Logger LOG = LoggerFactory.getLogger(ContentEnricher.class);

    @Handler
    public void enrich(Exchange exchange) {
        Message message = exchange.getIn();
        String body = message.getBody(String.class);
        LOG.info("file content: {}", body);
        message.setBody(body + " World!");
    }
}
