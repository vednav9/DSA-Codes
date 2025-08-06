// Google

// BFS
package Binary_Tree.Advance;

import java.util.*;

public class VOT_987 {

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

    public List<List<Integer>> verticalTraversal(TreeNode node) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();

        if (node == null) {
            return ans;
        }

        int col = 0;

        Queue<Map.Entry<TreeNode, int[]>> queue = new ArrayDeque<>();
        Map<Integer, List<int[]>> map = new HashMap<>();

        queue.offer(new AbstractMap.SimpleEntry<>(node, new int[] { 0, 0 })); // col, row

        int min = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            Map.Entry<TreeNode, int[]> removed = queue.poll();
            node = removed.getKey();
            int[] pos = removed.getValue();
            col = pos[0];
            int row = pos[1];

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }

            map.get(col).add(new int[] { row, node.val });

            min = Math.min(min, col);
            max = Math.max(max, col);

            if (node.left != null)
                queue.offer(new AbstractMap.SimpleEntry<>(node.left, new int[] { col - 1, row + 1 }));
            if (node.right != null)
                queue.offer(new AbstractMap.SimpleEntry<>(node.right, new int[] { col + 1, row + 1 }));

        }

        for (int i = min; i <= max; i++) {
            List<int[]> list = map.get(i);
            list.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]); // sort by row, then value
            List<Integer> colList = new ArrayList<>();
            for (int[] pair : list) {
                colList.add(pair[1]);
            }
            ans.add(colList);
        }

        return ans;
    }
}