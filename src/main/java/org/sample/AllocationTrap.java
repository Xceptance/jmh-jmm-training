package org.sample;

public class AllocationTrap
{
    private final static int OUTER = 1250;
    private final static int SIZE = 1000;
    private static Object o = null;
    
    public static void main(String[] args)
    {
        final long [] timers = new long[OUTER];
        Object trap = null;
        Object o = null;
        
        for (int i = 0; i < OUTER; i++)
        {
            final Timer t = Timer.startTimer();
            
            for (int j = 0; j < SIZE; j++)
            {
                // burn time and train that null is normal
                o = new Object();
                
                if (trap != null)
                {
                    System.out.println("Got you." + o);
                    trap = null;
                }
            }
                
            // Important: 350 and 400 creates different results... fun...!
            if (i == 500)
            {
                trap = new Object();
            }

            timers[i] = t.stop().runtimeNanos();
        }        

        for (int i = 0; i < OUTER; i++ )
        {
            System.out.printf("%4d, %4d\n", i, timers[i]);
        }
        
    }

}
