import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static int getBiggestRegion(int[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, countCells(matrix, i, j));
            }
        }
        return max;
    }

    private static int countCells(int[][] matrix, int row, int col) {
        if (row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length) return 0;
        if (matrix[row][col] == 0) return 0;
        int count = matrix[row][col]--;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i == row && j == col) continue;
                count += countCells(matrix, i, j);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }

        System.out.println(getBiggestRegion(grid));
    }
}
