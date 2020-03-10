package org.sample.memaccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
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
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;



@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 3, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class MemoryAccessNoTokenizer
{
    final int SIZE = 2000;
    List<String[]> src = new ArrayList<>();

    @Setup
    public void setup()
    {
        // we generate 10x more data than we will use and later we shuffle it, to avoid
        // perfect cache alignment
        final Random r = new Random(1L);
        for (int i = 0; i < SIZE * 10; i++)
        {
            final String[] ipv4 = new String[4];
            ipv4[0] = String.valueOf(r.nextInt(256));
            ipv4[1] = String.valueOf(r.nextInt(256));
            ipv4[2] = String.valueOf(r.nextInt(256));
            ipv4[3] = String.valueOf(r.nextInt(256));
            
            src.add(ipv4);
        }
        
        Collections.shuffle(src);
        src = src.subList(0, SIZE);
    }
    
    private static int OCTET1 = 256 * 256 * 256;
    private static int OCTET2 = 256 * 256;
    private static int OCTET3 = 256;
    
    private int convertIPV4_00a_IntegerNoLoop(String[] ipv4)
    {
        int sum = OCTET1 * Integer.valueOf(ipv4[0]);
        sum += OCTET2 * Integer.valueOf(ipv4[1]);
        sum += OCTET3 * Integer.valueOf(ipv4[2]);
        sum += Integer.valueOf(ipv4[3]);
        
        return sum;
    }

    private int convertIPV4_00b_ParseIntNoLoop(String[] ipv4)
    {
        int sum = OCTET1 * ParseInteger.parse(ipv4[0]);
        sum += OCTET2 * ParseInteger.parse(ipv4[1]);
        sum += OCTET3 * ParseInteger.parse(ipv4[2]);
        sum += ParseInteger.parse(ipv4[3]);
        
        return sum;
    }
    
    private int convertIPV4_01a_ParseIntLoopMul(String[] ipv4)
    {
        int[] octets = new int[4];
        
        for (int i = 0; i < ipv4.length; i++)
        {
            octets[i] = ParseInteger.parse(ipv4[i]);
        }
        
        int sum = OCTET1 * octets[0];
        sum += OCTET2 * octets[1];
        sum += OCTET3 * octets[2];
        sum += octets[3];
        
        return sum;
    }

    private int convertIPV4_01b_ParseIntLoopBitShift(String[] ipv4)
    {
        int[] octets = new int[4];
        
        for (int i = 0; i < ipv4.length; i++)
        {
            octets[i] = ParseInteger.parse(ipv4[i]);
        }
        
        int sum = octets[0] << 8;
        sum = (octets[1] + sum) << 8;
        sum = (octets[2] + sum) << 8;
        sum = octets[3] + sum;
       
        return sum;
    }

    private int convertIPV4_03a_BitShiftNoLoop(String[] ipv4)
    {
        int[] octets = new int[4];
        
        octets[0] = ParseInteger.parse(ipv4[0]);
        octets[1] = ParseInteger.parse(ipv4[1]);
        octets[2] = ParseInteger.parse(ipv4[2]);
        octets[3] = ParseInteger.parse(ipv4[3]);
        
        int sum = octets[0] << 8;
        sum = (octets[1] + sum) << 8;
        sum = (octets[2] + sum) << 8;
        sum = octets[3] + sum;
        
        return sum;        
    }

    private int convertIPV4_03b_BitShiftNoLoopCompact(String[] ipv4)
    {
        int sum = ParseInteger.parse(ipv4[0]) << 8;
        sum = (ParseInteger.parse(ipv4[1]) + sum) << 8;
        sum = (ParseInteger.parse(ipv4[2]) + sum) << 8;
        sum = ParseInteger.parse(ipv4[3]) + sum;
        
        return sum;        
    }
    
    @Benchmark
    public int cpu_00a_IntegerNoLoop()
    {
        int sum = 0;
        
        for (String[] ipv4 : src)
        {
            sum += convertIPV4_00a_IntegerNoLoop(ipv4);
        }

        return sum;
    }

    @Benchmark
    public int cpu_00b_ParseIntNoLoop()
    {
        int sum = 0;
        
        for (String[] ipv4 : src)
        {
            sum += convertIPV4_00b_ParseIntNoLoop(ipv4);
        }

        return sum;
    }
    
    @Benchmark
    public int cpu_01a_ParseIntLoopMul()
    {
        int sum = 0;
        
        for (String[] ipv4 : src)
        {
            sum += convertIPV4_01a_ParseIntLoopMul(ipv4);
        }

        return sum;
    }
    
    @Benchmark
    public int cpu_01b_ParseIntLoopBitShift()
    {
        int sum = 0;
        
        for (String[] ipv4 : src)
        {
            sum += convertIPV4_01b_ParseIntLoopBitShift(ipv4);
        }

        return sum;
    }    
    
    @Benchmark
    public int cpu_03a_BitShiftNoLoop()
    {
        int sum = 0;
        
        for (String[] ipv4 : src)
        {
            sum += convertIPV4_03a_BitShiftNoLoop(ipv4);
        }

        return sum;
    }     
    
    @Benchmark
    public int cpu_03b_BitShiftNoLoopCompact()
    {
        int sum = 0;
        
        for (String[] ipv4 : src)
        {
            sum += convertIPV4_03b_BitShiftNoLoopCompact(ipv4);
        }

        return sum;
    }      
    
    @Test
    public void test()
    {
        String[] ipv4 = new String[] {"46", "183", "103", "8"};
        Assert.assertEquals(783771400, convertIPV4_00a_IntegerNoLoop(ipv4));
        Assert.assertEquals(783771400, convertIPV4_00b_ParseIntNoLoop(ipv4));
        Assert.assertEquals(783771400, convertIPV4_01a_ParseIntLoopMul(ipv4));
        Assert.assertEquals(783771400, convertIPV4_01b_ParseIntLoopBitShift(ipv4));
        Assert.assertEquals(783771400, convertIPV4_03a_BitShiftNoLoop(ipv4));
        Assert.assertEquals(783771400, convertIPV4_03b_BitShiftNoLoopCompact(ipv4));
    }        
}
