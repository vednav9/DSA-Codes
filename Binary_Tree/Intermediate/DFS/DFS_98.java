// Google, Amazon

package Binary_Tree.Intermediate.DFS;

public class DFS_98 {
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

    public boolean isValidBST(TreeNode root){
        // Preorder
        if (root==null) {
            return true;
        }
        boolean yes;
        if (root.left.val<=root.val && root.val<=root.right.val) {
            yes=isValidBST(root.left);
            yes=isValidBST(root.right);
            return yes;
        }
        else{
            return false;
        }
    }

    public boolean isValidBST1(TreeNode root){
        return helper(root,null,null);
    }
    public boolean helper(TreeNode node, Integer low, Integer high){
        if (node==null) {
            return true;
        }

        if (low!=null && node.val<=low) {
            return false;
        }
        if (high!=null && node.val>=high) {
            return false;
        }

        boolean leftTree=helper(node.left, low, node.val);
        boolean rightTree=helper(node.right, node.val, high);

        return leftTree&&rightTree;
    }
}