package org.sample;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class LockingCost 
{
    int x;
    int x2;
    int x3;
    int x4;
    volatile int v; 
    final AtomicInteger xa = new AtomicInteger(0);
    
    @Benchmark
    public int plainInt() 
    {
        // this is really garbage of course
        x++;
        return x;
    }

    @Benchmark
    public int volatileInt() 
    {
        // this is a little garbage because it is not 
        // an atomic update
        v++;
        return v;
    }
    
    @Benchmark
    public int atomicInt() 
    {
        return xa.incrementAndGet();
    }
    
    @Benchmark
    public int lockedIntInc() 
    {
        synchronized (this) 
        {
            x2++;
            return x2;
        }
    }

    @Benchmark
    public int lockedIntNewObject() 
    {
        synchronized (new Object()) 
        {
            x4++;
            return x4;
        }
    }
    
    @Benchmark
    public synchronized int lockedIntMethod() 
    {
        x3++;
        return x3;
    }    
}