package leetcode;

import org.junit.Test;

public class SwapNodesInPairsTest
{

    @Test
    public void testSwapAllNodes()
    {

        final ListNode test1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))))));
        printListNode(test1);
        printListNode(swapPairs(test1));
    }

    public ListNode swapPairs(ListNode head)
    {

        ListNode current = head;
        ListNode last = new ListNode();
        while(current != null)
        {
            current = swapPair(current);

            System.out.println("current " + current.toString());

            if(current != null)
            {
                last.next = current;
                current = current.next;
            }
            else
            {
                break;
            }
            if(current != null)
            {
                last.next.next = current;
                current = current.next;
            }
            else
            {
                break;
            }

        }
        System.out.println("head " + head.toString());
        return last.next;
    }


    public ListNode swapPair(ListNode head)
    {
        final ListNode first = head;
        final ListNode second = head.next;
        final ListNode third = second.next;
        first.next = third;
        second.next = first;
        return second;
    }


    private void printListNode(final ListNode listNode)
    {
        if(listNode == null)
        {
            System.out.println("empty []");
        }
        final StringBuilder builder = new StringBuilder();
        builder.append('[');
        ListNode next = listNode;
        int i = 0;
        while(next.next != null)
        {
            builder.append(next.val + ", ");
            if(i > 100)
            {
                builder.append("Broke");
                break;
            }
            next = next.next;
            i++;
        }
        builder.append(next.val);
        builder.append(']');
        System.out.println(builder);
    }

    public class ListNode
    {
        int val;
        ListNode next;

        ListNode()
        {
        }

        ListNode(int val)
        {
            this.val = val;
        }

        ListNode(int val, ListNode next)
        {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString()
        {
            final StringBuilder builder = new StringBuilder();
            builder.append('[');
            ListNode next = this;
            int i = 0;
            while(next.next != null)
            {
                builder.append(next.val + ", ");
                if(i > 100)
                {
                    builder.append("Broke");
                    break;
                }
                next = next.next;
                i++;
            }
            builder.append(next.val);
            builder.append(']');
            return builder.toString();
        }
    }
}
