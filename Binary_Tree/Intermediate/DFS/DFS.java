package Binary_Tree.Intermediate.DFS;

import java.util.Stack;

public class DFS {
  class Node {
    int val;
    Node left;
    Node right;

    public Node(int val) {
      this.val = val;
    }
  }

  void dfsStack(Node node) {
    if (node == null) {
      return;
    }
    Stack<Node> stack = new Stack<>();
    stack.push(node);

    while (!stack.isEmpty()) {
      Node removed = stack.pop();
      System.out.print(removed.val + " ");
      if (removed.right != null) {
        stack.push(removed.right);
      }
      if (removed.left != null) {
        stack.push(removed.left);
      }
    }
  }
}