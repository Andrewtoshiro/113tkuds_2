import java.util.ArrayList;
import java.util.List;

public class TreePathProblems {

    // Static inner class for a tree node
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // --- 1. Find All Root-to-Leaf Paths ---
    public static List<List<Integer>> findAllRootToLeafPaths(TreeNode root) {
        List<List<Integer>> allPaths = new ArrayList<>();
        findPathsHelper(root, new ArrayList<>(), allPaths);
        return allPaths;
    }

    private static void findPathsHelper(TreeNode node, List<Integer> currentPath, List<List<Integer>> allPaths) {
        if (node == null) {
            return;
        }

        // Add the current node to the path
        currentPath.add(node.data);

        // If it's a leaf node, this path is complete. Add a copy to the results.
        if (node.left == null && node.right == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            // Continue searching down the left and right subtrees.
            findPathsHelper(node.left, currentPath, allPaths);
            findPathsHelper(node.right, currentPath, allPaths);
        }

        // Backtrack: Remove the current node from the path to explore other branches.
        currentPath.remove(currentPath.size() - 1);
    }

    // --- 2. Determine if a Root-to-Leaf Path with a Specific Sum Exists ---
    public static boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }

        int remainingSum = targetSum - root.data;

        // If it's a leaf node, check if the sum is met exactly.
        if (root.left == null && root.right == null) {
            return remainingSum == 0;
        }

        // Recursively check the left OR right subtree.
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }

    // --- 3. Find the Maximum Sum of a Root-to-Leaf Path ---
    // This implementation returns the sum value itself.
    public static int maxRootToLeafPathSum(TreeNode root) {
        if (root == null) {
            // Return a very small number for comparison so it won't be chosen.
            return Integer.MIN_VALUE;
        }
        
        // If it's a leaf node, the max path sum is just its own value.
        if (root.left == null && root.right == null) {
            return root.data;
        }

        // Get the max sum from the left and right subtrees.
        int leftMax = maxRootToLeafPathSum(root.left);
        int rightMax = maxRootToLeafPathSum(root.right);

        // The max sum through this node is its value plus the max of its children's path sums.
        return root.data + Math.max(leftMax, rightMax);
    }

    // --- 4. Maximum Path Sum Between Any Two Nodes ---
    // This uses an instance variable to hold the global maximum as we traverse.
    private static int maxSum;

    public static int maxPathSumAnyNode(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    // Helper function returns the max "gain" a path can have starting from 'node' and going downwards.
    private static int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively find the max gain from left and right children.
        // Ignore paths with negative sums by comparing with 0.
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // A new max path could be formed using the current node as the "split point".
        int newPathSum = node.data + leftGain + rightGain;
        maxSum = Math.max(maxSum, newPathSum);

        // For the recursion upwards, a path can only extend from one side.
        // Return the max gain if the path continues from this node's parent.
        return node.data + Math.max(leftGain, rightGain);
    }


    public static void main(String[] args) {
        // Create a sample tree for testing
        //        5
        //       / \
        //      4   8
        //     /   / \
        //    11  13  4
        //   /  \      \
        //  7    2      1
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        System.out.println("====== Tree Path Problems Demo ======");

        System.out.println("\n1. All Root-to-Leaf Paths:");
        List<List<Integer>> allPaths = findAllRootToLeafPaths(root);
        for (List<Integer> path : allPaths) {
            System.out.println("   " + path);
        }

        System.out.println("\n2. Path Sum Existence:");
        System.out.println("   Does a path with sum 22 exist? " + hasPathSum(root, 22)); // 5-4-11-2
        System.out.println("   Does a path with sum 26 exist? " + hasPathSum(root, 26)); // 5-8-13
        System.out.println("   Does a path with sum 10 exist? " + hasPathSum(root, 10));

        System.out.println("\n3. Maximum Root-to-Leaf Path Sum:");
        System.out.println("   The maximum sum is: " + maxRootToLeafPathSum(root)); // Path: 5-8-13 = 26

        System.out.println("\n4. Maximum Path Sum (Any Node to Any Node):");
        System.out.println("   The maximum sum is: " + maxPathSumAnyNode(root)); // Path: 7-11-4-5-8-13 = 48
        
        System.out.println("\n=====================================");
    }
}
