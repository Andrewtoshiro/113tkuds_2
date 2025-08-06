import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeBasicOperations {

    // Static inner class for a tree node
    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;

        TreeNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // --- 1. Calculate Sum and Average ---

    public static int sumAllNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return root.data + sumAllNodes(root.left) + sumAllNodes(root.right);
    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    public static double average(TreeNode root) {
        if (root == null) {
            return 0.0;
        }
        int sum = sumAllNodes(root);
        int count = countNodes(root);
        return (double) sum / count;
    }

    // --- 2. Find Maximum and Minimum Values ---

    public static int findMax(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty, no maximum value.");
        }
        int max = root.data;
        if (root.left != null) {
            max = Math.max(max, findMax(root.left));
        }
        if (root.right != null) {
            max = Math.max(max, findMax(root.right));
        }
        return max;
    }

    public static int findMin(TreeNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Tree is empty, no minimum value.");
        }
        int min = root.data;
        if (root.left != null) {
            min = Math.min(min, findMin(root.left));
        }
        if (root.right != null) {
            min = Math.min(min, findMin(root.right));
        }
        return min;
    }

    // --- 3. Calculate Tree Width ---

    public static int getTreeWidth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int maxWidth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            maxWidth = Math.max(maxWidth, levelSize);

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
        }
        return maxWidth;
    }

    // --- 4. Determine if a Tree is a Complete Binary Tree ---

    public static boolean isCompleteBinaryTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean foundFirstNull = false;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current == null) {
                foundFirstNull = true;
            } else {
                if (foundFirstNull) {
                    return false; // A non-null node appeared after a null node.
                }
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println("====== Binary Tree Basic Operations Demo ======");

        // Create a complete binary tree for testing
        //        10
        //       /  \
        //      5    15
        //     / \   /
        //    3   7 12
        TreeNode completeTree = new TreeNode(10);
        completeTree.left = new TreeNode(5);
        completeTree.right = new TreeNode(15);
        completeTree.left.left = new TreeNode(3);
        completeTree.left.right = new TreeNode(7);
        completeTree.right.left = new TreeNode(12);

        System.out.println("\n--- Testing with a COMPLETE Binary Tree ---");
        System.out.println("1. Sum of all nodes: " + sumAllNodes(completeTree));
        System.out.printf("   Average of all nodes: %.2f\n", average(completeTree));
        System.out.println("2. Maximum value: " + findMax(completeTree));
        System.out.println("   Minimum value: " + findMin(completeTree));
        System.out.println("3. Tree width: " + getTreeWidth(completeTree));
        System.out.println("4. Is the tree complete? " + isCompleteBinaryTree(completeTree));

        // Create a non-complete binary tree for testing
        //        10
        //       /  \
        //      5    15
        //     /       \
        //    3         18
        TreeNode incompleteTree = new TreeNode(10);
        incompleteTree.left = new TreeNode(5);
        incompleteTree.right = new TreeNode(15);
        incompleteTree.left.left = new TreeNode(3);
        incompleteTree.right.right = new TreeNode(18);

        System.out.println("\n--- Testing with an INCOMPLETE Binary Tree ---");
        System.out.println("Is the tree complete? " + isCompleteBinaryTree(incompleteTree));
        
        System.out.println("\n=============================================");
    }
}
