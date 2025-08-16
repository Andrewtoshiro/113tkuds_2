import java.util.function.Function;

// --- AVL Tree Classes Modified for Visualization ---
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
class AVLTree {
    private AVLNode root;
    public AVLNode getRoot() { return root; }
    public void insert(int data) { root = insertNode(root, data); }
    private AVLNode insertNode(AVLNode node, int data) { if (node == null) return new AVLNode(data); if (data < node.data) node.left = insertNode(node.left, data); else if (data > node.data) node.right = insertNode(node.right, data); else return node; node.updateHeight(); int balance = node.getBalance(); if (balance > 1 && data < node.left.data) return AVLRotations.rightRotate(node); if (balance < -1 && data > node.right.data) return AVLRotations.leftRotate(node); if (balance > 1 && data > node.left.data) { node.left = AVLRotations.leftRotate(node.left); return AVLRotations.rightRotate(node); } if (balance < -1 && data < node.right.data) { node.right = AVLRotations.rightRotate(node.right); return AVLRotations.leftRotate(node); } return node; }
}

// --- Red-Black Tree Classes Modified for Visualization ---
class RBNode { int data; RBNode left, right, parent; boolean isRed; public RBNode(int data) { this.data = data; this.isRed = true; } public RBNode(int data, boolean isRed) { this.data = data; this.isRed = isRed; } }
class RedBlackTree {
    private RBNode root; private final RBNode NIL;
    public RBNode getRoot() { return root; }
    public RBNode getNIL() { return NIL; }
    public RedBlackTree() { NIL = new RBNode(0, false); root = NIL; }
    public void insert(int data) { RBNode n = new RBNode(data); n.left = n.right = n.parent = NIL; RBNode p = NIL; RBNode c = root; while (c != NIL) { p = c; if (n.data < c.data) c = c.left; else c = c.right; } n.parent = p; if (p == NIL) root = n; else if (n.data < p.data) p.left = n; else p.right = n; insertFixup(n); }
    private void leftRotate(RBNode x) { RBNode y = x.right; x.right = y.left; if (y.left != NIL) y.left.parent = x; y.parent = x.parent; if (x.parent == NIL) root = y; else if (x == x.parent.left) x.parent.left = y; else x.parent.right = y; y.left = x; x.parent = y; }
    private void rightRotate(RBNode y) { RBNode x = y.left; y.left = x.right; if (x.right != NIL) x.right.parent = y; x.parent = y.parent; if (y.parent == NIL) root = x; else if (y == y.parent.right) y.parent.right = x; else y.parent.left = x; x.right = y; y.parent = x; }
    private void insertFixup(RBNode n) { while (n != root && n.parent.isRed) { if (n.parent == n.parent.parent.left) { RBNode u = n.parent.parent.right; if (u.isRed) { n.parent.isRed = false; u.isRed = false; n.parent.parent.isRed = true; n = n.parent.parent; } else { if (n == n.parent.right) { n = n.parent; leftRotate(n); } n.parent.isRed = false; n.parent.parent.isRed = true; rightRotate(n.parent.parent); } } else { RBNode u = n.parent.parent.left; if (u.isRed) { n.parent.isRed = false; u.isRed = false; n.parent.parent.isRed = true; n = n.parent.parent; } else { if (n == n.parent.left) { n = n.parent; rightRotate(n); } n.parent.isRed = false; n.parent.parent.isRed = true; leftRotate(n.parent.parent); } } } root.isRed = false; }
}

// --- Generic Tree Visualizer ---
class TreeVisualizer {
    public <T> void print(T node, Function<T, String> getText, Function<T, T> getLeft, Function<T, T> getRight, T nilNode) {
        printNode(new StringBuilder(), true, new StringBuilder(), node, getText, getLeft, getRight, nilNode);
    }

    private <T> void printNode(StringBuilder prefix, boolean isTail, StringBuilder sb, T node, Function<T, String> getText, Function<T, T> getLeft, Function<T, T> getRight, T nilNode) {
        if (node == nilNode || node == null) return;
        T right = getRight.apply(node);
        if (right != nilNode) {
            printNode(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, right, getText, getLeft, getRight, nilNode);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(getText.apply(node)).append("\n");
        T left = getLeft.apply(node);
        if (left != nilNode) {
            printNode(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, left, getText, getLeft, getRight, nilNode);
        }
    }
}

// --- Main Exercise Class ---
public class TreeVisualizerExercise {
    public static void main(String[] args) {
        TreeVisualizer visualizer = new TreeVisualizer();
        int[] values = {50, 25, 75, 15, 35, 65, 85, 5, 20, 30, 40, 90};

        System.out.println("--- AVL Tree Visualization ---");
        AVLTree avl = new AVLTree();
        for(int val : values) avl.insert(val);
        StringBuilder avlBuilder = new StringBuilder();
        visualizer.print(avl.getRoot(), 
            (AVLNode n) -> n.data + "(" + n.getBalance() + ")", 
            (AVLNode n) -> n.left, 
            (AVLNode n) -> n.right, 
            null
        );
        System.out.println(avlBuilder.toString());
        
        System.out.println("\n--- Red-Black Tree Visualization ---");
        RedBlackTree rbt = new RedBlackTree();
        for(int val : values) rbt.insert(val);
        StringBuilder rbtBuilder = new StringBuilder();
        visualizer.print(rbt.getRoot(),
            (RBNode n) -> n.data + "(" + (n.isRed ? "R" : "B") + ")",
            (RBNode n) -> n.left,
            (RBNode n) -> n.right,
            rbt.getNIL()
        );
        System.out.println(rbtBuilder.toString());
    }
}
