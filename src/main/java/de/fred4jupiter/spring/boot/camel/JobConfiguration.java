package de.fred4jupiter.spring.boot.camel;

import de.fred4jupiter.spring.boot.camel.quartz.ClusterExecutionDemoJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Most of the configuration will be done in QuartzAutoConfiguration.
 */
@Configuration
public class JobConfiguration {

    @Bean
    public JobDetail jobADetails() {
        return JobBuilder.newJob(ClusterExecutionDemoJob.class).withIdentity("sampleJobA").storeDurably().build();
    }

    @Bean
    public Trigger jobATrigger(JobDetail jobADetails) {
        return TriggerBuilder.newTrigger().forJob(jobADetails)
                .withIdentity("sampleTriggerA")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * ? * * *"))
                .build();
    }
}
