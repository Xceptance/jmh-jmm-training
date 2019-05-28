package org.sample;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Inline01
{
    Random r = new Random();
    
    public long iMethod05() { return r.nextInt(); };
    public long iMethod04() { return iMethod05() + 1; };
    public long iMethod03() { return iMethod04() + 2; };
    public long iMethod02() { return iMethod03() + 3; };    
    public long iMethod01() { return iMethod02() + 4; };

    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public long diMethod05() { return  r.nextInt(); };
//    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public long diMethod04() { return diMethod05() + 1; };
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public long diMethod03() { return diMethod04() + 2; };
//    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public long diMethod02() { return diMethod03() + 3; };    
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public long diMethod01() { return diMethod02() + 4; };

    @Benchmark
    public void noInline(Blackhole bh)
    {
        bh.consume(diMethod01());
    }

    @Benchmark
    public void inline(Blackhole bh)
    {
        bh.consume(iMethod01());
    }
}
