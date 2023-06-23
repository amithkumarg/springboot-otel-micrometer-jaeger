package com.example.springboototelmicrometerjaeger.services;

import com.example.springboototelmicrometerjaeger.tasks.ArraySumCalculatorTask;
import io.micrometer.tracing.Tracer;
import java.util.concurrent.ForkJoinPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class OperationService {
  private final ForkJoinPool forkJoinPool;
  private final Tracer tracer;

  public int arraySum(int[] intArray) {
    return forkJoinPool.invoke(new ArraySumCalculatorTask(tracer, intArray));
  }
}
