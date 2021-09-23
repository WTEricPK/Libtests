import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JavaMapsAndCollections
{

    /**
     * Keeping a subset of the keys.
     */
    @Test
    public void retainAll1()
    {

        Map<String,String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");

        Set<String> keepers = new HashSet<>();
        keepers.add("one");
        keepers.add("three");

        map.keySet().retainAll(keepers);

        System.out.println(map.toString());

    }

    /**
     * Call retainAll with a set containing keys that are not in the maps keyset.
     */
    @Test
    public void retainAll2()
    {

        Map<String,String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");

        Set<String> keepers = new HashSet<>();
        keepers.add("five");
        keepers.add("six");

        map.keySet().retainAll(keepers);

        System.out.println(map.toString());

    }


    /**
     * Call retainAll with a set containing keys into an empty map.
     */
    @Test
    public void retainAll3()
    {

        Map<String,String> map = new HashMap<>();

        Set<String> keepers = new HashSet<>();
        keepers.add("five");
        keepers.add("six");

        map.keySet().retainAll(keepers);

        System.out.println(map.toString());

        System.out.println( "Number " + new Integer("hello"));
    }

    @Test
    public void testRemove()
    {
        Map<String,String> map = new HashMap<>();
        map.put("one", "1");
        map.put("two", "2");
        map.put("three", "3");
        map.put("four", "4");

        String six = (String)map.remove("six");

        System.out.println("Six is: " + six);
    }

    @Test
    public void removeItem()
    {

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        for(String s:list)
        {
            if("a".equals(s))
            {
                list.remove(s);
                break;
            }
        }

    }


}
