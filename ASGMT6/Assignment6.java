
public class Assignment6{
    public static void main(String[] args){
        BST<Integer> treeEX = new BST<>();

        treeEX.insert(50);
        treeEX.insert(30);
        treeEX.insert(20);
        treeEX.insert(20);
        treeEX.insert(40);
        treeEX.insert(70);
        treeEX.insert(60);
        treeEX.insert(80);

        //treeEX.inorder();
        System.out.println("Pre order");
        treeEX.preorder();

        System.out.println("\nLeft rotate root");
        treeEX.rotateL_Root();
        treeEX.preorder();

        System.out.println("\nRight rotate root");
        treeEX.rotateR_Root();
        treeEX.preorder();


        
    }
    /* Assignment6' 
        Pre order
        Value :50       Size Node : 8
        Value :30       Size Node : 4
        Value :20       Size Node : 2
        Value :20       Size Node : 1
        Value :40       Size Node : 1
        Value :70       Size Node : 3
        Value :60       Size Node : 1
        Value :80       Size Node : 1

        Left rotate root
        Value :70       Size Node : 8
        Value :50       Size Node : 6
        Value :30       Size Node : 4
        Value :20       Size Node : 2
        Value :20       Size Node : 1
        Value :40       Size Node : 1
        Value :60       Size Node : 1
        Value :80       Size Node : 1

        Right rotate root
        Value :50       Size Node : 8
        Value :30       Size Node : 4
        Value :20       Size Node : 2
        Value :20       Size Node : 1
        Value :40       Size Node : 1
        Value :70       Size Node : 3
        Value :60       Size Node : 1
        Value :80       Size Node : 1
        \ASGMT6> 
     */
    
}

