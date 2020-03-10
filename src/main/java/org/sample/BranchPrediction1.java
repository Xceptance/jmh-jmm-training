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
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class BranchPrediction1
{
    private static final int COUNT = 10_000;

    private int[] sorted = new int[COUNT];
    private int[] unsorted = new int[COUNT];
    private int[] reversed = new int[COUNT];

    @Setup
    public void setup()
    {
        final Random r = new Random(1L);
        
        for (int i = 0; i < COUNT; i++)
        {
            final int d = r.nextInt(100) - 50;
            sorted[i] = d;
            unsorted[i] = d;
        }        
        Arrays.sort(sorted);

        for (int i = 0; i < COUNT; i++)
        {
            reversed[i] = sorted[COUNT - 1 - i];
        }
    }

    public void doIt(int[] array, Blackhole bh1, Blackhole bh2)
    {
        for (int v : array)
        {
            if (v <= 0)
            {
                bh1.consume(v);
            }
            else
            {
                bh2.consume(v);
            }
        }
    }
    
    
    @Benchmark
    public void sorted(Blackhole bh1, Blackhole bh2)
    {
        doIt(sorted, bh1, bh2);
    }

    @Benchmark
    public void unsorted(Blackhole bh1, Blackhole bh2)
    {
        doIt(unsorted, bh1, bh2);
    }

    @Benchmark
    public void reversed(Blackhole bh1, Blackhole bh2)
    {
        doIt(reversed, bh1, bh2);
    }
}
