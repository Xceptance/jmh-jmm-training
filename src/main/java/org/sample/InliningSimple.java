package org.sample;

import java.util.concurrent.TimeUnit;

import org.misc.FastRandom;
import org.misc.RandomUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
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
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class InliningSimple
{
    @Param({"1"})
    private int SIZE;
    private String[] strings = new String[1];
    
    @Setup
    public void setup()
    {   
        final FastRandom r = new FastRandom(7);
        
        for (int i = 0; i < strings.length; i++)
        {
            strings[i] = RandomUtils.randomString(r, 1, 1000);
        }
    }
    
    @CompilerControl(CompilerControl.Mode.INLINE)
    private static String uppercase(String s)
    {
        return s.toUpperCase();
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private static String uppercaseNoInline(String s)
    {
        return s.toUpperCase();
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    private static String lowercase(String s)
    {
        return s.toLowerCase();
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private static String lowercaseNoInline(String s)
    {
        return s.toLowerCase();
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    private static int sumOfChars(String s)
    {
        int sum = 0;
        for (char c : s.toCharArray())
        {
            sum += c;
        }
        
        return sum;
    }

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private static int sumOfCharsNoInline(String s)
    {
        int sum = 0;
        for (char c : s.toCharArray())
        {
            sum += c;
        }
        
        return sum;
    }
    
    @Benchmark
    public void _3largeMethod(Blackhole bh)
    { 
        final String s = strings[0];
        
        // uppercase it
        final String s2 = s.toUpperCase();
        
        // lowercase it      
        final String s3 = s2.toLowerCase();
        
        // sum
        int sum = 0;
        for (char c : s3.toCharArray())
        {
            sum += c;
        }
        
        bh.consume(sum);
    }
    
    @Benchmark
    public void _1smallAndCallsInline(Blackhole bh)
    {    
        final String s = strings[0];
        
        final String s2 = uppercase(s);
        final String s3 = lowercase(s2);
        
        int sum = sumOfChars(s3);
        
        bh.consume(sum);
    }

    @Benchmark
    public void _2smallAndCallsNoInline(Blackhole bh)
    {    
        final String s = strings[0];
        
        final String s2 = uppercaseNoInline(s);
        final String s3 = lowercaseNoInline(s2);
        
        int sum = sumOfCharsNoInline(s3);
        
        bh.consume(sum);
    }

}