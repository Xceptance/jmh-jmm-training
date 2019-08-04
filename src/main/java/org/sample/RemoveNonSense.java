package org.sample;

public class RemoveNonSense
{
    private final static int OUTER = 1000;
    private final static int SIZE = 5;
    
    /**
     * Vary SIZE from 1 to 10000    
     * @param args
     */
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
                o = new Object();
                
                if (trap != null)
                {
                    o = new Object();
                    trap = null;
                }
            }
                
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
