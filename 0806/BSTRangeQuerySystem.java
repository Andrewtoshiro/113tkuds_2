import java.util.ArrayList;
import java.util.List;

public class BSTRangeQuerySystem {

    // Static inner class for a tree node
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    private TreeNode root;

    // Helper method to build the BST for testing
    public void insert(int data) {
        root = insertRec(root, data);
    }

    private TreeNode insertRec(TreeNode current, int data) {
        if (current == null) {
            return new TreeNode(data);
        }
        if (data < current.data) {
            current.left = insertRec(current.left, data);
        } else if (data > current.data) {
            current.right = insertRec(current.right, data);
        }
        return current;
    }

    // --- 1. Range Query: Find all nodes in [min, max] ---
    public List<Integer> findInRange(int min, int max) {
        List<Integer> result = new ArrayList<>();
        findInRangeHelper(root, min, max, result);
        return result;
    }

    private void findInRangeHelper(TreeNode node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }

        // If current node's data is greater than min, there might be values in the left subtree.
        if (node.data > min) {
            findInRangeHelper(node.left, min, max, result);
        }

        // If current node's data is within the range, add it to the list.
        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }

        // If current node's data is less than max, there might be values in the right subtree.
        if (node.data < max) {
            findInRangeHelper(node.right, min, max, result);
        }
    }

    // --- 2. Range Count: Count nodes in [min, max] ---
    public int countInRange(int min, int max) {
        return countInRangeHelper(root, min, max);
    }

    private int countInRangeHelper(TreeNode node, int min, int max) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.data >= min && node.data <= max) {
            count = 1;
        }
        
        // Recursively count in left and right subtrees only if necessary.
        count += countInRangeHelper(node.left, min, max);
        count += countInRangeHelper(node.right, min, max);

        return count;
    }


    // --- 3. Range Sum: Sum of all nodes in [min, max] ---
    public long sumInRange(int min, int max) {
        return sumInRangeHelper(root, min, max);
    }

    private long sumInRangeHelper(TreeNode node, int min, int max) {
        if (node == null) {
            return 0;
        }

        long sum = 0;
        if (node.data >= min && node.data <= max) {
            sum = node.data;
        }

        sum += sumInRangeHelper(node.left, min, max);
        sum += sumInRangeHelper(node.right, min, max);

        return sum;
    }

    // --- 4. Closest Value Query ---
    public int findClosestValue(int target) {
        if (root == null) {
            throw new IllegalStateException("Tree is empty.");
        }
        return findClosestValueHelper(root, target, root.data);
    }

    private int findClosestValueHelper(TreeNode node, int target, int closest) {
        if (node == null) {
            return closest;
        }

        // Update the closest value if the current node is closer to the target.
        if (Math.abs(target - node.data) < Math.abs(target - closest)) {
            closest = node.data;
        }

        // Use BST property to decide which subtree to search.
        if (target < node.data) {
            return findClosestValueHelper(node.left, target, closest);
        } else if (target > node.data) {
            return findClosestValueHelper(node.right, target, closest);
        } else {
            // Target found exactly.
            return node.data;
        }
    }


    public static void main(String[] args) {
        System.out.println("====== BST Range Query System Demo ======");
        BSTRangeQuerySystem bst = new BSTRangeQuerySystem();
        int[] values = {50, 30, 70, 20, 40, 60, 80, 15, 25, 35, 45};
        for (int value : values) {
            bst.insert(value);
        }
        // BST Structure:
        //        50
        //       /  \
        //      30   70
        //     / \   / \
        //    20 40 60  80
        //   / \ / \
        //  15 25 35 45

        // 1. Test Range Query
        int minRange = 25, maxRange = 60;
        System.out.printf("\n1. Finding all nodes in range [%d, %d]:\n", minRange, maxRange);
        List<Integer> inRange = bst.findInRange(minRange, maxRange);
        System.out.println("   Result: " + inRange);

        // 2. Test Range Count
        System.out.printf("\n2. Counting nodes in range [%d, %d]:\n", minRange, maxRange);
        int count = bst.countInRange(minRange, maxRange);
        System.out.println("   Result: " + count + " nodes");
        
        // 3. Test Range Sum
        System.out.printf("\n3. Sum of nodes in range [%d, %d]:\n", minRange, maxRange);
        long sum = bst.sumInRange(minRange, maxRange);
        System.out.println("   Result: " + sum);

        // 4. Test Closest Value Query
        int target1 = 63;
        int target2 = 18;
        System.out.println("\n4. Finding the closest value:");
        int closest1 = bst.findClosestValue(target1);
        System.out.printf("   Closest value to %d is %d\n", target1, closest1);
        int closest2 = bst.findClosestValue(target2);
        System.out.printf("   Closest value to %d is %d\n", target2, closest2);
        
        System.out.println("\n==========================================");
    }
}
