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
}