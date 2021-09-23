import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.junit.Test;

public class JsonObjectTests
{

    @Test
    public void testGetInt()
    {

        final JsonObject json = new JsonObject();


        json.put("int", 10);
        json.put("double", 10.0);


        System.out.println( "int as double: " + new Double(json.getFloat("int")) + " double as int " + new Integer(json.getInteger("double")));

    }


    @Test
    public void testConvert()
    {

        final JsonArray arr = new JsonArray().add(ImmutableMap.of("key1", "val1"));

        final List list1 = arr.getList();
        printElementType(list1);

        final List list = new JsonArray(arr.encode()).getList();
        printElementType(list);

    }

    private void printElementType(final List list)
    {
        list.forEach(l -> {

            if(l instanceof JsonObject)
            {
                System.out.println("Json");
            }
            else if(l instanceof Map)
            {
                System.out.println("Map");
            }

        });
    }

}
