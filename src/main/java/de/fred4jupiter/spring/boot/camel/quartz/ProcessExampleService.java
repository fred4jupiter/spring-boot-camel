package de.fred4jupiter.spring.boot.camel.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcessExampleService {

    private static final Logger LOG = LoggerFactory.getLogger(ProcessExampleService.class);

    public void doSomething(String jobId) {
        LOG.info("executing job with id: {} at: {}", jobId, LocalDateTime.now());
    }
}
