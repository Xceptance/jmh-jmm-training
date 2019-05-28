package org.sample;

import java.util.HashMap;
import java.util.Map;
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
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import gnu.trove.map.hash.THashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class HashMapMemSizedTest
{
    @Param({"10", "100", "1000", "10000"})
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
    
    private Map<String, String> trove4JMap;
    private Map<String, String> jlMap;
    private Map<String, String> jdkMap;
    private Map<String, String> fastUtilsMap;
    
    @Setup
    public void setup()
    {
        strings = new String[SIZE];
        
        for (int i = 0; i < SIZE; i++)
        {
            strings[i] = randomString(5);
        }
        
        trove4JMap = new THashMap<>(2 * SIZE + 1);
        jdkMap = new HashMap<>(2 * SIZE + 1);
        jlMap = new javolution.util.FastMap<>();
        fastUtilsMap = new Object2ObjectOpenHashMap<>(2 * SIZE + 1);
    }
    
    public Map<String, String> doMap(Map<String, String> map, Blackhole bh)
    {
        for (int i = 0; i < SIZE; i++)
        {
            int k = r.nextInt(SIZE);
            
            // write data fresh data
            map.put(strings[i], strings[k]);
            
            if (i > 0)
            {
                // update something existing
                map.put(strings[i - 1], strings[k]);
            }

            // remove something random
            if (map.remove(strings[k]) != null)
            {
                // add it back
                map.remove(strings[k], strings[i]);
            }
        }
        
        return map;
    }

    @Benchmark
    public Map<String, String> troveMap(Blackhole bh)
    {
        return doMap(trove4JMap, bh);
    }

    @Benchmark
    public Map<String, String> jdkMap(Blackhole bh)
    {
        return doMap(jdkMap, bh);
    }
   
    @Benchmark
    public Map<String, String> jlMap(Blackhole bh)
    {
        return doMap(jlMap, bh);
    }

    @Benchmark
    public Map<String, String> fastUtilsMap(Blackhole bh)
    {
        return doMap(fastUtilsMap, bh);
    }

//    public static void main(String[] args) throws RunnerException {
//        HashMapMixTest t = new HashMapMixTest();
//        t.setup();
//        
//        t.doMap(t.jdkMap);
//    }
    
}
