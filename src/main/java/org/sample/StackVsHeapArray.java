package org.sample;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class StackVsHeapArray
{
    int[] x;
    static final int SIZE = 1000;
    
    @Benchmark
    public int stack()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            final int[] x = new int[SIZE];
            sum = sum + x[i];
        }

        return sum;
    }
    
    @Benchmark
    public int heap()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            x = new int[SIZE];
            sum = sum + x[i];
        }

        return sum;
    }
}
