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
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class InlineMethodOrNot1
{
    @Param({"10"})
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
        return a.toUpperCase();
    }
    
    @CompilerControl(CompilerControl.Mode.INLINE)
    private String inlinedUppercase(String a)
    {
        return a.toUpperCase();
    }
    
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    private String notInlinedUppercase(String a)
    {
        return a.toUpperCase();
    }
    
    @Benchmark
    public String jdkDefault()
    {
        return defaultUppercase(DATA);
    }
    
    @Benchmark
    public String dontInline()
    {
        return notInlinedUppercase(DATA);
    }
    
    @Benchmark
    public String inline()
    {
        return inlinedUppercase(DATA);
    }
    
    @Benchmark
    public String noMethodCall()
    {  
        return DATA.toUpperCase();
    }
}
