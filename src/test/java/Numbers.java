import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.vertx.core.json.JsonObject;
import org.junit.Test;

public class Numbers
{

    @Test
    public void bigDecimal()
    {
        final BigDecimal decimal = new BigDecimal("0.00000101010");

        System.out.println(decimal.toPlainString() +  " or " + decimal.toString());

        System.out.println(new BigDecimal(decimal.toString()));
    }


    @Test
    public void logCounter()
    {

//        System.out.println((int)1000000000);
//        for(int i = 1; i < 10001; i++)
//        {
//            if(shouldUpdate(i))
//            {
//                System.out.println("Updated on " + i);
//            }
////            if(shouldUpdate2(i))
////            {
////                System.out.println("Updated2 on " + i);
////            }
//        }

        boolean b1 = true;
        boolean b2 = true;


        System.out.println( (b1 ^ b2) );


    }


    @Test
    public void testIntStream()
    {
        final List<String> list = Arrays.asList("a","b","c");
        final List<Integer> nums = Arrays.asList(1,2,3);

        final JsonObject out = new JsonObject();
        IntStream.range(0, list.size()).forEachOrdered(value -> out.put(list.get(value), nums.get(value)));

        final JsonObject out2 = new JsonObject(IntStream.range(0, list.size()).boxed().collect(Collectors.toMap(list::get, nums::get)));
        System.out.println(out);
        System.out.println(out2);
    }

    private boolean shouldUpdate( int count )
    {
        return count % Math.pow(10, Math.floor(Math.log10(count))) == 0;
    }


    private boolean shouldUpdate2(final int count)
    {
        int c = Math.abs(count);
        int val = 10000;
        if(c <= 10)
        {
            val = 1;
        }
        else if(c <= 100)
        {
            val = 10;
        }
        else if(c <= 1000)
        {
            val = 100;
        }
        else if(c <= 10000)
        {
            val = 1000;
        }
        return c % val == 0;
    }


}
