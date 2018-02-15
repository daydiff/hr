import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    public static int calcWays(int n) {
        int[] calculated = new int[n + 1];
        return calcWaysWithMemo(n, calculated);
    }
    
    public static int calcWaysWithMemo(int n, int[] calculated) {
        if (n < 1) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 4;
        if (calculated[n] > 0) return calculated[n];
        int ways = 0;
        ways += calcWaysWithMemo(n - 1, calculated);
        ways += calcWaysWithMemo(n - 2, calculated);
        ways += calcWaysWithMemo(n - 3, calculated);
        calculated[n] = ways;
        return ways;
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
