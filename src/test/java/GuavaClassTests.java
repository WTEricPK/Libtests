import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GuavaClassTests {


    @Test
    public void testSetsDifference()
    {

        final Set<String> one = Sets.newHashSet("one", "two", "three");
        final Set<String> two = Sets.newHashSet("three", "four", "five");

        Sets.SetView<String> difference = Sets.difference(one, two);

        System.out.println(difference);
    }

    @Test
    public void testImmutableListWithExpectedSize()
    {
        List<Integer> list = ImmutableList.<Integer>builderWithExpectedSize(5).add(3).add(2).add(1).build();

        System.out.println(list);
    }

    /**
     * How to convert a list of object to a map using Multimaps.index
     */
    @Test
    public void testImmutableMapIndex()
    {
        
        List<Ad> list = new ArrayList<>();
        list.add(new Ad("1","fish"));
        list.add(new Ad("2","fish"));
        list.add(new Ad("3","cat"));
        list.add(new Ad("4","cat"));
        list.add(new Ad("5","dog"));
        list.add(new Ad("6","dog"));



        Map<String, Collection<Ad>> ms = Multimaps.index(list, ad -> ad.getNs()).asMap();

//        ms.entrySet().stream().map(e ->
//                builder.put(e.getKey(),
//                        );

        Map<String, String> out = ms.entrySet().stream().collect( ImmutableMap.toImmutableMap(o -> o.getKey(), o -> toJsonList(o.getValue())) );


//        ImmutableMap<String, String> ms2 = ms.toImmutableMap(new Function<String, String>() {
//                                                                   @NullableDecl
//                                                                   @Override
//                                                                   public String apply(@NullableDecl String t) {
//                                                                       return t;
//                                                                   }
//                                                               },
//                new Function<Collection<Ad>, String>() {
//                    @NullableDecl
//                    @Override
//                    public String apply(@NullableDecl Collection<Ad> t) {
//                        return new JsonObject().put("ad", new JsonArray(toJsonList(t))).encode();
//                    }
//                });

//        new JsonObject().put("ad",
//                new JsonArray( e.stream().map(ad -> new JsonObject().put("id",ad.getId()).put("ns",ad.getNs())).collect(Collectors.toList()) ) )
//                .encode() ));

        out.entrySet().forEach( e -> System.out.println("Key: " + e.getKey() + " value " + e.getValue()));


        
    }

    @Test
    public void listToMap()
    {
        List<Ad> list = new ArrayList<>();
        list.add(new Ad("1","fish"));
        list.add(new Ad("2","fish"));
        list.add(new Ad("3","cat"));
        list.add(new Ad("4","cat"));
        list.add(new Ad("5","dog"));
        list.add(new Ad("6","dog"));

        Map<String, String> map = list.stream().collect( (ImmutableMap.toImmutableMap(a -> a.getId(), a -> a.getNs() )));

        System.out.println(map);
    }


    @Test
    public void listToMap2()
    {
        List<Ad> list = new ArrayList<>();
        list.add(new Ad("1","fish"));
        list.add(new Ad("2","fish"));
        list.add(new Ad("3","cat"));
        list.add(new Ad("4","cat"));
        list.add(new Ad("5","dog"));
        list.add(new Ad("5","dog"));

        Map<String, String[]> map = list.stream().collect( (ImmutableMap.toImmutableMap(a -> a.getId(), a -> new String[]{"",a.toString()} )) );

        System.out.println(map);
    }


    @Test
    public void testCollectList()
    {
        JsonArray list = new JsonArray(Arrays.asList(new JsonObject().put("id","1"), new JsonObject().put("id","2"), new JsonObject().put("id","3"), new JsonObject().put("id","4") ) );

        System.out.println(list);


    }


    String toJsonList( Collection<Ad> col )
    {
        return new JsonObject().put("ad",new JsonArray(col.stream().map(ad -> new JsonObject().put("id",ad.getId()).put("ns",ad.getNs()) ).collect(Collectors.toList()))).encode();
    }


    @Test
    public void setMultiMap()
    {

        final SetMultimap<String, String> smm = HashMultimap.create();


        System.out.println(smm.put("a", "a") + " size " + smm.size());
        System.out.println(smm.put("a", "b") + " size " + smm.size());
        System.out.println(smm.put("a", "a") + " size " + smm.size());
        System.out.println(smm.put("b", "b") + " size " + smm.size());
        System.out.println(smm.put("a", "c") + " size " + smm.size());
        System.out.println(smm.put("a", "d") + " size " + smm.size());

        System.out.println(smm);
    }


    @Test
    public void hashTable()
    {

        final List<Map<String, Object>> list = new ArrayList<>();
        list.add(ImmutableMap.of("name", "gold", "level", 3));
        list.add(ImmutableMap.of("name", "silver", "level", 2));
        list.add(ImmutableMap.of("name", "bronze", "level", 1));
        list.add(ImmutableMap.of("name", "alumni"));


        Table table = createTable(list);

        System.out.println(table.toString());

    }




    private Table<String, String, Boolean> createTable(final List<Map<String,Object>> list)
    {

        final TreeMap<Integer, String> numberedLevels = new TreeMap<>();
        final Set<String> noLevel = new HashSet<>();
        for(Map<String, Object> m:list)
        {
            final String name = (String)m.get("name");
            final Integer level = (Integer)m.get("level");
            if(null != level)
            {
                numberedLevels.put(level, name);
            }
            else
            {
                noLevel.add(name);
            }
        }

        final Integer[] levelNumbers = numberedLevels.keySet().toArray(new Integer[numberedLevels.size()]);

        Table<String, String, Boolean> table = HashBasedTable.create();
        for(int i = 0; i < levelNumbers.length; i++)
        {
            for(int j = 0; j < levelNumbers.length; j++)
            {
                table.put(numberedLevels.get(levelNumbers[i]), numberedLevels.get(levelNumbers[j]), levelNumbers[i] >= levelNumbers[j]);

                for(String s:noLevel)
                {

                }
            }
        }


        return ImmutableTable.copyOf(table);
    }


    public class Ad{
        
        private final String id, ns;
        
        public Ad(String id, String ns)
        {
            this.id = id;
            this.ns = ns;
        }
        
        String getId()
        {
            return id;
        }
        
        String getNs()
        {
            return ns;
        }
        
    }



    
}
