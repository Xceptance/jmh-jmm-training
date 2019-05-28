package org.sample;

import java.util.Random;
import java.util.concurrent.TimeUnit;

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

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class ArraysAndHardware
{
    final int SIZE = 100_000 * 16;
    final int[] src = new int[SIZE];
    
    @Setup
    public void setup()
    {
        Random r = new Random();
        for (int i = 0; i < SIZE; i++)
        {
            src[i] = r.nextInt(1000);
        }
    }
    
    @Benchmark
    public int step1()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            sum += src[i];
        }
        
        return sum;
    }

    @Benchmark
    public int step20()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i = i + 20)
        {
            sum += src[i];
        }
        
        return sum;
    }    
}
