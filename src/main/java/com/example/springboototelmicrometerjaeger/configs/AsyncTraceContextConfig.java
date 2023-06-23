package com.example.springboototelmicrometerjaeger.configs;

import io.micrometer.context.ContextExecutorService;
import io.micrometer.context.ContextSnapshot;
import java.util.concurrent.Executor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//@Configuration(proxyBeanMethods = false)
@RequiredArgsConstructor
public class AsyncTraceContextConfig implements AsyncConfigurer {

  // NOTE: By design you can only have one AsyncConfigurer, thus only one executor pool is
  // configurable.
  private final ThreadPoolTaskExecutor taskExecutor;

  @Override
  public Executor getAsyncExecutor() {
    return ContextExecutorService.wrap(
        taskExecutor.getThreadPoolExecutor(), ContextSnapshot::captureAll);
  }
}
