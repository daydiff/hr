import java.util.*;

public class Solution {


    public static int fibonacci(int n) {
        int[] fib = {0,1};
        for (int i = 2; i <= n; i++) {
			fib[i % 2] = fib[1] + fib[0];
		}
		return fib[n % 2];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        System.out.println(fibonacci(n));
    }
}
