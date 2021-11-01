package hackarank;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class MakingAnagramsTest
{

    @Test
    public void testAnagram()
    {

        Assert.assertEquals("cde & dcf", 2, makeAnagram("cde", "dcf"));
        Assert.assertEquals("cde & abc", 4, makeAnagram("cde", "abc"));


        Assert.assertEquals("fcrxzwscanmligyxyvym & jxwtrhvujlmrpdoqbisbwhmgpmeoke", 30, makeAnagram("fcrxzwscanmligyxyvym", "jxwtrhvujlmrpdoqbisbwhmgpmeoke"));
    }


    public static int makeAnagram(final String a, final String b) {
        // Write your code here
        final AnagramDeletionCounter counter = new AnagramDeletionCounter(a, b);
        return counter.computeDeletionCount();
    }

    private static class AnagramDeletionCounter
    {

        AnagramDeletionCounter(final String a, final String b)
        {
            this.a = a;
            this.b = b;
        }

        public int computeDeletionCount()
        {
            final Map<Character, Integer> mapCountA = getUniqueStringCharacters(a);
            final Map<Character, Integer> mapCountB = getUniqueStringCharacters(b);

            final Set<Character> union = new HashSet<>();
            for(final Character charA : mapCountA.keySet())
            {
                if(mapCountB.containsKey(charA))
                {
                    union.add(charA);
                }
            }

            for(final Character charB : mapCountB.keySet())
            {
                if(mapCountA.containsKey(charB))
                {
                    union.add(charB);
                }
            }
//
//            System.out.println();

            return (a.length() + b.length()) - getNumberOfSameCharacters(union, mapCountA, mapCountB);
        }


        private int getNumberOfSameCharacters(final Set<Character> union, final Map<Character, Integer> charsetA, final Map<Character, Integer> charsetB)
        {
//            System.out.println(union);
//            System.out.println(charsetA);
//            System.out.println(charsetB);
            int count = 0;
            for(final Character c : union)
            {
                count = count + 2*Math.min(charsetA.getOrDefault(c, 0), charsetB.getOrDefault(c, 0));
            }
//            System.out.println(count);
            return count;
        }


        private Map<Character, Integer> getUniqueStringCharacters(final String s)
        {
            final Map<Character, Integer> map = new HashMap<>();
            for(int i = 0; i < s.length(); i++)
            {
                final Integer count = map.getOrDefault(s.charAt(i), 0);
                map.put(s.charAt(i), count + 1);
            }
            return map;
        }


        private final String a;
        private final String b;
    }


}
