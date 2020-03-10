package org.misc;

/**
 * This is a small helper class for parsing strings and converting them into longs. This implementation is optimized for
 * speed not for functionality. It is only able to parse plain numbers with base 10, e.g. 100828171 (see
 * String.parseLong())
 * 
 * @author René Schwietzke
 */
public final class ParseInteger
{
    private static final int DIGITOFFSET = 48;
    private static final int BASE = 10;
    
    /**
     * Parses the string and returns the result as int. Raises a NumberFormatException in case of an non-convertable
     * string. Due to conversion limitations, the content of s might be larger than an int, precision might be
     * inaccurate.
     * 
     * @param s
     *            the string to parse
     * @return the converted string as int
     * @throws java.lang.NumberFormatException
     */
    public static int parse(final String s)
    {
        // determine length
        final int length = s.length();
        
        try
        {
            int value = 0;
            for (int i = 0; i < length; i++)
            {
                final int digit = s.charAt(i);
                
                if (digit >= '0' && digit <= '9')
                {
                    value = value * BASE + (digit - DIGITOFFSET);
                }
                else
                {
                    throw new NumberFormatException();
                }
            }

            return value;
        }
        catch (final Exception e)
        {
            return Integer.parseInt(s);
        }
    }
}
