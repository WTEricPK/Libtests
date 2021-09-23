import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class HackerRank1Test
{
//
//    @Test
//    public void testMain()
//    {
//
//        final String[] args = new String[]{"hello", "ben"};
//        HackerRank1.main(args);
//
//    }
//
//
//    @Test
//    public void test2()
//    {
//
//        int weight_1 = 10;
//
////        int[] list = new int[26];
////        int counter = weight_1;
////        for(int i  = 0; i < list.length; i++)
////        {
////            list[i] = counter;
////            counter++;
////            if(counter > 25)
////            {
////                counter = 0;
////            }
////        }
////
////        String passowrd = "abcdefghijklmnopqrstuvwxyz";
////
////        int strength = 0;
////
////        for(int i = 0; i < passowrd.length(); i++)
////        {
////            strength += getWeight(passowrd.charAt(i), list);
////
////        }
////
////        System.out.println(strength);
//
//        String password = "aaaaa";
//        int weight_a = 1;
//
//        System.out.println(getStrength(password, weight_a) );
//
//    }
//
//    public static int getStrength(String password, int weight_a) {
//        // Complete the function
//        int[] list = new int[26];
//        int counter = weight_a;
//        for(int i  = 0; i < list.length; i++)
//        {
//            list[i] = counter;
//            counter++;
//            if(counter > 25)
//            {
//                counter = 0;
//            }
//        }
//
//        int strength = 0;
//
//        for(int i = 0; i < password.length(); i++)
//        {
//            strength += list[password.charAt(i)-97];
//        }
//
//        return strength;
//    }


    @Test
    public void testValues()
    {


        int nGuests = 9;
        int k = 3;
        int n = 3;
        int m = 3;

        int[][] charms = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        int[][] gens = new int[][]{{10,20,30},{40,50,60},{70,80,90}};

        solve(k, nGuests, n, m, gens, charms);

        nGuests = 5;
        k = 1;
        n = 3;
        m = 3;

        charms = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        gens = new int[][]{{100,200},{500},{30,30}};

        solve(k, nGuests, n, m, gens, charms);

        nGuests = 6;
        k = 1;
        n = 3;
        m = 3;

        charms = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        gens = new int[][]{{200},{500,500},{30,30,30}};

        solve(k, nGuests, n, m, gens, charms);

        nGuests = 7;
        k = 1;
        n = 3;
        m = 3;

        charms = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        gens = new int[][]{{200},{500,500},{30,30,30,30}};

        solve(k, nGuests, n, m, gens, charms);

    }


    public void solve(final int k, final int nGuests, final int n, final int m, final int[][] gensTab, final int[][] charms)
    {
        // create get the gen values
        long[] gens = new long[nGuests];
        int[] tableInds = new int[nGuests];
        int i1 = 0;
        int tabN = 0;
        for(int[] ii:gensTab)
        {
            if(ii.length > (k*n))
            {
                System.out.println(-1);
                return;
            }
            for(int j:ii)
            {
                gens[i1] = j;
                tableInds[i1] = tabN;
                i1++;
            }
            tabN++;
        }

        // charms
        int nStudents = n*m;
        long[] charmsNK = new long[nStudents*k];
        int i2 = 0;
        int gN = 0;
        int[] groupsIns = new int[nStudents*k];
        for(int[] cg:charms)
        {

            for(int c:cg)
            {
                for(int i = 0; i < k; i++)
                {
                    charmsNK[i2] = c;
                    groupsIns[i2] = gN;
                    i2++;
                }
            }
            gN++;
        }

        // create cost matrix.
        long[][] costMatrix = new long[nGuests][nStudents*k];
        for(int i = 0; i < nGuests; i++)
        {
            for(int j = 0; j < charmsNK.length; j++)
            {
                costMatrix[i][j] = gens[i]*charmsNK[j];
            }
        }

        // solve matrix
        long d = 1;
        long[] ijd;
        int nGuestsServed = 0;
        int nGuestsServedValid = 0;
        long dSum = 0;

        final int[][] tableGroup = new int[gensTab.length][m];
        for(int[] vals:tableGroup)
        {
            Arrays.fill(vals,-1);
        }

        int tableId;
        int groupId;
        int assignedGroup;
        int[] firstSolution = new int[nGuests];
        for(int i = 0; i < nGuests; i++)
        {
            ijd = getMax(costMatrix);
            int iPos = (int)ijd[0];
            int jPos = (int)ijd[1];
            d = ijd[2];
            // check if that table has been visited by a different group
            tableId = tableInds[iPos];
            groupId = groupsIns[jPos];
            assignedGroup = tableGroup[tableId][groupId];

            zeroGuestStudent(iPos, jPos, costMatrix);
            if(assignedGroup == -1 || assignedGroup == groupId)
            {
                dSum += d;
                nGuestsServedValid++;
                firstSolution[i] = jPos;
            }
            nGuestsServed++;
        }




//        System.out.println("nGsV " + nGuestsServedValid + " nGs " + nGuestsServed + " dSum " + dSum);
        System.out.println(dSum);
    }

    private long[] getMax(long[][] costMatrix)
    {
        int iPos = 0;
        int jPos = 0;
        long maxV = 0;
        for(int i = 0; i < costMatrix.length; i++)
        {
            long[] ll = costMatrix[i];

            for(int j = 0; j < ll.length; j++ )
            {
                if(ll[j] > maxV)
                {
                    maxV = ll[j];
                    iPos = i;
                    jPos = j;
                }
            }
        }
        return new long[]{iPos,jPos,maxV};
    }


    private long getCost(long[][] costMatrix, int[] sol)
    {
        long cost = 0;
        for(int i = 0; i < sol.length; i++)
        {
            cost += costMatrix[i][sol[i]];
        }
        return cost;
    }

    private void zeroGuestStudent(int iPos, int jPos, long[][] costMatrix)
    {

        Arrays.fill(costMatrix[iPos], -1l);
        for(int i = 0; i < costMatrix.length; i++)
        {
            costMatrix[i][jPos] = -1l;
        }

    }

    private void zeroStudentTable(long[][] costMatrix)
    {

    }

}
