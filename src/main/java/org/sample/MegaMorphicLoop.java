package org.sample;

import java.util.Random;
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
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

@Warmup(iterations = 2, time = 4, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class MegaMorphicLoop
{
    private static final int COUNT = 20_000;
    private C[] array = new C[COUNT];

    @Param({"1", "2", "3", "4"})
    int params;
    
    private final static C1 c1 = new C1();
    private final static C2 c2 = new C2();
    private final static C3 c3 = new C3();
    private final static C4 c4 = new C4();
    
    @Setup
    public void setup()
    {
        final Random r = new Random(1L);
        
        for (int x = 0; x < COUNT; x++)
        {
            int i = r.nextInt(params);
            
            if (i == 0)
            {
                array[x] = c1;
            }
            else if (i == 1)
            {
                array[x] = c2;
            }
            else if (i == 2)
            {
                array[x] = c3;
            }
            else if (i == 3)
            {
                array[x] = c4;
            }
            else
            {
                throw new IllegalStateException();
            }
        }        
    }
    
    @Benchmark
    public int morphic()
    {
        int sum = 0;
        for (int i = 0; i < COUNT; i++)
        {
            sum += array[i].apply(i);
        }
        
        return sum;
    }

    @Benchmark
    public int peeled()
    {
        int sum = 0;
        for (int i = 0; i < COUNT; i++)
        {
            final C c = array[i];
            if (c instanceof C1)
            {
                sum += ((C1) c).apply(i);
            }
            else if (c instanceof C2)
            {
                sum += ((C2) c).apply(i);
            }
            else if (c instanceof C3)
            {
                sum += ((C3) c).apply(i);
            }
            else
            {
                sum += ((C4) c).apply(i);
            }
        }
        
        return sum;
    }

    
    
    static interface C { public int apply(int x); }
    static class C1 implements C { public int apply(int x){ return x * x; }};
    static class C2 implements C { public int apply(int x){ return x * x; }};
    static class C3 implements C { public int apply(int x){ return x * x; }};
    static class C4 implements C { public int apply(int x){ return x * x; }};
}