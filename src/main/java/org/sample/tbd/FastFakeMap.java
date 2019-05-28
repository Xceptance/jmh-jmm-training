package org.sample.tbd;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FastFakeMap<K, V>
{
    private K[] keys = (K[]) new Object[16];
    private V[] values = (V[]) new Object[16];
    private int[] hashCodes = new int[16];
    
    private int size = 0;
    
    public FastFakeMap()
    {
        // no presizing, sorry
    }
    
    public V put(final K key, final V value)
    {
        int pos = findPos(key);
        
        V oldValue = null;
        
        if (pos < 0)
        {
            if (size + 1 > hashCodes.length)
            {
                // grow nicely
                int newSize = hashCodes.length + 16;

                K[] newKeys = (K[]) new Object[newSize];
                V[] newValues = (V[]) new Object[newSize];
                int[] newHashCodes = new int[newSize];
            
                // set the new pos
                pos = -pos - 1;
    
                copyAndInsertPos(keys, newKeys, pos);
                copyAndInsertPos(values, newValues, pos);
                copyAndInsertPos(hashCodes, newHashCodes, pos);
            
                keys = newKeys;
                values = newValues;
                hashCodes = newHashCodes;
            }
            else
            {
                // set the new pos
                pos = -pos - 1;
    
                copyAndInsertPos(keys, keys, pos);
                copyAndInsertPos(values, values, pos);
                copyAndInsertPos(hashCodes, hashCodes, pos);
            }

            size++;
        }
        else
        {
            oldValue = values[pos];
        }

        hashCodes[pos] = key.hashCode();
        keys[pos] = key;
        values[pos] = value;
        
        return oldValue;
    }

    private void copyAndInsertPos(Object[] src, Object[] target, int posToAdd)
    {
        int offset = 0;
        
        for (int i = 0; i < size; i++)
        {
            if (posToAdd == i)
            {
                offset = 1;
            }
            else
            {
                target[i + offset] = src[i];
            }
        }
        
        // alternative for later Arrays.copyOfRange(a, 0, a.length, Integer[].class);
    } 
    
    private void copyAndInsertPos(int[] src, int[] target, int posToAdd)
    {
        int offset = 0;
        
        for (int i = 0; i < size; i++)
        {
            if (posToAdd == i)
            {
                offset = 1;
            }
            else
            {
                target[i + offset] = src[i];
            }
        }
        
        // alternative for later Arrays.copyOfRange(a, 0, a.length, Integer[].class);
    }
    
    public boolean containsKey(final K key)
    {
        return findPos(key) >= 0;
    }
    
    public V remove(final K key)
    {
        int pos = findPos(key);
        
        if (pos < 0)
        {
            // nothing
            return null;
        }
        else
        {
            final V oldValue = values[pos];
            
            copyButPos(keys, keys, pos);
            copyButPos(values, values, pos);
            copyButPos(hashCodes, hashCodes, pos);
            
            size--;
            
            return oldValue;
        }
    }

    private void copyButPos(Object[] src, Object[] target, int posToRemove)
    {
        int offset = 0;
        
        for (int i = 0; i < src.length; i++)
        {
            if (posToRemove == i)
            {
                offset = 1;
            }
            else
            {
                target[i - offset] = src[i];
            }
        }
        
        // alternative for later Arrays.copyOfRange(a, 0, a.length, Integer[].class);
    }
    
    private void copyButPos(int[] src, int[] target, int posToRemove)
    {
        int offset = 0;
        
        for (int i = 0; i < src.length; i++)
        {
            if (posToRemove == i)
            {
                offset = 1;
            }
            else
            {
                target[i - offset] = src[i];
            }
        }
        
        // alternative for later Arrays.copyOfRange(a, 0, a.length, Integer[].class);
    }
    
    public V get(final K key)
    {
        int pos = findPos(key);
        
        if (pos < 0)
        {
            // nothing
            return null;
        }
        else
        {
            return values[pos];
        }
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public int size()
    {
        return size;
    }
    
    public List<K> keys()
    {
        return isEmpty() ? Collections.emptyList() : Arrays.asList(Arrays.copyOfRange(keys, 0, size));
    }

    public List<V> values()
    {
        return isEmpty() ? Collections.emptyList() : Arrays.asList(Arrays.copyOfRange(values, 0, size));
    }
    
    public void clear()
    {
        keys = (K[]) new Object[16];
        values = (V[]) new Object[16];
        hashCodes = new int[16];
        
        size = 0;
    }

    private int findPos(final K key)
    {
        return findPosBinarySearch(key, size);
    }
    
    private int findPosLinear(final K key, int from, int to)
    {
        int hashCode = key.hashCode();
        
        for (int i = from; i < to; i++)
        {
            final int currentHashCode = hashCodes[i];
            
            // is the hashcode the same?
            if (currentHashCode == hashCode)
            {
                // compare with equals
                if (keys[i].equals(key))
                {
                    return i;
                }
            }
            
            if (currentHashCode > hashCode)
            {
                // already too far?
                return -i - 1;
            }
        }
        
        return -size - 1;
    }

    public int findPosBinarySearch(final K key, int to)
    {
        // if small, direcly search linear to avoid cache
        // misses when jumping forward
        if (to < 10)
        {
            return findPosLinear(key, 0, to);
        }
        
        int hashCode = key.hashCode();
        
        int low = 0, high = to - 1;
        
        while (low <= high)
        {
            int mid = (low + high) >>> 1;
        
            int midVal = hashCodes[mid];
        
            if (midVal < hashCode)
                low = mid + 1;
            else if (mid > 0 && hashCodes[mid - 1] >= hashCode) // we already know
                // midval>=key here
                high = mid - 1;
            else if (midVal == hashCode)
            {
                // found the 1st key, no start linear from here
                return findPosLinear(key, mid, to);
            }
            else
                return -mid - 1; // found insertion point
        }
        
        return -size - 1 ; // insertion point after everything
    }
    
    @Override
    public String toString()
    {
        return String.format("FastFakeMap [keys=%s, hashCodes=%s, size=%s]", Arrays.toString(keys),
                        Arrays.toString(hashCodes), size);
    }
}
