package de.fred4jupiter.spring.boot.camel.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class ClusterExecutionDemoJob extends QuartzJobBean {

    private static final Logger LOG = LoggerFactory.getLogger(ClusterExecutionDemoJob.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        String id = context.getJobDetail().getKey().getName();
        LOG.info("executing job={} with jobId={}", this.getClass().getSimpleName(), id);
    }
}
