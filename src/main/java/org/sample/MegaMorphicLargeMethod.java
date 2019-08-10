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
public class MegaMorphicLargeMethod
{
    private Random random = new Random(1L);

    @Benchmark
    public String _1_mono()
    {
        C c = null;
        int r = random.nextInt(4);
        
        if (r == 0) { c = new C1(); } 
        else if (r == 1) { c = new C1(); } 
        else if (r == 2) { c = new C1(); }
        else if (r == 3) { c = new C1(); }
         
        return c.apply(r);
    }

    @Benchmark
    public String _2_bi()
    {
        C c = null;
        int r = random.nextInt(4);
        
        if (r == 0) { c = new C1(); } 
        else if (r == 1) { c = new C2(); } 
        else if (r == 2) { c = new C1(); }
        else if (r == 3) { c = new C2(); }
         
        return c.apply(r);
    }

    @Benchmark
    public String _4_mega()
    {
        C c = null;
        int r = random.nextInt(4);
        
        if (r == 0) { c = new C1(); } 
        else if (r == 1) { c = new C2(); } 
        else if (r == 2) { c = new C3(); }
        else if (r == 3) { c = new C4(); }
         
        return c.apply(r);
    }

    @Benchmark
    public String _5_manualDispatch()
    {
        C c = null;
        int r = random.nextInt(4);
        
        if (r == 0) { c = new C1(); } 
        else if (r == 1) { c = new C2(); } 
        else if (r == 2) { c = new C3(); }
        else if (r == 3) { c = new C4(); }
         
        if (c instanceof C1)
        {
            return ((C1) c).apply(r);
        }
        else if (c instanceof C2)
        {
            return ((C2) c).apply(r);            
        }
        else if (c instanceof C3)
        {
            return ((C3) c).apply(r);            
        }
        else
        {
            return ((C4) c).apply(r);            
        }
    }
    

    static interface C 
    { 
        public String apply(int x);
    }
    
    static class C1 implements C 
    {
        long a = System.currentTimeMillis();
        
        @Override
        public String apply(int x)
        { 
            long b = a * x;
            
            final String xs1 = String.valueOf(b).toLowerCase().replace("[1-4]", "0"); 
            final String xs2 = String.valueOf(b).toUpperCase().replace("[5-8]ab", "1");
            
            return xs1 + xs2;
        }        
    }
    static class C2 implements C 
    {
        long a = System.currentTimeMillis();
        
        @Override
        public String apply(int x)
        { 
            long b = a * x;
            
            final String xs1 = String.valueOf(b).toLowerCase().replace("[1-4]", "0"); 
            final String xs2 = String.valueOf(b).toUpperCase().replace("[5-8]ab", "1");
            
            return xs1 + xs2;
        }        
    }
    static class C3 implements C 
    {
        long a = System.currentTimeMillis();
        
        @Override
        public String apply(int x)
        { 
            long b = a * x;
            
            final String xs1 = String.valueOf(b).toLowerCase().replace("[1-4]", "0"); 
            final String xs2 = String.valueOf(b).toUpperCase().replace("[5-8]ab", "1");
            
            return xs1 + xs2;
        }        
    }
    static class C4 implements C 
    {
        long a = System.currentTimeMillis();
        
        @Override
        public String apply(int x)
        { 
            long b = a * x;
            
            final String xs1 = String.valueOf(b).toLowerCase().replace("[1-4]", "0"); 
            final String xs2 = String.valueOf(b).toUpperCase().replace("[5-8]ab", "1");
            
            return xs1 + xs2;
        }        
    }
}