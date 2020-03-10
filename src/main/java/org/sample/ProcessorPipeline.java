package org.sample;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
@Warmup(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 4, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class ProcessorPipeline
{
    @Param({"10000"})
    int size = 10000;
    
    private FastRandom r = new FastRandom(7L);
    
    private int[] ints = null;
    
    @Setup
    @Before
    public void setup()
    {
        ints = new int[size];
        
        for (int i = 0; i < size; i++)
        {
            ints[i] = r.nextInt(10) + 1;
        }
    }
    
    @Benchmark
    public int singleAdd()
    {
        int s1 = 1;
        
        for (int i = 0; i < ints.length; i++)
        {
            s1 += i;
        }
        
        return s1 + s1;
    }
    
    @Benchmark
    public int doubleAdd()
    {
        int s1 = 1;
        int s2 = 1;
        
        for (int i = 0; i < ints.length; i = i + 1)
        {
            s1 += i;
            s2 += i;
        }
        
        return s1 + s2;
    }
    
    @Test
    public void test()
    {
        Assert.assertEquals(singleAdd(), doubleAdd());
    }
}