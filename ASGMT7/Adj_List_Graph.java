import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

// Class to represent a directed graph using adjacency lists
public class Adj_List_Graph {

   int n;// Number of vertices
   ArrayList<ArrayList<Integer>> adj; // Adjacency list representation

   // Constructor: initializes the graph with 'no_nodes' vertices
   Adj_List_Graph(int no_nodes) {
      n = no_nodes;

      adj = new ArrayList<>(n);

      // Create an empty list for each vertex
      for (int i = 0; i < n; i++) {
         adj.add(new ArrayList<>());
      }
   }

// Returns a new graph that is the square of this graph (G^2)
public Adj_List_Graph graphSquared() {
   Adj_List_Graph squareGraph = new Adj_List_Graph(n);
   LinkedList<Integer> pathNext = new LinkedList<>();
   int[] pathLength = new int[n];

   for (int originBFS = 0; originBFS < n; ++originBFS) {
      pathNext.clear();
      pathNext.add(originBFS);

      // Use a Set to collect unique neighbors reachable in <=2 steps
      java.util.Set<Integer> uniqueNeighbors = new java.util.HashSet<>();

      for (int k = 0; k < n; k++) {
         pathLength[k] = -1;
      }
      pathLength[originBFS] = 0;

      while (!pathNext.isEmpty()) {
         int fromNode = pathNext.removeFirst();

         if (pathLength[fromNode] < 2) {
            for (int j = 0; j < adj.get(fromNode).size(); j++) {
               int toNode = adj.get(fromNode).get(j);

               if (pathLength[toNode] == -1) {
                  pathLength[toNode] = pathLength[fromNode] + 1;
                  pathNext.addLast(toNode);
               }

               // Regardless of visitation, add if within 2 steps
               if (pathLength[fromNode] + 1 <= 2) {
                  uniqueNeighbors.add(toNode);
               }
            }
         }
      }

      // Add all unique neighbors to the resulting graph
      for (int neighbor : uniqueNeighbors) {
         squareGraph.addEdge(originBFS, neighbor);
      }
   }
   return squareGraph;
}

   public void addEdge(int u, int v) {
      adj.get(u).add(v);

      // this line should be un-commented, if graph is undirected
      // adj.get(v).add(u);
   }

   // Prints the graph in adjacency list format
   public void printGraph() {
      for (int i = 0; i < n; i++) {

         System.out.print("\nAdjacency list of vertex " + i + "  :  ");
         System.out.print("head");

         for (int j = 0; j < adj.get(i).size(); j++) {
            System.out.print(" -> " + adj.get(i).get(j));
         }
         // System.out.println();
      }
   }

   // Reads a graph from a file
   public static Adj_List_Graph file_Intake() throws FileNotFoundException {
      Scanner scnr = new Scanner(System.in);

      System.out.print("Input File Name : ");
      String userVar = scnr.next();

      try {
         while (!new File(userVar).exists()) {
            scnr.nextLine();
            System.out.print("Input File Name : ");
            userVar = scnr.next();
         }
         // System.out.println();
         Scanner scnrX = new Scanner(new File(userVar));

         int n = scnrX.nextInt();
         Adj_List_Graph x = new Adj_List_Graph(n); // Create new graph

         int k = 0;

         // Read the flattened adjacency matrix (n^2 numbers)
         while (scnrX.hasNextInt()) {
            int key = k / n; // Row index (from-node)
            int in = scnrX.nextInt(); 
            if (in == 1) {
               x.addEdge(key, k % n); // Column index (to-node)
            }
            k++;
         }
         return x;
      } catch (FileNotFoundException e) {
         System.out.println("Error File Not Found...");
         return null;
      }
   }
}
