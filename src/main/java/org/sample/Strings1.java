package org.sample;

import java.util.concurrent.TimeUnit;

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

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2000, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
public class Strings1
{
    private String foo = null;
    
    @Setup
    public void setup()
    {
        foo = String.valueOf(System.currentTimeMillis()) + "ashdfhgas dfhsa df";
    }
    
    @Benchmark
    public String plain()
    {    
        return "a" + foo;
    }

    @Benchmark
    public String concat()
    {    
        return "a".concat(foo);
    }

    @Benchmark
    public String builder()
    {    
        return new StringBuilder().append("a").append(foo).toString();
    }

    @Benchmark
    public String builderSized()
    {    
        return new StringBuilder(32).append("a").append(foo).toString();
    }
    
    @Benchmark
    public String builderNonFluid()
    {    
         final StringBuilder sb = new StringBuilder();
         sb.append("a");
         sb.append(foo);
         return sb.toString();
    }
    
    @Benchmark
    public String builderNonFluidSized()
    {    
        final StringBuilder sb = new StringBuilder(32);
        sb.append("a");
        sb.append(foo);
        return sb.toString();
    }
}