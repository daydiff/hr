import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    static long mergeSort(int[] arr) {
        return mergeSort(arr, new int[arr.length], 0, arr.length - 1);
    }

    static long mergeSort(int[] arr, int[] tmp, int start, int end) {
        if (start == end) {
            return 0;
        }

        long count = 0;
        int middle = start + (end - start) / 2; // start + half of the length to prevent possible int overloading
        count += mergeSort(arr, tmp, start, middle);
        count += mergeSort(arr, tmp, middle + 1, end);
        count += mergeHalves(arr, tmp, start, end);

        return count;
    }

    static long mergeHalves(int[] arr, int[] tmp, int start, int end) {
        int middle = start + (end - start) / 2;
        int left = start;
        int right = middle + 1;
        int current = left;
        long count = 0;

        while (left <= middle && right <= end) {
            if (arr[left] <= arr[right]) {
                tmp[current++] = arr[left++];
            } else {
                tmp[current++] = arr[right++];
                count += middle - left + 1; // really tricky part!
                /**
                 * Every time the element goes from the right part it reverts all the elements rom the left,
                 * which are not merged yet.
                 * See 1: https://www.youtube.com/watch?v=4IvYaOY8Pxw
                 * See 2: https://www.youtube.com/watch?v=PLkuid82dbc
                 */
            }
        }

        System.arraycopy(arr, left, tmp, current, middle - left + 1);
        System.arraycopy(arr, right, tmp, current, end - right + 1);
        System.arraycopy(tmp, start, arr, start, end - start + 1);

        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int a0 = 0; a0 < t; a0++){
            int n = in.nextInt();
            int[] arr = new int[n];
            for(int arr_i = 0; arr_i < n; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            int[] orig = new int[n];
            System.arraycopy(arr, 0, orig, 0, n);
            System.out.println(mergeSort(arr));
        }
        in.close();
    }
}
