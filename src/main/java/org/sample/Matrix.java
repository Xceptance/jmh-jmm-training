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
import org.openjdk.jmh.annotations.Warmup;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 2, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class Matrix
{
    @Param({"1", "10", "100", "1000"})
    int SIZE;
    int[][] src;

    @Setup
    public void setup()
    {
        src = new int[SIZE][SIZE];

        final Random r = new Random(1L);
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                src[i][j] = r.nextInt(10);
            }
        }
    }
    
    @Benchmark
    public int horizontal()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                sum += src[i][j];
            }
        }
        return sum;
    }

    @Benchmark
    public int vertical()
    {
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
            {
                sum += src[j][i];
            }
        }
        return sum;
    }
    
    @Benchmark
    public int horizontalBackwards()
    {
        int sum = 0;
        for (int i = SIZE - 1; i >=0; i--)
        {
            for (int j = SIZE - 1; j >= 0; j--)
            {
                sum += src[i][j];
            }
        }
        return sum;
    }
}
