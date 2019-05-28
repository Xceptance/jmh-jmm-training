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
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import gnu.trove.map.hash.THashMap;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class HashMapTest
{
    private final static int SIZE = 10240;
    private final FastRandom r = new FastRandom(7L);
    private final String[] strings = new String[SIZE];
    
    private final static String CHARS = "abcdefghijklmnopqrstuvwxyz";
    private final static String EMPTY = "";
    
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
        for (int i = 0; i < SIZE; i++)
        {
            strings[i] = randomString(5);
        }
        
        trove4JMap = writeTrove4JMap();
        jdkMap = writeJDKMap();
        jlMap = writeJDKMap();
        fastUtilsMap = writeFastUtilsMap();
    }
    
    @Benchmark
    public Map<String, String> writeJDKMap()
    {
        final Map<String, String> map = new HashMap<>();
        
        for (int i = 0; i < SIZE; i++)
        {
            map.put(strings[i], EMPTY);
        }
        
        return map;
    }

    @Benchmark
    public Map<String, String> writeJLMap()
    {
        final Map<String, String> map = new javolution.util.FastMap<>();
        
        for (int i = 0; i < SIZE; i++)
        {
            map.put(strings[i], EMPTY);
        }
        
        return map;
    }

    @Benchmark
    public Map<String, String> writeTrove4JMap()
    {
        final Map<String, String> map = new THashMap<>();
        
        for (int i = 0; i < SIZE; i++)
        {
            map.put(strings[i], EMPTY);
        }
        
        return map;
    }

    @Benchmark
    public Map<String, String> writeFastUtilsMap()
    {
        final Map<String, String> map = new Object2ObjectOpenHashMap<>();
        
        for (int i = 0; i < SIZE; i++)
        {
            map.put(strings[i], EMPTY);
        }
        
        return map;
    }
    
    @Benchmark
    public Map<String, String> readTrove4jMap(Blackhole bh)
    {
        for (int i = 0; i < SIZE; i++)
        {
            if (trove4JMap.get(strings[i]) == null)
            {
                bh.consume(trove4JMap);
                throw new NullPointerException();
            }
        }
        
        return trove4JMap;
    }

    @Benchmark
    public Map<String, String> readJDKMap(Blackhole bh)
    {
        for (int i = 0; i < SIZE; i++)
        {
            if (jdkMap.get(strings[i]) == null)
            {
                bh.consume(jdkMap);
                throw new NullPointerException();
            }
        }
        
        return jdkMap;
    }
   
    @Benchmark
    public Map<String, String> readJLMap(Blackhole bh)
    {
        for (int i = 0; i < SIZE; i++)
        {
            if (jlMap.get(strings[i]) == null)
            {
                bh.consume(jlMap);
                throw new NullPointerException();
            }
        }
        
        return jlMap;
    }

    @Benchmark
    public Map<String, String> readFastUtilsMap(Blackhole bh)
    {
        for (int i = 0; i < SIZE; i++)
        {
            if (fastUtilsMap.get(strings[i]) == null)
            {
                bh.consume(fastUtilsMap);
                throw new NullPointerException();
            }
        }
        
        return fastUtilsMap;
    }
}
