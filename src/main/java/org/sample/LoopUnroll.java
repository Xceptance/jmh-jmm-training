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
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class LoopUnroll
{
    @Param({"10000"})
    int size = 10;
    
    private FastRandom r = new FastRandom(7L);
    
    private int[] ints = null;
    
    @Setup
    public void setup()
    {
        ints = new int[size];
        
        for (int i = 0; i < size; i++)
        {
            ints[i] = i;
        }
    }
    
    /**
     * This is a random function call but with a fixed result
     * @param size
     * @return
     */
    private int next(int size)
    {
        final int i = r.nextInt(1) + size;
        return i;
    }
    
    @Benchmark
    public int classic()
    {
        int sum = 0;
        int step = next(1);
        for (int i = 0; i < ints.length; i = i + 1)
        {
            sum += ints[i];
            step = next(1);
        }
        return sum + step;
    }
    
    @Benchmark
    public int variable()
    {
        int sum = 0;
        int step = next(1);
        for (int i = 0; i < ints.length; i = i + step)
        {
            sum += ints[i];
            step = next(1);
        }
        return sum + step;
    }
    
    @Benchmark
    public int classic2()
    {
        int sum = 0;
        int step = next(10);
        for (int i = 0; i < ints.length; i = i + 10)
        {
            sum += ints[i];
            step = next(10);
        }
        return sum + step;
    }
    
    @Benchmark
    public void variable2()
    {
        int sum = 0;
        int step = next(10);
        for (int i = 0; i < ints.length; i = i + step)
        {
            sum += ints[i];
            step = next(10);
        }
        int i = sum + step;
    }    
    
}