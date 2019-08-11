package org.sample;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

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
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Fork(1)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
public class Lambda02
{
    private final static int COUNT = 100;
    private final Integer[] array = new Integer[COUNT];
    
    private final static Predicate<Integer> evenPredicate = i -> i % 2 == 0;
    
    @Setup
    public void setup()
    {
        for (int i = 0; i < COUNT; i++)
        {
            array[i] = new Integer(i);
        }
    }
    
    interface PseudoLambda<T>
    {
        boolean test(T t);
    }
    
    public int forLambda(final Predicate<Integer> p)
    {
        int sum = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (p.test(array[i]))
            {
                sum++;
            }
        }
        return sum;
    }

    public int forAnonymous(final PseudoLambda<Integer> p)
    {
        int sum = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (p.test(array[i]))
            {
                sum++;
            }
        }
        return sum;
    }

    public int fix()
    {
        int sum = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i] % 2 == 0)
            {
                sum++;
            }
        }
        return sum;
    }
    
    @Benchmark
    public void forLambdaPredicatePredefined(Blackhole bh)
    {
        bh.consume(forLambda(evenPredicate));
    }

    @Benchmark
    public void simple(Blackhole bh)
    {
        bh.consume(fix());
    }
    
    @Benchmark
    public void forLambdaPredicate(Blackhole bh)
    {
        bh.consume(forLambda(i -> i % 2 == 0));
    }

    @Benchmark
    public void forAnonymousClass(Blackhole bh)
    {
        bh.consume(forAnonymous(new PseudoLambda<Integer>(){
            @Override
            public boolean test(Integer i)
            {
                return i % 2 == 0;
            }}));
    }
}
