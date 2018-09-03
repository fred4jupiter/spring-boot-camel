package de.fred4jupiter.spring.boot.camel;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyCamelRoutes extends SpringRouteBuilder {

    @Override
    public void configure() {
        from("file:inbox").to("bean:contentEnricher").to("file:outbox", "mock:out");
    }
}
