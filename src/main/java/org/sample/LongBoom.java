package org.sample;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(4)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class LongBoom 
{
    long x;

    volatile int y;
    
    private void check(long now)
    {
        if (!(now == 2L || now == -1L))
        {
            System.out.println("Failed: " + now);
        }
    }
    
    @Benchmark
    @Group("g")
    @GroupThreads(2)
    public long positive() 
    {
        for (int i = 0; i < 100; i++)
        {
            x = 2L;
            check(x);
        }
        
        return x;    
    }

    @Benchmark
    @Group("g")
    @GroupThreads(2)
    public long negative() 
    {
        for (int i = 0; i < 100; i++)
        {
            x = -1L;
            check(x);
        }
        
        return x;
    } 

}