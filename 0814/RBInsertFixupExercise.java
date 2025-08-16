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

    private void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(RBNode y) {
        RBNode x = y.left;
        y.left = x.right;
        if (x.right != NIL) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == NIL) root = x;
        else if (y == y.parent.right) y.parent.right = x;
        else y.parent.left = x;
        x.right = y;
        y.parent = x;
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

    private void insertFixup(RBNode node) {
        while (node != root && node.parent.isRed) {
            if (node.parent == node.parent.parent.left) {
                RBNode uncle = node.parent.parent.right;
                if (uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rightRotate(node.parent.parent);
                }
            } else {
                RBNode uncle = node.parent.parent.left;
                if (uncle.isRed) {
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isRed = false;
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
}

public class RBInsertFixupExercise {

    public static void main(String[] args) {
        testFixupCase("Case 1 (Uncle is Red)", new int[]{20, 10, 30, 5});
        testFixupCase("Symmetric Case 1 (Uncle is Red)", new int[]{20, 10, 30, 35});
        testFixupCase("Case 2 -> 3 (Uncle is Black, LR)", new int[]{20, 10, 15});
        testFixupCase("Symmetric Case 2 -> 3 (Uncle is Black, RL)", new int[]{20, 30, 25});
        testFixupCase("Case 3 (Uncle is Black, LL)", new int[]{20, 10, 5});
        testFixupCase("Symmetric Case 3 (Uncle is Black, RR)", new int[]{20, 30, 35});
    }

    private static void testFixupCase(String caseName, int[] values) {
        System.out.println("--- Testing: " + caseName + " ---");
        RedBlackTree tree = new RedBlackTree();
        for (int value : values) {
            System.out.println("Inserting: " + value);
            tree.insert(value);
            System.out.print("  Tree state: ");
            tree.printTree();
        }
        System.out.println("========================================\n");
    }
}
