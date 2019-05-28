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
@Fork(1)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
public class IntrinsicsArrayCopy_AverageTime
{
    final int SIZE = 100_000;
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
    
    public int[] manualArrayCopy()
    {
        int[] target = new int[SIZE];
        for (int i = 0; i < SIZE; i++)
        {
            target[i] = src[i];
        }
        
        return target;
    }
    
    public int[] systemArrayCopy()
    {
        int[] target = new int[SIZE];
        System.arraycopy(src, 0, target, 0, SIZE);
        
        return target;
    }
    
    @Benchmark
    public int[] manual()
    {
        return manualArrayCopy();
    }

    @Benchmark
    public int[] system()
    {
        return systemArrayCopy();
    }
    
    
}
