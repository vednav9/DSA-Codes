// Google, Amazon

package Binary_Tree.Intermediate.BFS;

import java.util.*;

public class LevelOrderTraversal2_103 {
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

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        Deque<TreeNode> queue = new LinkedList<>();

        queue.offer(root);

        boolean reverse = false;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);

            for (int i = 0; i < levelSize; i++) {

                if (!reverse) {
                    TreeNode curentNode = queue.pollFirst();
                    currentLevel.add(curentNode.val);

                    if (curentNode.left != null) {
                        queue.offerLast(curentNode.left);
                    }
                    if (curentNode.right != null) {
                        queue.offerLast(curentNode.right);
                    }
                } else {
                    TreeNode curentNode = queue.pollLast();
                    currentLevel.add(curentNode.val);
                    // swap if

                    if (curentNode.right != null) {
                        queue.offerFirst(curentNode.right);
                    }
                    if (curentNode.left != null) {
                        queue.offerFirst(curentNode.left);
                    }
                }
            }
            reverse = !reverse;
            result.add(currentLevel);
        }

        return result;
    }
}