import java.io.FileNotFoundException;

public class Test_Adj {

   public static void main(String[] args) throws FileNotFoundException {

      Adj_List_Graph k = Adj_List_Graph.file_Intake();
      k.printGraph();

      k.shortestPath(0, 6);

   }
   /*
    * Input File Name : input-8-1.txt
    * 
    * Adjacency list of vertex 0 : head -> 1 -> 2 -> 3
    * Adjacency list of vertex 1 : head -> 0 -> 4
    * Adjacency list of vertex 2 : head -> 0 -> 4
    * Adjacency list of vertex 3 : head -> 0 -> 5
    * Adjacency list of vertex 4 : head -> 1 -> 2 -> 5 -> 6
    * Adjacency list of vertex 5 : head -> 3 -> 4 -> 6
    * Adjacency list of vertex 6 : head -> 4 -> 5
    * 
    * 
    * Known Distance from 0 to 6 : 3
    * Known paths that distance = 3
    * 
    * Distances[] = 0 1 1 1 2 2 3
    * Npaths[] = 1 1 1 1 2 1 3
    * 
    * 0-2-4-6
    * 0-1-4-6
    * 0-3-5-6
    */
}
