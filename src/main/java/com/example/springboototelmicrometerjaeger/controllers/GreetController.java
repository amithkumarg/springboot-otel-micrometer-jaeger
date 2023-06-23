package com.example.springboototelmicrometerjaeger.controllers;

import com.example.springboototelmicrometerjaeger.services.GreetingService;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class GreetController {

  private final GreetingService greetingService;
  private final Random random = new Random();

  @GetMapping("/greet/{name}")
  public String greet(@PathVariable("name") String name) {
    log.info("Request received to greet {}", name);
    if (random.nextInt(2) % 2 == 0) {
      return greetingService.hello(name);
    } else {
      return greetingService.hey(name);
    }
  }

  @GetMapping("/sweepstake/{name}")
  public void sweepstake(@PathVariable("name") String name) {
    log.info("Request received to submit {} for sweepstake", name);
    greetingService.sweepstakeSubmission(name);
  }
}
