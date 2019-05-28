package org.sample;

import java.util.Arrays;
import java.util.Random;
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
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 0, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, batchSize = 10)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class NPEBlackhole
{
    /**
     * Does not work as planned.... mmmmm
     */
    
    @Param({"false"})
    private boolean safeHandler;
    
    private static final int COUNT = 10000;
    private static final int AMOUNT = 20;
    
    private String[] nullAtTheStart = new String[COUNT];
    private String[] nullSpreadOut = new String[COUNT];
    private String[] nullAtTheEnd = new String[COUNT];
    private String[] allNull = new String[COUNT];
    private String[] noNull = new String[COUNT];

    private String EMPTY = new String();
    
    @Setup
    public void setup(Blackhole bh) throws InterruptedException
    {
        // fill all
        Arrays.fill(nullAtTheStart, EMPTY);
        Arrays.fill(nullSpreadOut, EMPTY);
        Arrays.fill(nullAtTheEnd, EMPTY);
        Arrays.fill(allNull, EMPTY);
        Arrays.fill(noNull, EMPTY);
        
        for (int i = 0; i < COUNT / AMOUNT; i++)
        {
            nullAtTheStart[i] = null;
        }
        for (int i = COUNT - (COUNT / AMOUNT); i < COUNT; i++)
        {
            nullAtTheEnd[i] = null;
        }
        for (int i = 0; i < COUNT; i = i + AMOUNT)
        {
            nullSpreadOut[i] = null;
        }
        for (int i = 0; i < COUNT; i++)
        {
            allNull[i] = null;
        }    
        
    }
    
    public int hotMethodSafe(String s)
    {
        return s != null ? s.hashCode() : 0;
    }
    public int hotMethodUnsafe(String s)
    {
        return s.hashCode();
    }

    public int hotMethod(String s)
    {
        return safeHandler ? hotMethodSafe(s) : hotMethodUnsafe(s);
    }
    
    @Benchmark
    public void nullAtTheStart(Blackhole bh)
    {
        for (int i = 0; i < COUNT; i++)
        {
            try
            {
                bh.consume(hotMethod(nullAtTheStart[i]));
            }
            catch (NullPointerException e)
            {
            }
        }
    }

    @Benchmark
    public void nullSpreadOut(Blackhole bh)
    {
        for (int i = 0; i < COUNT; i++)
        {
            try
            {
                bh.consume(hotMethod(nullSpreadOut[i]));
            }
            catch (NullPointerException e)
            {
            }
        }
    }

    @Benchmark
    public void nullAtTheEnd(Blackhole bh)
    {
        for (int i = 0; i < COUNT; i++)
        {
            try
            {
                bh.consume(hotMethod(nullAtTheEnd[i]));
            }
            catch (NullPointerException e)
            {
            }
        }
   }

    @Benchmark
    public void noNull(Blackhole bh)
    {
        for (int i = 0; i < COUNT; i++)
        {
            bh.consume(hotMethod(noNull[i]));
        }
    }
    
    @Benchmark
    public void allNull(Blackhole bh)
    {
        for (int i = 0; i < COUNT; i++)
        {
            try
            {
                bh.consume(hotMethod(allNull[i]));
            }
            catch (NullPointerException e)
            {
                bh.consume(1);
            }
        }
   }
}