import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static void solve(int[] arr, int money) {
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < arr.length; i++) {
            int complement = money - arr[i];
            if (map.containsKey(complement)) {
                printSolution(map.get(complement), i);
                break;
            } else {
                map.put(arr[i], i);
            }
        }
    }
    
    static void printSolution(int first, int second) {
        System.out.println(++first + " " + ++second);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int money = in.nextInt();
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            solve(arr, money);
        }
        in.close();
    }
}
