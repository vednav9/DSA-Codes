package Binary_Tree.Advance;

public class BTtoDLL {
    class LLNode {
        int val;
        LLNode prev;
        LLNode next;

        public LLNode(int val) {
            this.val = val;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    LLNode head;
    LLNode tail;

    public LLNode covert(TreeNode root) {
        if (root == null) {
            return null;
        }
        helper(root);
        return head;
    }

    private void helper(TreeNode node) {
        if (node == null) {
            return;
        }
        helper(node.left);
        LLNode newNode = new LLNode(node.val);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        helper(node.right);
    }
}