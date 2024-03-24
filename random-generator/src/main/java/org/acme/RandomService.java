package org.acme;

import io.opentelemetry.instrumentation.annotations.WithSpan;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.LongStream;

@ApplicationScoped
public class RandomService {

  private final Random random = new Random();

  @WithSpan
  public long get() {
    var longs = LongStream.rangeClosed(0, 5_000_000).map((x) -> random.nextLong()).toArray();
    Arrays.sort(longs);
    return (long)Math.floor(Arrays.stream(longs).average().orElse(0));
  }

}
