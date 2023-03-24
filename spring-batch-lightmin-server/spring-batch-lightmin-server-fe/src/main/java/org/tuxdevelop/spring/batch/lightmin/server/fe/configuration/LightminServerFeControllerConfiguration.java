package org.tuxdevelop.spring.batch.lightmin.server.fe.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.tuxdevelop.spring.batch.lightmin.server.fe.controller.*;
import org.tuxdevelop.spring.batch.lightmin.server.fe.service.*;

@Configuration
public class LightminServerFeControllerConfiguration {

    //IndexController
    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "index", havingValue = "true", matchIfMissing = true)
    public IndexController indexController() {
        return new IndexController();
    }

    //ApplicationsController

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "application", havingValue = "true", matchIfMissing = true)
    public ApplicationsController applicationsController(
            final LightminClientApplicationFeService lightminClientApplicationFeService) {
        return new ApplicationsController(lightminClientApplicationFeService);
    }

    //Application Instance Controller

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "applicationInstance", havingValue = "true", matchIfMissing = true)
    public ApplicationInstanceController applicationInstanceController(
            final LightminClientApplicationFeService lightminClientApplicationFeService) {
        return new ApplicationInstanceController(lightminClientApplicationFeService);
    }

    //Batch Jobs Controller
    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "batchJobs", havingValue = "true", matchIfMissing = true)
    public BatchJobsController batchJobsController(final JobFeService jobFeService) {
        return new BatchJobsController(jobFeService);
    }

    //Batch Job Executions Controller

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "batchJobExecution", havingValue = "true", matchIfMissing = true)
    public BatchJobExecutionsController batchJobExecutionsController(final JobExecutionFeService jobExecutionFeService) {
        return new BatchJobExecutionsController(jobExecutionFeService);
    }

    //Events Controller

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "events", havingValue = "true", matchIfMissing = true)
    public EventsController eventsController() {
        return new EventsController();
    }

    //Job Execution Event Controller

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "jobExecutionEvents", havingValue = "true", matchIfMissing = true)
    public JobExecutionEventsController jobExecutionEventsController(
            final JobExecutionEventFeService jobExecutionEventFeService) {
        return new JobExecutionEventsController(jobExecutionEventFeService);
    }

    //Job Launcher Controller

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "jobLauncher", havingValue = "true", matchIfMissing = true)
    public JobLauncherController jobLauncherController(final JobLauncherFeService jobLauncherFeService,
                                                       final Validator validator) {
        return new JobLauncherController(jobLauncherFeService, validator);
    }

    //Job Scheduler Controller

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "jobScheduler", havingValue = "true", matchIfMissing = true)
    public JobSchedulersController jobSchedulersController(final JobSchedulerFeService jobSchedulerFeService,
                                                           final Validator validator) {
        return new JobSchedulersController(jobSchedulerFeService, validator);
    }

    //Job Listeners Controller

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "jobListeners", havingValue = "true", matchIfMissing = true)
    public JobListenersController jobListenersController(final JobListenerFeService jobListenerFeService,
                                                         final Validator validator) {
        return new JobListenersController(jobListenerFeService, validator);
    }

    //Global Exception Advice

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "globalExceptionAdvice", havingValue = "true", matchIfMissing = true)
    public GlobalExceptionControllerAdvice globalExceptionControllerAdvice() {
        return new GlobalExceptionControllerAdvice();
    }

    //Journals Controller

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "journals", havingValue = "true", matchIfMissing = true)
    public JournalsController journalsController(final JournalsFeService journalsFeService) {
        return new JournalsController(journalsFeService);
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "about", havingValue = "true", matchIfMissing = true)
    public AboutController aboutController() {
        return new AboutController();
    }

    //Server Schedulers

    @Bean
    @ConditionalOnProperty(prefix = "spring.batch.lightmin.server.fe.controller", value = "serverScheduler", havingValue = "true", matchIfMissing = true)
    public ServerSchedulerController serverSchedulerController(final ServerSchedulerFeService serverSchedulerFeService,
                                                               final Validator validator) {
        return new ServerSchedulerController(serverSchedulerFeService, validator);
    }
}
