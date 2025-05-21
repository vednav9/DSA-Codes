// Google

package Binary_Tree.Intermediate.DFS;

public class DFS_104 {
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

    int max=0;

    public int maxDepth(TreeNode root){
        if (root==null) {
            return 0;
        }

        int left=maxDepth(root.left) +1;
        int right=maxDepth(root.right) +1;
        max=Math.max(left, right);

        return max;
    }

    public int maxDepth1(TreeNode root){
        if (root==null) {
            return 0;
        }

        int left=maxDepth(root.left);
        int right=maxDepth(root.right);
        int depth=Math.max(left, right)+1;

        return depth;
    }
}