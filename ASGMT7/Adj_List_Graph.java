import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Adj_List_Graph {


   int n;
   ArrayList<ArrayList<Integer>> adj;


   Adj_List_Graph(int no_nodes) {
      n = no_nodes;

      adj = new ArrayList<>(n);

      for (int i = 0; i < n; i++) {
         adj.add(new ArrayList<>());
      }

   }


   public Adj_List_Graph graphSquared() {

      Adj_List_Graph squareGraph = new Adj_List_Graph(n);
      LinkedList<Integer> pathNext = new LinkedList<>();
      int[] pathLength = new int[n];

      for (int originBFS = 0; originBFS < n; ++originBFS) {

         pathNext.clear();
         pathNext.add(originBFS);

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

                     if (pathLength[toNode] <= 2) {
                        squareGraph.addEdge(originBFS, adj.get(fromNode).get(j));
                     }

                  }

               }
            }

         }

      }

      return squareGraph;
   }


   public void addEdge(int u, int v) {
      adj.get(u).add(v);

      // this line should be un-commented, if graph is undirected
      // adj.get(v).add(u);
   }


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
         Adj_List_Graph x = new Adj_List_Graph(n);

         int k = 0;

         while (scnrX.hasNextInt()) {
            int key = k / n;
            int in = scnrX.nextInt();
            // System.out.println("KEY = "+key+" : IN : " +in+" : k = "+k+"");
            if (in == 1) {
               x.addEdge(key, k % n);
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
