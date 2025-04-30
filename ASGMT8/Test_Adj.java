import java.io.FileNotFoundException;

public class Test_Adj {

   public static void main(String[] args) throws FileNotFoundException {

      Adj_List_Graph k = Adj_List_Graph.file_Intake();
      k.printGraph();

      k.shortestPath(0, 6);

      System.out.println("");

      Adj_List_Graph f = Adj_List_Graph.file_Intake();
      f.printGraph();

      f.shortestPath(0, 9);

   }
      /*
   Input File Name : input-8-1.txt

   Adjacency list of vertex 1  :  head -> 2 -> 3 -> 4
   Adjacency list of vertex 2  :  head -> 1 -> 5
   Adjacency list of vertex 3  :  head -> 1 -> 5
   Adjacency list of vertex 4  :  head -> 1 -> 6
   Adjacency list of vertex 5  :  head -> 2 -> 3 -> 6 -> 7
   Adjacency list of vertex 6  :  head -> 4 -> 5 -> 7
   Adjacency list of vertex 7  :  head -> 5 -> 6

   Known Distance from 1 to 7 : 3
   Known paths that distance = 3

   Distances[] = 0 1 1 1 2 2 3 
   Npaths[] = 1 1 1 1 2 1 3 

   1-2-5-7
   1-3-5-7
   1-4-6-7

   Input File Name : input-8-2.txt

   Adjacency list of vertex 1  :  head -> 2 -> 3 -> 4 -> 5 -> 6
   Adjacency list of vertex 2  :  head -> 1 -> 7
   Adjacency list of vertex 3  :  head -> 1 -> 7
   Adjacency list of vertex 4  :  head -> 1 -> 7
   Adjacency list of vertex 5  :  head -> 1 -> 7
   Adjacency list of vertex 6  :  head -> 1 -> 7
   Adjacency list of vertex 7  :  head -> 2 -> 3 -> 4 -> 5 -> 6 -> 8 -> 9
   Adjacency list of vertex 8  :  head -> 7 -> 10
   Adjacency list of vertex 9  :  head -> 7 -> 10
   Adjacency list of vertex 10  :  head -> 8 -> 9

   Known Distance from 1 to 10 : 4
   Known paths that distance = 10

   Distances[] = 0 1 1 1 1 1 2 3 3 4
   Npaths[] = 1 1 1 1 1 1 5 5 5 10

   1-2-7-8-10
   1-3-7-8-10
   1-4-7-8-10
   1-5-7-8-10
   1-6-7-8-10
   1-2-7-9-10
   1-3-7-9-10
   1-4-7-9-10
   1-5-7-9-10
   1-6-7-9-10
    */
}