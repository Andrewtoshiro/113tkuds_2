import java.util.ArrayList;
import java.util.List;

class Interval {
    int low, high;

    public Interval(int low, int high) {
        this.low = low;
        this.high = high;
    }

    @Override
    public String toString() {
        return "[" + low + ", " + high + "]";
    }
}

class IntervalNode {
    Interval interval;
    int max;
    IntervalNode left, right, parent;
    boolean isRed;

    public IntervalNode(Interval interval) {
        this.interval = interval;
        this.max = interval.high;
        this.isRed = true;
    }
}

class IntervalTree {
    private IntervalNode root;
    private final IntervalNode NIL;

    public IntervalTree() {
        NIL = new IntervalNode(new Interval(0, 0));
        NIL.isRed = false;
        root = NIL;
    }

    private int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }
    
    private void updateMax(IntervalNode node) {
        if (node != NIL) {
            node.max = max(node.interval.high, (node.left != NIL) ? node.left.max : Integer.MIN_VALUE, (node.right != NIL) ? node.right.max : Integer.MIN_VALUE);
        }
    }

    private void leftRotate(IntervalNode x) {
        IntervalNode y = x.right;
        x.right = y.left;
        if (y.left != NIL) y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == NIL) root = y;
        else if (x == x.parent.left) x.parent.left = y;
        else x.parent.right = y;
        y.left = x;
        x.parent = y;
        updateMax(x);
        updateMax(y);
    }

    private void rightRotate(IntervalNode y) {
        IntervalNode x = y.left;
        y.left = x.right;
        if (x.right != NIL) x.right.parent = y;
        x.parent = y.parent;
        if (y.parent == NIL) root = x;
        else if (y == y.parent.right) y.parent.right = x;
        else y.parent.left = x;
        x.right = y;
        y.parent = x;
        updateMax(y);
        updateMax(x);
    }

    public void insert(Interval interval) {
        IntervalNode newNode = new IntervalNode(interval);
        newNode.left = newNode.right = newNode.parent = NIL;
        IntervalNode parent = NIL;
        IntervalNode current = root;
        while (current != NIL) {
            parent = current;
            if (newNode.interval.low < current.interval.low) current = current.left;
            else current = current.right;
        }
        newNode.parent = parent;
        if (parent == NIL) root = newNode;
        else if (newNode.interval.low < parent.interval.low) parent.left = newNode;
        else parent.right = newNode;
        
        insertFixup(newNode);
    }

    private void insertFixup(IntervalNode node) {
        IntervalNode current = node;
        while (current.parent.isRed) {
            if (current.parent == current.parent.parent.left) {
                IntervalNode uncle = current.parent.parent.right;
                if (uncle.isRed) {
                    current.parent.isRed = false; uncle.isRed = false;
                    current.parent.parent.isRed = true; current = current.parent.parent;
                } else {
                    if (current == current.parent.right) {
                        current = current.parent; leftRotate(current);
                    }
                    current.parent.isRed = false; current.parent.parent.isRed = true;
                    rightRotate(current.parent.parent);
                }
            } else {
                IntervalNode uncle = current.parent.parent.left;
                if (uncle.isRed) {
                    current.parent.isRed = false; uncle.isRed = false;
                    current.parent.parent.isRed = true; current = current.parent.parent;
                } else {
                    if (current == current.parent.left) {
                        current = current.parent; rightRotate(current);
                    }
                    current.parent.isRed = false; current.parent.parent.isRed = true;
                    leftRotate(current.parent.parent);
                }
            }
        }
        root.isRed = false;
        
        IntervalNode fixMaxNode = node;
        while (fixMaxNode != NIL) {
            updateMax(fixMaxNode);
            fixMaxNode = fixMaxNode.parent;
        }
    }

    public List<Interval> findOverlapping(Interval interval) {
        List<Interval> result = new ArrayList<>();
        findOverlappingRecursive(root, interval, result);
        return result;
    }

    private void findOverlappingRecursive(IntervalNode node, Interval interval, List<Interval> result) {
        if (node == NIL) return;

        boolean isOverlapping = (node.interval.low <= interval.high && node.interval.high >= interval.low);
        if (isOverlapping) {
            result.add(node.interval);
        }

        if (node.left != NIL && node.left.max >= interval.low) {
            findOverlappingRecursive(node.left, interval, result);
        }
        
        if (node.right != NIL && node.interval.low <= interval.high) {
             findOverlappingRecursive(node.right, interval, result);
        }
    }
}

public class IntervalTreeExercise {
    public static void main(String[] args) {
        IntervalTree tree = new IntervalTree();
        Interval[] intervals = {
            new Interval(15, 20), new Interval(10, 30), new Interval(17, 19),
            new Interval(5, 20), new Interval(12, 15), new Interval(30, 40)
        };

        for (Interval i : intervals) {
            tree.insert(i);
        }

        Interval queryInterval = new Interval(14, 16);
        List<Interval> overlapping = tree.findOverlapping(queryInterval);
        System.out.println("Intervals overlapping with " + queryInterval + ": " + overlapping);
        
        Interval queryInterval2 = new Interval(21, 23);
        List<Interval> overlapping2 = tree.findOverlapping(queryInterval2);
        System.out.println("Intervals overlapping with " + queryInterval2 + ": " + overlapping2);
    }
}
