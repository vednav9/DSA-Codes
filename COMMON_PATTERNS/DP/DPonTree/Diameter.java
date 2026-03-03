package DP.DPonTree;

public class Diameter {
    class TreeNode {
        TreeNode left;
        TreeNode right;
        int val;
    }

    int diameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }
}