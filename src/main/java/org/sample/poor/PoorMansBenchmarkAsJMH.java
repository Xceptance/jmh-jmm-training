package org.sample.poor;

import java.util.Arrays;
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
import org.sample.Timer;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 0, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 20, time = 10, timeUnit = TimeUnit.MILLISECONDS)
@Fork(1)
public class PoorMansBenchmarkAsJMH
{
    private static String append()
    {
        StringBuffer sb = new StringBuffer();
        sb.append(System.currentTimeMillis());
        sb.append("Foo");
        sb.append(1);
        sb.append('c');
        sb.append("lkJAHJ AHSDJHF KJASHF HSAKJFD");
        
        String s = sb.toString();
        char[] ch = s.toCharArray();
        Arrays.sort(ch);
        
        return new String(ch);
    }

    @Benchmark
    public int plain()
    {                        
        final Timer t1 = Timer.startTimer();         
        int x = append().length(); 
        t1.stop().runtimeNanos();
        
        return x;
    }
}
