import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
    }
}

public class M09_AVLValidate {
    
    private static final int UNBALANCED = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = sc.nextInt();
        }
        sc.close();

        TreeNode root = buildTree(nodes);

        if (!isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
            return;
        }

        if (checkHeight(root) == UNBALANCED) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }
    
    public static boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }
    
    public static int checkHeight(TreeNode node) {
        if (node == null) {
            return -1;
        }

        int leftHeight = checkHeight(node.left);
        if (leftHeight == UNBALANCED) return UNBALANCED;

        int rightHeight = checkHeight(node.right);
        if (rightHeight == UNBALANCED) return UNBALANCED;
        
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return UNBALANCED;
        }

        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static TreeNode buildTree(int[] nodes) {
        if (nodes == null || nodes.length == 0 || nodes[0] == -1) {
            return null;
        }
        TreeNode root = new TreeNode(nodes[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (i < nodes.length) {
            TreeNode current = queue.poll();
            if (i < nodes.length && nodes[i] != -1) {
                current.left = new TreeNode(nodes[i]);
                queue.add(current.left);
            }
            i++;
            if (i < nodes.length && nodes[i] != -1) {
                current.right = new TreeNode(nodes[i]);
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
    
    /*
     * Time Complexity: O(N)
     * 說明:
     * 1. 驗證過程包含兩個主要步驟：檢查BST屬性與檢查平衡因子屬性。
     * 2. isValidBST 函式會對樹進行一次完整遍歷，訪問每個節點一次，時間複雜度為 O(N)。
     * 3. checkHeight 函式同樣會對樹進行一次完整的後序遍歷來計算高度與檢查平衡，訪問每個節點一次，時間複雜度也為 O(N)。
     * 4. 由於這兩個操作是依序執行的，總時間複雜度為 O(N) + O(N)，即 O(N)。
     */
}
