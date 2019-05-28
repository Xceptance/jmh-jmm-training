package org.sample;

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
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class ExpensiveLastAdd
{
    final int SIZE = 10000;
    final int[] a = new int[SIZE];
    
    @Setup
    public void setup()
    {
        for (int i = 0; i < SIZE; i++)
        {
            a[i] = i;
        }
    }
    
    @Benchmark
    public long add230()
    {
        int sum = 0;
        int i = 0;
        
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];

        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];

        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];

        return sum;
    }

    @Benchmark
    public long add231()
    {
        int sum = 0;
        int i = 0;
        
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];

        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];

        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i] + a[++i];
        sum += a[++i];
        
        return sum;
    }
}
