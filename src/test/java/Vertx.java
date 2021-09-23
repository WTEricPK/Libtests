import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

public class Vertx
{


    @Test
    public void testObjectCodec()
    {
        final UUID guid = UUID.randomUUID();


        final JsonObject jo = new JsonObject();

        jo.put("uuid", guid);

        System.out.println("uuid " + jo.getMap().get("uuid"));

    }

    @Test
    public void testMerge()
    {

        final JsonObject j1 = new JsonObject().put("a", "a1").put("b", "b1");
        final JsonObject j2 = new JsonObject().put("a", "a2").put("b", "b2").put("c","c1");

        final JsonObject merged1 = j1.copy().mergeIn(j2);
        final JsonObject merged2 = j2.copy().mergeIn(j1);


        System.out.println(merged1.encodePrettily());
        System.out.println(merged2.encodePrettily());
    }


    @Test
    public void testTypeReturn()
    {

       final Map<String, Object> map = new HashMap<>();
       map.put("obj1", "OBJ1");
       map.put("obj2", (int)100);

        final Map<String, Object> map2 = new HashMap<>();
        map2.put("list", Arrays.asList("A","B","C"));


       final JsonObject jj = new JsonObject(new JsonObject(map).encode());


        jj.put("mapIn1", map);
        jj.put("mapIn2", new JsonObject(map));
        jj.put("mapIn3", ImmutableMap.copyOf(map));

        System.out.println(instanceOf(jj.getValue("mapIn1")));
        System.out.println(instanceOf(jj.getValue("mapIn2")));
        System.out.println(instanceOf(jj.getValue("mapIn3")));
        System.out.println(instanceOf(null));
    }

    private String instanceOf(final Object obj)
    {
        if(obj instanceof Map)
        {
            return "Map";
        }
        else if(obj instanceof JsonObject)
        {
            return "Json";
        }
        else if(obj instanceof List)
        {
            return "List";
        }
        else
        {
            return "Other";
        }
    }


}
