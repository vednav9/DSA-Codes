package DP.DPonTree;

public class IncludeExclude {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
    }
    public int rob(TreeNode root) {
        int[] res = dfs(root);
        return Math.max(res[0], res[1]);
    }

    // returns [exclude, include]
    private int[] dfs(TreeNode node) {
        if (node == null)
            return new int[] { 0, 0 };

        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        int include = node.val + left[0] + right[0];
        int exclude = Math.max(left[0], left[1]) +
                Math.max(right[0], right[1]);

        return new int[] { exclude, include };
    }
}