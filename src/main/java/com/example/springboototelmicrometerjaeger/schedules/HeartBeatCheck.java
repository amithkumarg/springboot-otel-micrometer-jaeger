package com.example.springboototelmicrometerjaeger.schedules;

import io.micrometer.tracing.annotation.NewSpan;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class HeartBeatCheck {

  @NewSpan // TODO remove once this issue is resolved: https://github.com/spring-projects/spring-framework/issues/29883
  @Scheduled(fixedRate = 60000)
  public void heartBeat() {
    log.info("Heartbeat");
  }
}
