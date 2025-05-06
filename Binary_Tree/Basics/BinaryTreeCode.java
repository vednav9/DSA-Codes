package Binary_Tree.Basics;

import java.util.Scanner;

public class BinaryTreeCode {

    public class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void populate(Scanner scanner) {
        System.out.println("Enter the root node : ");
        int value = scanner.nextInt();
        root = new Node(value);

        populate(root, scanner);
    }

    public void populate(Node node, Scanner sc) {
        System.out.println("Do you want to enter left of " + node.value);
        boolean left = sc.nextBoolean();

        if (left) {
            System.out.println("Enter element for it's left : ");
            int value = sc.nextInt();
            node.left = new Node(value);
            populate(node.left, sc);
        }
        System.out.println("Do you want to enter right of " + node.value);

        boolean right = sc.nextBoolean();
        if (right) {
            System.out.println("Enter element for it's right : ");
            int value = sc.nextInt();
            node.right = new Node(value);
            populate(node.right, sc);
        }
    }

    public void display() {
        display(root, 0);
    }

    private void display(Node node, int level) {
        if (node == null) {
            return;
        }
        display(node.right, level + 1);
        if (level != 0) {
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t\t");
            }
            System.out.println("|------->" + node.value);
        } else {
            System.out.println(node.value);
        }
        display(node.left, level + 1);

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTreeCode tree = new BinaryTreeCode();
        tree.populate(scanner);
        tree.display();
    }
}