public class RBNode {
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

    public RBNode getSibling() {
        if (parent == null) return null;
        if (this == parent.left) return parent.right;
        return parent.left;
    }

    public RBNode getUncle() {
        if (parent == null || parent.parent == null) return null;
        return parent.getSibling();
    }

    public RBNode getGrandparent() {
        if (parent == null) return null;
        return parent.parent;
    }
}
