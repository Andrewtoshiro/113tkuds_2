import java.util.concurrent.atomic.AtomicInteger;

public class BSTConversionAndBalance {

    // Static inner class for a tree/list node
    static class TreeNode {
        int data;
        TreeNode left;  // In list, this is 'prev'
        TreeNode right; // In list, this is 'next'

        TreeNode(int data) {
            this.data = data;
        }
    }

    // --- 1. Convert BST to a Sorted Circular Doubly-Linked List ---
    // Instance variables to track head and previous node during in-order traversal
    private static TreeNode head = null;
    private static TreeNode prev = null;

    public static TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) return null;
        
        head = null;
        prev = null;
        
        treeToDoublyListHelper(root);
        
        // Close the circle by connecting the head and tail (last 'prev')
        prev.right = head;
        head.left = prev;
        
        return head;
    }

    private static void treeToDoublyListHelper(TreeNode node) {
        if (node == null) return;
        
        // In-order traversal: left, root, right
        treeToDoublyListHelper(node.left);
        
        if (prev == null) {
            // This is the first node (smallest element), so it's the head.
            head = node;
        } else {
            // Wire up the previous node with the current node.
            prev.right = node;
            node.left = prev;
        }
        // The current node becomes the previous node for the next call.
        prev = node;
        
        treeToDoublyListHelper(node.right);
    }
    
    // --- 2. Convert a Sorted Array to a Balanced BST ---
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private static TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
        if (start > end) return null;
        
        // Middle element becomes the root to ensure balance
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        
        // Recursively build left and right subtrees
        node.left = sortedArrayToBSTHelper(nums, start, mid - 1);
        node.right = sortedArrayToBSTHelper(nums, mid + 1, end);
        
        return node;
    }

    // --- 3. Check if a BST is Balanced ---
    public static boolean isBalanced(TreeNode root) {
        // If the helper returns -1, the tree is unbalanced.
        return checkHeight(root) != -1;
    }

    // Helper returns the height of a node, or -1 if it's unbalanced.
    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1; // Propagate unbalance
        
        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1; // Propagate unbalance
        
        // Check if the current node is unbalanced
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        
        // Return the height of the current node
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // --- 4. Convert BST to a Greater Sum Tree ---
    public static TreeNode convertBST(TreeNode root) {
        // Use AtomicInteger to pass the sum by reference through recursion.
        convertBSTHelper(root, new AtomicInteger(0));
        return root;
    }

    // Helper performs a reverse in-order traversal (Right, Root, Left).
    private static void convertBSTHelper(TreeNode node, AtomicInteger sum) {
        if (node == null) return;
        
        // Traverse the right subtree first
        convertBSTHelper(node.right, sum);
        
        // Update the sum and the current node's data
        sum.addAndGet(node.data);
        node.data = sum.get();
        
        // Traverse the left subtree
        convertBSTHelper(node.left, sum);
    }

    // --- Main Method for Demonstration ---
    public static void main(String[] args) {
        System.out.println("====== BST Conversion and Balance Demo ======");

        // --- 1. BST to Doubly-Linked List ---
        TreeNode bstRoot = new TreeNode(4);
        bstRoot.left = new TreeNode(2);
        bstRoot.right = new TreeNode(5);
        bstRoot.left.left = new TreeNode(1);
        bstRoot.left.right = new TreeNode(3);
        System.out.println("\n--- 1. BST to Circular Doubly-Linked List ---");
        TreeNode listHead = treeToDoublyList(bstRoot);
        System.out.print("List forward: ");
        TreeNode curr = listHead;
        do {
            System.out.print(curr.data + " ");
            curr = curr.right;
        } while (curr != listHead);
        System.out.println();

        // --- 2. Sorted Array to Balanced BST ---
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("\n--- 2. Sorted Array to Balanced BST ---");
        TreeNode balancedRoot = sortedArrayToBST(sortedArray);
        System.out.println("In-order traversal of constructed tree (should be sorted):");
        printInOrder(balancedRoot);
        System.out.println();

        // --- 3. Check Balance ---
        System.out.println("\n--- 3. Check if BST is Balanced ---");
        System.out.println("Is the tree from the sorted array balanced? " + isBalanced(balancedRoot));
        TreeNode unbalancedRoot = new TreeNode(1);
        unbalancedRoot.right = new TreeNode(2);
        unbalancedRoot.right.right = new TreeNode(3);
        System.out.println("Is the skewed tree balanced? " + isBalanced(unbalancedRoot));

        // --- 4. Convert to Greater Sum Tree ---
        System.out.println("\n--- 4. Convert to Greater Sum Tree ---");
        TreeNode greaterSumRoot = sortedArrayToBST(new int[]{4,1,6,0,2,5,7,3,8});
        System.out.print("Original tree (in-order): ");
        printInOrder(greaterSumRoot);
        System.out.println();
        convertBST(greaterSumRoot);
        System.out.print("Greater Sum Tree (in-order): ");
        printInOrder(greaterSumRoot);
        System.out.println();

        System.out.println("\n==============================================");
    }
    
    public static void printInOrder(TreeNode node) {
        if(node == null) return;
        printInOrder(node.left);
        System.out.print(node.data + " ");
        printInOrder(node.right);
    }
}
