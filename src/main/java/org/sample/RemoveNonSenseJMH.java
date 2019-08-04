package org.sample;

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
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 0, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class RemoveNonSenseJMH
{
    private final static int OUTER = 1000;

    @Param({"1", "10", "100", "1000", "10000"})
    private int SIZE = 5;

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Measurement(iterations = 1)
    public int nonSenseSingle()
    {
        return nonSense();
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    public int nonSenseNoWarmup()
    {
        return nonSense();
    }
    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
    public int nonSenseWarmup()
    {
        return nonSense();
    }    
    /**
     * Vary SIZE from 1 to 10000    
     * @param args
     */
    public int nonSense()
    {
        Object trap = null;
        Object o = null;
        
        for (int i = 0; i < OUTER; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                o = new Object();
                
                if (trap != null)
                {
                    o = new Object();
                    trap = null;
                }
            }
                
            if (i == 500)
            {
                trap = new Object();
            }
        }        

        return trap == null ? 1 : 0;
    }

}
