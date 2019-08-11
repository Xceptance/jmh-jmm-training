package org.sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.misc.FastRandom;
import org.misc.RandomUtils;
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

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
public class GCAndAccessSpeed
{
    private final static int SIZE  = 100_000;
    private final List<String> STRINGS = new ArrayList<>();
    private final List<String> ordered = new ArrayList<>();
    private final List<String> nonOrdered = new ArrayList<>();
    
    @Param({"false", "true"})
    boolean gc;

    @Param({"1", "10"})
    private int COUNT;

    @Param({"false", "true"})
    private boolean drop;
    
    @Setup
    @Before
    public void setup() throws InterruptedException
    {
        final FastRandom r = new FastRandom(7);
        for (int i = 0; i < COUNT * SIZE; i++)
        {
            STRINGS.add(RandomUtils.randomString(r, 1, 20));
        }
        
        for (int i = 0; i < SIZE; i++)
        {
            ordered.add(STRINGS.get(i * COUNT));
        }
        nonOrdered.addAll(ordered);
        Collections.shuffle(nonOrdered, new Random(r.nextInt()));
        
        if (drop)
        {
            STRINGS.clear();
        }
        
        if (gc) 
        {
            for (int c = 0; c < 5; c++) 
            {
                System.gc();
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }
    

    @Benchmark
    public int walkOrdered()
    {
        int sum = 0;
        for (int i = 0; i < ordered.size(); i++)
        {
            sum += ordered.get(i).length();
        }
        return sum;
    }

    @Benchmark
    public int walkNonOrdered()
    {
        int sum = 0;
        for (int i = 0; i < nonOrdered.size(); i++)
        {
            sum += nonOrdered.get(i).length();
        }
        return sum;
    }
    
    @Test
    public void test()
    {
        int a = walkNonOrdered();
        int b = walkOrdered();
        
        Assert.assertTrue(a == b);
    }
}
