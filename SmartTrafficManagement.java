import java.util.*;

class Edge {
    int to, weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

class Graph {
    int V;
    List<List<Edge>> adj;

    Graph(int V) {
        this.V = V;
        adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int u, int v, int weight) {
        adj.get(u).add(new Edge(v, weight));
        adj.get(v).add(new Edge(u, weight)); 
    }

    void updateEdgeWeight(int u, int v, int newWeight) {
        for (Edge edge : adj.get(u)) {
            if (edge.to == v) {
                edge.weight = newWeight;
                break;
            }
        }
        for (Edge edge : adj.get(v)) {
            if (edge.to == u) {
                edge.weight = newWeight;
                break;
            }
        }
    }

    void dijkstra(int src, Set<Integer> hospitals) {
        int[] dist = new int[V];
        int[] parent = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, src});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0];
            int u = curr[1];

            if (d > dist[u]) continue;

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int w = edge.weight;
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    parent[v] = u;
                    pq.add(new int[]{dist[v], v});
                }
            }
        }

        int minDist = Integer.MAX_VALUE;
        int nearestHospital = -1;
        for (int h : hospitals) {
            if (dist[h] < minDist) {
                minDist = dist[h];
                nearestHospital = h;
            }
        }

        if (nearestHospital == -1) {
            System.out.println("No hospital reachable!");
            return;
        }

        System.out.println("Nearest hospital: Node " + nearestHospital);
        System.out.println("Shortest travel time: " + minDist + " minutes");

        List<Integer> path = new ArrayList<>();
        for (int at = nearestHospital; at != -1; at = parent[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.print("Optimal path: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i != path.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
}

public class AmbulancePathFinder {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of intersections (nodes): ");
        int V = sc.nextInt();

        Graph g = new Graph(V);

        System.out.print("Enter number of roads: ");
        int E = sc.nextInt();

        System.out.println("Enter road details (u v travelTime):");
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();
            g.addEdge(u, v, w);
        }

        System.out.print("Enter ambulance source node: ");
        int src = sc.nextInt();

        System.out.print("Enter number of hospitals: ");
        int hCount = sc.nextInt();

        Set<Integer> hospitals = new HashSet<>();
        System.out.println("Enter hospital node indices:");
        for (int i = 0; i < hCount; i++) {
            hospitals.add(sc.nextInt());
        }

        g.dijkstra(src, hospitals);

        System.out.println("\nUpdate traffic on road (u v newTravelTime):");
        System.out.print("Enter u v newWeight: ");
        int u = sc.nextInt();
        int v = sc.nextInt();
        int newW = sc.nextInt();

        g.updateEdgeWeight(u, v, newW);

        System.out.println("Recalculating shortest path after traffic update...");
        g.dijkstra(src, hospitals);

        sc.close();
    }
}
