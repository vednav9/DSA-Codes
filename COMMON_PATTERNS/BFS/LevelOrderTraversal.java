package COMMON_PATTERNS.BFS;

import java.util.*;

public class LevelOrderTraversal {
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result=new ArrayList<>();

        if (root==null){
            return result;
        }

        Queue<TreeNode> queue=new LinkedList<>();

        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize=queue.size();
            List<Integer> currentLevel=new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode curentNode=queue.poll();
                currentLevel.add(curentNode.val);
                
                if (curentNode.left!=null) {
                    queue.offer(curentNode.left);
                }
                if (curentNode.right!=null) {
                    queue.offer(curentNode.right);
                }
            }
            result.add(currentLevel);
        }

        return result;
    }
}