package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ArrayTests
{

    @Test
    public void arrayQueries()
    {
        int n = 10;

        final List<List<Integer>> queries = new ArrayList<>();
        queries.add(Arrays.asList(2,6,8));
        queries.add(Arrays.asList(3,5,7));
        queries.add(Arrays.asList(1,8,1));
        queries.add(Arrays.asList(5,8,15));

        int m = queries.size();

        long[][] queryArray = new long[m][n];

        int count = 0;
        long maxValue = Long.MIN_VALUE;
        for(List<Integer> query : queries)
        {
            int a = query.get(0) - 1;
            int b = query.get(1) - 1;
            int k = query.get(2);

            for(int i = 0; i < m; i++)
            {
                long[] subArray = queryArray[i];
                for( int j = a; j <= b; j++)
                {
                    subArray[j] = subArray[j] + k;

                    maxValue = Math.max(maxValue, subArray[j]);
                }
            }
            count++;
        }

        System.out.println(maxValue);
    }

    @Test
    public void binarySearchTest()
    {
        final double[] array = new double[]{1d,2d,3d,4d,5d,6d};

        System.out.println(Arrays.binarySearch(array, 3));
        System.out.println(binarySearch(array, 3));

        int c = 1000000;

        //Math.max(, )

    }

    private int binarySearch(double[] values, double target)
    {
        return binarySearchRecurr(values, 0, values.length - 1, target);
    }

    private int binarySearchRecurr(double[] values, int x1, int x2, final double target)
    {

        if(x2 >= x1)
        {
            int mid = x1 + (x2 - 1) / 2;

            if(values[mid] == target)
            {
                return mid;
            }
            if(values[mid] > target)
            {
                return binarySearchRecurr(values, mid, x2, target);
            }
            else
            {
                return binarySearchRecurr(values, x1, mid, target);
            }
        }

        return -1;
    }


}
