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

@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 1, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class NPELoop
{
    @Param({"true", "false"})
    private boolean safeHandler;
    
    private static final int COUNT = 5000;
    private static final int AMOUNT = 2;
    
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
        
//        // train it, NPE won't happen
//        int sum = 0;
//        for (int i = 0; i < COUNT ; i++)
//        {
//            sum += hotMethod(String.valueOf(i));
//        }
//        bh.consume(sum);
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
    public int nullAtTheStart(Blackhole bh)
    {
        int sum = 0;
        for (int i = 0; i < COUNT; i++)
        {
            try
            {
                sum += hotMethod(nullAtTheStart[i]);
            }
            catch (NullPointerException e)
            {
            }
        }
        return sum;
    }

    @Benchmark
    public int nullSpreadOut(Blackhole bh)
    {
        int sum = 0;
        for (int i = 0; i < COUNT; i++)
        {
            try
            {
                sum += hotMethod(nullSpreadOut[i]);
            }
            catch (NullPointerException e)
            {
            }
        }
        return sum;
    }

    @Benchmark
    public int nullAtTheEnd(Blackhole bh)
    {
        int sum = 0;
        for (int i = 0; i < COUNT; i++)
        {
            try
            {
                sum += hotMethod(nullAtTheEnd[i]);
            }
            catch (NullPointerException e)
            {
            }
        }
        return sum;
   }

    @Benchmark
    public int noNull(Blackhole bh)
    {
        int sum = 0;
        
        // be fair
        for (int i = 0; i < COUNT; i++)
        {
            sum += hotMethod(noNull[i]);
        }
        return sum;
    }
    
    @Benchmark
    public int allNull(Blackhole bh)
    {
        int sum = 0;
        for (int i = 0; i < COUNT; i++)
        {
            try
            {
                sum += hotMethod(allNull[i]);
            }
            catch (NullPointerException e)
            {
            }
        }
        return sum;
   }
}