import java.io.FileNotFoundException;

public class Test_Adj {

   public static void main(String[] args) throws FileNotFoundException {

      Adj_List_Graph k = Adj_List_Graph.file_Intake();
      System.out.print("\nOriginal Graph");
      k.printGraph();

      System.out.print("\n\nGraph^2");
      Adj_List_Graph doubled = k.graphSquared();
      doubled.printGraph();
   }
}
