package com.example.springboototelmicrometerjaeger.configs;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.annotation.DefaultNewSpanParser;
import io.micrometer.tracing.annotation.ImperativeMethodInvocationProcessor;
import io.micrometer.tracing.annotation.MethodInvocationProcessor;
import io.micrometer.tracing.annotation.NewSpanParser;
import io.micrometer.tracing.annotation.SpanAspect;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class SpanAspectConfiguration {

  @Bean
  NewSpanParser newSpanParser() {
    return new DefaultNewSpanParser();
  }

  @Bean
  MethodInvocationProcessor methodInvocationProcessor(
      NewSpanParser newSpanParser, Tracer tracer, BeanFactory beanFactory) {
    return new ImperativeMethodInvocationProcessor(
        newSpanParser, tracer, beanFactory::getBean, beanFactory::getBean);
  }

  @Bean
  SpanAspect spanAspect(MethodInvocationProcessor methodInvocationProcessor) {
    return new SpanAspect(methodInvocationProcessor);
  }
}
