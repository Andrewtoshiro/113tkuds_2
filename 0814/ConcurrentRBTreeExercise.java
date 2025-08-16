import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Random;

class RBNode {
    int data;
    RBNode left, right, parent;
    boolean isRed;

    public RBNode(int data) { this.data = data; this.isRed = true; }
    public RBNode(int data, boolean isRed) { this.data = data; this.isRed = isRed; }
}

class RedBlackTree {
    private RBNode root;
    private final RBNode NIL;

    public RedBlackTree() {
        NIL = new RBNode(0, false);
        root = NIL;
    }

    public boolean search(int data) {
        return searchNode(root, data) != NIL;
    }

    private RBNode searchNode(RBNode node, int data) {
        while (node != NIL && data != node.data) {
            if (data < node.data) node = node.left;
            else node = node.right;
        }
        return node;
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
    
    public void printTree() {
        printInOrder(root);
        System.out.println();
    }

    private void printInOrder(RBNode node) {
        if (node != NIL) {
            printInOrder(node.left);
            System.out.print(node.data + "(" + (node.isRed ? "R" : "B") + ") ");
            printInOrder(node.right);
        }
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

class ConcurrentRedBlackTree {
    private final RedBlackTree tree = new RedBlackTree();
    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();
    private final Lock writeLock = rwLock.writeLock();

    public void insert(int data) {
        writeLock.lock();
        try {
            tree.insert(data);
        } finally {
            writeLock.unlock();
        }
    }

    public boolean search(int data) {
        readLock.lock();
        try {
            return tree.search(data);
        } finally {
            readLock.unlock();
        }
    }

    public void print() {
        readLock.lock();
        try {
            tree.printTree();
        } finally {
            readLock.unlock();
        }
    }
}

public class ConcurrentRBTreeExercise {

    public static void main(String[] args) {
        ConcurrentRedBlackTree concurrentTree = new ConcurrentRedBlackTree();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    int value = random.nextInt(1000);
                    concurrentTree.insert(value);
                }
            });
        }

        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    int value = random.nextInt(1000);
                    concurrentTree.search(value);
                }
            });
        }

        try {
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.print("Final tree state after concurrent operations: ");
        concurrentTree.print();
    }
}
