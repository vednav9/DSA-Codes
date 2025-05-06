//Google

package Binary_Tree.Intermediate.BFS;

import java.util.*;

public class AvgOfLevel_637 {
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

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result=new ArrayList<>();

        if (root==null){
            return result;
        }

        Queue<TreeNode> queue=new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize=queue.size();
            double average=0;
            for (int i = 0; i < levelSize; i++) {
                TreeNode curentNode=queue.poll();
                average+=curentNode.val;
                if (curentNode.left!=null) {
                    queue.offer(curentNode.left);
                }
                if (curentNode.right!=null) {
                    queue.offer(curentNode.right);
                }
            }
            result.add(average/levelSize);
        }
        return result;
    }
}
