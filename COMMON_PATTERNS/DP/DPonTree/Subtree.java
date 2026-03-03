package DP.DPonTree;

public class Subtree {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
    }
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode node) {
        if (node == null)
            return 0;

        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        // update global answer
        maxSum = Math.max(maxSum, node.val + left + right);

        // return best downward path
        return node.val + Math.max(left, right);
    }
}
