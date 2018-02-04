import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static Graph graph = new Graph();

    public static int getBiggestRegion(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        populateGraph(matrix, rows, cols);
        return getBiggestRegionSize(rows, cols);
    }

    private static void populateGraph(int[][] matrix, int rows, int cols) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int val = matrix[i][j];
                if (val == 0) {
                    continue;
                }

                int id = i * cols + j;
                graph.addNode(id);

                if (j > 0) {
                    int leftVal = matrix[i][j - 1];
                    if (leftVal == 1) {
                        int leftId = i * cols + j - 1;
                        graph.addAjacent(id, leftId);
                    }
                }
                if (i > 0) {
                    int topVal = matrix[i - 1][j];
                    if (topVal == 1) {
                        int topId = (i - 1) * cols + j;
                        graph.addAjacent(id, topId);
                    }
                }
                if (i > 0 && j > 0) {
                    int leftTopVal = matrix[i - 1][j - 1];
                    if (leftTopVal == 1) {
                        int leftTopId = (i - 1) * cols + j - 1;
                        graph.addAjacent(id, leftTopId);
                    }
                }
                if (i > 0 && j < cols - 1) {
                    int rightTopVal = matrix[i - 1][j + 1];
                    if (rightTopVal == 1) {
                        int rightTopId = (i - 1) * cols + j + 1;
                        graph.addAjacent(id, rightTopId);
                    }
                }
            }
        }
    }

    private static int getBiggestRegionSize(int rows, int cols) {
        int size = rows * cols - 1;
        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<Integer> regions = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < size; i++) {
            if (visited.contains(i)) {
                continue;
            }
            if (!graph.doesNodeExist(i)) {
                continue;
            }

            int regionSize = 1;
            for (int j = i + 1; j <= size; j++) {
                if (!graph.doesNodeExist(j)) {
                    visited.add(j);
                    continue;
                }
                if (graph.hasPathDFS(i, j)) {
                    visited.add(j);
                    regionSize++;
                }
            }
            regions.add(regionSize);
        }
        return regions.peek();
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

class Graph {
    private HashMap<Integer, Node> nodes = new HashMap<>();

    class Node {
        public int id;
        public LinkedList<Node> ajacent = new LinkedList<>();

        public Node(int id) {
            this.id = id;
        }
    }

    public void addNode(int id) {
        if (!doesNodeExist(id)) {
            Node node = new Node(id);
            nodes.put(id, node);
        }
    }

    public boolean doesNodeExist(int id) {
        return nodes.containsKey(id);
    }

    private Node getNode(int id) {
        return nodes.get(id);
    }


    public void addAjacent(int a, int b) {
        Node nodeA = doesNodeExist(a) ? getNode(a) : new Node(a);
        Node nodeB = doesNodeExist(b) ? getNode(b) : new Node(b);
        nodeA.ajacent.add(nodeB);
        nodeB.ajacent.add(nodeA);
    }

    public boolean hasPathDFS(int src, int dst) {
        Node srcNode = getNode(src);
        Node dstNode = getNode(dst);
        HashSet<Integer> visited = new HashSet<>();
        return hasPathDFS(srcNode, dstNode, visited);
    }

    private boolean hasPathDFS(Node src, Node dst, HashSet<Integer> visited) {
        if (visited.contains(src.id)) {
            return false;
        }
        visited.add(src.id);

        if (src.id == dst.id) {
            return true;
        }

        for (Node node : src.ajacent) {
            if (hasPathDFS(node, dst, visited)) {
                return true;
            }
        }
        return false;
    }
}
