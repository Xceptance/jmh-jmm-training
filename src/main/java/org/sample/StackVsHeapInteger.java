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
public class StackVsHeapInteger
{
    Integer x;
    static final int SIZE = 10000;
    
    @Benchmark
    public int stack()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            final Integer x = new Integer(i);
            sum = sum + x.intValue();
        }

        return sum;
    }

    @Benchmark
    public Integer stackAndEscape()
    {
        Integer x = null;
        
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            x = new Integer(i);
            sum = sum + x.intValue();
        }

        return x;
    }

    
    @Benchmark
    public int heap()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            x = new Integer(i);
            sum = sum + x.intValue();
        }

        return sum;
    }
}
