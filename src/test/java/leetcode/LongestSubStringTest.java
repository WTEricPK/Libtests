package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class LongestSubStringTest
{

    @Test
    public void longestSubstring()
    {
        Assert.assertEquals("pwwkew", 3, lengthOfLongestSubstring("pwwkew"));
        Assert.assertEquals("aau", 2, lengthOfLongestSubstring("aau"));
        Assert.assertEquals("abcabcbb", 3, lengthOfLongestSubstring("abcabcbb"));
        Assert.assertEquals("qrsvbspk", 5, lengthOfLongestSubstring("qrsvbspk"));

    }

    public int lengthOfLongestSubstring(final String s)
    {
        final int n = s.length();
        if(s.length() < 1)
        {
            return n;
        }

        int maxLength = 0;
        for(int i = 0 ; i < n; i++)
        {
            final Set<Character> chars = new HashSet<>();
            for(int j = i; j < n; j++)
            {
                boolean isNew = chars.add(s.charAt(j));
                maxLength = Math.max(chars.size(), maxLength);
                if(!isNew)
                {
                    break;
                }
            }
        }

        return maxLength;
    }


    @Test
    public void longestSubstringFast()
    {
        Assert.assertEquals("pwwkew", 3, lengthOfLongestSubstringFast("pwwkew"));
        Assert.assertEquals("aau", 2, lengthOfLongestSubstringFast("aau"));
        Assert.assertEquals("abcabcbb", 3, lengthOfLongestSubstringFast("abcabcbb"));
        Assert.assertEquals("qrsvbspk", 5, lengthOfLongestSubstringFast("qrsvbspk"));

    }

    public int lengthOfLongestSubstringFast(final String s)
    {

        final int n = s.length();
        if(n <= 1)
        {
            return n;
        }

        int maxLength = 1;

        final Map<Character, Integer> charPositions = new HashMap<>();
        int i = 0;
        int j = 0;

        while(true)
        {
            final Character head = s.charAt(i);

            final Integer existing = charPositions.get(head);

            if(existing != null && existing.compareTo(j) >= 0)
            {
                charPositions.remove(s.charAt(j));
                j = Math.min(n-1, existing + 1);
            }
            else
            {
                if((1 + i - j) > maxLength)
                {
                    maxLength = 1 + i - j;
                }
            }

            charPositions.put(head, i);

            if(j >= (n - maxLength))
            {
                break;
            }
            i = Math.min(n-1, i + 1);
        }

        return maxLength;
    }

}
