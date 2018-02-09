import java.util.*;

public class Solution {
    public static class Graph {
        int EDGE_WEIGHT = 6;
        int size;
        HashMap<Integer, Node> nodes;

        class Node {
            public int id;
            public LinkedList<Node> ajacent = new LinkedList<>();

            public Node(int id) {
                this.id = id;
            }
        }

        public Graph(int size) {
            nodes = new HashMap<>();
            this.size = size;
        }

        public void addEdge(int first, int second) {
            Node firstNode = nodes.containsKey(first) ? nodes.get(first) : new Node(first);
            Node secondNode = nodes.containsKey(second) ? nodes.get(second) : new Node(second);
            if (!nodes.containsKey(first)) {
                nodes.put(first, firstNode);
            }
            if (!nodes.containsKey(second)) {
                nodes.put(second, secondNode);
            }
            firstNode.ajacent.add(secondNode);
            secondNode.ajacent.add(firstNode);
        }

        public int[] shortestReach(int startId) { // 0 indexed
            return calcPathesBFS(startId);
        }

        private Node getNode(int id) {
            return nodes.get(id);
        }

        private int[] calcPathesBFS(int start) {
            int[] pathes = new int[size];
            Arrays.fill(pathes, -1);

            Node node = getNode(start);
            if (node == null) return pathes;

            LinkedList<Node> nextToVisit = new LinkedList<>();
            pathes[node.id] = 0;
            nextToVisit.add(node);

            while (!nextToVisit.isEmpty()) {
                node = nextToVisit.remove();
                for (Node next : node.ajacent) {
                    if (nextToVisit.contains(next)) continue;
                    if (pathes[next.id] != -1) continue;
                    pathes[next.id] = pathes[node.id] + EDGE_WEIGHT;
                    nextToVisit.add(next);
                }
            }

            return pathes;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            // Create a graph of size n where each edge weight is 6:
            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            // read and set edges
            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                // add each edge to the graph
                graph.addEdge(u, v);
            }

            // Find shortest reach from node s
            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }
}
