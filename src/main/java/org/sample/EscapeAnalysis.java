package org.sample;

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
    
    @Benchmark
    public long array63()
    {
        int[] a = new int[63];
        
        a[0] = r.nextInt();
        a[1] = r.nextInt();
        
        return a[0] + a[1];
    }

    @Benchmark
    public long array64()
    {
        int[] a = new int[64];
        
        a[0] = r.nextInt();
        a[1] = r.nextInt();
        
        return a[0] + a[1];
    }

    @Benchmark
    public long array65()
    {
        int[] a = new int[65];
        
        a[0] = r.nextInt();
        a[1] = r.nextInt();
        
        return a[0] + a[1];
    }
}
