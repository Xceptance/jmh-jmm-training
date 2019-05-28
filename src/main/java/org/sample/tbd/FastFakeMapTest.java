package org.sample.tbd;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class FastFakeMapTest
{
    /**
     * Create a default map that should be empty
     */
    @Test 
    public void createEmptyMap()
    { 
        FastFakeMap<?, ?> map = new FastFakeMap<>();
        
        Assert.assertEquals("Map wasn't empty", 0, map.size());
        Assert.assertTrue("Map was not empty", map.isEmpty());
        
        List<?> entries = map.keys();
        Assert.assertTrue("Found entries in an expected empty map", entries.isEmpty());
    }
    
    /**
     * Basic happy path for a hashmap
     */
    @Test
    public void happyPath()
    {
        FastFakeMap<Integer, Integer> map = new FastFakeMap<>();
        final Integer result = map.put(1, 2); // important
        
        Assert.assertNull(result);
        Assert.assertEquals(1, map.size());
        Assert.assertFalse(map.isEmpty());
        
        final Integer finalResult = map.get(1); // important
        
        Assert.assertEquals(new Integer(2), finalResult);
    }
    
    /**
     * A key already exists with a value
     */
    @Test
    public void keyExists()
    {
        final FastFakeMap<String, String> map = new FastFakeMap<>();
        map.put("key", "value");
        final String oldValue = map.put("key", "newValue");
        
        Assert.assertEquals("value", oldValue);
        Assert.assertEquals(1, map.size());
        
        final String newValue = map.get("key");
        
        Assert.assertEquals("newValue", newValue);
    }
    
    /**
     * Key does not exist
     */
    @Test
    public void keyDoesNotExist()
    {
        final FastFakeMap<String, String> map = new FastFakeMap<>();
        final String value1 = map.get("foo");
        map.put("foo2", "fff");

        final String value2 = map.get("foo");

        Assert.assertNull(value1);
        Assert.assertNull(value2);
    }
    
//    /**
//     * Null key for get
//     */
//    @Test(expected = IllegalArgumentException.class)
//    public void nullKeyGet()
//    {
//        final FastFakeMap<String, String> map = new FastFakeMap<>();
//        map.get(null);
//    }
 
//    /**
//     * Null key for put
//     */
//    @Test
//    public void nullKeyPut()
//    {
//        final FastFakeMap<String, String> map = new FastFakeMap<>();
//        
//        try
//        {
//            map.put(null, "kdhgd");
//            Assert.fail();
//        }
//        catch(IllegalArgumentException e)
//        {
//        }
//    }
    
    /**
     * Verify that we can store objects with same hashCode
     * but being different.
     */
    @Test
    public void identicalHashCodeButDifferentKey()
    {
        final FastFakeMap<HashCheater, String> map = new FastFakeMap<>();
        final HashCheater c1 = new HashCheater(1, "a");
        final HashCheater c2 = new HashCheater(1, "b");
        
        map.put(c1, "c1");
        map.put(c2, "c2");
        
        Assert.assertEquals("c1", map.get(c1));
        Assert.assertEquals("c2", map.get(c2));
        
        Assert.assertEquals(2, map.size());
    }

    /**
     *  "put" on larger single hashcode list with existing key
     */
    @Test
    public void largeListOfSameHashCodeButDifferentKey()
    {
        final FastFakeMap<HashCheater, String> map = new FastFakeMap<>();
        
        int count = 11;
        final List<HashCheater> list = new ArrayList<>();
        
        // put
        for (int i = 0; i < count; i++)
        {
            final HashCheater c = new HashCheater(42, "c" + i);
            list.add(c);
            map.put(c, "c" + i);
        }
        Assert.assertEquals(count, map.size());

        // order of add
        for (HashCheater c : list)
        {
            Assert.assertEquals(c.value, map.get(c));    
        }
        
        // reversed order
        Collections.reverse(list);
        for (HashCheater c : list)
        {
            Assert.assertEquals(c.value, map.get(c));    
        }

        // random order
        Collections.shuffle(list);
        for (HashCheater c : list)
        {
            Assert.assertEquals(c.value, map.get(c));    
        }

        Assert.assertEquals(count, map.size());
    }
    
    /**
     * Get for value with entry at this position but not matching key
     */
    @Test
    public void positionAkaHashCodeExistsButNotKey()
    {
        final FastFakeMap<HashCheater, String> map = new FastFakeMap<>();
        
        final HashCheater c1 = new HashCheater(1, "a");
        final HashCheater c2 = new HashCheater(1, "b");
        final HashCheater c3 = new HashCheater(1, "c");
        
        map.put(c1, "c1");
        map.put(c2, "c2");  
        
        Assert.assertEquals(2, map.size());
        Assert.assertNull(map.get(c3));
    }

    
    /**
     * Verify that we can store objects with same hashCode
     * but being different - String
     */
    @Test
    public void identicalHashCodeButDifferentKeyButSameKeyValue()
    {
        final FastFakeMap<String, String> map = new FastFakeMap<>();
        map.put(new String("a"), "c1");
        
        Assert.assertEquals("c1", map.get(new String("a")));
    }
    
    /**
     * 
     */
    @Test
    public void differentHashCodeButSamePosition()
    {
        final FastFakeMap<HashCheater, String> map = new FastFakeMap<>();
        final HashCheater c1 = new HashCheater(0 * 11 + 1);
        final HashCheater c2 = new HashCheater(1 * 11 + 1);
        
        map.put(c1, "c1");
        map.put(c2, "c2");
        
        Assert.assertEquals("c1", map.get(c1));
        Assert.assertEquals("c2", map.get(c2));
    }

    /**
     * Negative hash code
     */
    @Test
    public void negativeHashCodes()
    {
        final FastFakeMap<HashCheater, String> map = new FastFakeMap<>();
        final HashCheater c1 = new HashCheater(-1);
        
        map.put(c1, "c1");
        
        Assert.assertEquals("c1", map.get(c1));
    }

    /**
     * Containskey - happy path
     * 
     * @author rschwietzke
     */
    @Test
    public void containsKeyHappyPath() 
    {
        final FastFakeMap<HashCheater, String> map = new FastFakeMap<>();
        HashCheater c1 = new HashCheater(1, "a");
        HashCheater c2 = new HashCheater(1, "b");
        HashCheater c3 = new HashCheater(2, "c");

        Assert.assertFalse(map.containsKey(c1));

        map.put(c1, "value1");
        
        Assert.assertTrue(map.containsKey(c1));
        Assert.assertFalse(map.containsKey(c2));
        
        map.put(c2, "vaulue2");
        Assert.assertTrue(map.containsKey(c1));
        Assert.assertTrue(map.containsKey(c2));
        Assert.assertFalse(map.containsKey(c3));
    }
    
    /**
     * Contains key, null value
     * @author rschwietzke
     */
    @Test
    public void containsKeyNullValue()
    {
        final FastFakeMap<String, String> map = new FastFakeMap<>();
        map.put("key", null);

        Assert.assertTrue(map.containsKey("key"));
    }
    
//    /**
//     * Contains key, null value
//     * @author rschwietzke
//     */
//    @Test
//    public void containsKeyNullKey()
//    {
//        final FastFakeMap<String, String> map = new FastFakeMap<>();
//        try
//        {
//            map.containsKey(null);
//            Assert.fail("Null is valid, cannot be");
//        }
//        catch(IllegalArgumentException e)
//        {
//        }
//    }
    
    /**
     * happy path for keys
     */
    @Test
    public void happyPathkeys()
    {
        final FastFakeMap<HashCheater, String> map = new FastFakeMap<>();
        final List<HashCheater> cheaters = new ArrayList<>();
        
        for (int i = 0; i < 42; i++)
        {
            final HashCheater c = new HashCheater(i);
            cheaters.add(c);
            
            map.put(c, "c" + i);
        } 

        // get us all keys
        final List<HashCheater> set = map.keys();
        
        // do we have as many keys as we have in the map
        Assert.assertEquals(map.size(), set.size());
        
        for (final HashCheater c : set)
        {
            Assert.assertTrue(map.containsKey(c));
        }

        // all keys from set
        for (final HashCheater setCheater : set)
        {
            boolean found = false;
            
            // find this cheater in our original list of
            // stored cheaters
            for (final HashCheater cheater : cheaters)
            {
                if (cheater == setCheater)
                {
                    found = true;
                    continue;
                }
            }
            
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Return an empty keys when the map is empty
     */
    @Test
    public void testkeysOfEmptyMap()
    {
        final FastFakeMap<String, String> map = new FastFakeMap<>();
        Assert.assertTrue(map.keys().isEmpty());
    }
    
//    /**
//     * Happy Path
//     */
//    @Test
//    public void testPutAllHappyPath()
//    {
//        // both maps are empty
//        {
//            final FastFakeMap<String, String> target = new FastFakeMap<>();
//            final FastFakeMap<String, String> source = new FastFakeMap<>();
//
//            target.putAll(source);
//            Assert.assertTrue(target.isEmpty());
//            Assert.assertTrue(source.isEmpty());
//        }
//
//        // target is empty, source one entry
//        {
//            final FastFakeMap<String, String> target = new FastFakeMap<>();
//            final FastFakeMap<String, String> source = new FastFakeMap<>();
//
//            source.put("s1", "s1v1");
//            
//            target.putAll(source);
//            Assert.assertEquals(1, target.size());
//            Assert.assertEquals(1, source.size());
//            Assert.assertEquals("s1v1", target.get("s1"));
//        }
//
//        // target is empty, source has entries
//        {
//            final FastFakeMap<String, String> target = new FastFakeMap<>();
//            final FastFakeMap<String, String> source = new FastFakeMap<>();
//
//            source.put("s1", "s1v1");
//            source.put("s2", "s2v2");
//            
//            target.putAll(source);
//            Assert.assertEquals(2, target.size());
//            Assert.assertEquals(2, source.size());
//            Assert.assertEquals("s1v1", target.get("s1"));
//            Assert.assertEquals("s2v2", target.get("s2"));
//        }
//
//        
//        // source is empty, target has entries 
//        {
//            final FastFakeMap<String, String> target = new FastFakeMap<>();
//            final FastFakeMap<String, String> source = new FastFakeMap<>();
//
//            target.put("t1", "t1v1");
//            target.put("t2", "t2v2");
//            
//            target.putAll(source);
//            Assert.assertEquals(2, target.size());
//            Assert.assertEquals(0, source.size());
//            Assert.assertEquals("t1v1", target.get("t1"));
//            Assert.assertEquals("t2v2", target.get("t2"));
//        }
//        
//        // both have entries
//        {
//            final FastFakeMap<String, String> target = new FastFakeMap<>();
//            final FastFakeMap<String, String> source = new FastFakeMap<>();
//
//            target.put("t1", "t1v1");
//            target.put("t2", "t2v2");
//
//            source.put("s1", "s1v1");
//            source.put("s2", "s2v2");
//
//            target.putAll(source);
//            Assert.assertEquals(4, target.size());
//            Assert.assertEquals(2, source.size());
//            Assert.assertEquals("s1v1", target.get("s1"));
//            Assert.assertEquals("s2v2", target.get("s2"));
//            Assert.assertEquals("t1v1", target.get("t1"));
//            Assert.assertEquals("t2v2", target.get("t2"));
//        }
//        
//    }
//
//    // both have the same keys, different values
//    @Test
//    public void testPutAllSameKeys()
//    {
//        final FastFakeMap<String, String> target = new FastFakeMap<>();
//        final FastFakeMap<String, String> source = new FastFakeMap<>();
//
//        target.put("t1", "t1v1");
//        target.put("t2", "t2v2");
//
//        source.put("t1", "s1v1");
//        source.put("t2", "s2v2");
//
//        target.putAll(source);
//        Assert.assertEquals(2, target.size());
//        Assert.assertEquals(2, source.size());
//        Assert.assertEquals("s1v1", target.get("t1"));
//        Assert.assertEquals("s2v2", target.get("t2"));
//    }
//
//    // source is null
//    @Test
//    public void testPutAllNull()
//    {
//        final FastFakeMap<String, String> target = new FastFakeMap<>();
//        target.put("t1", "t1v1");
//        target.put("t2", "t2v2");
//
//        target.putAll(null);
//        Assert.assertEquals(2, target.size());
//        Assert.assertEquals("t1v1", target.get("t1"));
//        Assert.assertEquals("t2v2", target.get("t2"));
//    }
//    
//    @Test
//    public void testPutAllSelf()
//    {
//        final FastFakeMap<String, String> target = new FastFakeMap<>();
//        target.put("t1", "t1v1");
//        target.put("t2", "t2v2");
//
//        target.putAll(target);
//        Assert.assertEquals(2, target.size());
//        Assert.assertEquals("t1v1", target.get("t1"));
//        Assert.assertEquals("t2v2", target.get("t2"));
//    }    
    
    /**
     * Clear
     */
    @Test
    public void testClearEmptyMap()
    {
        final FastFakeMap<String, String> target = new FastFakeMap<>();
        target.clear();
        
        Assert.assertEquals(0, target.size());
        Assert.assertEquals(0, target.keys().size());
    }

    @Test
    public void testClearFullMap()
    {
        final FastFakeMap<String, String> target = new FastFakeMap<>();
        target.put("t1", "t1v1");
        target.put("t2", "t2v2");
        
        target.clear(); 
        
        Assert.assertEquals(0, target.size());
        Assert.assertEquals(0, target.keys().size());
    }
    
    
    /**
     * Remove
     */
    @Test
    public void testRemoveHappyPath()
    {
        final FastFakeMap<String, String> map = new FastFakeMap<>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        
        Assert.assertEquals("v1", map.remove("k1"));
        Assert.assertFalse(map.containsKey("k1"));
        Assert.assertEquals(1, map.size());
        
        Assert.assertEquals("v2", map.get("k2"));

        Assert.assertEquals("v2", map.remove("k2"));
        Assert.assertFalse(map.containsKey("k2"));
        Assert.assertEquals(0, map.size());
    }
    
    @Test
    public void testRemoveSameHashValue()
    {
        final FastFakeMap<HashCheater, String> map = new FastFakeMap<>();
        HashCheater c1 = new HashCheater(11, "a");
        HashCheater c2 = new HashCheater(11, "b");
        HashCheater c3 = new HashCheater(11, "c");
        
        map.put(c1, "v1");
        map.put(c2, "v2");
        map.put(c3, "v3");
        
        Assert.assertEquals("v2", map.remove(c2));
        Assert.assertEquals(2, map.size());
        Assert.assertEquals("v1", map.get(c1));
        Assert.assertEquals("v3", map.get(c3));

        HashCheater c4 = new HashCheater(11);
        map.put(c4, "v4");

        Assert.assertEquals("v1", map.remove(c1));
        Assert.assertEquals(2, map.size());
        Assert.assertEquals("v4", map.get(c4));
        Assert.assertEquals("v3", map.get(c3));

        Assert.assertEquals("v4", map.remove(c4));
        Assert.assertEquals(1, map.size());
        Assert.assertEquals("v3", map.get(c3));

        Assert.assertEquals("v3", map.remove(c3));
        Assert.assertEquals(0, map.size());
    }
    
    @Test
    public void testRemoveNoneExistingEntry()
    {
        final FastFakeMap<String, String> map = new FastFakeMap<>();
        Assert.assertNull(map.remove("k1"));
        
        map.put("k1", "v1");
        
        Assert.assertEquals("v1", map.remove("k1"));        
        Assert.assertNull(map.remove("k1"));        
    }
    
//    @Test(expected = IllegalArgumentException.class)
//    public void testRemoveNullKey()
//    {
//        final FastFakeMap<String, String> map = new FastFakeMap<>();
//        map.remove(null);
//    }   

    /**
     * Random execution to excerise the map heavily
     */
    @Test
    public void randomness()
    {
        final long seed = System.currentTimeMillis();
        final Random r = new Random(seed);
        
        List<String> data = new ArrayList<>();
        int size = r.nextInt(1000) + 42;
        
        for (int i = 0; i < size; i++)
        {
            String s = "k" + String.valueOf(r.nextInt(size / 2));
            data.add(s);
        }
        
        final FastFakeMap<String, Integer> map = new FastFakeMap<>();
        final Map<String, Integer> reference = new HashMap<>();

        for (int i = 0; i < size; i++)
        {
            int x = r.nextInt(10);
            
            switch(x)
            {
                case 0: 
                    reference.put(data.get(i), i);
                    map.put(data.get(i), i);
                    break;
                case 1: 
            }
            
            // verify();
        }
    }
    
    /**
     * A helper class with predictable hash codes
     * @author rschwietzke
     */
    class HashCheater
    {
        private final int hashCode;
        private final String value;
        
        public HashCheater(int hashCode)
        {
            this.hashCode = hashCode;
            this.value = "";
        }
        
        public HashCheater(int hashCode, String value)
        {
            this.hashCode = hashCode;
            this.value = value;
        }

        @Override
        public int hashCode()
        {
            return hashCode;
        }
        
        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            
            HashCheater other = (HashCheater) obj;
            if (hashCode != other.hashCode)
                return false;
            if (value == null)
            {
                if (other.value != null)
                    return false;
            }
            else if (!value.equals(other.value))
                return false;
            return true;
        }
        
        public String toString()
        {
            return MessageFormat.format("{0}({1}, value={2})", this.getClass().getSimpleName(), hashCode, value);
        }
        
    }
}