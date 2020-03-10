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
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class EscapeAnalysis
{
    final Random r = new Random();
    final static int SIZE = 64;
    
    @Benchmark
    public int array64()
    {
        int[] a = new int[SIZE];
        
        a[0] = r.nextInt();
        a[1] = r.nextInt();
        
        a[3] = a[0] + a[1];

        return a[3];
    }

    @Benchmark
    public int array65()
    {
        int[] a = new int[SIZE + 1];
        
        a[0] = r.nextInt();
        a[1] = r.nextInt();
        
        a[3] = a[0] + a[1];

        return a[3];
    }

    @Benchmark
    public int[] array64Returned()
    {
        int[] a = new int[SIZE];
        
        a[0] = r.nextInt();
        a[1] = r.nextInt();
        
        a[3] = a[0] + a[1];
        
        return a;
    }
}
