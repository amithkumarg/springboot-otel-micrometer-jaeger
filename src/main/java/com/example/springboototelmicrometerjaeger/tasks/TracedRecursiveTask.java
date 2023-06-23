package com.example.springboototelmicrometerjaeger.tasks;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import java.util.concurrent.RecursiveTask;

public abstract class TracedRecursiveTask<V> extends RecursiveTask<V> {

  protected final Tracer tracer;
  private final Span parentSpan;
  private final String spanName;

  public TracedRecursiveTask(Tracer tracer) {
    this(tracer, "compute");
  }

  public TracedRecursiveTask(Tracer tracer, String spanName) {
    this.tracer = tracer;
    this.parentSpan = tracer.currentSpan();
    this.spanName = spanName;
  }

  protected abstract V process();

  @Override
  protected final V compute() {
    Span span = this.tracer.nextSpan(parentSpan).name(spanName).start();
    try (Tracer.SpanInScope ignored = this.tracer.withSpan(span)) {
      return process();
    } finally {
      span.end();
    }
  }
}
