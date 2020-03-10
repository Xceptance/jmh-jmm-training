package org.sample.memaccess;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.misc.ParseInteger;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;



@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class JDK11LoopIssue
{
    private int convertIPV4_03_BitShift(final String[] ipv4)
    {
        int[] octets = new int[4];
        
        int count = 0;
        for (String token : ipv4)
        {
            octets[count] = ParseInteger.parse(token);
            count++;
        }
        
        int sum = octets[0] << 24;
        sum += (octets[1] << 16);
        sum += (octets[2] << 8);
        sum += octets[3];
        
        return sum;
    }

    private int convertIPV4_04a_NoLoop(final String[] ipv4)
    {
        int[] octets = new int[4];
        octets[0] = ParseInteger.parse(ipv4[0]);
        octets[1] = ParseInteger.parse(ipv4[1]);
        octets[2] = ParseInteger.parse(ipv4[2]);
        octets[3] = ParseInteger.parse(ipv4[3]);
        
        int sum = octets[0] << 24;
        sum += (octets[1] << 16);
        sum += (octets[2] << 8);
        sum += octets[3];
        
        return sum;
    }
    
    @Benchmark
    public int cpu_03_Bitshift()
    {
        final String[] ipv4 = new String[] {"101", "99", "255", "13" };
        return convertIPV4_03_BitShift(ipv4);
    }

    @Benchmark
    public int cpu_04a_NoLoop()
    {
        final String[] ipv4 = new String[] {"101", "99", "255", "13" };
        return convertIPV4_04a_NoLoop(ipv4);
    }
    
    @Test
    public void test()
    {
        Assert.assertEquals(783771400, convertIPV4_03_BitShift(new String[] {"46", "183", "103", "8"}));
        Assert.assertEquals(783771400, convertIPV4_04a_NoLoop(new String[] {"46", "183", "103", "8"}));
    }
}
