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

    private void transplant(RBNode u, RBNode v) {
        if (u.parent == NIL) root = v;
        else if (u == u.parent.left) u.parent.left = v;
        else u.parent.right = v;
        v.parent = u.parent;
    }
    
    private RBNode searchNode(RBNode node, int data) {
        while (node != NIL && data != node.data) {
            if (data < node.data) node = node.left;
            else node = node.right;
        }
        return node;
    }

    public void delete(int data) {
        RBNode nodeToDelete = searchNode(root, data);
        if (nodeToDelete != NIL) {
            deleteNode(nodeToDelete);
        }
    }

    private void deleteNode(RBNode node) {
        RBNode originalNode = node;
        boolean originalColor = originalNode.isRed;
        RBNode replacement;
        if (node.left == NIL) {
            replacement = node.right;
            transplant(node, node.right);
        } else if (node.right == NIL) {
            replacement = node.left;
            transplant(node, node.left);
        } else {
            originalNode = minimum(node.right);
            originalColor = originalNode.isRed;
            replacement = originalNode.right;
            if (originalNode.parent == node) {
                replacement.parent = originalNode;
            } else {
                transplant(originalNode, originalNode.right);
                originalNode.right = node.right;
                originalNode.right.parent = originalNode;
            }
            transplant(node, originalNode);
            originalNode.left = node.left;
            originalNode.left.parent = originalNode;
            originalNode.isRed = node.isRed;
        }
        if (!originalColor) {
            deleteFixup(replacement);
        }
    }
    
    private RBNode minimum(RBNode node) {
        while (node.left != NIL) node = node.left;
        return node;
    }

    private void deleteFixup(RBNode node) {
        while (node != root && !node.isRed) {
            if (node == node.parent.left) {
                RBNode sibling = node.parent.right;
                if (sibling.isRed) {
                    sibling.isRed = false;
                    node.parent.isRed = true;
                    leftRotate(node.parent);
                    sibling = node.parent.right;
                }
                if (!sibling.left.isRed && !sibling.right.isRed) {
                    sibling.isRed = true;
                    node = node.parent;
                } else {
                    if (!sibling.right.isRed) {
                        sibling.left.isRed = false;
                        sibling.isRed = true;
                        rightRotate(sibling);
                        sibling = node.parent.right;
                    }
                    sibling.isRed = node.parent.isRed;
                    node.parent.isRed = false;
                    sibling.right.isRed = false;
                    leftRotate(node.parent);
                    node = root;
                }
            } else {
                RBNode sibling = node.parent.left;
                if (sibling.isRed) {
                    sibling.isRed = false;
                    node.parent.isRed = true;
                    rightRotate(node.parent);
                    sibling = node.parent.left;
                }
                if (!sibling.right.isRed && !sibling.left.isRed) {
                    sibling.isRed = true;
                    node = node.parent;
                } else {
                    if (!sibling.left.isRed) {
                        sibling.right.isRed = false;
                        sibling.isRed = true;
                        leftRotate(sibling);
                        sibling = node.parent.left;
                    }
                    sibling.isRed = node.parent.isRed;
                    node.parent.isRed = false;
                    sibling.left.isRed = false;
                    rightRotate(node.parent);
                    node = root;
                }
            }
        }
        node.isRed = false;
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


public class RBDeleteFixupExercise {

    private static void testDeleteCase(String caseName, int[] initialValues, int valueToDelete) {
        System.out.println("--- Testing: " + caseName + " ---");
        RedBlackTree tree = new RedBlackTree();
        for (int v : initialValues) tree.insert(v);
        
        System.out.print("  Before deleting " + valueToDelete + ": ");
        tree.printTree();

        tree.delete(valueToDelete);

        System.out.print("  After deleting " + valueToDelete + ":  ");
        tree.printTree();
        System.out.println("========================================\n");
    }

    public static void main(String[] args) {
        testDeleteCase("Case 1 (Sibling is Red)", new int[]{10, 5, 20}, 5);
        testDeleteCase("Case 2 (Black Sibling, Black Children)", new int[]{10, 5, 20}, 5);
        testDeleteCase("Case 3 (Black Sibling, Near Child Red)", new int[]{20, 10, 30, 25}, 10);
        testDeleteCase("Case 4 (Black Sibling, Far Child Red)", new int[]{10, 20, 30}, 10);
    }
}
