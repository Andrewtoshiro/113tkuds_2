import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class MultiWayTreeAndDecisionTree {

    // --- 1. Multi-way Tree Node ---
    static class MultiWayNode {
        String data;
        List<MultiWayNode> children;

        MultiWayNode(String data) {
            this.data = data;
            this.children = new ArrayList<>();
        }

        public void addChild(MultiWayNode child) {
            this.children.add(child);
        }

        public boolean isLeaf() {
            return this.children.isEmpty();
        }
    }

    // --- 2. DFS and BFS Traversals ---
    
    /**
     * Performs a Depth-First Search (Pre-order traversal) on the tree.
     */
    public static void dfsTraversal(MultiWayNode node) {
        if (node == null) return;
        
        System.out.print(node.data + " ");
        for (MultiWayNode child : node.children) {
            dfsTraversal(child);
        }
    }
    
    /**
     * Performs a Breadth-First Search (Level-order traversal) on the tree.
     */
    public static void bfsTraversal(MultiWayNode node) {
        if (node == null) return;
        
        Queue<MultiWayNode> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty()) {
            MultiWayNode current = queue.poll();
            System.out.print(current.data + " ");
            
            for (MultiWayNode child : current.children) {
                queue.offer(child);
            }
        }
    }

    // --- 4. Height and Degree Calculation ---

    /**
     * Calculates the height of a multi-way tree.
     * The height of a single-node tree is 1.
     */
    public static int getHeight(MultiWayNode node) {
        if (node == null) return 0;
        
        if (node.isLeaf()) return 1;
        
        int maxChildHeight = 0;
        for (MultiWayNode child : node.children) {
            maxChildHeight = Math.max(maxChildHeight, getHeight(child));
        }
        
        return 1 + maxChildHeight;
    }

    /**
     * Calculates the degree of a given node (number of children).
     */
    public static int getDegree(MultiWayNode node) {
        if (node == null) return 0;
        return node.children.size();
    }

    // --- 3. Simple Decision Tree Simulation ---
    
    /**
     * Simulates a simple number guessing game using a decision tree.
     */
    public static void playGuessingGame(MultiWayNode root, Scanner scanner) {
        MultiWayNode currentNode = root;

        while (!currentNode.isLeaf()) {
            System.out.println(currentNode.data + " (y/n)");
            String input = scanner.nextLine().toLowerCase();
            
            if (input.equals("y")) {
                currentNode = currentNode.children.get(0); // "Yes" branch
            } else if (input.equals("n")) {
                currentNode = currentNode.children.get(1); // "No" branch
            } else {
                System.out.println("Invalid input. Please enter 'y' or 'n'.");
            }
        }
        
        // When we reach a leaf, we have the answer.
        System.out.println("The answer is: " + currentNode.data);
    }
    
    // --- Main Method for Demonstration ---
    
    public static void main(String[] args) {
        // --- Part 1: General Multi-way Tree Demo (Reqs 1, 2, 4) ---
        System.out.println("====== Multi-way Tree Demo ======");
        MultiWayNode root = new MultiWayNode("A");
        MultiWayNode b = new MultiWayNode("B");
        MultiWayNode c = new MultiWayNode("C");
        MultiWayNode d = new MultiWayNode("D");
        root.addChild(b);
        root.addChild(c);
        root.addChild(d);
        
        b.addChild(new MultiWayNode("E"));
        b.addChild(new MultiWayNode("F"));
        
        d.addChild(new MultiWayNode("G"));
        d.addChild(new MultiWayNode("H"));
        d.addChild(new MultiWayNode("I"));
        
        System.out.print("DFS Traversal: ");
        dfsTraversal(root);
        System.out.println();
        
        System.out.print("BFS Traversal: ");
        bfsTraversal(root);
        System.out.println();
        
        System.out.println("\nHeight of the tree: " + getHeight(root));
        System.out.println("Degree of root node 'A': " + getDegree(root));
        System.out.println("Degree of node 'B': " + getDegree(b));
        System.out.println("Degree of leaf node 'E': " + getDegree(b.children.get(0)));

        // --- Part 2: Decision Tree Simulation (Req 3) ---
        System.out.println("\n====== Decision Tree Game Demo ======");
        // Build the decision tree for a number guessing game (1-8)
        MultiWayNode q1 = new MultiWayNode("Is the number > 4?");
        MultiWayNode q2 = new MultiWayNode("Is the number > 6?");
        MultiWayNode q3 = new MultiWayNode("Is the number > 2?");
        q1.addChild(q2); // Yes
        q1.addChild(q3); // No

        MultiWayNode q4 = new MultiWayNode("Is the number > 7?");
        MultiWayNode q5 = new MultiWayNode("Is the number > 5?");
        q2.addChild(q4); // Yes
        q2.addChild(q5); // No

        MultiWayNode q6 = new MultiWayNode("Is the number > 3?");
        MultiWayNode q7 = new MultiWayNode("Is the number > 1?");
        q3.addChild(q6); // Yes
        q3.addChild(q7); // No

        q4.addChild(new MultiWayNode("8"));
        q4.addChild(new MultiWayNode("7"));

        q5.addChild(new MultiWayNode("6"));
        q5.addChild(new MultiWayNode("5"));
        
        q6.addChild(new MultiWayNode("4"));
        q6.addChild(new MultiWayNode("3"));
        
        q7.addChild(new MultiWayNode("2"));
        q7.addChild(new MultiWayNode("1"));
        
        System.out.println("Think of a number between 1 and 8, and I will guess it.");
        Scanner scanner = new Scanner(System.in);
        playGuessingGame(q1, scanner);
        scanner.close();

        System.out.println("\n==================================");
    }
}
