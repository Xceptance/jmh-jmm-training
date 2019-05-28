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
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class StackVsHeapObject
{
    A x;
    static final int SIZE = 10000;
    
    @Benchmark
    public int stack()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            final A o = new A();
        }

        return 4;
    }
    
    @Benchmark
    public int heap()
    {
        int sum = 0;
        
        for (int i = 0; i < SIZE; i++)
        {
            x = new A();
        }

        return 4;
    }
    
    static class A
    {
        int x = 0;
        String s = "";
    }
}
