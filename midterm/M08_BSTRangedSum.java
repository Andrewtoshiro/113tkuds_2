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

public class M08_BSTRangedSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = sc.nextInt();
        }
        int L = sc.nextInt();
        int R = sc.nextInt();
        sc.close();

        TreeNode root = buildTree(nodes);
        int sum = rangedSum(root, L, R);

        System.out.println("Sum: " + sum);
    }

    public static int rangedSum(TreeNode node, int L, int R) {
        if (node == null) {
            return 0;
        }

        if (node.val < L) {
            return rangedSum(node.right, L, R);
        }

        if (node.val > R) {
            return rangedSum(node.left, L, R);
        }

        return node.val + rangedSum(node.left, L, R) + rangedSum(node.right, L, R);
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
            if (current != null) {
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
        }
        return root;
    }
}
