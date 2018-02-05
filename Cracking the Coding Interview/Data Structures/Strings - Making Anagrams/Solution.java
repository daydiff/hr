import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static int[] countLetters(String str) {
        int[] letters = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int letter = str.charAt(i) - (int) 'a';
            letters[letter]++;
        }
        return letters;
    }

    public static int diff(int[] a, int[] b) {
        int sum = 0;
        for (int i = 0; i < 26; i++) {
            sum += Math.abs(a[i] - b[i]);
        }
        return sum;
    }

    public static int numberNeeded(String first, String second) {
        int[] a = countLetters(first);
        int[] b = countLetters(second);
        return diff(a, b);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        System.out.println(numberNeeded(a, b));
    }
}
