package org.sample;

import java.text.MessageFormat;

public class Timer
{
    private long ns;
    private long stoppedNs;
    
    private Timer()
    {
        ns = System.nanoTime();
    }
    
    public static Timer startTimer()
    {
        return new Timer();
    }
    
    public Timer stop()
    {
        stoppedNs = System.nanoTime();
        
        return this;
    }
    
    public Timer resetAndStart()
    {
        ns = System.nanoTime();
        return this;
    }
    
    public long runtimeNanos()
    {
        return stoppedNs - ns;
    }

    public long stoppedNanos()
    {
        return stoppedNs;
    }
    
    public void printNanos(final String prefix)
    {
        System.out.println(MessageFormat.format(prefix + "{0, number, #,###}ns", runtimeNanos()));
    }
    
}
