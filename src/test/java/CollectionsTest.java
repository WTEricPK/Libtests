import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

public class CollectionsTest
{

    @Test
    public void testTreeSet()
    {

        final TreeSet<Integer> set = new TreeSet<>();
        set.add(1);
        set.add(2);
        set.add(4);
        set.add(6);


        System.out.println("lower lowest " + set.lower(1));
        System.out.println("highest higher " + set.higher(6));

    }

    @Test
    public void testAddWhilstIterating()
    {

        final Map<String,String> map = new HashMap<>();
        map.put("a", "aa");
        map.put("b", "bb");

        final Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();

        while(it.hasNext())
        {
            final Map.Entry e = it.next();
            if(e.getKey().equals("a"))
            {
                e.setValue("cc");
            }
        }

        System.out.println(map.toString());

    }


    @Test
    public void addingEmpty()
    {

        Set<String> set = new ImmutableSet.Builder<String>().addAll(Arrays.asList("A","B")).addAll(Collections.emptyList()).build();

        System.out.println(set);
    }


    @Test
    public void listEqual()
    {
//        final List<Map> list = Arrays.asList(ImmutableMap.of("padoqName", "Products"));
//        final List<Map> list2 = Arrays.asList(ImmutableMap.of("padoqName", "products"));
//
//        System.out.println(list.equals(list2));

        Map<String, String> map = Arrays.asList("A", "B", "C", "B").stream().collect(Collectors.toMap(Function.identity(), Function.identity(), (key1, key2) ->  null));


        System.out.println(map.toString());

    }


    @Test
    public void getOrDefaultImmutableMap()
    {
        final Map<String, Object> map = ImmutableMap.of("a", "a");

        System.out.println(map.getOrDefault("b", Collections.emptyMap()));

    }


    @Test
    public void setsAreEqual()
    {
        final Set<String> s1 = ImmutableSet.of("1","2","3","4");
        final Set<String> s2 = ImmutableSet.of("2","1","4","3", "6");

        System.out.println(s1.equals(s2));
    }


    @Test
    public void listsAreEqual()
    {
        final List<String> s1 = ImmutableList.of("1", "2", "3", "4");
        final List<String> s2 = ImmutableList.of("1", "3", "2", "4");

        System.out.println(s1.equals(s2));
    }


    @Test
    public void addToQueWhilstIterating()
    {

        final Queue<String> que = new PriorityQueue<>();

        que.add("A");
        que.add("B");
        que.add("C");


        int i = 0;
        while(!que.isEmpty())
        {
            if(i == 1)
            {
                que.add("POO");
            }
            else if(i == 2)
            {
                que.add("POO1");
            }
            else if(i >= 10)
            {
                break;
            }

            String s = que.peek();

            System.out.println(s);
            que.remove();
            i++;

        }


    }


    @Test
    public void computeIfAbsent()
    {
        final Map<String, Object> map = new HashMap<>();

        System.out.println(map);

        map.put("showAtBottom", null);

        map.computeIfAbsent("showAtBottom", s -> Boolean.FALSE);

        System.out.println(map);
    }


    @Test
    public void arrayList()
    {
        List<String> s = new ArrayList<>(Arrays.asList("A","B"));
    }


}
