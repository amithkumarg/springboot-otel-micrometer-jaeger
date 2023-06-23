package com.example.springboototelmicrometerjaeger.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ForkJoinPoolFactoryBean;

@Configuration(proxyBeanMethods = false)
public class ForkJoinPoolConfig {
  @Bean
  public ForkJoinPoolFactoryBean forkJoinPoolFactoryBean() {
    return new ForkJoinPoolFactoryBean();
  }
}
