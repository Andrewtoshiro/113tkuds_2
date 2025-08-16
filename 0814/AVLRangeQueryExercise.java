import java.util.ArrayList;
import java.util.List;

class AVLNode {
    int data;
    AVLNode left, right;
    int height;

    public AVLNode(int data) {
        this.data = data;
        this.height = 1;
    }

    public int getBalance() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        return leftHeight - rightHeight;
    }

    public void updateHeight() {
        int leftHeight = (left != null) ? left.height : 0;
        int rightHeight = (right != null) ? right.height : 0;
        this.height = Math.max(leftHeight, rightHeight) + 1;
    }
}

class AVLRotations {
    public static AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;
        x.right = y;
        y.left = T2;
        y.updateHeight();
        x.updateHeight();
        return x;
    }

    public static AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;
        y.left = x;
        x.right = T2;
        x.updateHeight();
        y.updateHeight();
        return y;
    }
}

class AVLTree {
    private AVLNode root;

    public void insert(int data) {
        root = insertNode(root, data);
    }

    private AVLNode insertNode(AVLNode node, int data) {
        if (node == null) return new AVLNode(data);
        if (data < node.data) node.left = insertNode(node.left, data);
        else if (data > node.data) node.right = insertNode(node.right, data);
        else return node;

        node.updateHeight();
        int balance = node.getBalance();

        if (balance > 1 && data < node.left.data) return AVLRotations.rightRotate(node);
        if (balance < -1 && data > node.right.data) return AVLRotations.leftRotate(node);
        if (balance > 1 && data > node.left.data) {
            node.left = AVLRotations.leftRotate(node.left);
            return AVLRotations.rightRotate(node);
        }
        if (balance < -1 && data < node.right.data) {
            node.right = AVLRotations.rightRotate(node.right);
            return AVLRotations.leftRotate(node);
        }
        return node;
    }

    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(AVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }

    public List<Integer> rangeQuery(int min, int max) {
        List<Integer> result = new ArrayList<>();
        rangeQueryRecursive(root, min, max, result);
        return result;
    }

    private void rangeQueryRecursive(AVLNode node, int min, int max, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (node.data > min) {
            rangeQueryRecursive(node.left, min, max, result);
        }

        if (node.data >= min && node.data <= max) {
            result.add(node.data);
        }

        if (node.data < max) {
            rangeQueryRecursive(node.right, min, max, result);
        }
    }
}

public class AVLRangeQueryExercise {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        int[] values = {50, 25, 75, 10, 30, 60, 90, 5, 15, 28, 40, 55, 65, 80, 100};

        for (int value : values) {
            tree.insert(value);
        }
        System.out.print("Complete tree (in-order): ");
        tree.printTree();
        System.out.println("========================================\n");

        int minRange = 28;
        int maxRange = 80;

        List<Integer> results = tree.rangeQuery(minRange, maxRange);

        System.out.println("Query result for range [" + minRange + ", " + maxRange + "]: " + results);
    }
}
