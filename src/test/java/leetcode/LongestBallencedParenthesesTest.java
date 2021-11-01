package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

public class LongestBallencedParenthesesTest
{

    @Test
    public void ballencedBrackets()
    {
        Assert.assertTrue("()", isValid("()"));
        Assert.assertTrue("()[]{}", isValid("()[]{}"));
        Assert.assertFalse("(]", isValid("(]"));
        Assert.assertFalse("([)]", isValid("([)]"));
        Assert.assertTrue("{[]}", isValid("{[]}"));
        Assert.assertFalse("((", isValid("(("));

    }


    public boolean isValid(String s)
    {

        if(s.length() % 2 > 0)
        {
            return false;
        }

        final Map<Character, Character> pairs = new HashMap<>();
        pairs.put('{', '}');
        pairs.put('(', ')');
        pairs.put('[', ']');

        final Set<Character> openBraces = new HashSet<>(pairs.keySet());
        final Set<Character> closeBrackets = new HashSet<>(pairs.values());

        final Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i++)
        {
            final Character current = s.charAt(i);
            if(openBraces.contains(current))
            {
                stack.push(current);
            }
            else if(!stack.isEmpty() && closeBrackets.contains(current))
            {
                if(!pairs.get(stack.pop()).equals(current))
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
