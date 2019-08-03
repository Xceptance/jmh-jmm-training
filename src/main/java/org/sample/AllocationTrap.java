package org.sample;

public class AllocationTrap
{
    private final static int SIZE = 1000;
    private static Object o = null;
    
    public static void main(String[] args)
    {
        Object trap = null;
        Object o = null;
        
        for (int i = 0; i < 1250; i++)
        {
            final Timer t = Timer.startTimer();
            
            for (int j = 0; j < SIZE; j++)
            {
                // burn time and train that null is normal
                o = new Object();
            }
                
            if (i == 400)
            {
                trap = new Object();
            }

            System.out.printf("%4d, %4d\n", i, t.stop().runtimeNanos());
        }        
    }

}
