import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int swaps = 0;
    
    public static int[] sort(int[] a, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a[j] > a[j + 1]) {
                    a = swap(a, j, j + 1);
                    swaps++;
                }
            }
        }
        return a;
    }
    
    public static int[] swap(int[] a, int one, int two) {
        int tmp = a[one];
        a[one] = a[two];
        a[two] = tmp;
        return a;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int a[] = new int[n];
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
        }
        a = sort(a, n);
        System.out.println("Array is sorted in " + swaps + " swaps.");
        System.out.println("First Element: " + a[0]);
        System.out.println("Last Element: " + a[n - 1]);
        
        //Array is sorted in 0 swaps.
        //First Element: 1
        //Last Element: 3
    }
}
