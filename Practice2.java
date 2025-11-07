import java.util.*;

public class Practice2 {
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

    int sum;

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root==null){
            return false;
        }
        sum+=root.val;
        if(sum==targetSum){
            return true;
        }
        boolean left=hasPathSum(root.left, targetSum);
        if(sum>targetSum){
            sum-=root.val;
        }
        boolean right=hasPathSum(root.right, targetSum);

        return left||right;
    }
}