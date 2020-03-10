package org.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.misc.ParseInteger;
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

/**
 * This code is meant to discuss the performance change between JDK 8 and 11.
 * It uses a copy of the JDK 11 StringTokenizer to ensure that this is not the
 * cause.
 * 
 * The LightStringTokenizer is a legacy class written years ago (2002).
 * It was written because it was faster back in the days and also reports
 * empty tokens between consecutive delimiters. But for our demo, this is not 
 * the important feature.
 * 
 * @author Rene Schwietzke <r.schwietzke@xceptance.com>
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class CPUvsMemory
{
    final int SIZE = 1000;
    String[] src;
    
    private final static Map<String, Integer> cache = new HashMap<>();
    
    @Setup
    @Before
    public void setup()
    {
        // just create fancy IPs for later conversion to an int
        src = new String[SIZE];

        final Random r = new Random(1L);
        for (int i = 0; i < SIZE; i++)
        {
            src[i] = String.valueOf(r.nextInt(100_000)); 
            cache(src[i]); // prime cache
        }
        int i = 0;
    }
    
    /**
     * Parse the strings again and again
     */
    private int cpu(final String s)
    {
        return ParseInteger.parse(s);
    }

    /**
     * use the cache version
     */
    private int cache(final String s)
    {
        return cache.computeIfAbsent(s, k -> cpu(k));
    }
    
    @Benchmark
    public int cpu()
    {
        int sum = 0;

        for (int i = 0; i < SIZE; i++)
        {
            sum += cpu(src[i]);
        }

        return sum;
    }

    @Benchmark
    public int cache()
    {
        int sum = 0;

        for (int i = 0; i < SIZE; i++)
        {
            sum += cache(src[i]);
        }

        return sum;
    }

    /**
     * Just a litte test code to ensure we have not messed up the method under test
     */
    @Test
    public void test()
    {
        Assert.assertEquals(918181, cpu("918181"));
        Assert.assertEquals(918181111, cache("918181111"));
        Assert.assertEquals(918181111, cache("918181111"));
    }
}
