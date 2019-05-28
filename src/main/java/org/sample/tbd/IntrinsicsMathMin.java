package org.sample.tbd;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
//@BenchmarkMode(Mode.AverageTime)
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 1, time = 5, timeUnit = TimeUnit.SECONDS)
//@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 120)
@Fork(1)
public class IntrinsicsMathMin
{
    final int SIZE = 10_000;
    final int[] a = new int[SIZE];
    final int[] b = new int[SIZE];
    
    @Setup
    public void setup()
    {
        Random r = new Random();
        for (int i = 0; i < SIZE; i++)
        {
            a[i] = r.nextInt(1000) - 500;
            b[i] = r.nextInt(1000) - 500;
        }
    }

    private int jdkMin(int a, int b)
    {
        return Math.min(a, b);
    }
    
    private int min(int a, int b)
    {
        return (a <= b) ? a : b;
    }

    private int min2(int a, int b)
    {
        if (a <= b)
        {
            return a;
        }
        else
        {
            return b;
        }
    }
    
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private int notInlinedMin(int a, int b)
    {
        return (a <= b) ? a : b;   
    }
    
    @Benchmark
    public void jdkMin(Blackhole bh)
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            sum += jdkMin(a[i], b[i]);
        }
        
        bh.consume(sum);
    }

    @Benchmark
    public void ownMin(Blackhole bh)
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            sum += min(a[i], b[i]);
        }
        
        bh.consume(sum);
    }

    @Benchmark
    public void ownMin2(Blackhole bh)
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            sum += min2(a[i], b[i]);
        }
        
        bh.consume(sum);
    }
    
    @Benchmark
    public void ownMinNotInlined(Blackhole bh)
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            sum += notInlinedMin(a[i], b[i]);
        }
        
        bh.consume(sum);
    }    
}
