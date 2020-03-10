package org.sample;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.misc.FastRandom;
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

@Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class NPEElimination
{
    int SIZE = 1000;
    Cheap[] a1 = new Cheap[SIZE];
    Cheap[] a2 = new Cheap[SIZE];
    Cheap[] a3 = new Cheap[SIZE];
    
    @Setup
    public void setup()
    {
        final FastRandom r = new FastRandom(11L);
        for (int i = 0; i < SIZE; i++)
        {
            a1[i] = new Cheap(r.nextInt(10), r.nextInt(20));
            a2[i] = new Cheap(r.nextInt(10), r.nextInt(20));
            a3[i] = null;
        }
    }
    
    private int nullChecks(final Cheap a, final Cheap b, final Cheap c)
    {
        int total = 0;
        
        if (a != null)
        {
            total = a.a;
        }
        if (b != null)
        {
            total += b.b;
        }
        if (a != null)
        {
            total = a.a + b.b;
        }
        if (c == null)
        {
            total += total;
        }
        
        return total;
    }
    
    private int noNullChecks(final Cheap a, final Cheap b, final Cheap c)
    {
        int total = 0;
        
        total = a.a;
        total += b.b;
        total = a.a + b.b;
        total += total;
        
        return total;
    }   

    @Benchmark
    public int noNullChecks()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            sum += noNullChecks(a1[i], a2[i], a3[i]);
        }
        
        return sum;
    }
    
    @Benchmark
    public int nullChecks()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            sum += nullChecks(a1[i], a2[i], a3[i]);
        }
        
        return sum;
    }  
    
    @Test
    public void test()
    {
        Cheap a = new Cheap(1, 2);
        Cheap b = new Cheap(4, 5);
        
        Assert.assertEquals(noNullChecks(a, b, null), nullChecks(a, b, null));
    }
    
    static class Cheap
    {
        int a;
        int b;
        
        public Cheap(int a, int b)
        {
            this.a = a;
            this.b = b;
        }
        
        public int add(int c)
        {
            a++;
            b += c;
            
            return b;
        }
    }
}