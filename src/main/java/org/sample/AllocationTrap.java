package org.sample;

import java.text.MessageFormat;

public class AllocationTrap
{
    private final static int SIZE = 1000;
    
    public static void main(String[] args)
    {
        Object trap = null;
        Object o = null;
        
        for (int i = 0; i < 2000; i++)
        {
            final Timer t = Timer.startTimer();
            
            for (int j = 0; j < SIZE; j++)
            {
                // burn time and train that null is normal
                o = new Object();
                
                if (trap != null)
                {
                    System.out.println("Got you.");
                    trap = null;
                }
                
                
            }
                
            if (i == 400)
            {
                trap = new Object();
            }

            System.out.println(
                            MessageFormat.format("{1, number, #} {0, number, #}", t.stop().runtimeNanos(), i));
        }        
    }

}
