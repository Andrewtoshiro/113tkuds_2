import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

class RBNode {
    int data;
    RBNode left, right, parent;
    boolean isRed;

    public RBNode(int data) { this.data = data; this.isRed = true; }
    public RBNode(int data, boolean isRed) { this.data = data; this.isRed = isRed; }
}

class RBTreeIterator implements Iterator<RBNode> {
    private final Stack<RBNode> stack;
    private final RBNode NIL;

    public RBTreeIterator(RBNode root, RBNode nil) {
        this.stack = new Stack<>();
        this.NIL = nil;
        pushLeft(root);
    }

    private void pushLeft(RBNode node) {
        while (node != NIL) {
            stack.push(node);
            node = node.left;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public RBNode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        RBNode node = stack.pop();
        if (node.right != NIL) {
            pushLeft(node.right);
        }
        return node;
    }
}

class RedBlackTree implements Iterable<RBNode> {
    private RBNode root;
    private final RBNode NIL;

    public RedBlackTree() {
        NIL = new RBNode(0, false);
        root = NIL;
    }

    @Override
    public Iterator<RBNode> iterator() {
        return new RBTreeIterator(root, NIL);
    }

    public void insert(int data) {
        RBNode newNode = new RBNode(data);
        newNode.left = newNode.right = newNode.parent = NIL;
        RBNode parent = NIL;
        RBNode current = root;
        while (current != NIL) {
            parent = current;
            if (newNode.data < current.data) current = current.left;
            else current = current.right;
        }
        newNode.parent = parent;
        if (parent == NIL) root = newNode;
        else if (newNode.data < parent.data) parent.left = newNode;
        else parent.right = newNode;
        insertFixup(newNode);
    }

    private void leftRotate(RBNode x) {
        RBNode y = x.right; x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x; x.parent = y;
    }

    private void rightRotate(RBNode y) {
        RBNode x = y.left; y.left = x.right;
        if (x.right != NIL) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == NIL) root = x;
        else if (y == y.parent.right) y.parent.right = x;
        else y.parent.left = x;
        x.right = y; y.parent = x;
    }
    
    private void insertFixup(RBNode node) {
        while (node != root && node.parent.isRed) {
            if (node.parent == node.parent.parent.left) {
                RBNode uncle = node.parent.parent.right;
                if (uncle.isRed) {
                    node.parent.isRed = false; uncle.isRed = false;
                    node.parent.parent.isRed = true; node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent; leftRotate(node);
                    }
                    node.parent.isRed = false; node.parent.parent.isRed = true;
                    rightRotate(node.parent.parent);
                }
            } else {
                RBNode uncle = node.parent.parent.left;
                if (uncle.isRed) {
                    node.parent.isRed = false; uncle.isRed = false;
                    node.parent.parent.isRed = true; node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent; rightRotate(node);
                    }
                    node.parent.isRed = false; node.parent.parent.isRed = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isRed = false;
    }
}


public class RBTreeIteratorExercise {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        int[] values = {20, 10, 30, 5, 15, 25, 35, 12, 18};
        for (int v : values) {
            tree.insert(v);
        }

        System.out.print("1. Sequential (In-Order) Traversal: ");
        for (RBNode node : tree) {
            System.out.print(node.data + " ");
        }
        System.out.println("\n");

        System.out.print("2. Reverse Traversal: ");
        List<RBNode> nodes = new ArrayList<>();
        for (RBNode node : tree) {
            nodes.add(node);
        }
        for (int i = nodes.size() - 1; i >= 0; i--) {
            System.out.print(nodes.get(i).data + " ");
        }
        System.out.println("\n");

        int minRange = 12;
        int maxRange = 25;
        System.out.print("3. Range Traversal [" + minRange + ", " + maxRange + "]: ");
        for (RBNode node : tree) {
            if (node.data >= minRange && node.data <= maxRange) {
                System.out.print(node.data + " ");
            }
        }
        System.out.println();
    }
}
