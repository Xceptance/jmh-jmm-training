package org.misc;

import java.util.Random;

/**
 * Ultra-fast pseudo random generator that is not synchronized!
 * @author rschwietzke
 *
 */
public class FastRandom extends Random
{
    private static final long serialVersionUID = 1L;
    private long seed;

    public FastRandom()
    {
        this.seed = System.currentTimeMillis();
    }
    
    public FastRandom(long seed)
    {
        this.seed = seed;
    }
    
    protected int next(int nbits) 
    {
        // N.B. Not thread-safe!
        long x = this.seed;
        x ^= (x << 21);
        x ^= (x >>> 35);
        x ^= (x << 4);
        this.seed = x;
        
        x &= ((1L << nbits) -1);
        
        return (int) x;
    }
}
