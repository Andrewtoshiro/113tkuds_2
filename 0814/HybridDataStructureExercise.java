import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

// --- AVL Tree Prerequisite Classes ---
class AVLNode {
    int data; AVLNode left, right; int height;
    public AVLNode(int data) { this.data = data; this.height = 1; }
    public int getBalance() { return ((left != null) ? left.height : 0) - ((right != null) ? right.height : 0); }
    public void updateHeight() { this.height = 1 + Math.max(((left != null) ? left.height : 0), ((right != null) ? right.height : 0)); }
}
class AVLRotations {
    public static AVLNode rightRotate(AVLNode y) { AVLNode x = y.left; AVLNode T2 = x.right; x.right = y; y.left = T2; y.updateHeight(); x.updateHeight(); return x; }
    public static AVLNode leftRotate(AVLNode x) { AVLNode y = x.right; AVLNode T2 = y.left; y.left = x; x.right = T2; x.updateHeight(); y.updateHeight(); return y; }
}
class AVLIterator implements Iterator<Integer> {
    private final Stack<AVLNode> stack = new Stack<>();
    public AVLIterator(AVLNode root) { pushLeft(root); }
    private void pushLeft(AVLNode node) { while (node != null) { stack.push(node); node = node.left; } }
    @Override public boolean hasNext() { return !stack.isEmpty(); }
    @Override public Integer next() { if (!hasNext()) throw new NoSuchElementException(); AVLNode node = stack.pop(); pushLeft(node.right); return node.data; }
}
class AVLTree implements Iterable<Integer> {
    private AVLNode root;
    public void insert(int data) { root = insertNode(root, data); }
    public boolean search(int data) { AVLNode current = root; while (current != null) { if (data < current.data) current = current.left; else if (data > current.data) current = current.right; else return true; } return false; }
    @Override public Iterator<Integer> iterator() { return new AVLIterator(root); }
    private AVLNode insertNode(AVLNode node, int data) { if (node == null) return new AVLNode(data); if (data < node.data) node.left = insertNode(node.left, data); else if (data > node.data) node.right = insertNode(node.right, data); else return node; node.updateHeight(); int balance = node.getBalance(); if (balance > 1 && data < node.left.data) return AVLRotations.rightRotate(node); if (balance < -1 && data > node.right.data) return AVLRotations.leftRotate(node); if (balance > 1 && data > node.left.data) { node.left = AVLRotations.leftRotate(node.left); return AVLRotations.rightRotate(node); } if (balance < -1 && data < node.right.data) { node.right = AVLRotations.rightRotate(node.right); return AVLRotations.leftRotate(node); } return node; }
}

// --- Red-Black Tree Prerequisite Classes ---
class RBNode { int data; RBNode left, right, parent; boolean isRed; public RBNode(int data) { this.data = data; this.isRed = true; } public RBNode(int data, boolean isRed) { this.data = data; this.isRed = isRed; } }
class RBIterator implements Iterator<Integer> {
    private final Stack<RBNode> stack = new Stack<>(); private final RBNode NIL;
    public RBIterator(RBNode root, RBNode nil) { this.NIL = nil; pushLeft(root); }
    private void pushLeft(RBNode node) { while (node != NIL) { stack.push(node); node = node.left; } }
    @Override public boolean hasNext() { return !stack.isEmpty(); }
    @Override public Integer next() { if (!hasNext()) throw new NoSuchElementException(); RBNode node = stack.pop(); pushLeft(node.right); return node.data; }
}
class RedBlackTree implements Iterable<Integer> {
    private RBNode root; private final RBNode NIL;
    public RedBlackTree() { NIL = new RBNode(0, false); root = NIL; }
    public boolean search(int data) { RBNode node = root; while (node != NIL && data != node.data) { if (data < node.data) node = node.left; else node = node.right; } return node != NIL; }
    @Override public Iterator<Integer> iterator() { return new RBIterator(root, NIL); }
    public void insert(int data) { RBNode n = new RBNode(data); n.left = n.right = n.parent = NIL; RBNode p = NIL; RBNode c = root; while (c != NIL) { p = c; if (n.data < c.data) c = c.left; else c = c.right; } n.parent = p; if (p == NIL) root = n; else if (n.data < p.data) p.left = n; else p.right = n; insertFixup(n); }
    private void leftRotate(RBNode x) { RBNode y = x.right; x.right = y.left; if (y.left != NIL) y.left.parent = x; y.parent = x.parent; if (x.parent == NIL) root = y; else if (x == x.parent.left) x.parent.left = y; else x.parent.right = y; y.left = x; x.parent = y; }
    private void rightRotate(RBNode y) { RBNode x = y.left; y.left = x.right; if (x.right != NIL) x.right.parent = y; x.parent = y.parent; if (y.parent == NIL) root = x; else if (y == y.parent.right) y.parent.right = x; else y.parent.left = x; x.right = y; y.parent = x; }
    private void insertFixup(RBNode n) { while (n != root && n.parent.isRed) { if (n.parent == n.parent.parent.left) { RBNode u = n.parent.parent.right; if (u.isRed) { n.parent.isRed = false; u.isRed = false; n.parent.parent.isRed = true; n = n.parent.parent; } else { if (n == n.parent.right) { n = n.parent; leftRotate(n); } n.parent.isRed = false; n.parent.parent.isRed = true; rightRotate(n.parent.parent); } } else { RBNode u = n.parent.parent.left; if (u.isRed) { n.parent.isRed = false; u.isRed = false; n.parent.parent.isRed = true; n = n.parent.parent; } else { if (n == n.parent.left) { n = n.parent; rightRotate(n); } n.parent.isRed = false; n.parent.parent.isRed = true; leftRotate(n.parent.parent); } } } root.isRed = false; }
}

// --- Hybrid Data Structure ---
class HybridTree {
    private AVLTree avlTree;
    private RedBlackTree rbTree;
    private boolean isUsingAVL = false;
    private long readCount = 0;
    private long writeCount = 0;
    private static final int SWITCH_THRESHOLD = 2000;

    public HybridTree() {
        this.rbTree = new RedBlackTree();
        this.isUsingAVL = false;
    }

    public void insert(int data) {
        writeCount++;
        if (isUsingAVL) avlTree.insert(data);
        else rbTree.insert(data);
        checkAndSwitch();
    }

    public boolean search(int data) {
        readCount++;
        boolean result = isUsingAVL ? avlTree.search(data) : rbTree.search(data);
        checkAndSwitch();
        return result;
    }

    private void checkAndSwitch() {
        if (readCount + writeCount < SWITCH_THRESHOLD) return;

        if (!isUsingAVL && readCount > writeCount * 2) {
            System.out.println("\n*** Workload is READ-HEAVY. Switching to AVL Tree. ***\n");
            AVLTree newAVL = new AVLTree();
            for (int data : this.rbTree) newAVL.insert(data);
            this.avlTree = newAVL;
            this.rbTree = null;
            this.isUsingAVL = true;
        } else if (isUsingAVL && writeCount > readCount * 2) {
            System.out.println("\n*** Workload is WRITE-HEAVY. Switching to Red-Black Tree. ***\n");
            RedBlackTree newRB = new RedBlackTree();
            for (int data : this.avlTree) newRB.insert(data);
            this.rbTree = newRB;
            this.avlTree = null;
            this.isUsingAVL = false;
        }
        readCount = 0;
        writeCount = 0;
    }

    public String getActiveTreeType() {
        return isUsingAVL ? "AVLTree" : "RedBlackTree";
    }
}

// --- Main Exercise Class ---
public class HybridDataStructureExercise {
    public static void main(String[] args) {
        HybridTree hybridTree = new HybridTree();
        System.out.println("Initial active tree: " + hybridTree.getActiveTreeType());

        System.out.println("\n--- Simulating WRITE-HEAVY Workload ---");
        for(int i = 0; i < 2100; i++) hybridTree.insert(i);
        for(int i = 0; i < 100; i++) hybridTree.search(i);
        System.out.println("Active tree after write-heavy phase: " + hybridTree.getActiveTreeType());

        System.out.println("\n--- Simulating READ-HEAVY Workload ---");
        for(int i = 0; i < 2100; i++) hybridTree.search(i);
        for(int i = 0; i < 100; i++) hybridTree.insert(i + 3000);
        System.out.println("Active tree after read-heavy phase: " + hybridTree.getActiveTreeType());
        
        System.out.println("\n--- Simulating WRITE-HEAVY Workload Again ---");
        for(int i = 0; i < 2100; i++) hybridTree.insert(i + 6000);
        for(int i = 0; i < 100; i++) hybridTree.search(i);
        System.out.println("Active tree after second write-heavy phase: " + hybridTree.getActiveTreeType());
    }
}
