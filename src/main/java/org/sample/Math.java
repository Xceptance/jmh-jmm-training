package org.sample;

import java.util.concurrent.TimeUnit;

import org.misc.FastRandom;
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

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Math
{
    @Param({"64"})
    int SIZE;
    
    int[] array;

    @Setup
    public void setup()
    {
        array = new int[SIZE];
        final FastRandom r = new FastRandom(7L);
        
        for (int i = 0; i < SIZE; i++)
        {
            array[i] = r.nextInt(10000) + 1;
        }
    }
    
    private int divByMultiplication93(int a)
    {
        return ((1 / 93) * a) + a;
    }
    
    private int divByDivision93(int a)
    {
        return (a / 93) + a;
    }
    
    
    @Benchmark
    public long divByMultiplication()
    {
        long sum = 0;
        
        for (int i = 0; i < SIZE; i++)
        {
            sum += divByMultiplication93(array[i]);
        }
        
        return sum;
    }
    
    @Benchmark
    public long divByDivision()
    {
        long sum = 0;
        
        for (int i = 0; i < SIZE; i++)
        {
            sum += divByDivision93(array[i]);
        }
        
        return sum;
    }
}
