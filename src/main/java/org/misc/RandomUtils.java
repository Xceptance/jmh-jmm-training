package org.misc;

public class RandomUtils
{
    private final static String CHARS = "abcdefghijklmnopqrstuvwxyz";
    
    public static String randomString(FastRandom random, final int length)
    {
        final StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++)
        {
            int pos = random.nextInt(CHARS.length());
            sb.append(CHARS.charAt(pos));
        }
        
        return sb.toString();
    }

}
