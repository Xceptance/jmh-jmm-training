package org.sample;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class ForEachExpensive
{
    final List<String> list = new ArrayList<>();
    String[] array = null;

    
    @Param({"1000", "10000", "100000"})
    int length;
    
    @Setup
    public void setup()
    {
        array = new String[length];

        for (int i = 0; i < length; i++)
        {
            final String entry = MessageFormat.format("{0},{0},{0},{0},{0},{0}", String.valueOf(i));
            array[i] = entry;
            list.add(entry);
        }
    }

    @Benchmark
    public void arrayFor(Blackhole bh)
    {
        int result = 0;
        for (int i = 0; i < array.length; i++)
        {
            final String s = array[i];
            if (s.startsWith("5"))
            {
                continue;
            }
            
            final String[] a = s.split(",");
            for (int j = 0; j < a.length; j++)
            {
                result += Integer.valueOf(a[j]);
            }
        }
        
        bh.consume(result);
    }
    
    @Benchmark
    public void arrayEnhanced(Blackhole bh)
    {
        int result = 0;
        for (String s : array)
        {
            if (s.startsWith("5"))
            {
                continue;
            }
            
            final String[] a = s.split(",");
            for (int j = 0; j < a.length; j++)
            {
                result += Integer.valueOf(a[j]);
            }
        }
        
        bh.consume(result);
    }    
    
    @Benchmark
    public void classicFor(Blackhole bh)
    {
        int result = 0;
        for (int i = 0; i < list.size(); i++)
        {
            final String s = list.get(i);
            if (s.startsWith("5"))
            {
                continue;
            }
            
            final String[] a = s.split(",");
            for (int j = 0; j < a.length; j++)
            {
                result += Integer.valueOf(a[j]);
            }
        }
        
        bh.consume(result);
    }
    
    
    @Benchmark
    public void enhancedFor(Blackhole bh)
    {
        int result = 0;
        for (String line : list)
        {
            if (line.startsWith("5"))
            {
                continue;
            }
            
            final String[] a = line.split(",");
            for (final String s : a)
            {
                result += Integer.valueOf(s);
            }
        }

        bh.consume(result);
    }    

    
    @Benchmark
    public void lambdaStream(Blackhole bh)
    {
        int result = list.stream()
            .filter(s -> !s.startsWith("5"))
            .flatMap(s -> Arrays.stream(s.split(",")))
            .mapToInt(s -> Integer.valueOf(s))
            .sum();
        
        bh.consume(result);
    } 
    
    @Benchmark
    public void parallelLambdaStream(Blackhole bh)
    {
        long result = list.parallelStream()
            .filter(s -> !s.startsWith("5"))
            .flatMap(s -> Arrays.stream(s.split(",")))
            .mapToInt(s -> Integer.valueOf(s))
            .sum();
        
        bh.consume(result);
    } 


}
