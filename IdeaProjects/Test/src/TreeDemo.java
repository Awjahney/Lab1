class Node{
    int value;
    Node left, right;
    public Node(int value){
        this.value = value;
        left = null;
        right = null;
    }
}
class BinarySearchTree{
    Node root;
    /*
    recursive insert method
    */
/*
inserts a node into the tree
*/
    public void insert(int value){
//tree is empty
        if(root == null){
            root = new Node(value);
            return;
        }else{
            Node current = root;
            Node parent = null;
            while(true){
                parent = current;
                if(value < current.value){
                    current = current.left;
                    if(current == null){
                        parent.left = new Node(value);
                        return;
                    }
                }else{
                    current = current.right;
                    if(current == null){
                        parent.right = new Node(value);
                        return;
                    }
                }
            }//closing while
        }//closing main if-else
    }
    /**
     * Performs a pre-order traversal of the tree and prints the values of each node.
     *
     * @param root The root of the subtree to traverse.
     */
    public void preOrderTraversal(Node root) {
        if (root != null) {
            System.out.print(root.value + " "); // Visit the root
            preOrderTraversal(root.left);        // Visit the left subtree
            preOrderTraversal(root.right);       // Visit the right subtree
        }
    }
    /**
     * Performs an in-order traversal of the tree and prints the values of each node.
     *
     * @param root The root of the subtree to traverse.
     */
    public void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);         // Visit the left subtree
            System.out.print(root.value + " "); // Visit the root
            inOrderTraversal(root.right);        // Visit the right subtree
        }
    }
    /**
     * Performs a post-order traversal of the tree and prints the values of each node.
     *
     * @param root The root of the subtree to traverse.
     */
    public void postOrderTraversal(Node root) {
        if (root != null) {
            postOrderTraversal(root.left);       // Visit the left subtree
            postOrderTraversal(root.right);      // Visit the right subtree
            System.out.print(root.value + " "); // Visit the root
        }
    }
    /**
     * Searches for a node with the specified key in the tree.
     *
     * @param root The root of the subtree to search.
     * @param key  The key to search for.
     * @return true if the key is found; false otherwise.
     */
    public boolean find(Node root, int key) {
        if (root == null) {
            return false; // Key not found
        }
        if (key < root.value) {
            return find(root.left, key); // Search in the left subtree
        } else if (key > root.value) {
            return find(root.right, key); // Search in the right subtree
        } else {
            return true; // Key found
        }
    }
    /**
     * Finds the minimum value in the tree.
     *
     * @param root The root of the subtree to search.
     * @return The minimum value in the tree.
     * @throws IllegalArgumentException if the tree is empty.
     */
    public int getMin(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty");
        }
        Node current = root;
        while (current.left != null) {
            current = current.left; // Traverse to the leftmost node
        }
        return current.value; // Return the minimum value
    }
    /**
     * Finds the maximum value in the tree.
     *
     * @param root The root of the subtree to search.
     * @return The maximum value in the tree.
     * @throws IllegalArgumentException if the tree is empty.
     */
    public int getMax(Node root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty");
        }
        Node current = root;
        while (current.right != null) {
            current = current.right; // Traverse to the rightmost node
        }
        return current.value; // Return the maximum value
    }
    /*
this method will not compile until getMax
is implemented
*/
    public Node delete(Node root, int key){
        if(root == null){
            return root;
        }else if(key < root.value){
            root.left = delete(root.left, key);
        }else if(key > root.value){
            root.right = delete(root.right, key);
        }else{
//node has been found
            if(root.left==null && root.right==null){
//case #1: leaf node
                root = null;
            }else if(root.right == null){
//case #2 : only left child
                root = root.left;
            }else if(root.left == null){
//case #2 : only right child
                root = root.right;
            }else{
//case #3 : 2 children
                root.value = getMax(root.left);
                root.left = delete(root.left, root.value);
            }
        }
        return root;
    }
}
public class TreeDemo {
    public static void main(String[] args) {
        BinarySearchTree t1 = new BinarySearchTree();
        t1.insert(24);
        t1.insert(80);
        t1.insert(18);
        t1.insert(9);
        t1.insert(90);
        t1.insert(22);

        System.out.print("in-order : ");
        t1.inOrderTraversal(t1.root);

        System.out.print("\n" + "pre-order : ");
        t1.preOrderTraversal(t1.root);

        System.out.print("\n" + "post-order : ");
        t1.postOrderTraversal(t1.root);

        System.out.print("\n" + "finding root 24: " + t1.find(t1.root, 24));
        System.out.print("\n" + "finding root 78: " + t1.find(t1.root, 78));

        System.out.print("\n" + "getting min: " + t1.getMin(t1.root));
        System.out.print("\n" + "getting max: " + t1.getMax(t1.root));
        System.out.println();
    }
}