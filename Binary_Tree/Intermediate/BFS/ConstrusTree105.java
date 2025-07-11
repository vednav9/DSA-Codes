// Amazon

package Binary_Tree.Intermediate.BFS;

import java.util.*;

public class ConstrusTree105 {
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

    // public TreeNode buildTree(int[] preorder, int[] inorder) {
    //     Deque<Integer> preorderQueue = new ArrayDeque<>();
    //     for (int val : preorder) {
    //         preorderQueue.offer(val);
    //     }

    //     return build(preorderQueue, inorder);        
    // }

    private TreeNode build(Deque<Integer> preorder, int[] inorder) {
        if (inorder.length > 0) {
            int idx = indexOf(inorder, preorder.poll());
            TreeNode root = new TreeNode(inorder[idx]);

            root.left = build(preorder, Arrays.copyOfRange(inorder, 0, idx));
            root.right = build(preorder, Arrays.copyOfRange(inorder, idx + 1, inorder.length));

            return root;
        }
        return null;
    }

    private int indexOf(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1; // shouldn't happen with valid input
    }    
}