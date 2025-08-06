import java.util.ArrayList;
import java.util.List;

public class BSTValidationAndRepair {

    // Static inner class for a tree node
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node(" + data + ")";
        }
    }

    // --- 1. Validate if a Binary Tree is a Valid BST ---
    public static boolean isValidBST(TreeNode root) {
        // We use Long instead of Integer to handle cases where node.data is Integer.MIN_VALUE or MAX_VALUE.
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBSTHelper(TreeNode node, long min, long max) {
        if (node == null) return true;
        
        // The current node's data must be within the valid range (min, max).
        if (node.data <= min || node.data >= max) return false;

        // Recursively check the left subtree with an updated max bound,
        // and the right subtree with an updated min bound.
        return isValidBSTHelper(node.left, min, node.data) &&
               isValidBSTHelper(node.right, node.data, max);
    }

    // --- 2. Find Nodes Violating the BST Rule ---
    // This is done by finding out-of-order elements in an in-order traversal.
    private static TreeNode prevNode;
    public static List<TreeNode> findViolatingNodes(TreeNode root) {
        List<TreeNode> violators = new ArrayList<>();
        prevNode = null;
        findViolatingNodesHelper(root, violators);
        return violators;
    }

    private static void findViolatingNodesHelper(TreeNode node, List<TreeNode> violators) {
        if (node == null) return;

        findViolatingNodesHelper(node.left, violators);
        
        // Check for an out-of-order pair.
        if (prevNode != null && prevNode.data > node.data) {
            if (!violators.contains(prevNode)) violators.add(prevNode);
            if (!violators.contains(node)) violators.add(node);
        }
        prevNode = node;
        
        findViolatingNodesHelper(node.right, violators);
    }

    // --- 3. Repair a BST with Two Swapped Nodes ---
    private static TreeNode first;
    private static TreeNode second;
    private static TreeNode prev;
    public static void recoverTree(TreeNode root) {
        first = null;
        second = null;
        prev = null;

        findSwappedNodes(root);

        // Swap the data of the two found nodes.
        if (first != null && second != null) {
            int temp = first.data;
            first.data = second.data;
            second.data = temp;
        }
    }

    private static void findSwappedNodes(TreeNode node) {
        if (node == null) return;
        
        findSwappedNodes(node.left);
        
        if (prev != null && prev.data > node.data) {
            // This is the first time we found a dip.
            if (first == null) {
                first = prev;
            }
            // The second node is always the 'current' node in a dip.
            // If there's a second dip, this will update it.
            second = node;
        }
        prev = node;
        
        findSwappedNodes(node.right);
    }

    // --- 4. Minimum Nodes to Remove for a Valid BST ---
    // This is a complex problem equivalent to finding the Largest BST Subtree.
    // The implementation below is a simpler, less efficient (O(n^2)) brute-force approach
    // that checks every node as a potential root of the largest BST.
    public static int minNodesToRemove(TreeNode root) {
        if (root == null) return 0;
        int totalNodes = countNodes(root);
        int largestBSTSize = findLargestBSTSubtreeSize(root);
        return totalNodes - largestBSTSize;
    }
    
    private static int findLargestBSTSubtreeSize(TreeNode node) {
        if (node == null) return 0;
        // If the subtree at the current node is a valid BST, its size is the count of its nodes.
        if (isValidBST(node)) {
            return countNodes(node);
        }
        // Otherwise, the largest BST must be entirely in the left or right subtree.
        // We find the max size between the two recursive calls.
        return Math.max(findLargestBSTSubtreeSize(node.left), findLargestBSTSubtreeSize(node.right));
    }
    
    private static int countNodes(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    // Helper to print tree in-order
    public static void printInOrder(TreeNode root) {
        if (root == null) return;
        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }


    public static void main(String[] args) {
        System.out.println("====== BST Validation and Repair Demo ======");

        // --- 1. Test isValidBST ---
        TreeNode validBst = new TreeNode(10);
        validBst.left = new TreeNode(5);
        validBst.right = new TreeNode(15);
        validBst.left.right = new TreeNode(7);

        TreeNode invalidBst = new TreeNode(5);
        invalidBst.left = new TreeNode(1);
        invalidBst.right = new TreeNode(4); // Invalid: 4 should be less than 5
        invalidBst.right.left = new TreeNode(3);
        invalidBst.right.right = new TreeNode(6);
        
        System.out.println("\n1. BST Validation");
        System.out.println("Is the first tree a valid BST? " + isValidBST(validBst));
        System.out.println("Is the second tree a valid BST? " + isValidBST(invalidBst));

        // --- 2 & 3. Test Find and Repair ---
        TreeNode swappedBst = new TreeNode(10);
        swappedBst.left = new TreeNode(15); // Swapped with 5
        swappedBst.right = new TreeNode(5);  // Swapped with 15
        swappedBst.left.left = new TreeNode(3); // This structure is invalid due to swaps
        
        System.out.println("\n2. Find Violating Nodes");
        System.out.print("Original swapped tree (in-order): ");
        printInOrder(swappedBst);
        System.out.println("\nViolating nodes: " + findViolatingNodes(swappedBst));

        System.out.println("\n3. Repair Tree");
        System.out.println("Is swapped tree valid before recovery? " + isValidBST(swappedBst));
        recoverTree(swappedBst);
        System.out.print("Tree after recovery (in-order): ");
        printInOrder(swappedBst);
        System.out.println("\nIs tree valid after recovery? " + isValidBST(swappedBst));
        
        // --- 4. Test Minimum Nodes to Remove ---
        System.out.println("\n4. Minimum Nodes to Remove");
        System.out.print("For the invalid BST, in-order is: ");
        printInOrder(invalidBst);
        System.out.println("\nMinimum nodes to remove to make it a valid BST: " + minNodesToRemove(invalidBst));
        System.out.println("(The largest valid BST subtree is the single node '1' or '3' or '6', so 5-1=4 nodes must be removed)");

        System.out.println("\n=============================================");
    }
}
