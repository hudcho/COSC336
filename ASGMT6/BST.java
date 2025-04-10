

public class BST<T extends Comparable<T>> {

    private class Node<T> {
        protected int size;

        protected T item;
        protected Node<T> left;
        protected Node<T> right;

        public Node(T new_item)
        {
            this.size = 1;
            this.item = new_item;
            this.left = null;
            this.right = null;
        }

    }

    private Node<T> head;

    public BST(){
        this.head = null;
    }

    public void insert(T item){
        
        if (head == null){
            head = new Node<>(item);
        }
        else{
            Node<T> curr = this.head;
            insert_Private(curr, item);
        }

    }
    private Node<T> insert_Private(Node<T> run, T k){
        if(run == null){
            return new Node<>(k);
        }
        //Increment size of the (non.root && not null node) by +1//
        run.size += 1;

        Comparable<T> runItem = (Comparable<T>) run.item;
        if(runItem.compareTo(k) >= 0){
            run.left = insert_Private(run.left, k);
        }
        else{
            run.right = insert_Private(run.right, k);
        }
        

        return run;
    }

    public void inorder(){
        if (head == null){
            System.out.println("ERROR DEAD TREE");
        }
        else{
            Node<T> curr = this.head;
            inorder_Private(curr);
        }

    }
    private void inorder_Private(Node<T> curr){

        if (curr != null) {
            inorder_Private(curr.left);
            System.out.println("Size Node : "+curr.size+"\tValue :"+(curr.item.toString()) + " ");
            inorder_Private(curr.right);
        }
    }
    public void preorder(){
        if (head == null){
            System.out.println("ERROR DEAD TREE");
        }
        else{
            Node<T> curr = this.head;
            preorder_Private(curr);
        }

    }
    private void preorder_Private(Node<T> curr){

        if (curr != null) {
            System.out.println("Value :"+(curr.item.toString()) +"\tSize Node : "+curr.size);
            preorder_Private(curr.left);
            preorder_Private(curr.right);
        }
    }

}