package self_balancing_tree;

import java.util.Objects;

class Node {
    int key;
    int height;
    Node left;
    Node right;

    public Node(int key) {
        this.key = key;
        this.height = 1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key=" + key +
                ", height=" + height +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}

public class AVL_Tree {

    Node root;

    public static void main(String[] args) {

        AVL_Tree avl_tree = new AVL_Tree();
        avl_tree.root = avl_tree.insertNode(avl_tree.root, 10);
        avl_tree.root = avl_tree.insertNode(avl_tree.root, 20);
        avl_tree.root = avl_tree.insertNode(avl_tree.root, 30);
        avl_tree.root = avl_tree.insertNode(avl_tree.root, 40);
        avl_tree.root = avl_tree.insertNode(avl_tree.root, 50);
        avl_tree.root = avl_tree.insertNode(avl_tree.root, 25);

        System.out.println("PreOrder Traversal of the Tree : ");
        avl_tree.preOrder(avl_tree.root);

    }

    // Utility Function to find the height of the tree
    public int getHeight(Node node) {
        if (Objects.isNull(node))
            return 0;
        return node.height;
    }

    // Utility Function to find the maximum of two numbers
    public int findMax(int a, int b) {
        return a > b ? a : b;
    }

    // Utility function to right rotate subtree rooted with y
    public Node rightRotate(Node y) {
     /*   16                             14
        /                              /\
       14                            12   16
       /\                                 /
     12  15                              15
    */
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights as well
        y.height = findMax(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = findMax(getHeight(x.left), getHeight(x.right)) + 1;

        // Return new root
        return x;
    }

    //Utility function to left rotate subtree rooted with x
    public Node leftRotate(Node x) {
        /*
        20                             22
          \                          /    \
            22                     20       24
            /  \                     \
           21    24                    21
         */
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update the heights
        x.height = findMax(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = findMax(getHeight(y.left), getHeight(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balancing Factor of node
    public int getBalancingFactor(Node node) {
        if (Objects.isNull(node))
            return 0;
        return getHeight(node.left) - getHeight(node.right);
    }

    // Insertion in AVL Tree
    public Node insertNode(Node node, int key) {
        // Perform normal BST insertion
        if (Objects.isNull(node))
            return new Node(key);

        if (key < node.key)
            node.left = insertNode(node.left, key);
        else if (key > node.key)
            node.right = insertNode(node.right, key);
        else    // Duplication of keys not allowed
            return node;

        // Update height of this parent node after insertion of nodes as BST
        node.height = findMax(getHeight(node.left), getHeight(node.right)) + 1;

        // Check the balancing factor of this node to make sure if the node is balanced
        int balancingFactor = getBalancingFactor(node);

        // Rotations will always be done when the balancing factor is greater than 1

        // LL Case   -> Single R Rotation
        if (balancingFactor > 1 && key < node.left.key)
            return rightRotate(node);

        // RR Case    -> Single L Rotation
        if (balancingFactor < -1 && key > node.right.key)
            return leftRotate(node);

        // LR Case     -> LR Rotation
        if (balancingFactor > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Case    -> RL Rotation
        if (balancingFactor < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the unchanged node pointer
        return node;
    }

    // Utility Function to print preOrder traversal of the tree
    public void preOrder(Node node) {
        if (Objects.nonNull(node)) {
            System.out.print(node.key + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}
