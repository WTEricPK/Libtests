import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import org.junit.Test;

public class JavaStackTest
{

    @Test
    public void stackTest()
    {
        final String s1 = "[([[]])]";
        final String s2 = "[)]";
        final String s3 = "[{}({)}]";

        System.out.println(isBalanced(s1));
        System.out.println(isBalanced(s2));
        System.out.println(isBalanced(s3));
    }

    public static String isBalanced(String s) {

        if(s.length() % 2 > 0)
        {
            return "NO";
        }
        final Map<Character, Character> bracketPairs = new HashMap<>();
        bracketPairs.put('{','}');
        bracketPairs.put('(',')');
        bracketPairs.put('[',']');


        final Set<Character> open = new HashSet<>();
        open.add('{'); open.add('['); open.add('(');

        final Set<Character> close = new HashSet<>();
        close.add('}'); close.add(']'); close.add(')');

        boolean stillBallenced = true;
        final Stack<Character> closeStore = new Stack<>();


        for(int i = 0; i < s.length(); i++)
        {
            final Character curr = s.charAt(i);
            if(open.contains(curr))
            {
                closeStore.add(bracketPairs.get(curr));
            }
            else if(close.contains(curr))
            {
                if(!closeStore.isEmpty() && closeStore.pop().equals(curr))
                {
                    stillBallenced = true;
                }
                else
                {
                    stillBallenced = false;
                    break;
                }
            }
            else
            {
                // illegal argument.
                stillBallenced = false;
                break;
            }

        }

        return stillBallenced & closeStore.isEmpty() ? "YES" : "NO";
    }

}
