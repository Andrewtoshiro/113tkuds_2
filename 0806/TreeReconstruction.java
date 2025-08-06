import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeReconstruction {

    // Static inner class for a tree node
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // Map to store indices of inorder elements for O(1) lookup
    private static Map<Integer, Integer> inorderMap;

    // --- 1. Reconstruct from Pre-order and In-order ---
    public static TreeNode buildTreeFromPreIn(int[] preorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreePreInHelper(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode buildTreePreInHelper(int[] preorder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;

        // The first element in preorder is the root
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRootIndex = inorderMap.get(root.data);
        int leftSubtreeSize = inRootIndex - inStart;

        // Recursively build left and right subtrees
        root.left = buildTreePreInHelper(preorder, preStart + 1, preStart + leftSubtreeSize, inStart, inRootIndex - 1);
        root.right = buildTreePreInHelper(preorder, preStart + leftSubtreeSize + 1, preEnd, inRootIndex + 1, inEnd);
        
        return root;
    }

    // --- 2. Reconstruct from Post-order and In-order ---
    public static TreeNode buildTreeFromPostIn(int[] postorder, int[] inorder) {
        inorderMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return buildTreePostInHelper(postorder, 0, postorder.length - 1, 0, inorder.length - 1);
    }

    private static TreeNode buildTreePostInHelper(int[] postorder, int postStart, int postEnd, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd) return null;

        // The last element in postorder is the root
        TreeNode root = new TreeNode(postorder[postEnd]);
        int inRootIndex = inorderMap.get(root.data);
        int leftSubtreeSize = inRootIndex - inStart;

        // Recursively build left and right subtrees
        root.left = buildTreePostInHelper(postorder, postStart, postStart + leftSubtreeSize - 1, inStart, inRootIndex - 1);
        root.right = buildTreePostInHelper(postorder, postStart + leftSubtreeSize, postEnd - 1, inRootIndex + 1, inEnd);
        
        return root;
    }

    // --- 3. Reconstruct Complete Binary Tree from Level-Order ---
    public static TreeNode buildCompleteTreeFromLevelOrder(int[] levelOrder) {
        return buildCompleteHelper(levelOrder, 0);
    }

    private static TreeNode buildCompleteHelper(int[] arr, int index) {
        if (index >= arr.length) return null;

        TreeNode node = new TreeNode(arr[index]);
        node.left = buildCompleteHelper(arr, 2 * index + 1);
        node.right = buildCompleteHelper(arr, 2 * index + 2);
        
        return node;
    }

    // --- 4. Verification Helpers ---
    public static List<Integer> getPreOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preOrderHelper(root, result);
        return result;
    }
    private static void preOrderHelper(TreeNode node, List<Integer> list) {
        if (node == null) return;
        list.add(node.data);
        preOrderHelper(node.left, list);
        preOrderHelper(node.right, list);
    }
    
    public static List<Integer> getPostOrderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postOrderHelper(root, result);
        return result;
    }
    private static void postOrderHelper(TreeNode node, List<Integer> list) {
        if (node == null) return;
        postOrderHelper(node.left, list);
        postOrderHelper(node.right, list);
        list.add(node.data);
    }

    public static void main(String[] args) {
        System.out.println("====== Tree Reconstruction Demo ======");

        // --- Demo 1: Pre-order and In-order ---
        System.out.println("\n--- 1. From Pre-order and In-order ---");
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root1 = buildTreeFromPreIn(preorder, inorder);
        System.out.println("Original Pre-order: " + Arrays.toString(preorder));
        System.out.println("Original In-order:  " + Arrays.toString(inorder));
        List<Integer> reconstructedPostOrder = getPostOrderTraversal(root1);
        System.out.println("Verification (reconstructed Post-order): " + reconstructedPostOrder);

        // --- Demo 2: Post-order and In-order ---
        System.out.println("\n--- 2. From Post-order and In-order ---");
        int[] postorder = {9, 15, 7, 20, 3};
        // inorder is the same as above
        TreeNode root2 = buildTreeFromPostIn(postorder, inorder);
        System.out.println("Original Post-order: " + Arrays.toString(postorder));
        System.out.println("Original In-order:   " + Arrays.toString(inorder));
        List<Integer> reconstructedPreOrder = getPreOrderTraversal(root2);
        System.out.println("Verification (reconstructed Pre-order): " + reconstructedPreOrder);

        // --- Demo 3: Complete Tree from Level-order ---
        System.out.println("\n--- 3. Complete Tree from Level-order ---");
        int[] levelorder = {1, 2, 3, 4, 5, 6};
        TreeNode root3 = buildCompleteTreeFromLevelOrder(levelorder);
        System.out.println("Original Level-order: " + Arrays.toString(levelorder));
        List<Integer> reconstructedInOrder = new ArrayList<>();
        // In-order traversal for verification
        inOrderHelper(root3, reconstructedInOrder);
        System.out.println("Verification (reconstructed In-order): " + reconstructedInOrder);
        
        System.out.println("\n========================================");
    }
    
    // Helper for printing In-order
    private static void inOrderHelper(TreeNode node, List<Integer> list) {
        if(node == null) return;
        inOrderHelper(node.left, list);
        list.add(node.data);
        inOrderHelper(node.right, list);
    }
}
