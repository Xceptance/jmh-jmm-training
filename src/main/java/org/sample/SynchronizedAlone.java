package org.sample;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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

@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class SynchronizedAlone 
{
    private final int SIZE = 1024;
    private final Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>(SIZE));
    private final Map<String, String> unsyncMap = new HashMap<>(SIZE);
    private final Map<String, String> conMap = new ConcurrentHashMap<>(SIZE);
    
    @Setup
    public void setup()
    {
        for (int i = 0; i < SIZE; i++)
        {
            final String s = String.valueOf(i);
            
            syncMap.put(s, s);
            unsyncMap.put(s, s);
            conMap.put(s, s);
        }
    }
    
    @Benchmark
    public String syncAccess() 
    {
        return syncMap.get("1");
    }

    @Benchmark
    public String unsyncAccess() 
    {
        return unsyncMap.get("1");
    }
    @Benchmark
    public String conAccess() 
    {
        return conMap.get("1");
    }
}