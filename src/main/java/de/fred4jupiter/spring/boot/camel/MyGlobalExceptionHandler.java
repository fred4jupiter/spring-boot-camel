package de.fred4jupiter.spring.boot.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MyGlobalExceptionHandler implements Processor {

    private static final Logger LOG = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    @Override
    public void process(Exchange exchange) {
        Exception e = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);
        LOG.error("ERROR: There is a global error: {}", e.getMessage());
    }
}
