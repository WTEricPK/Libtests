package leetcode;

import org.junit.Test;

public class LongestBallencedBracketsTest
{

    @Test
    public void findLongestBalencedBrackets()
    {

        final String string = ")()()))()()())";
        System.out.println(longestValidParentheses(string));
    }


    public int longestValidParentheses(String s)
    {
        int maxLangth = 0;

        for(int i = 0; i < s.length(); i++)
        {
            int sum = increment(s.charAt(i));
            for(int j = i + 1; j < s.length(); j++)
            {
                if(sum < 0)
                {
                    break;
                }
                sum += increment(s.charAt(j));

                if(sum == 0)
                {
                    maxLangth = Math.max(j - i + 1, maxLangth);
                }
            }
        }

        return maxLangth;
    }

    private int increment(char c)
    {
        if('('==c)
        {
            return 1;
        }
        else if(')'==c)
        {
            return -1;
        }
        return 0;
    }

}
