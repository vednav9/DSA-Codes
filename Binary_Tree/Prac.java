package Binary_Tree;

public class Prac {
    // Node class representing each node in the binary tree
    static class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    // BinaryTree class containing the root of the binary tree
    static class BinaryTree {
        Node root;

        BinaryTree() {
            root = null;
        }

        // Method to insert a new node in the binary tree
        void insert(int data) {
            root = insertRec(root, data);
        }

        // Recursive method to insert a new node in the binary tree
        Node insertRec(Node root, int data) {
            if (root == null) {
                root = new Node(data);
                return root;
            }

            if (data < root.data)
                root.left = insertRec(root.left, data);
            else if (data > root.data)
                root.right = insertRec(root.right, data);

            return root;
        }

        // Method to perform inorder traversal of the binary tree
        void inorder() {
            inorderRec(root);
        }

        // Recursive method to perform inorder traversal of the binary tree
        void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.left);
                System.out.print(root.data + " ");
                inorderRec(root.right);
            }
        }

        // Method to display the binary tree structure
        void display() {
            displayRec(root, 0);
        }

        // Recursive method to display the binary tree structure
        void displayRec(Node root, int level) {
            if (root == null) {
                return;
            }

            displayRec(root.right, level + 1);
            System.out.println(" ".repeat(level * 4) + root.data);
            displayRec(root.left, level + 1);
        }
    }

    // Main method to test the binary tree implementation
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Print inorder traversal of the binary tree
        System.out.println("Inorder Traversal:");
        tree.inorder();

        // Display the binary tree structure
        System.out.println("\n\nBinary Tree Structure:");
        tree.display();
    }
}