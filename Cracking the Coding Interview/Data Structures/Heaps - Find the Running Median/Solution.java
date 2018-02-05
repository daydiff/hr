import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    private static PriorityQueue<Integer> minHeap;
    private static PriorityQueue<Integer> maxHeap;
    private static int step = 0;

    private static double getMedian() {
        if (minHeap.size() == maxHeap.size()) {
            double value = (double)(minHeap.peek()+maxHeap.peek()) / 2;
            return round(value, 1);
        }

        if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private static void add(int value) {
        if (maxHeap.size()==0 || value < maxHeap.peek()) {
            maxHeap.add(value);
        } else {
            minHeap.add(value);
        }
        rebalance();
        step++;
    }

    private static void rebalance() {
        if (Math.abs(minHeap.size() - maxHeap.size()) < 2) {
            return;
        }
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        } else {
            minHeap.add(maxHeap.poll());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        minHeap = new PriorityQueue<Integer>(n);
        maxHeap = new PriorityQueue<Integer>(n, Collections.reverseOrder());
        for(int a_i=0; a_i < n; a_i++){
            add(in.nextInt());
            double median = getMedian();
            System.out.println(median);
        }
    }
}
