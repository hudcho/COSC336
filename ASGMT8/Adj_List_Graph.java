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

   public void shortestPath(int start, int end) {
      // Called using indexing nodes labeled [0, 1, 2, (n-1)] 
      // Lab spec [ 1,2,3,...,n ] : Fixed on lines with comment 
      // '// FIXs print VALUES'

      LinkedList<Integer> bfs_Paths = new LinkedList<>();

      int[] distance = new int[n];
      int[] nPaths = new int[n];
      Boolean[] traveled = new Boolean[n];

      LinkedList<Integer>[] prev = new LinkedList[n];

      for (int k = 0; k < n; k++) {
         prev[k] = new LinkedList<Integer>();
         nPaths[k] = 0;
         distance[k] = (int) Integer.MAX_VALUE;
         traveled[k] = false;
      }

      distance[start] = 0;
      nPaths[start] = 1;
      bfs_Paths.add(start);

      System.out.println("");

      while (!bfs_Paths.isEmpty() && traveled[end] == false) {

         int currParent = bfs_Paths.removeFirst();
         traveled[currParent] = true;

         // All shortest paths to curr are found...
         if (currParent != end) {
            // iff (curr == end) Break ==> shortest path to curr is known//

            for (Integer c : adj.get(currParent)) {

               if (distance[c] == distance[currParent] + 1) {
                  // Node.c is a 'peer' to curr best paths//
                  nPaths[c] = nPaths[currParent] + nPaths[c];

                  prev[c].add(currParent);

               } else if (distance[c] > distance[currParent] + 1) {
                  // Node.c is a best found path or first visit//
                  nPaths[c] = nPaths[currParent];

                  distance[c] = distance[currParent] + 1;
                  bfs_Paths.addLast(c);

                  prev[c].clear();
                  prev[c].add(currParent);

               }

            }
         }

      }

      // Direct print Info...
      int start1 = start+1;// FIXs print VALUES
      int end1 = end+1;// FIXs print VALUES
      System.out.println("\nKnown Distance from " + start1 + " to " + end1 + " : " + distance[end] + "");
      System.out.println("Known paths that distance = " + nPaths[end]);
      System.out.print("\nDistances[] = ");
      for (int k : distance) {
         if (k < Integer.MAX_VALUE) {
            System.out.print(k + " ");
         } else {
            System.out.print(" - ");
         }

      }
      System.out.print("\nNpaths[] = ");
      for (int k : nPaths) {
         System.out.print(k + " ");
      }
      System.out.println("");
      // Direct print Info...


      // BFS ON PREV[] TO PRINT PATHS...
      int[][] pathsToEnd = new int[nPaths[end]][distance[end] + 1];
      LinkedList<Integer> queBFS = new LinkedList<>();
      queBFS.add(end);
      int row = 0;
      while (!queBFS.isEmpty()) {
         int curr = queBFS.removeFirst();

         for (int k : prev[curr]) {

            for (int f = nPaths[k]; f > 0; f--) {
               // curr is a parent on path with a previous node//
               pathsToEnd[row][distance[curr]] = curr + 1; // FIXs print VALUES

               if (k == start) { // start doesnt have prev nodes //
                  pathsToEnd[row][distance[curr] - 1] = k + 1; // FIXs print VALUES
               }

               row++;

            }
            if (row == pathsToEnd.length) {
               row = 0;
            }
            queBFS.addLast(k);
         }

      }
      // BFS ON PREV[] TO PRINT PATHS...

      printArray(pathsToEnd);
   }

   private static void printArray(int[][] graphEX) {

      System.out.println("");
      for (int i = 0; i < graphEX.length; i++) {
         for (int k = 0; k < graphEX[i].length; k++) {
            if (k == 0) {
               System.out.print("" + graphEX[i][k]);
            } else {
               System.out.print("-" + graphEX[i][k]);
            }
         }
         System.out.println("");
      }

   }

   public void addEdge(int u, int v) {
      adj.get(u).add(v);
      adj.get(v).add(u); // graph is undirected
   }

   // FIX print VALUES +1 Lab Index [ 1, 2, ... ,n ]
   public void printGraph() {
      for (int i = 0; i < n; i++) {

         int node = i + 1; // FIX print VALUES

         System.out.print("\nAdjacency list of vertex " + node + "  :  ");
         System.out.print("head");

         for (int j = 0; j < adj.get(i).size(); j++) {

            node = adj.get(i).get(j) + 1; // FIX print VALUES
            System.out.print(" -> " + node); // FIX print VALUES

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