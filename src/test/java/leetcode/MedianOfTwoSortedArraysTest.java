package leetcode;

import org.junit.Assert;
import org.junit.Test;

public class MedianOfTwoSortedArraysTest
{

    @Test
    public void findMedianOfTwoArrays()
    {
        Assert.assertEquals("Input: nums1 = [1,3], nums2 = [2]", 2, findMedianSortedArrays(new int[]{1,2,3}, new int[]{2}), 0.000001);
        Assert.assertEquals("Input: nums1 = [1,2], nums2 = [3,4]", 2.50000, findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}), 0.000001);
        Assert.assertEquals("Input: nums1 = [0,0], nums2 = [0,0]", 0, findMedianSortedArrays(new int[]{0,0}, new int[]{0,0}), 0.000001);
        Assert.assertEquals("Input: nums1 = [], nums2 = [1]", 1, findMedianSortedArrays(new int[]{}, new int[]{1}), 0.000001);
        Assert.assertEquals("Input: nums1 = [2], nums2 = []", 2, findMedianSortedArrays(new int[]{2}, new int[]{}), 0.000001);
    }


    public double findMedianSortedArrays(int[] nums1, int[] nums2)
    {

        final int length = nums1.length + nums2.length;

        final int cut = length / 2;

        int count = 0;
        int i = 0;
        int j = 0;
        int currentVal = 0;
        int lastVal = 0;
        while(count <= cut)
        {
            if(i < nums1.length && j < nums2.length)
            {
                if(nums1[i] < nums2[j])
                {
                    lastVal = currentVal;
                    currentVal = nums1[i];
                    i++;
                }
                else
                {
                    lastVal = currentVal;
                    currentVal = nums2[j];
                    j++;
                }
            }
            else if(j == nums2.length)
            {
                lastVal = currentVal;
                currentVal = nums1[i];
                i++;
            }
            else
            {
                lastVal = currentVal;
                currentVal = nums2[j];
                j++;
            }

            count++;
        }

        double median;
        if(length % 2 == 0)
        {
            median = ((double)currentVal + (double)lastVal)/2d;
        }
        else
        {
            median = currentVal;
        }

        return median;
    }

}
