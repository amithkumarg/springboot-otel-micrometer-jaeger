package com.example.springboototelmicrometerjaeger.controllers;

import com.example.springboototelmicrometerjaeger.services.OperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class OperationController {

  private final OperationService operationService;

  @PostMapping("/integers/sum")
  public int intArraySum(@RequestBody int[] intArray) {
    log.info("Request received to sum {}", intArray);
    return operationService.arraySum(intArray);
  }
}
