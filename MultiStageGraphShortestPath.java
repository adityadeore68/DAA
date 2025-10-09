import java.util.*;

public class ShortestPathDAG {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int n = sc.nextInt();

        int[][] graph = new int[n][n];

        System.out.println("Enter adjacency matrix (0 if no edge, weight otherwise):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
            }
        }

        int source = 0;           
        int destination = n - 1;  

        int[] cost = new int[n];  
        int[] next = new int[n];  

        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(next, -1);

        cost[destination] = 0; 

        for (int i = n - 2; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;
            int minVertex = -1;

            for (int j = i + 1; j < n; j++) {
                if (graph[i][j] > 0 && cost[j] != Integer.MAX_VALUE) {
                    int tempCost = graph[i][j] + cost[j];
                    if (tempCost < minCost) {
                        minCost = tempCost;
                        minVertex = j;
                    }
                }
            }

            cost[i] = minCost;
            next[i] = minVertex;
        }

        System.out.println("Shortest distance from " + source + " to " + destination + " = " + cost[source]);

        System.out.print("Path: ");
        int v = source;
        System.out.print(v);

        while (v != destination && next[v] != -1) {
            v = next[v];
            System.out.print(" -> " + v);
        }

        System.out.println();
        sc.close();
    }
}
