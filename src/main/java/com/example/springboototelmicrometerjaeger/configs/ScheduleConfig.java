package com.example.springboototelmicrometerjaeger.configs;

import io.micrometer.context.ContextScheduledExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import org.springframework.boot.task.TaskSchedulerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

// This configuration doesn't work at the moment and can be ignored, because of this open ticket
// with Spring
// https://github.com/spring-projects/spring-framework/issues/29883
// @Configuration(proxyBeanMethods = false)
public class ScheduleConfig {

  static class TracedThreadPoolTaskScheduler extends ThreadPoolTaskScheduler {

    @Override
    public ScheduledExecutorService getScheduledExecutor() throws IllegalStateException {
      return ContextScheduledExecutorService.wrap(super.getScheduledExecutor());
    }
  }

  @Bean
  public ThreadPoolTaskScheduler taskScheduler(TaskSchedulerBuilder builder) {
    return builder.configure(new TracedThreadPoolTaskScheduler());
  }
}
