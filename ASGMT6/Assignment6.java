
public class Assignment6{
    public static void main(String[] args){
        BST<Integer> test_BST = new BST<>();

        test_BST.insert(50);
        test_BST.insert(30);
        test_BST.insert(20);
        test_BST.insert(20);
        test_BST.insert(40);
        test_BST.insert(70);
        test_BST.insert(60);
        test_BST.insert(80);

        //test_BST.inorder();
        //System.out.println();
        test_BST.preorder();
        System.out.println();


        
    }
    
}

