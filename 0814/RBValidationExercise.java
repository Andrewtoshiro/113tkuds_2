class RBNode {
    int data;
    RBNode left, right, parent;
    boolean isRed;

    public RBNode(int data) {
        this.data = data;
        this.isRed = true;
    }

    public RBNode(int data, boolean isRed) {
        this.data = data;
        this.isRed = isRed;
    }
}

class RedBlackTree {
    RBNode root;
    final RBNode NIL;

    public RedBlackTree() {
        NIL = new RBNode(0, false);
        root = NIL;
    }
    
    public void insert(int data) {
        RBNode newNode = new RBNode(data);
        newNode.left = NIL;
        newNode.right = NIL;
        newNode.parent = NIL;
        
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
    }

    public boolean isValidRBTree() {
        if (root != NIL && root.isRed) {
            return false;
        }
        return checkBlackHeight(root) != -1;
    }

    private int checkBlackHeight(RBNode node) {
        if (node == NIL) {
            return 1;
        }

        if (node.isRed) {
            if ((node.left != NIL && node.left.isRed) || (node.right != NIL && node.right.isRed)) {
                return -1;
            }
        }

        int leftBlackHeight = checkBlackHeight(node.left);
        if (leftBlackHeight == -1) return -1;

        int rightBlackHeight = checkBlackHeight(node.right);
        if (rightBlackHeight == -1) return -1;
        
        if (leftBlackHeight != rightBlackHeight) {
            return -1;
        }

        return leftBlackHeight + (node.isRed ? 0 : 1);
    }
}

public class RBValidationExercise {

    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();

        tree.root = new RBNode(7, false);
        tree.root.left = new RBNode(3, true);
        tree.root.right = new RBNode(11, false);
        tree.root.left.left = new RBNode(1, false);
        tree.root.left.right = new RBNode(5, false);
        tree.root.left.left.left = tree.NIL;
        tree.root.left.left.right = tree.NIL;
        tree.root.left.right.left = tree.NIL;
        tree.root.left.right.right = tree.NIL;
        tree.root.right.left = tree.NIL;
        tree.root.right.right = tree.NIL;

        System.out.println("Is the initial tree valid? " + tree.isValidRBTree());

        tree.root.isRed = true;
        System.out.println("Is the tree valid after making root red? " + tree.isValidRBTree());
        tree.root.isRed = false;
        
        tree.root.left.isRed = true;
        tree.root.left.left.isRed = true;
        System.out.println("Is the tree valid with a red-red violation? " + tree.isValidRBTree());
        tree.root.left.left.isRed = false;
        tree.root.left.isRed = true;
        
        tree.root.left.left.isRed = true; 
        System.out.println("Is the tree valid with a black-height imbalance? " + tree.isValidRBTree());
        tree.root.left.left.isRed = false;
    }
}
