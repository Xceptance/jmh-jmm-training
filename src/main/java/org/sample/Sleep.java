package org.sample;

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
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Sleep
{
    // ms
    private final static int LENGTH = 1;
        
    private String task(String s, long filler)
    {
        return s + "1";
    }
    
    private String sleep() throws InterruptedException
    {
        final String s = task("foo", 2000L);
        
        Thread.sleep(LENGTH);
        
        return task(s, 10000L);
    }

    private String busyWait() throws InterruptedException
    {
        String s = task("foo", 2000L);
        
        long target = System.nanoTime() + LENGTH * 1000 * 1000;
        
        int filler = 0;
        while (System.nanoTime() < target)
        {
            filler++;
        }
        
        return task(s, filler);
    }
    
     @Benchmark
     public String sleepTest() throws InterruptedException
     {
         return sleep();
     }

     @Benchmark
     public String busyWaitTest() throws InterruptedException
     {
         return busyWait();
     }

}
