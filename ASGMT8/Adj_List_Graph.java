import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

// Class to represent an undirected graph using adjacency lists
public class Adj_List_Graph {

    int n; // Number of vertices
    ArrayList<ArrayList<Integer>> adj; // Adjacency list representation

    // Constructor: initializes graph with 'num_nodes' vertices
    Adj_List_Graph(int num_nodes) {
        n = num_nodes;
        adj = new ArrayList<>(n);

        // Create an empty list for each vertex
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Performs BFS to compute shortest path lengths and path counts from start node
    public void shortestPath(int start) {
        LinkedList<Integer> queue = new LinkedList<>();
        int[] distance = new int[n];   // distance[i] = shortest path length from start to i
        int[] nPaths = new int[n];     // nPaths[i] = number of shortest paths from start to i
        boolean[] visited = new boolean[n]; // unused but initialized for possible extensions

        // Initialize distances and path counts
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            nPaths[i] = 0;
            visited[i] = false;
        }

        // Initialize the starting node
        distance[start] = 0;
        nPaths[start] = 1;
        queue.add(start);

        // Standard BFS loop
        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // Explore all neighbors of current node
            for (int neighbor : adj.get(curr)) {
                if (distance[neighbor] == distance[curr] + 1) {
                    // Found another shortest path to neighbor
                    nPaths[neighbor] += nPaths[curr];
                } else if (distance[neighbor] > distance[curr] + 1) {
                    // Found a shorter path to neighbor
                    distance[neighbor] = distance[curr] + 1;
                    nPaths[neighbor] = nPaths[curr];
                    queue.add(neighbor);
                }
            }
        }

        // Output the shortest path lengths
        System.out.print("Distances[] = ");
        for (int d : distance) {
            System.out.print((d < Integer.MAX_VALUE ? d : "-") + " ");
        }
        System.out.println();

        // Output the number of shortest paths
        System.out.print("Npaths[] = ");
        for (int p : nPaths) {
            System.out.print(p + " ");
        }
        System.out.println();
    }

    // Adds an undirected edge between vertex u and vertex v
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u); // undirected edge
    }

    // Reads a graph from file containing a flattened adjacency matrix
    public static Adj_List_Graph file_Intake() throws FileNotFoundException {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Input File Name: ");
        String userVar = scnr.next();

        // Keep prompting until a valid file is entered
        while (!new File(userVar).exists()) {
            System.out.println("File not found. Try again.");
            System.out.print("Input File Name: ");
            userVar = scnr.next();
        }

        Scanner fileScanner = new Scanner(new File(userVar));
        int num_nodes = fileScanner.nextInt(); // number of vertices
        Adj_List_Graph graph = new Adj_List_Graph(num_nodes); // create graph

        int k = 0;
        // Parse the flattened n x n adjacency matrix
        while (fileScanner.hasNextInt()) {
            int from = k / num_nodes;
            int to = k % num_nodes;
            int val = fileScanner.nextInt();

            // Only add undirected edge once
            if (val == 1 && from < to) {
                graph.addEdge(from, to);
            }
            k++;
        }

        return graph;
    }
}
