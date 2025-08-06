import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BSTKthElement {

    // --- Part 1: Simple BST for Requirements 1, 2, 3 ---

    static class SimpleBST {
        static class TreeNode {
            int data;
            TreeNode left;
            TreeNode right;

            TreeNode(int data) {
                this.data = data;
            }
        }

        private TreeNode root;

        public void insert(int data) {
            root = insertRec(root, data);
        }

        private TreeNode insertRec(TreeNode current, int data) {
            if (current == null) return new TreeNode(data);
            if (data < current.data) current.left = insertRec(current.left, data);
            else if (data > current.data) current.right = insertRec(current.right, data);
            return current;
        }

        // 1. Find the k-th smallest element
        public Integer findKthSmallest(int k) {
            AtomicInteger count = new AtomicInteger(k);
            AtomicInteger result = new AtomicInteger(Integer.MIN_VALUE);
            kthSmallestHelper(root, count, result);
            return result.get() == Integer.MIN_VALUE ? null : result.get();
        }

        private void kthSmallestHelper(TreeNode node, AtomicInteger k, AtomicInteger result) {
            if (node == null || k.get() <= 0) return;
            kthSmallestHelper(node.left, k, result);
            if (k.get() > 0) {
                k.decrementAndGet();
                if (k.get() == 0) {
                    result.set(node.data);
                    return;
                }
                kthSmallestHelper(node.right, k, result);
            }
        }

        // 2. Find the k-th largest element
        public Integer findKthLargest(int k) {
            AtomicInteger count = new AtomicInteger(k);
            AtomicInteger result = new AtomicInteger(Integer.MIN_VALUE);
            kthLargestHelper(root, count, result);
            return result.get() == Integer.MIN_VALUE ? null : result.get();
        }

        private void kthLargestHelper(TreeNode node, AtomicInteger k, AtomicInteger result) {
            if (node == null || k.get() <= 0) return;
            kthLargestHelper(node.right, k, result);
            if (k.get() > 0) {
                k.decrementAndGet();
                if (k.get() == 0) {
                    result.set(node.data);
                    return;
                }
                kthLargestHelper(node.left, k, result);
            }
        }
        
        // 3. Find all elements between k-th and j-th smallest
        public List<Integer> findBetweenKandJ(int k, int j) {
            List<Integer> allElements = new ArrayList<>();
            inOrderTraversal(root, allElements);
            if (k < 1 || j > allElements.size() || k > j) {
                throw new IllegalArgumentException("Invalid range for k and j.");
            }
            return allElements.subList(k - 1, j);
        }

        private void inOrderTraversal(TreeNode node, List<Integer> elements) {
            if (node == null) return;
            inOrderTraversal(node.left, elements);
            elements.add(node.data);
            inOrderTraversal(node.right, elements);
        }
    }


    // --- Part 2: Augmented BST for Requirement 4 ---
    
    static class AugmentedBST {
        static class AugTreeNode {
            int data;
            int size; // Number of nodes in the subtree rooted here
            AugTreeNode left;
            AugTreeNode right;

            AugTreeNode(int data) {
                this.data = data;
                this.size = 1;
            }
        }

        private AugTreeNode root;
        
        private int size(AugTreeNode node) {
            return node == null ? 0 : node.size;
        }
        
        private void updateSize(AugTreeNode node) {
            node.size = 1 + size(node.left) + size(node.right);
        }

        public void insert(int data) {
            root = insertRec(root, data);
        }

        private AugTreeNode insertRec(AugTreeNode node, int data) {
            if (node == null) return new AugTreeNode(data);
            if (data < node.data) node.left = insertRec(node.left, data);
            else if (data > node.data) node.right = insertRec(node.right, data);
            updateSize(node);
            return node;
        }
        
        // 4. Find k-th smallest element dynamically
        public Integer findKthSmallestDynamic(int k) {
            if (k < 1 || k > size(root)) return null;
            return findKthSmallestRec(root, k);
        }

        private Integer findKthSmallestRec(AugTreeNode node, int k) {
            if (node == null) return null;
            
            int leftSize = size(node.left);
            
            if (k == leftSize + 1) {
                return node.data; // Root of the current subtree is the answer
            } else if (k <= leftSize) {
                return findKthSmallestRec(node.left, k); // It's in the left subtree
            } else {
                // It's in the right subtree, adjust k
                return findKthSmallestRec(node.right, k - leftSize - 1);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("====== BST K-th Element Demo ======");

        // --- Demo for Requirements 1, 2, 3 ---
        System.out.println("\n--- Part 1: Simple BST Queries ---");
        SimpleBST bst = new SimpleBST();
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        for (int v : values) bst.insert(v);

        System.out.println("1. Find K-th Smallest: The 3rd smallest element is " + bst.findKthSmallest(3));
        System.out.println("2. Find K-th Largest: The 2nd largest element is " + bst.findKthLargest(2));
        System.out.println("3. Find between 2nd and 5th smallest: " + bst.findBetweenKandJ(2, 5));

        // --- Demo for Requirement 4 ---
        System.out.println("\n--- Part 2: Augmented BST for Dynamic Queries ---");
        AugmentedBST augBst = new AugmentedBST();
        for (int v : values) augBst.insert(v);
        
        System.out.println("Dynamically found 4th smallest element: " + augBst.findKthSmallestDynamic(4));
        System.out.println("Dynamically found 7th smallest element: " + augBst.findKthSmallestDynamic(7));
        
        // This part would require a full delete implementation for the augmented tree.
        // For now, we demonstrate with inserts.
        System.out.println("\nInserting 10 and 90...");
        augBst.insert(10);
        augBst.insert(90);
        
        System.out.println("After inserts, the 1st smallest is: " + augBst.findKthSmallestDynamic(1));
        System.out.println("After inserts, the 9th smallest is: " + augBst.findKthSmallestDynamic(9));
        
        System.out.println("\n=====================================");
    }
}
