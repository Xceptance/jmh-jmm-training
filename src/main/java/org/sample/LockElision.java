package org.sample;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class LockElision 
{
    @Benchmark
    public int sync() 
    {
        final List<Integer> b = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
        {
            synchronized (b)
            {
                b.add(i);
            }
        }
        
        return b.get(50);
    }

    @Benchmark
    public int unsync() 
    {
        final List<Integer> b = new ArrayList<>();
        for (int i = 0; i < 1000; i++)
        {
            b.add(i);
        }
        
        return b.get(50);
    }
}