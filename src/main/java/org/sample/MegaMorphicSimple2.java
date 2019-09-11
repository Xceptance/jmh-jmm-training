package org.sample;

import java.util.Random;
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
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;

/**
 * Combines MegaMorphicSimple and MegaMorphicArray to verify
 * a theory but does not really do what I thought.
 * @author rschwietzke
 *
 */
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class MegaMorphicSimple2
{
    private Random random = new Random(1L);
    private static final int COUNT = 20_000;

    private C[] c; 
    
    @Setup
    public void setup()
    {
        c = new C[4];
        c[0] = new C1();
        c[1] = new C2();
        c[2] = new C3();
        c[3] = new C4();
    }
    
    @Benchmark
    public int _1_mono()
    {
        int sum = 0;
        for (int x = 0; x < COUNT; x++)
        {
            int r = random.nextInt(1);
            sum += c[r].apply(r);
        }
        return sum;
    }

    @Benchmark
    public int _2_bi()
    {
        int sum = 0;
        for (int x = 0; x < COUNT; x++)
        {
            int r = random.nextInt(2);
            sum += c[r].apply(r);
        }
        return sum;
    }

    @Benchmark
    public int _4_mega()
    {
        int sum = 0;
        for (int x = 0; x < COUNT; x++)
        {
            int r = random.nextInt(4);
            sum += c[r].apply(r);
        }
        return sum;
    }

    @Benchmark
    public int _5_manualDispatch()
    {
        int sum = 0;
        for (int x = 0; x < COUNT; x++)
        {
            int r = random.nextInt(4);
            final C _c = c[r];

            if (_c instanceof C1)
            {
                sum +=  ((C1) _c).apply(r);
            }
            else if (_c instanceof C2)
            {
                sum +=  ((C2) _c).apply(r);            
            }
            else if (_c instanceof C3)
            {
                sum +=  ((C3) _c).apply(r);            
            }
            else
            {
                sum += ((C4) _c).apply(r);            
            }

        }
        return sum;
    }
    
    static interface C { public int apply(int x); }
    static class C1 implements C { @Override
    public int apply(int x){ return x * x; }};
    static class C2 implements C { @Override
    public int apply(int x){ return x * x; }};
    static class C3 implements C { @Override
    public int apply(int x){ return x * x; }};
    static class C4 implements C { @Override
    public int apply(int x){ return x * x; }};
}