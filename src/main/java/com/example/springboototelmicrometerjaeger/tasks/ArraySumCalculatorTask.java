package com.example.springboototelmicrometerjaeger.tasks;

import io.micrometer.tracing.Tracer;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ArraySumCalculatorTask extends TracedRecursiveTask<Integer> {
  private final int[] arr;
  private final int startIndex;
  private final int endIndex;
  private static final int THRESHOLD = 3;

  public ArraySumCalculatorTask(Tracer tracer, int[] arr) {
    this(tracer, arr, 0, arr.length);
  }

  public ArraySumCalculatorTask(Tracer tracer, int[] arr, int startIndex, int endIndex) {
    super(tracer, "sumCalculatorTask");
    this.arr = arr;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  @Override
  protected Integer process() {
    if ((endIndex - startIndex) > THRESHOLD) {
      return invokeAll(createSubtasks()).stream().mapToInt(ForkJoinTask::join).sum();
    } else {
      return processing(arr);
    }
  }

  private List<ArraySumCalculatorTask> createSubtasks() {
    int mid = (startIndex + endIndex) / 2;
    return List.of(
        new ArraySumCalculatorTask(tracer, arr, startIndex, mid),
        new ArraySumCalculatorTask(tracer, arr, mid, endIndex));
  }

  private Integer processing(int[] arr) {
    int sum = Arrays.stream(arr, startIndex, endIndex).sum();
    log.info("Sum of array elements from index range: {} to {} is {}", startIndex, endIndex, sum);
    return sum;
  }
}
