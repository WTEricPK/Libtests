import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

import com.conversantmedia.util.concurrent.DisruptorBlockingQueue;
import org.junit.Test;

public class QuesAndBuffersTest
{

    @Test
    public void testDisruptorBlockingQueue()
    {

        final BlockingQueue<String> que = new DisruptorBlockingQueue<>(4);

        System.out.println(que.offer("A"));
        System.out.println(que.offer("B"));

        int n = 0;
        final Iterator<String> iterator = que.iterator();

        final ArrayList<String> letters = new ArrayList(Arrays.asList("C", "D", "E", "F", "G", "H"));

        System.out.println(que.poll());
        System.out.println(que.poll());
        System.out.println(que.poll());
        System.out.println(que.poll());

    }


}
