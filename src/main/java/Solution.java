import java.io.*;
import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

public class Solution {

    /*
     * Complete the guestTable function below.
     */
    static void guestTable(int[] generosities) {
        /*
         * Write your code here.
         */

    }

    /*
     * Complete the solve function below.
     */
    static void solve() {
        /*
         * Write your code here.
         */

    }

    private static final Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {

        int tc = Integer.parseInt(scanner.next());
        for (int tcItr = 0; tcItr < tc; tcItr++) {
            String[] mn = scanner.nextLine().split(" ");

            int m = Integer.parseInt(mn[0].trim());

            int n = Integer.parseInt(mn[1].trim());

            int[][] charm = new int[m][n];

            for (int charmRowItr = 0; charmRowItr < m; charmRowItr++) {
                String[] charmRowItems = scanner.nextLine().split(" ");

                for (int charmColumnItr = 0; charmColumnItr < n; charmColumnItr++) {
                    int charmItem = Integer.parseInt(charmRowItems[charmColumnItr].trim());
                    charm[charmRowItr][charmColumnItr] = charmItem;
                }
            }

            int t = Integer.parseInt(scanner.nextLine().trim());

            for (int tItr = 0; tItr < t; tItr++) {
                int x = Integer.parseInt(scanner.nextLine().trim());

                int[] generosities = new int[x];

                String[] generositiesItems = scanner.nextLine().split(" ");

                for (int generositiesItr = 0; generositiesItr < x; generositiesItr++) {
                    int generositiesItem = Integer.parseInt(generositiesItems[generositiesItr].trim());
                    generosities[generositiesItr] = generositiesItem;
                }

                guestTable(generosities);
            }

            int k = Integer.parseInt(scanner.nextLine().trim());

            solve();
        }
    }
}