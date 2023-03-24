package org.tuxdevelop.spring.batch.lightmin.server.scheduler.service;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.tuxdevelop.spring.batch.lightmin.server.scheduler.configuration.ServerSchedulerCoreConfigurationProperties;
import org.tuxdevelop.spring.batch.lightmin.server.scheduler.repository.domain.ExecutionStatus;
import org.tuxdevelop.spring.batch.lightmin.server.scheduler.repository.domain.SchedulerExecution;

import java.util.Date;
import java.util.List;

public abstract class AbstractExecutionPollerService implements ExecutionPollerService {

    protected final ServerSchedulerService serverSchedulerService;
    protected final SchedulerExecutionService schedulerExecutionService;
    protected final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    protected final ServerSchedulerCoreConfigurationProperties properties;

    protected AbstractExecutionPollerService(final ServerSchedulerService serverSchedulerService,
                                             final SchedulerExecutionService schedulerExecutionService,
                                             final ServerSchedulerCoreConfigurationProperties properties) {
        this.serverSchedulerService = serverSchedulerService;
        this.schedulerExecutionService = schedulerExecutionService;
        this.threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        this.properties = properties;
        this.threadPoolTaskExecutor.setCorePoolSize(properties.getThreadPoolCoreSize());
        this.threadPoolTaskExecutor.setMaxPoolSize(properties.getThreadPoolSize());
        this.threadPoolTaskExecutor.afterPropertiesSet();
    }

    public void triggerScheduledExecutions() {
        final List<SchedulerExecution> executions =
                this.schedulerExecutionService.findScheduledExecutions(ExecutionStatus.NEW, new Date());
        this.runExecutions(executions);
    }

    public void triggerRetryExecutions() {
        final List<SchedulerExecution> executions = this.schedulerExecutionService.findSchedulerExecutionsForRetry();
        this.runExecutions(executions);
    }

    protected void runExecutions(final List<SchedulerExecution> executions) {
        executions.parallelStream()
                .forEach(
                        schedulerExecution -> {
                            final ExecutionRunner runner =
                                    new ExecutionRunner(
                                            schedulerExecution,
                                            this.serverSchedulerService, this.properties);
                            this.threadPoolTaskExecutor.execute(runner);
                        }
                );
    }
}
