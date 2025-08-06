import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Map;

public class LevelOrderTraversalVariations {

    // Static inner class for a tree node
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // --- 1. Store Each Level in a Different List ---
    public static List<List<Integer>> levelOrderByLists(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.data);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
        }
        return result;
    }

    // --- 2. Zigzag Level-Order Traversal ---
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            LinkedList<Integer> currentLevel = new LinkedList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    currentLevel.addLast(node.data);
                } else {
                    currentLevel.addFirst(node.data);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(currentLevel);
            leftToRight = !leftToRight; // Flip direction for the next level
        }
        return result;
    }

    // --- 3. Print Only the Last Node of Each Level ---
    public static List<Integer> lastNodeOfEachLevel(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                // If it's the last node of the current level, add it to the result.
                if (i == levelSize - 1) {
                    result.add(node.data);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }

    // --- 4. Vertical-Order Traversal ---
    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // Use a TreeMap to keep columns sorted by horizontal distance.
        Map<Integer, List<Integer>> columnTable = new TreeMap<>();
        // Queue stores pairs of <TreeNode, HorizontalDistance>
        Queue<Map.Entry<TreeNode, Integer>> queue = new LinkedList<>();
        
        queue.offer(new SimpleImmutableEntry<>(root, 0));

        while (!queue.isEmpty()) {
            Map.Entry<TreeNode, Integer> p = queue.poll();
            TreeNode node = p.getKey();
            int hd = p.getValue();

            columnTable.computeIfAbsent(hd, k -> new ArrayList<>()).add(node.data);
            
            if (node.left != null) {
                queue.offer(new SimpleImmutableEntry<>(node.left, hd - 1));
            }
            if (node.right != null) {
                queue.offer(new SimpleImmutableEntry<>(node.right, hd + 1));
            }
        }
        
        result.addAll(columnTable.values());
        return result;
    }


    public static void main(String[] args) {
        // Create a sample tree for testing
        //        10
        //       /  \
        //      5    15
        //     / \   / \
        //    3   7 12  18
        //   /
        //  1
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(18);
        root.left.left.left = new TreeNode(1);

        System.out.println("====== Level-Order Traversal Variations Demo ======");

        System.out.println("\n1. Storing each level in a different list:");
        List<List<Integer>> levels = levelOrderByLists(root);
        for (List<Integer> level : levels) {
            System.out.println("   " + level);
        }

        System.out.println("\n2. Zigzag Level-Order Traversal:");
        List<List<Integer>> zigzagLevels = zigzagLevelOrder(root);
        for (List<Integer> level : zigzagLevels) {
            System.out.println("   " + level);
        }

        System.out.println("\n3. Last node of each level:");
        List<Integer> lastNodes = lastNodeOfEachLevel(root);
        System.out.println("   " + lastNodes);

        System.out.println("\n4. Vertical-Order Traversal:");
        List<List<Integer>> verticalLevels = verticalOrder(root);
        for (List<Integer> column : verticalLevels) {
            System.out.println("   " + column);
        }

        System.out.println("\n=================================================");
    }
}
