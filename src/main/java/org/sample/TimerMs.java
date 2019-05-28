package org.sample;

import java.text.MessageFormat;

public class TimerMs
{
    private final long ms;
    private final long ns;
    
    private long stoppedMs; 
    private long stoppedNs;
                    
    private boolean stopped = false;
    
    private TimerMs()
    {
        ms = System.currentTimeMillis();
        ns = System.nanoTime();
    }
    
    public static TimerMs startTimer()
    {
        return new TimerMs();
    }
    
    public TimerMs stop()
    {
        stoppedMs = System.currentTimeMillis();
        stoppedNs = System.nanoTime();
        
        if (stopped)
        {
            throw new IllegalArgumentException("Stopped called twice");
        }
        
        stopped = true;
        
        return this;
    }
    
    public long runtimeMillis()
    {
        return stoppedMs - ms;
    }
    
    public long runtimeNanos()
    {
        return stoppedNs - ns;
    }

    public long stoppedNanos()
    {
        return stoppedNs;
    }

    public long stoppedMilis()
    {
        return stoppedMs;
    }
    
    public void printNanos(final String prefix)
    {
        System.out.println(MessageFormat.format(prefix + "{0, number, #,###}ns", runtimeNanos()));
    }
    
    public void printMillis(final String prefix)
    {
        System.out.println(MessageFormat.format(prefix + "{0, number, #,###.##}ms", runtimeMillis()));
    }
}
