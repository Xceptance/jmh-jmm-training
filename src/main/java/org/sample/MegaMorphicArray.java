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

@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class MegaMorphicArray
{
    private static final int COUNT = 20_000;
    private C[] array = new C[COUNT];

    @Param({"1", "2", "3", "4"})
    int params;
    
    @Setup
    public void setup()
    {
        final Random r = new Random(42L);
        
        for (int x = 0; x < COUNT; x++)
        {
            int i = r.nextInt(params);
            
            if (i == 0)
            {
                array[x] = new C1();
            }
            else if (i == 1)
            {
                array[x] = new C2();
            }
            else if (i == 2)
            {
                array[x] = new C3();
            }
            else if (i == 3)
            {
                array[x] = new C4();
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
    
    static class C1 implements C { 
        @Override
        public int apply(int x) { return x * x; }
    };
    static class C2 implements C { 
        @Override
        public int apply(int x) { return x * x; }
    };
    static class C3 implements C { 
        @Override
        public int apply(int x) { return x * x; }
    };
    static class C4 implements C { 
        @Override
        public int apply(int x) { return x * x; }
    };

}