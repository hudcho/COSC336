import java.util.*;
import java.io.*;

class Node {
    int key;
    int size;
    Node left, right;

    public Node(int item)
    {
        key = item;
        size = 1;
        left = right = null;
    }
}

class Assignment6 {

    // A utility function to insert a new node
    // with the given key
    static Node insert(Node root, int key)
    {

        // If the tree is empty, return a new node
        if (root == null)
            return new Node(key);

        // Otherwise, recur down the tree
        if (key <= root.key)
            root.left = insert(root.left, key);
        else
            root.right = insert(root.right, key);

        //increments root size by + size of left subtree and size of right subtree
        root.size = 1 + getSize(root.left) + getSize(root.right);
        // Return the (unchanged) node pointer
        return root;
    }

    static Node rotateRight(Node root) {
        if (root == null || root.left == null)
            return root;

        //creates two new nodes to essentially act as temp nodes
        Node newRoot = root.left;
        Node temp = newRoot.right;

        //actual rotation step.
        newRoot.right = root;
        root.left = temp;

        //readjusts size
        root.size = 1 + getSize(root.left) + getSize(root.right);
        newRoot.size = 1 + getSize(newRoot.left) + getSize(newRoot.right);


        return newRoot;
    }

    static Node rotateLeft(Node root) {
        if (root == null || root.right == null)
            return root;

        //creates two new nodes to essentially act as temp nodes
        Node newRoot = root.right;
        Node temp = newRoot.left;

        //actual rotation step.
        newRoot.left = root;
        root.right = temp;

        //readjusts size
        root.size = 1 + getSize(root.left) + getSize(root.right);
        newRoot.size = 1 + getSize(newRoot.left) + getSize(newRoot.right);


        return newRoot;
    }

    static int getSize(Node node) {
        return (node == null) ? 0 : node.size;
    }

    // A utility function to do preorder tree traversal
    static void preorder(Node root)
    {
        if (root != null) {
            System.out.printf("(%d, %d) ", root.key, root.size);
            preorder(root.left);
            preorder(root.right);
        }
    }

    static Node fillTreeFromArray(int[] arr) {
        Node root = null;
        for (int value : arr) {
            root = insert(root, value);
        }
        return root;
    }

    static int[] fillArrFromFile(String fileName) {
        try (Scanner scnr = new Scanner(new File(fileName))) {
            int n = scnr.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = scnr.nextInt();
            }
            return arr;
        }

        catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
            return null;
        }

    }

    // Driver method
    public static void main(String[] args)
    {
        int[] testData1 = {7, 10, 3, 9, 13, 11};

        int[] testData2 = fillArrFromFile("input-6-1.txt");
        if (testData2 == null) return;

        int[] testData3 = fillArrFromFile("input-6-2.txt");
        if (testData3 == null) return;

        Node root1 = fillTreeFromArray(testData1);
        Node root2 = fillTreeFromArray(testData2);
        Node root3 = fillTreeFromArray(testData3);

        System.out.println("Testing with test data 1");
        System.out.print("Before rotation: ");
        preorder(root1);
        System.out.println();
        root1 = rotateLeft(root1);
        System.out.print("After left rotation around root: ");
        preorder(root1);

        System.out.println("\n\n***Testing with input-6-1***");
        System.out.print("Before rotation: ");
        preorder(root2);
        System.out.println();
        root2 = rotateLeft(root2);
        System.out.print("After left rotation around root: ");
        preorder(root2);

        System.out.println("\n\n***Testing with input-6-2***");
        System.out.print("Before rotation: ");
        preorder(root3);
        System.out.println();
        root3 = rotateRight(root3);
        System.out.print("After right rotation around root: ");
        preorder(root3);

    }
}
