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

    
    @Param({"1", "10", "100", "1000"})
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

    /*
    ForEachExpensive.classicFor                  1  avgt    2     232.843          ns/op
    ForEachExpensive.classicFor                 10  avgt    2    2060.264          ns/op
    ForEachExpensive.classicFor                100  avgt    2   21705.958          ns/op
    ForEachExpensive.classicFor               1000  avgt    2  244277.931          ns/op
    ForEachExpensive.enhancedFor                 1  avgt    2     222.465          ns/op
    ForEachExpensive.enhancedFor                10  avgt    2    2123.579          ns/op
    ForEachExpensive.enhancedFor               100  avgt    2   22203.045          ns/op
    ForEachExpensive.enhancedFor              1000  avgt    2  249561.558          ns/op
    ForEachExpensive.lamdaStream                 1  avgt    2     352.786          ns/op
    ForEachExpensive.lamdaStream                10  avgt    2    2492.908          ns/op
    ForEachExpensive.lamdaStream               100  avgt    2   25046.700          ns/op
    ForEachExpensive.lamdaStream              1000  avgt    2  273181.221          ns/op
    ForEachExpensive.parallelLamdaStream         1  avgt    2     375.847          ns/op
    ForEachExpensive.parallelLamdaStream        10  avgt    2   11468.792          ns/op
    ForEachExpensive.parallelLamdaStream       100  avgt    2   25909.986          ns/op
    ForEachExpensive.parallelLamdaStream      1000  avgt    2  225207.607          ns/op
    */

}
