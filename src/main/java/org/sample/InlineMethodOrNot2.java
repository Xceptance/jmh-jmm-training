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

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class InlineMethodOrNot2
{
    /**
     * CLI: mvn clean compile install; java -Xms4g -Xmx4g -XX:+UseG1GC -XX:+AlwaysPreTouch -jar target/benchmarks.jar InlineMethodOrNot2
     */
    
    /**
     * GraalVM
     * JDK 1.8.0_222, OpenJDK 64-Bit GraalVM CE 19.1.1, 25.222-b08-jvmci-19.1-b01
     *   
     * Benchmark                        (size)  Mode  Cnt    Score    Error  Units
     * InlineMethodOrNot2.dontInline       100  avgt    5  463.394 ± 27.551  ns/op
     * InlineMethodOrNot2.inline           100  avgt    5  473.785 ±  7.501  ns/op
     * InlineMethodOrNot2.jdkDefault       100  avgt    5  459.002 ± 11.599  ns/op
     * InlineMethodOrNot2.noMethodCall     100  avgt    5  472.372 ±  6.543  ns/op
     */

    /**
     * OpenJDK 11
     * Benchmark                        (size)  Mode  Cnt    Score     Error  Units
     * InlineMethodOrNot2.dontInline       100  avgt    5  548.942 ±  12.993  ns/op
     * InlineMethodOrNot2.inline           100  avgt    5  750.228 ±  10.826  ns/op
     * InlineMethodOrNot2.jdkDefault       100  avgt    5  581.725 ± 185.251  ns/op
     * InlineMethodOrNot2.noMethodCall     100  avgt    5  747.839 ±  27.456  ns/op
     */
    
    /**
     * Problem: It is not clear why no inlining is faster than even direct code without
     * any method call (at least not set by me). GraalVM is within expectations, but
     * JDK 11 is not. 
     */
    
    @Param({"100"})
    int size;
    String DATA;
    
    @Setup
    public void setup()
    {
        final FastRandom r = new FastRandom(7);
        DATA = RandomUtils.randomString(r, size);
    }

    private String defaultUppercase(String a)
    {
        final StringBuilder sb = new StringBuilder(a.length());
        for (int i = 0; i < a.length(); i++)
        {
            char c = a.charAt(i);
            char uc = Character.toUpperCase(c);
            sb.append(uc);
        }
        return sb.toString();
    }
    @Benchmark
    public String jdkDefault()
    {
        return defaultUppercase(DATA);
    }

    @CompilerControl(CompilerControl.Mode.INLINE)
    private String inlinedUppercase(String a)
    {
        final StringBuilder sb = new StringBuilder(a.length());
        for (int i = 0; i < a.length(); i++)
        {
            char c = a.charAt(i);
            char uc = Character.toUpperCase(c);
            sb.append(uc);
        }
        return sb.toString();
    }
    
    @Benchmark
    public String inline()
    {
        return inlinedUppercase(DATA);
    }
    
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private String notInlinedUppercase(String a)
    {
        final StringBuilder sb = new StringBuilder(a.length());
        for (int i = 0; i < a.length(); i++)
        {
            char c = a.charAt(i);
            char uc = Character.toUpperCase(c);
            sb.append(uc);
        }
        return sb.toString();
    }  
    
    @Benchmark
    public String dontInline()
    {
        return notInlinedUppercase(DATA);
    }

    
    @Benchmark
    public String noMethodCall()
    {  
        final String a = DATA;
        
        final StringBuilder sb = new StringBuilder(a.length());
        for (int i = 0; i < a.length(); i++)
        {
            char c = a.charAt(i);
            char uc = Character.toUpperCase(c);
            sb.append(uc);
        }
        
        return sb.toString();
    }
}
