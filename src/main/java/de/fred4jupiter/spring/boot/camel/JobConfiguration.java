package de.fred4jupiter.spring.boot.camel;

import de.fred4jupiter.spring.boot.camel.quartz.AutowiringSpringBeanJobFactory;
import de.fred4jupiter.spring.boot.camel.quartz.ClusterExecutionDemoJob;
import org.quartz.*;
import org.quartz.spi.JobFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfiguration {

    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

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
