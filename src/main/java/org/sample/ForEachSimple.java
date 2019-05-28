package org.sample;

import java.util.ArrayList;
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

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class ForEachSimple
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
            array[i] = String.valueOf(i);
            list.add(String.valueOf(i));
        }
    }

    @Benchmark
    public void arrayFor(Blackhole bh)
    {
        int result = 0;
        for (int i = 0; i < array.length; i++)
        {
            result += array[i].length();
        }
        
        bh.consume(result);
    }
 
    @Benchmark
    public void classicFor(Blackhole bh)
    {
        int result = 0;
        for (int i = 0; i < list.size(); i++)
        {
            result += list.get(i).length();
        }
        
        bh.consume(result);
    }
    
    @Benchmark
    public void enhancedFor(Blackhole bh)
    {
        int result = 0;
        for (String s : list)
        {
            result += s.length();
        }

        bh.consume(result);
    }    

    @Benchmark
    public void lambdaStream(Blackhole bh)
    {
        int result = list.stream()
            .mapToInt(s -> s.length())
            .sum();
        
        bh.consume(result);
    } 
    
    @Benchmark
    public void parallelLambdaStream(Blackhole bh)
    {
        int result = list.parallelStream()
            .mapToInt(s -> s.length())
            .sum();
        
        bh.consume(result);
    } 

}
