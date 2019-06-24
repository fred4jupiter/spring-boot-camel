package de.fred4jupiter.spring.boot.camel.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class ClusterExecutionDemoJob extends QuartzJobBean {

    @Autowired
    private ProcessExampleService processExampleService;

    @Override
    protected void executeInternal(JobExecutionContext context) {
        String id = context.getJobDetail().getKey().getName();
        processExampleService.doSomething(id);
    }
}
