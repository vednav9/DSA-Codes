// Google, Amazon, Facebook

package Binary_Tree.Advance;

public class BFS_230 {
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

    private int k;
    private int ans;

    public int kthSmallest(TreeNode root, int k) {
        this.k=k;
        helper(root);
        return ans;
    }

    private void helper(TreeNode root) {
        if (root == null) {
            return;
        }
        
        helper(root.left);
        k--;
        if (k==0) {
            ans=root.val;
        }
        helper(root.right);
    }
}