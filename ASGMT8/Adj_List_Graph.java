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

      LinkedList<Integer> paths_insertionSorted = new LinkedList<>();

      int[] knownDistance = new int[n];
      int[] nPaths = new int[n];
      Boolean[] traveled = new Boolean[n];

      LinkedList<Integer>[] prev = new LinkedList[n];

      for (int k = 0; k < n; k++) {
         prev[k] = new LinkedList<Integer>();
         nPaths[k] = -1;
         knownDistance[k] = (int) Integer.MAX_VALUE;
         traveled[k] = false;
      }

      knownDistance[start] = 0;
      nPaths[start] = 0;
      paths_insertionSorted.add(start);


      //BFS with a prev[] : LinkedList Array to track all current backpaths with same distance...
      while (!paths_insertionSorted.isEmpty() && traveled[end] == false) {

         int currParent = paths_insertionSorted.removeFirst();
         traveled[currParent] = true;

         for (Integer c : adj.get(currParent)) {

            if (knownDistance[c] == knownDistance[currParent] + 1) {
               //Node.c is a 'peer' to curr best paths//
               nPaths[c] += 1;

               prev[c].add(currParent);
            
            } else if (knownDistance[c] > knownDistance[currParent] + 1) {
               //Node.c is a best found path or first visit//
               nPaths[c] = 1;

               knownDistance[c] = knownDistance[currParent] + 1;
               paths_insertionSorted = insertInOrder(paths_insertionSorted, knownDistance, c);

               prev[c].clear();
               prev[c].add(currParent);

            }

         }
      }

      //Direct print Info...
      System.out.println("\n\nKnown Distance from " + start + " to " + end + " : " + knownDistance[end] + "");
      System.out.println("Known nPaths that distance = " + nPaths[end]);
      System.out.println();
      //Direct print Info...


      //[START] - Reconstructing a printable array using a backwards BFS using prev[ |V| ] & knownDistance[ |V| ]//
      int[][] pathsToEnd = new int[nPaths[end]][knownDistance[end] + 1]; 
      LinkedList<Integer> currChildren = new LinkedList<Integer>();
      currChildren.add(end);
      int curr = end;
      int row = 0;
      
      while(curr!=start){
         curr = currChildren.removeFirst();
         for(Integer k: prev[curr]){
            currChildren.add(k);
         }
         int col = knownDistance[curr];
         for(int j=nPaths[curr]; j>0; j--){
            pathsToEnd[row][col] = curr;
            row++;
         }
         if(nPaths[end]-row == 0){
            row = 0;
         }
      }
      //[END] - Reconstructing a printable array using a backwards BFS using prev[ |V| ] & knownDistance[ |V| ]//
      

      //Direct printing paths...
      for(int i = 0; i < pathsToEnd.length; i++) {
         for (int k = 0; k < pathsToEnd[i].length; k++) {
            if (k == 0) {
               System.out.print("" + pathsToEnd[i][k]);
            } else {
               System.out.print("-" + pathsToEnd[i][k]);
            }
         }
         System.out.println("");
      }
      //Direct printing paths...

   }

   private static LinkedList<Integer> insertInOrder(LinkedList<Integer> validPaths, int[] dist, int toNode) {
      int index = 0;

      while (index < validPaths.size() && dist[validPaths.get(index)] < dist[toNode]) {
         index++;
      }
      validPaths.add(index, toNode);
      return validPaths;
   }

   public void addEdge(int u, int v) {
      adj.get(u).add(v);
      adj.get(v).add(u); // this line should be un-commented, if graph is undirected
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
         scnr.close();
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

         scnrX.close();
         return x;
      } catch (FileNotFoundException e) {
         System.out.println("Error File Not Found...");
         return null;
      }
   }

}
