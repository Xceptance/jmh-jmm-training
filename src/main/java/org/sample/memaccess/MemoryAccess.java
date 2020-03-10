package org.sample.memaccess;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;
import org.misc.LightStringTokenizer;
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
@Warmup(iterations = 3, time = 3, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
public class MemoryAccess
{
    final int SIZE = 10;
    String[] src;
    final Map<String, Integer> cache = new ConcurrentHashMap<>(211);
    
//    @Param({"true", "false"})
    boolean shuffle = false;

//    @Param({"1", "10"})
    int factor = 1;
    
    @Setup
    public void setup()
    {
        final List<String> list = new ArrayList<>(SIZE);
        src = new String[SIZE];

        // we generate 10x more data than we will use and later we shuffle it, to avoid
        // perfect cache alignment
        final Random r = new Random(1L);
        for (int i = 0; i < SIZE * factor; i++)
        {
            list.add(MessageFormat.format("{0}.{1}.{2}.{3}", r.nextInt(256), r.nextInt(256), r.nextInt(256), r.nextInt(256))); 
        }
        
        if (shuffle)
        {
            Collections.shuffle(list);
        }
        src = list.stream().limit(SIZE).collect(Collectors.toList()).toArray(src);
    }
    
    private static int OCTET1 = 256 * 256 * 256;
    private static int OCTET2 = 256 * 256;
    private static int OCTET3 = 256;
    
    private int convertIPV4_01_RegexNoLoop(String ipv4)
    {
        final String[] groups = ipv4.split("\\.");
        
        int sum = OCTET1 * Integer.valueOf(groups[0]);
        sum += OCTET2 * Integer.valueOf(groups[1]);
        sum += OCTET3 * Integer.valueOf(groups[2]);
        sum += Integer.valueOf(groups[3]);
        
        return sum;
    }
    
    private int convertIPV4_02_StringTokenizer(String ipv4)
    {
        final StringTokenizer groups = new StringTokenizer(ipv4, ".");
        int[] octets = new int[4];

        int count = 0;
        while (groups.hasMoreTokens())
        {
            final String token = groups.nextToken();
            octets[count] = Integer.valueOf(token);
            count++;
        }
        
        int sum = OCTET1 * octets[0];
        sum += OCTET2 * octets[1];
        sum += OCTET3 * octets[2];
        sum += octets[3];
        
        return sum;
    }

    private int convertIPV4_03_LightTokenizer(String ipv4)
    {
        final LightStringTokenizer groups = new LightStringTokenizer(ipv4, ".");
        int[] octets = new int[4];
        
        int count = 0;
        while (groups.hasMoreTokens())
        {
            final String token = groups.nextToken();
            octets[count] = Integer.valueOf(token);
            count++;
        }
        
        int sum = OCTET1 * octets[0];
        sum += OCTET2 * octets[1];
        sum += OCTET3 * octets[2];
        sum += octets[3];
        
        return sum;
    }
//    
//    private int convertIPV4_02a_ParseInt(String ipv4)
//    {
//        final LightStringTokenizer groups = new LightStringTokenizer(ipv4, ".");
//        int[] octets = new int[4];
//        
//        int count = 0;
//        while (groups.hasMoreTokens())
//        {
//            final String token = groups.nextToken();
//            octets[count] = ParseInteger.parse(token);
//            count++;
//        }
//        
//        int sum = OCTET1 * octets[0];
//        sum += OCTET2 * octets[1];
//        sum += OCTET3 * octets[2];
//        sum += octets[3];
//        
//        return sum;
//    }    
//    
//    private int convertIPV4_03_BitShift(String ipv4)
//    {
//        final LightStringTokenizer groups = new LightStringTokenizer(ipv4, ".");
//        int[] octets = new int[4];
//        
//        int count = 0;
//        while (groups.hasMoreTokens())
//        {
//            final String token = groups.nextToken();
//            octets[count] = ParseInteger.parse(token);
//            count++;
//        }
//        
//        int sum = octets[0] << 8;
//        sum = (octets[1] + sum) << 8;
//        sum = (octets[2] + sum) << 8;
//        sum = octets[3] + sum;
//        
//        return sum;
//    }
//
//    private int convertIPV4_04a_NoLoop(String ipv4)
//    {
//        final LightStringTokenizer groups = new LightStringTokenizer(ipv4, ".");
//        
//        int[] octets = new int[4];
//        octets[0] = ParseInteger.parse(groups.nextToken());
//        octets[1] = ParseInteger.parse(groups.nextToken());
//        octets[2] = ParseInteger.parse(groups.nextToken());
//        octets[3] = ParseInteger.parse(groups.nextToken());
//        
//        int sum = octets[0] << 8;
//        sum = (octets[1] + sum) << 8;
//        sum = (octets[2] + sum) << 8;
//        sum = octets[3] + sum;
//        
//        return sum;
//    }
//    
//    private int convertIPV4_04b_NoLoopNoArray(String ipv4)
//    {
//        final LightStringTokenizer groups = new LightStringTokenizer(ipv4, ".");
//        
//        int sum = ParseInteger.parse(groups.nextToken()) << 8;
//        sum = (sum + ParseInteger.parse(groups.nextToken())) << 8;
//        sum = (sum + ParseInteger.parse(groups.nextToken())) << 8;
//        sum =  sum + ParseInteger.parse(groups.nextToken());
//        
//        return sum;        
//    }
//
//    private int convertIPV4_05a_ParallelBitshift(String ipv4)
//    {
//        final StringTokenizer groups = new StringTokenizer(ipv4, ".");
//        
//        int s1 = ParseInteger.parse(groups.nextToken()) << 24;
//        int s2 = ParseInteger.parse(groups.nextToken()) << 16;
//        int s3 = ParseInteger.parse(groups.nextToken()) << 8;
//        int s4 = ParseInteger.parse(groups.nextToken());
//        
//        return s1 + s2 + s3 + s4;        
//    }
//
//    private int convertIPV4_05b_ParallelBitshiftSplit(String ipv4)
//    {
//        final StringTokenizer groups = new StringTokenizer(ipv4, ".");
//        
//        int s1 = ParseInteger.parse(groups.nextToken());
//        int s2 = ParseInteger.parse(groups.nextToken());
//        int s3 = ParseInteger.parse(groups.nextToken());
//        int s4 = ParseInteger.parse(groups.nextToken());
//
//        s1 = s1 << 24;
//        s2 = s2 << 16;
//        s3 = s3 << 8;
//        
//        return s1 + s2 + s3 + s4;        
//    }
//
//    private int convertIPV4_05b_ParallelBitshiftSplitReordered(String ipv4)
//    {
//        final StringTokenizer groups = new StringTokenizer(ipv4, ".");
//        
//        int s1 = ParseInteger.parse(groups.nextToken());
//        s1 = s1 << 24;
//        int s2 = ParseInteger.parse(groups.nextToken());
//        s2 = s2 << 16;
//        int s3 = ParseInteger.parse(groups.nextToken());
//        s3 = s3 << 8;
//
//        int s4 = ParseInteger.parse(groups.nextToken());
//        int sum = s1 + s2;
//        sum += s3;
//        sum += s4;
//
//        return sum;        
//    }
//
//    
//    private int convertIPV4_05c_ParallelMul(String ipv4)
//    {
//        final StringTokenizer groups = new StringTokenizer(ipv4, ".");
//        
//        int sum1 = ParseInteger.parse(groups.nextToken()) * OCTET1;
//        int sum2 = ParseInteger.parse(groups.nextToken()) * OCTET2;
//        int sum3 = ParseInteger.parse(groups.nextToken()) * OCTET3;
//        int sum4 = ParseInteger.parse(groups.nextToken());
//        
//        return sum1 + sum2 + sum3 + sum4;        
//    }
//
//    private int convertIPV4_05d_ParallelMulSplit(String ipv4)
//    {
//        final StringTokenizer groups = new StringTokenizer(ipv4, ".");
//
//        int sum1 = ParseInteger.parse(groups.nextToken());
//        int sum2 = ParseInteger.parse(groups.nextToken());
//        int sum3 = ParseInteger.parse(groups.nextToken());
//        int sum4 = ParseInteger.parse(groups.nextToken());
//        
//        sum1 *= OCTET1;
//        sum2 *= OCTET2;
//        sum3 *= OCTET3;
//        
//        return sum1 + sum2 + sum3 + sum4;        
//    }
//
//    private int convertIPV4Cached(String ipv4)
//    {
//        Integer result = cache.get(ipv4);
//        
//        if (result == null)
//        {
//            result = convertIPV4_00(ipv4);
//            cache.put(ipv4, result);
//        }
//        
//        return result;
//    }
    
    // @Benchmark
    public int b_00_RegExpNoLoop()
    {
        int sum = 0;
        
        for (int i = 0; i < SIZE; i++)
        {
            sum += convertIPV4_01_RegexNoLoop(src[i]);
        }

        return sum;
    }

    @Benchmark
    public int cpu_02_StringTokenizer()
    {
        int sum = 0;

        for (int i = 0; i < SIZE; i++)
        {
            sum += convertIPV4_02_StringTokenizer(src[i]);
        }

        return sum;
    }

    @Benchmark
    public int cpu_03_LightTokenizer()
    {
        int sum = 0;
        
        for (int i = 0; i < SIZE; i++)
        {
            sum += convertIPV4_03_LightTokenizer(src[i]);
        }

        return sum;
    }
    
    //
//    @Benchmark
//    public int cpu_02a_ParseInt()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4_02a_ParseInt(src[i]);
//        }
//
//        return sum;
//    }
//

//    
//    @Benchmark
//    public int cpu_03_Bitshift()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4_03_BitShift(src[i]);
//        }
//
//        return sum;
//    }
//
//    @Benchmark
//    public int cpu_04a_NoLoop()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4_04a_NoLoop(src[i]);
//        }
//
//        return sum;
//    }
//
//    @Benchmark
//    public int cpu_04b_NoLoopCompact()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4_04b_NoLoopNoArray(src[i]);
//        }
//
//        return sum;
//    }
//    
//    @Benchmark
//    public int cpu_05a_ParallelBitshift()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4_05a_ParallelBitshift(src[i]);
//        }
//
//        return sum;
//    }
//
//    @Benchmark
//    public int cpu_05b_ParallelBitshiftSplit()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4_05b_ParallelBitshiftSplit(src[i]);
//        }
//
//        return sum;
//    }
//
//    @Benchmark
//    public int cpu_05b_ParallelBitshiftSplitReordered()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4_05b_ParallelBitshiftSplitReordered(src[i]);
//        }
//
//        return sum;
//    }
//    
//    @Benchmark
//    public int cpu_05c_ParallelMul()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4_05c_ParallelMul(src[i]);
//        }
//
//        return sum;
//    }
//
//    @Benchmark
//    public int cpu_05d_ParallelMulSplit()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4_05d_ParallelMulSplit(src[i]);
//        }
//
//        return sum;
//    }
//    
//    @Benchmark
//    public int cached()
//    {
//        int sum = 0;
//        
//        for (int i = 0; i < SIZE; i++)
//        {
//            sum += convertIPV4Cached(src[i]);
//        }
//
//        return sum;
//    }
    
    @Test
    public void test()
    {
        Assert.assertEquals(783771400, convertIPV4_01_RegexNoLoop("46.183.103.8"));
        
//        Assert.assertEquals(783771400, convertIPV4Cached("46.183.103.8"));
//        Assert.assertEquals(783771400, convertIPV4Cached("46.183.103.8"));
//        
        Assert.assertEquals(783771400, convertIPV4_02_StringTokenizer("46.183.103.8"));
//        Assert.assertEquals(783771400, convertIPV4_02a_ParseInt("46.183.103.8"));
//        Assert.assertEquals(783771400, convertIPV4_02b_LightTokenizer("46.183.103.8"));
//
//        Assert.assertEquals(783771400, convertIPV4_03_BitShift("46.183.103.8"));
//        Assert.assertEquals(783771400, convertIPV4_04a_NoLoop("46.183.103.8"));
//        Assert.assertEquals(783771400, convertIPV4_04b_NoLoopNoArray("46.183.103.8"));
//        
//        Assert.assertEquals(783771400, convertIPV4_05a_ParallelBitshift("46.183.103.8"));
//        Assert.assertEquals(783771400, convertIPV4_05b_ParallelBitshiftSplit("46.183.103.8"));
//        Assert.assertEquals(783771400, convertIPV4_05b_ParallelBitshiftSplitReordered("46.183.103.8"));
//        Assert.assertEquals(783771400, convertIPV4_05c_ParallelMul("46.183.103.8"));
//        Assert.assertEquals(783771400, convertIPV4_05d_ParallelMulSplit("46.183.103.8"));

    }
}
