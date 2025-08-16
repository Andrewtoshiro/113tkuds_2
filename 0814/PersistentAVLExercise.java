import java.util.ArrayList;
import java.util.List;

final class PersistentAVLNode {
    final int data;
    final PersistentAVLNode left;
    final PersistentAVLNode right;
    final int height;

    public PersistentAVLNode(int data, PersistentAVLNode left, PersistentAVLNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = 1 + Math.max(height(left), height(right));
    }

    private PersistentAVLNode with(PersistentAVLNode newLeft, PersistentAVLNode newRight) {
        return new PersistentAVLNode(this.data, newLeft, newRight);
    }
    
    public PersistentAVLNode withLeft(PersistentAVLNode newLeft) {
        return with(newLeft, this.right);
    }

    public PersistentAVLNode withRight(PersistentAVLNode newRight) {
        return with(this.left, newRight);
    }

    public static int height(PersistentAVLNode node) {
        return node == null ? 0 : node.height;
    }

    public static int getBalance(PersistentAVLNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
}

class PersistentAVLTree {

    private PersistentAVLNode rightRotate(PersistentAVLNode y) {
        PersistentAVLNode x = y.left;
        PersistentAVLNode T2 = x.right;
        return x.withRight(y.withLeft(T2));
    }

    private PersistentAVLNode leftRotate(PersistentAVLNode x) {
        PersistentAVLNode y = x.right;
        PersistentAVLNode T2 = y.left;
        return y.withLeft(x.withRight(T2));
    }

    public PersistentAVLNode insert(PersistentAVLNode node, int data) {
        if (node == null) {
            return new PersistentAVLNode(data, null, null);
        }

        PersistentAVLNode newNode;
        if (data < node.data) {
            PersistentAVLNode newLeft = insert(node.left, data);
            newNode = node.withLeft(newLeft);
        } else if (data > node.data) {
            PersistentAVLNode newRight = insert(node.right, data);
            newNode = node.withRight(newRight);
        } else {
            return node;
        }

        int balance = PersistentAVLNode.getBalance(newNode);

        if (balance > 1 && data < newNode.left.data) {
            return rightRotate(newNode);
        }
        if (balance < -1 && data > newNode.right.data) {
            return leftRotate(newNode);
        }
        if (balance > 1 && data > newNode.left.data) {
            return rightRotate(newNode.withLeft(leftRotate(newNode.left)));
        }
        if (balance < -1 && data < newNode.right.data) {
            return leftRotate(newNode.withRight(rightRotate(newNode.right)));
        }

        return newNode;
    }

    public void printTree(PersistentAVLNode node) {
        printInOrder(node);
        System.out.println();
    }

    private void printInOrder(PersistentAVLNode node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(node.data + " ");
            printInOrder(node.right);
        }
    }
}

public class PersistentAVLExercise {
    public static void main(String[] args) {
        PersistentAVLTree tree = new PersistentAVLTree();
        List<PersistentAVLNode> versions = new ArrayList<>();

        versions.add(null);

        PersistentAVLNode v1 = tree.insert(versions.get(0), 10);
        versions.add(v1);

        PersistentAVLNode v2 = tree.insert(versions.get(1), 20);
        versions.add(v2);

        PersistentAVLNode v3 = tree.insert(versions.get(2), 30);
        versions.add(v3);
        
        PersistentAVLNode v4 = tree.insert(versions.get(3), 5);
        versions.add(v4);

        System.out.print("Version 0 (empty): ");
        tree.printTree(versions.get(0));

        System.out.print("Version 1 (10 inserted): ");
        tree.printTree(versions.get(1));
        
        System.out.print("Version 2 (20 inserted): ");
        tree.printTree(versions.get(2));
        
        System.out.print("Version 3 (30 inserted, rebalanced): ");
        tree.printTree(versions.get(3));

        System.out.print("Version 4 (5 inserted): ");
        tree.printTree(versions.get(4));
    }
}
