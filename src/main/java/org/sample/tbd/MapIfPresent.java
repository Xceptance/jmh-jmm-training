package org.sample.tbd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.misc.FastRandom;
import org.misc.RandomUtils;
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
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class MapIfPresent
{
    @Param({"100"})
    int SIZE;
    
    private FastRandom r = new FastRandom(7L);
    private List<String> exists = new ArrayList<>(SIZE);
    private List<String> doesNotExists = new ArrayList<>(SIZE);

    private Map<String, String> map = new HashMap<>();

    @Setup
    public void setup()
    {
        for (int i = 0; i < SIZE; i++)
        {
            final String s = RandomUtils.randomString(r, r.nextInt(10));
            exists.add(s);
            map.put(s, s);
        }
        for (int i = 0; i < SIZE; i++)
        {
            doesNotExists.add(RandomUtils.randomString(r, r.nextInt(10)));
        }
    }
    
    @Benchmark
    public Map<String, String> classicIfAbsent()
    {
        for (int i = 0; i < doesNotExists.size(); i++)
        {
            final String key = doesNotExists.get(i);
            
            if (!map.containsKey(key))
            {
                map.put(key, key);
            }
        }
        return map;
    }
    
    @Benchmark
    public Map<String, String> lambdaIfAbsent()
    {
        for (int i = 0; i < doesNotExists.size(); i++)
        {
            final String key = doesNotExists.get(i);
            map.computeIfAbsent(key, k -> key);
        }
        return map;
    }

    @Benchmark
    public void classicIfPresent(Blackhole bh)
    {
        for (int i = 0; i < exists.size(); i++)
        {
            final String key = exists.get(i);
            final String value = map.get(key);
            
            if (value != null)
            {
                map.put(key, value + value);
            }
        }

        bh.consume(map);
    }
    
    @Benchmark
    public void lambdaIfPresent(Blackhole bh)
    {
        for (int i = 0; i < exists.size(); i++)
        {
            final String key = exists.get(i);
            
            map.computeIfPresent(key, (k, v) -> v + v);
        }
        
        bh.consume(map);
    }
}
