import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.vertx.core.json.JsonObject;
import org.junit.Test;

public class SreamsTest
{

    @Test
    public void filterStream()
    {

        final List<Integer> list = Arrays.asList(1,2,3,4,1,2,3,2);

        final List<Integer> list2 = list.stream().skip(1).collect(Collectors.toList());

        System.out.println(list2);

    }

}
