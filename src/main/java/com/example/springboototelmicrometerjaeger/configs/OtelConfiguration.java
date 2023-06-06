package com.example.springboototelmicrometerjaeger.configs;

import io.opentelemetry.context.propagation.TextMapPropagator;
import io.opentelemetry.extension.trace.propagation.JaegerPropagator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class OtelConfiguration {

  @Bean
  public TextMapPropagator jaegerPropagator() {
    return JaegerPropagator.getInstance();
  }
}
