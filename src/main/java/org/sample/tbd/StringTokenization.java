package org.sample.tbd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
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

import java.util.StringTokenizer;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class StringTokenization
{
    @Param({"1", "3", "5", "10", "20"})
    int SIZE;
    
    private FastRandom r = new FastRandom(7L);
    private String s;
    
    @Setup
    public void setup()
    {
        final StringJoiner sj = new StringJoiner(",");
        for (int i = 0; i < SIZE; i++)
        {
             sj.add(RandomUtils.randomString(r, r.nextInt(10)));
        }
        s = sj.toString();
    }
    
    @Benchmark
    public String[] split()
    {
        return s.split(",");
    }
    
    @Benchmark
    public String[] tokenizer()
    {
        final List<String> target = new ArrayList<>(SIZE);
        
        final StringTokenizer st = new StringTokenizer(s, ",");
        while (st.hasMoreTokens())
        {
            target.add(st.nextToken());
        }
        
        return target.toArray(new String[target.size()]);
    }
}
