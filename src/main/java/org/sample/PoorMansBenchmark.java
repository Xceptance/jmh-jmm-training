package org.sample;

import java.util.Arrays;

public class PoorMansBenchmark
{   
    private static String append(int i)
    {
        final StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        sb.append("Foo");
        sb.append(i);
        sb.append('c');
        sb.append("lkJAHJ AHSDJHF KJASHF HSAKJFD");
        
        final String s = sb.toString();
        final char[] ch = s.toCharArray();
        Arrays.sort(ch);
        
        return new String(ch);
    }

    public static void main (String[] args)
    {
        int SIZE = 100_000;
        final long[] result = new long[SIZE];
        
        // compile our utilities first
        for (int i = 0; i < SIZE; i++)
        {
            final org.sample.Timer t1 = Timer.startTimer().resetAndStart();
            result[i] = t1.stop().runtimeNanos();
        }
       
        int sum = 0;
        for (int i = 0; i < SIZE; i++)
        {
            Timer t = Timer.startTimer();       
            
            sum += append(i).length();
            
            result[i] = t.stop().runtimeNanos();
        }  
        
        for (int i = 0; i < SIZE; i++)
        {
            if ((i + 1) % (i >= 10 ? 10 : 1) == 0)
            {
                System.out.println(i + 1 + "," + result[i]);
            }
        } 
        
        // pretend you need it but you don't need it
        // just to avoid optimizations
        if (sum < 0)
        {
            System.out.println(sum);
        }
    }
}
