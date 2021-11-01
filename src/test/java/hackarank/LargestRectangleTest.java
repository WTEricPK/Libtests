package hackarank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;


public class LargestRectangleTest
{

    @Test
    public void test()
    {
        Assert.assertEquals("[1, 2, 3, 4, 5]", 9, largestRectangle(Arrays.asList(1, 2, 3, 4, 5)));
    }


    public static long largestRectangle(final List<Integer> hList)
    {
        int maxRect = 0;
        for(int i = 0; i < hList.size(); i++)
        {
            int hMin = hList.get(i);
            for(int j = i; j < hList.size(); j++)
            {
                if(hList.get(j) < hMin)
                {
                    hMin = hList.get(j);
                }
                maxRect = Math.max(hMin * (j - i + 1), maxRect);
            }
        }
        return maxRect;
    }

    /*
     * Complete the 'largestRectangle' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY h as parameter.
     */

    public static long largestRectangle2(final List<Integer> hList)
    {

        if(hList.isEmpty())
        {
            return 0;
        }
        else if(hList.size() == 1)
        {
            return hList.get(0);
        }

        // used as a window accross the list of heights.
        final Queue<Integer> integerQueue = new LinkedList<>();

        final Map<Integer, Integer> heightMap = new HashMap<>();
        int j = 0;
        int n = hList.size() - 1;
        int hMin = 0;
        long maxRect = 0;
        do
        {

            final Integer current = hList.get(j);
            integerQueue.add(current);
            heightMap.put(current, heightMap.getOrDefault(current, 0) + 1);

            if(current < hMin)
            {
                hMin = current;
            }

            long currentMax = (long)integerQueue.size() * (long)hMin;

            if(currentMax >= maxRect)
            {
                maxRect = currentMax;
                j = Math.min(j + 1, n);
            }
            else
            {
                Integer removed = integerQueue.poll();
                Integer lastCount = heightMap.get(removed);
                if(lastCount == 1)
                {
                    heightMap.remove(lastCount);
                }
                else
                {
                    heightMap.put(removed, lastCount - 1);
                }
            }
            System.out.println(j);
        }
        while(!integerQueue.isEmpty() && j == n);

        return maxRect;
    }


}
