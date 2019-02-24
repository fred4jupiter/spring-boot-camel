package de.fred4jupiter.spring.boot.camel.simple;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleFileRoute extends SpringRouteBuilder {

    @Autowired
    private ContentEnricher contentEnricher;

    @Override
    public void configure() {
        from("file:inbox").bean(contentEnricher).to("file:outbox", "mock:out");
    }
}
