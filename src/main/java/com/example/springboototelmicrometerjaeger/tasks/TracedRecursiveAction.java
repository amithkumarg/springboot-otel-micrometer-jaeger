package com.example.springboototelmicrometerjaeger.tasks;

import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import java.util.concurrent.RecursiveAction;

public abstract class TracedRecursiveAction extends RecursiveAction {

  protected final Tracer tracer;
  private final Span parentSpan;
  private final String spanName;

  public TracedRecursiveAction(Tracer tracer) {
    this(tracer, "compute");
  }

  public TracedRecursiveAction(Tracer tracer, String spanName) {
    this.tracer = tracer;
    this.parentSpan = tracer.currentSpan();
    this.spanName = spanName;
  }

  protected abstract void process();

  @Override
  protected final void compute() {
    Span span = this.tracer.nextSpan(parentSpan).name(spanName).start();
    try (Tracer.SpanInScope ignored = this.tracer.withSpan(span)) {
      process();
    } finally {
      span.end();
    }
  }
}
