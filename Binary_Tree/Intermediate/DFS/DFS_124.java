// Meta

package Binary_Tree.Intermediate.DFS;

public class DFS_124 {
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

    int ans=Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return ans;
    }

    int helper(TreeNode node){
        if(node==null){
            return 0;
        }

        int left=helper(node.left);
        int right=helper(node.right);

        left=Math.max(0, left);
        right=Math.max(0, right);

        int hasPathSum=left+right+node.val;

        ans=Math.max(ans, hasPathSum);

        return Math.max(left, right)+node.val;
    }
}