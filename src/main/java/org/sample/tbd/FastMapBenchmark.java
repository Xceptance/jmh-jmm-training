package org.sample.tbd;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.misc.FastRandom;
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
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class FastMapBenchmark
{
    @Param({"1", "5", "10", "20", "60"})
    private int SIZE;
    
    private final FastRandom r = new FastRandom(7L);
    private String[] strings;
    
    private final static String CHARS = "abcdefghijklmnopqrstuvwxyz";
    
    public String randomString(final int length)
    {
        final StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++)
        {
            int pos = r.nextInt(CHARS.length());
            sb.append(CHARS.charAt(pos));
        }
        
        return sb.toString();
    }
    
    private HashMap<String, String> jdkMap;
    private FastFakeMap<String, String> fastMap;
    
    @Setup
    public void setup()
    {
        strings = new String[SIZE];

        jdkMap = new HashMap<>();
        fastMap = new FastFakeMap<>();
        
        for (int i = 0; i < SIZE; i++)
        {
            strings[i] = randomString(5);
            
            jdkMap.put(strings[i], strings[i]);
            fastMap.put(strings[i], strings[i]);
        }
        
    }
    
    public FastFakeMap<String, String> doMap(FastFakeMap<String, String> map, Blackhole bh)
    {
        for (int i = 0; i < SIZE; i++)
        {
            // always hit
            if (map.get(strings[i]) != null)
            {
                bh.consume(map);
            }
        }
        
        return map;
    }

    public HashMap<String, String> doMap(HashMap<String, String> map, Blackhole bh)
    {
        for (int i = 0; i < SIZE; i++)
        {
            // always hit
            if (map.get(strings[i]) != null)
            {
                bh.consume(map);
            }
        }
        
        return map;
    }
    
    @Benchmark
    public FastFakeMap<String, String> fastMap(Blackhole bh)
    {
        return doMap(fastMap, bh);
    }

    @Benchmark
    public HashMap<String, String> jdkMap(Blackhole bh)
    {
        return doMap(jdkMap, bh);
    }

    
}
