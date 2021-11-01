package leetcode;

import org.junit.Test;

public class MergeTwoSortedLinkedListsTest
{


    @Test
    public void mergeTwoSortedLists()
    {

        final ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        final ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));
        printListNode(l1);
        printListNode(l2);

        //printListNode(mergeTwoLists(l1, l2));

        printListNode(addTwoNumbers(l1, l2));

    }


    public ListNode mergeTwoLists(ListNode l1, ListNode l2)
    {

        final ListNode dummy = new ListNode(0);
        ListNode sorted = dummy;


        while(true)
        {
            if(l1 == null)
            {
                sorted.next = l2;
                break;
            }
            if(l2 == null)
            {
                sorted.next = l1;
                break;
            }

            if(l1.val < l2.val)
            {
                sorted.next = l1;
                l1 = l1.next;
            }
            else
            {
                sorted.next = l2;
                l2 = l2.next;
            }

            sorted = sorted.next;

        }

        return dummy.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2)
    {
        ListNode dummy = new ListNode(0);
        ListNode sumList = dummy;
        while(!(l1 == null && l2 == null))
        {
            int l1Val = 0;
            int l2Val = 0;

            if(l1 != null)
            {
                l1Val = l1.val;
                l1 = l1.next;
            }

            if(l2 != null)
            {
                l2Val = l2.val;
                l2 = l2.next;
            }

            int sum = l1Val + l2Val;

            int rem = sum % 10 ;
            System.out.println("sum " + sum + " rem " + rem);

            sumList.next = new ListNode(sum);

            sumList = sumList.next;
        }

        return dummy.next;
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
    }
}
