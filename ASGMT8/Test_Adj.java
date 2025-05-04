import java.io.FileNotFoundException;

public class Test_Adj {
    public static void main(String[] args) throws FileNotFoundException {
    
        // Prompt user to enter file name, read the graph from file
        // Builds the adjacency list representation for Graph G1
        Adj_List_Graph G1 = Adj_List_Graph.file_Intake();
        G1.shortestPath(0);
        
        // Prompt again for the second input file
        // Builds the adjacency list for Graph G2
        Adj_List_Graph G2 = Adj_List_Graph.file_Intake();
        G2.shortestPath(0);
    }
}
