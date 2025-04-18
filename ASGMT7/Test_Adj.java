import java.io.FileNotFoundException;

public class Test_Adj {

   public static void main(String[] args) throws FileNotFoundException {

      Adj_List_Graph k = Adj_List_Graph.file_Intake();
      System.out.print("\nOrigonal Graph");
      k.printGraph();

      System.out.print("\n\nGraph^2");
      Adj_List_Graph doubled = k.graphSquared();
      doubled.printGraph();

   }

}
/** 'Test_Adj'
Input File Name : input-7-2.txt

Origonal Graph
Adjacency list of vertex 0  :  head -> 1
Adjacency list of vertex 1  :  head -> 2 -> 3
Adjacency list of vertex 2  :  head
Adjacency list of vertex 3  :  head -> 4
Adjacency list of vertex 4  :  head

Graph^2
Adjacency list of vertex 0  :  head -> 1 -> 2 -> 3
Adjacency list of vertex 1  :  head -> 2 -> 3 -> 4
Adjacency list of vertex 2  :  head
Adjacency list of vertex 3  :  head -> 4
Adjacency list of vertex 4  :  head

PS C:\Users\Rwils\Desktop\Assign7>
 */
