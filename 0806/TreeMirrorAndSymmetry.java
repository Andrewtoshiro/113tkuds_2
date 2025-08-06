import java.util.LinkedList;
import java.util.Queue;

public class TreeMirrorAndSymmetry {

    // Static inner class for a tree node
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
        }
    }

    // --- 1. Determine if a Binary Tree is Symmetric ---
    public static boolean isSymmetric(TreeNode root) {
        // A tree is symmetric if its left and right subtrees are mirror images of each other.
        if (root == null) {
            return true;
        }
        return isMirror(root.left, root.right);
    }

    // --- 2. Convert a Binary Tree into its Mirror Image ---
    public static TreeNode mirror(TreeNode node) {
        // Base case: If the node is null, there's nothing to do.
        if (node == null) {
            return null;
        }

        // Recursively mirror the left and right subtrees first.
        TreeNode leftMirror = mirror(node.left);
        TreeNode rightMirror = mirror(node.right);

        // Swap the left and right children of the current node.
        node.left = rightMirror;
        node.right = leftMirror;

        return node;
    }

    // --- 3. Compare Two Trees to see if they are Mirror Images ---
    // This is the core helper function used by isSymmetric.
    public static boolean areMirrorImages(TreeNode node1, TreeNode node2) {
        return isMirror(node1, node2);
    }
    
    // Helper function for requirements 1 and 3.
    private static boolean isMirror(TreeNode node1, TreeNode node2) {
        // Base case 1: If both nodes are null, they are mirrors.
        if (node1 == null && node2 == null) {
            return true;
        }
        // Base case 2: If one is null and the other is not, they are not mirrors.
        if (node1 == null || node2 == null) {
            return false;
        }

        // Check if the data matches and if the subtrees are mirrors of each other.
        // node1's left must be a mirror of node2's right, and vice versa.
        return (node1.data == node2.data) &&
               isMirror(node1.left, node2.right) &&
               isMirror(node1.right, node2.left);
    }

    // --- 4. Check if one Tree is a Subtree of Another ---
    public static boolean isSubtree(TreeNode mainTree, TreeNode subTree) {
        // Base case 1: A null subtree is always a subtree.
        if (subTree == null) {
            return true;
        }
        // Base case 2: If the main tree is null but subtree is not, it can't be found.
        if (mainTree == null) {
            return false;
        }

        // Check if the trees are identical starting from the current node.
        if (areIdentical(mainTree, subTree)) {
            return true;
        }

        // Recursive step: If not, check if the subtree exists in the left or right part of the main tree.
        return isSubtree(mainTree.left, subTree) || isSubtree(mainTree.right, subTree);
    }

    // Helper function to check if two trees are structurally and value-wise identical.
    private static boolean areIdentical(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return (node1.data == node2.data) &&
               areIdentical(node1.left, node2.left) &&
               areIdentical(node1.right, node2.right);
    }
    
    // Helper to print tree level by level for visualization
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("(empty tree)");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.data + " ");
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
        System.out.println();
    }


    public static void main(String[] args) {
        System.out.println("====== Tree Mirror and Symmetry Demo ======");

        // --- 1. Symmetric Tree Test ---
        TreeNode symmetricTree = new TreeNode(1);
        symmetricTree.left = new TreeNode(2);
        symmetricTree.right = new TreeNode(2);
        symmetricTree.left.left = new TreeNode(3);
        symmetricTree.left.right = new TreeNode(4);
        symmetricTree.right.left = new TreeNode(4);
        symmetricTree.right.right = new TreeNode(3);
        System.out.println("\n1. Is the first tree symmetric? " + isSymmetric(symmetricTree));
        
        TreeNode asymmetricTree = new TreeNode(1);
        asymmetricTree.left = new TreeNode(2);
        asymmetricTree.right = new TreeNode(2);
        asymmetricTree.left.right = new TreeNode(3);
        asymmetricTree.right.right = new TreeNode(3);
        System.out.println("   Is the second tree symmetric? " + isSymmetric(asymmetricTree));

        // --- 2. Mirror Tree Test ---
        TreeNode originalTree = new TreeNode(10);
        originalTree.left = new TreeNode(5);
        originalTree.right = new TreeNode(15);
        originalTree.left.left = new TreeNode(3);
        originalTree.right.right = new TreeNode(18);
        System.out.print("\n2. Mirroring a tree. Original tree: ");
        printTree(originalTree);
        mirror(originalTree);
        System.out.print("   Mirrored tree: ");
        printTree(originalTree);

        // --- 3. Mirror Images Test ---
        TreeNode tree1 = new TreeNode(1);
        tree1.left = new TreeNode(2);
        tree1.right = new TreeNode(3);
        TreeNode tree2 = new TreeNode(1);
        tree2.left = new TreeNode(3);
        tree2.right = new TreeNode(2);
        System.out.println("\n3. Are tree1 and tree2 mirror images? " + areMirrorImages(tree1, tree2));
        System.out.println("   Is tree1 a mirror of itself? " + areMirrorImages(tree1, tree1));
        
        // --- 4. Subtree Test ---
        TreeNode mainTree = new TreeNode(26);
        mainTree.left = new TreeNode(10);
        mainTree.right = new TreeNode(3);
        mainTree.left.left = new TreeNode(4);
        mainTree.left.right = new TreeNode(6);
        mainTree.right.right = new TreeNode(3);

        TreeNode subTree1 = new TreeNode(10);
        subTree1.left = new TreeNode(4);
        subTree1.right = new TreeNode(6);

        TreeNode subTree2 = new TreeNode(10);
        subTree2.left = new TreeNode(4);
        subTree2.right = new TreeNode(7); // Different value

        System.out.println("\n4. Subtree Check");
        System.out.println("   Is subTree1 a subtree of mainTree? " + isSubtree(mainTree, subTree1));
        System.out.println("   Is subTree2 a subtree of mainTree? " + isSubtree(mainTree, subTree2));

        System.out.println("\n==========================================");
    }
}
