package com.example.springboototelmicrometerjaeger.services;

import io.micrometer.tracing.annotation.ContinueSpan;
import io.micrometer.tracing.annotation.NewSpan;
import io.micrometer.tracing.annotation.SpanTag;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class GreetingService {

  @ContinueSpan
  public String hello(@SpanTag("name") String name) {
    return greet("hello", name);
  }

  @NewSpan
  public String hey(@SpanTag("name") String name) {
    return greet("hey", name);
  }

  @Async
  public void sweepstakeSubmission(String name) {
    log.info("Submitting {} for sweepstake", name);
  }

  private String greet(String greeting, String name) {
    return String.format("%s %s", greeting, name);
  }
}
