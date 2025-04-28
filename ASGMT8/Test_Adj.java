import java.io.FileNotFoundException;

public class Test_Adj {

   public static void main(String[] args) throws FileNotFoundException {

      Adj_List_Graph k = Adj_List_Graph.file_Intake();
      System.out.print("\nOrigonal Graph");
      k.printGraph();

      k.shortestPath(0, 2);
      


   } 
   /*
      Input File Name : input-7-3.txt

      Origonal Graph
      Adjacency list of vertex 0  :  head -> 1
      Adjacency list of vertex 1  :  head -> 0 -> 2 -> 3
      Adjacency list of vertex 2  :  head -> 1 -> 3 -> 5
      Adjacency list of vertex 3  :  head -> 1 -> 2 -> 4 -> 5
      Adjacency list of vertex 4  :  head -> 3 -> 6
      Adjacency list of vertex 5  :  head -> 2 -> 3
      Adjacency list of vertex 6  :  head -> 4

      Known Distance from 0 to 5 : 3
      Known nPaths that distance = 2

      0-1-3-5
      0-1-2-5
    */

}
