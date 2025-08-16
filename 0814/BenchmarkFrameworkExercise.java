import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// --- Common Interface for Benchmarkable Trees ---
interface BenchmarkableTree {
    void insert(int data);
    boolean search(int data);
    void clear();
    String getName();
}

// --- AVL Tree and Adapter ---
class AVLNode { int data; AVLNode left, right; int height; public AVLNode(int data) { this.data = data; this.height = 1; } public int getBalance() { return ((left != null) ? left.height : 0) - ((right != null) ? right.height : 0); } public void updateHeight() { this.height = 1 + Math.max(((left != null) ? left.height : 0), ((right != null) ? right.height : 0)); } }
class AVLRotations { public static AVLNode rightRotate(AVLNode y) { AVLNode x = y.left; AVLNode T2 = x.right; x.right = y; y.left = T2; y.updateHeight(); x.updateHeight(); return x; } public static AVLNode leftRotate(AVLNode x) { AVLNode y = x.right; AVLNode T2 = y.left; y.left = x; x.right = T2; x.updateHeight(); y.updateHeight(); return y; } }
class AVLTree {
    protected AVLNode root;
    public void insert(int data) { root = insertNode(root, data); }
    public boolean search(int data) { AVLNode current = root; while (current != null) { if (data < current.data) current = current.left; else if (data > current.data) current = current.right; else return true; } return false; }
    private AVLNode insertNode(AVLNode node, int data) { if (node == null) return new AVLNode(data); if (data < node.data) node.left = insertNode(node.left, data); else if (data > node.data) node.right = insertNode(node.right, data); else return node; node.updateHeight(); int balance = node.getBalance(); if (balance > 1 && data < node.left.data) return AVLRotations.rightRotate(node); if (balance < -1 && data > node.right.data) return AVLRotations.leftRotate(node); if (balance > 1 && data > node.left.data) { node.left = AVLRotations.leftRotate(node.left); return AVLRotations.rightRotate(node); } if (balance < -1 && data < node.right.data) { node.right = AVLRotations.rightRotate(node.right); return AVLRotations.leftRotate(node); } return node; }
}
class AVLTreeAdapter extends AVLTree implements BenchmarkableTree {
    @Override public void clear() { root = null; }
    @Override public String getName() { return "AVL Tree"; }
}

// --- Red-Black Tree and Adapter ---
class RBNode { int data; RBNode left, right, parent; boolean isRed; public RBNode(int data, boolean isRed) { this.data = data; this.isRed = isRed; } }
class RedBlackTree {
    protected RBNode root; protected RBNode NIL;
    public RedBlackTree() { NIL = new RBNode(0, false); root = NIL; }
    public boolean search(int data) { RBNode node = root; while (node != NIL && data != node.data) { if (data < node.data) node = node.left; else node = node.right; } return node != NIL; }
    public void insert(int data) { RBNode n = new RBNode(data, true); n.left = n.right = n.parent = NIL; RBNode p = NIL; RBNode c = root; while (c != NIL) { p = c; if (n.data < c.data) c = c.left; else c = c.right; } n.parent = p; if (p == NIL) root = n; else if (n.data < p.data) p.left = n; else p.right = n; insertFixup(n); }
    private void leftRotate(RBNode x) { RBNode y = x.right; x.right = y.left; if (y.left != NIL) y.left.parent = x; y.parent = x.parent; if (x.parent == NIL) root = y; else if (x == x.parent.left) x.parent.left = y; else x.parent.right = y; y.left = x; x.parent = y; }
    private void rightRotate(RBNode y) { RBNode x = y.left; y.left = x.right; if (x.right != NIL) x.right.parent = y; x.parent = y.parent; if (y.parent == NIL) root = x; else if (y == y.parent.right) y.parent.right = x; else y.parent.left = x; x.right = y; y.parent = x; }
    private void insertFixup(RBNode n) { while (n != root && n.parent.isRed) { if (n.parent == n.parent.parent.left) { RBNode u = n.parent.parent.right; if (u.isRed) { n.parent.isRed = false; u.isRed = false; n.parent.parent.isRed = true; n = n.parent.parent; } else { if (n == n.parent.right) { n = n.parent; leftRotate(n); } n.parent.isRed = false; n.parent.parent.isRed = true; rightRotate(n.parent.parent); } } else { RBNode u = n.parent.parent.left; if (u.isRed) { n.parent.isRed = false; u.isRed = false; n.parent.parent.isRed = true; n = n.parent.parent; } else { if (n == n.parent.left) { n = n.parent; rightRotate(n); } n.parent.isRed = false; n.parent.parent.isRed = true; leftRotate(n.parent.parent); } } } root.isRed = false; }
}
class RedBlackTreeAdapter extends RedBlackTree implements BenchmarkableTree {
    @Override public void clear() { root = NIL; }
    @Override public String getName() { return "Red-Black Tree"; }
}

// --- Benchmark Framework ---
class BenchmarkFramework {
    private final List<BenchmarkableTree> trees = new ArrayList<>();
    private final int[] randomData;
    private final int[] sequentialData;

    public BenchmarkFramework(int size) {
        Random rand = new Random(42);
        this.randomData = new int[size];
        this.sequentialData = new int[size];
        for (int i = 0; i < size; i++) {
            this.randomData[i] = rand.nextInt(size * 10);
            this.sequentialData[i] = i;
        }
    }
    
    public void addTree(BenchmarkableTree tree) {
        this.trees.add(tree);
    }

    public void run() {
        System.out.printf("%-18s | %-20s | %-20s | %-20s%n", "Tree Type", "Random Inserts (ms)", "Sequential Inserts (ms)", "Searches (ms)");
        System.out.println(new String(new char[85]).replace("\0", "-"));

        for (BenchmarkableTree tree : trees) {
            tree.clear();
            long randomInsertTime = measure(() -> { for (int d : randomData) tree.insert(d); });
            
            tree.clear();
            long sequentialInsertTime = measure(() -> { for (int d : sequentialData) tree.insert(d); });
            
            tree.clear();
            for(int d : randomData) tree.insert(d);
            long searchTime = measure(() -> { for (int d : randomData) tree.search(d); });

            System.out.printf("%-18s | %-20d | %-20d | %-20d%n", tree.getName(), randomInsertTime, sequentialInsertTime, searchTime);
        }
    }

    private long measure(Runnable task) {
        long startTime = System.nanoTime();
        task.run();
        return (System.nanoTime() - startTime) / 1_000_000;
    }
}


public class BenchmarkFrameworkExercise {
    public static void main(String[] args) {
        BenchmarkFramework framework = new BenchmarkFramework(50000);

        framework.addTree(new AVLTreeAdapter());
        framework.addTree(new RedBlackTreeAdapter());

        framework.run();
    }
}
