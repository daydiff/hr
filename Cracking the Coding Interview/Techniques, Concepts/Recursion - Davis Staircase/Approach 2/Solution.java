import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int calcWays(int n) {
        if (n < 2) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;

        int[] ways = new int[] {1,2,4};

        for (int i = 4; i < n; i++) {
            int idx = (i - 1) % 3;
            ways[idx] = ways[0] + ways[1] + ways[2];
        }
        return ways[0] + ways[1] + ways[2];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        for(int a0 = 0; a0 < s; a0++){
            int n = in.nextInt();
            int ways = calcWays(n);
            System.out.println(ways);
        }
    }
}
