package de.fred4jupiter.spring.boot.camel.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class JobStarter {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void startJobs() throws SchedulerException {
        String id = UUID.randomUUID().toString();
        JobDetail jobDetail = JobBuilder.newJob(ClusterExecutionDemoJob.class).build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(id + "-trigger", "defaultGroup")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
