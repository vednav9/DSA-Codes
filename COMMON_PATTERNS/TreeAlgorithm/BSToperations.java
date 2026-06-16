package TreeAlgorithm;

public class BSToperations {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Search
    public TreeNode search(TreeNode root, int key) {
        if (root == null || root.val == key)
            return root;

        if (key < root.val)
            return search(root.left, key);
        else
            return search(root.right, key);
    }

    // Insert
    public TreeNode insert(TreeNode root, int val) {
        if (root == null)
            return new TreeNode(val);

        if (val < root.val)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);

        return root;
    }

    // Delete
    // 3 cases:
    // 1. Leaf → delete
    // 2. One child → replace
    // 3. Two children → replace with inorder successor
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (key < root.val)
            root.left = deleteNode(root.left, key);
        else if (key > root.val)
            root.right = deleteNode(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            TreeNode min = findMin(root.right);
            root.val = min.val;
            root.right = deleteNode(root.right, min.val);
        }
        return root;
    }

    TreeNode findMin(TreeNode node) {
        while (node.left != null)
            node = node.left;
        return node;
    }
}