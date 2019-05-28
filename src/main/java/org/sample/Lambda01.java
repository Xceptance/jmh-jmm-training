package org.sample;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
public class Lambda01
{
    final int SIZE = 10240;
    final int[] integers = new int[SIZE];
    
    @Setup
    public void setup()
    {
        for (int i = 0; i < integers.length; i++)
        {
            integers[i] = i;
        }
    }
    
    // ==========================================================================
    @Benchmark
    @Warmup(iterations = 1, batchSize = 1)
    @Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public int lambdaArrayCold()
    {
        return Arrays.stream(integers).filter(i -> i % 2 == 0).sum();
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public int lambdaArrayHot()
    {
        return Arrays.stream(integers).filter(i -> i % 2 == 0).sum();
    }

    // ==========================================================================
    @Benchmark
    @Warmup(iterations = 1, batchSize = 1)
    @Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public int lambdaIntStreamCold()
    {
        return IntStream.range(0, SIZE).filter(i -> i % 2 == 0).sum();
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public int lambdaIntStreamHot()
    {
        return IntStream.range(0, SIZE).filter(i -> i % 2 == 0).sum();
    }
    
    // ==========================================================================
    @Benchmark
    @Warmup(iterations = 1, batchSize = 1)
    @Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public int loopCold()
    {
        int sum = 0;
        for (int i = 0; i < integers.length; i++)
        {
            if (i % 2 == 0)
            {
                sum += integers[i];
            }
        }
        
        return sum;
    }

    @Benchmark
    @Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 10, timeUnit = TimeUnit.MILLISECONDS)
    public int loopHot()
    {
        int sum = 0;
        for (int i = 0; i < integers.length; i++)
        {
            if (i % 2 == 0)
            {
                sum += integers[i];
            }
        }
        
        return sum;
    }
}
