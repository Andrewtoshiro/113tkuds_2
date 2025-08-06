import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursiveTreePreview {

    // --- Node Class for Requirements 1 & 2 ---
    // A generic node to represent a file/folder or a menu/menu-item.
    static class Node {
        String name;
        boolean isContainer; // True if it's a folder or a menu with children
        List<Node> children;

        // Constructor for a container (folder/menu)
        Node(String name) {
            this.name = name;
            this.isContainer = true;
            this.children = new ArrayList<>();
        }

        // Constructor for a leaf (file/menu-item)
        Node(String name, boolean isContainer) {
            this.name = name;
            this.isContainer = isContainer;
            if (!isContainer) {
                this.children = null;
            } else {
                this.children = new ArrayList<>();
            }
        }

        public void addChild(Node child) {
            if (this.isContainer) {
                this.children.add(child);
            }
        }
    }

    // --- 1. Recursively Count Files in a Folder ---
    public static int countFiles(Node node) {
        // Base case: If the node is a file (not a container), it counts as 1.
        if (!node.isContainer) {
            return 1;
        }

        // Recursive step: If it's a folder, sum the file counts of all its children.
        int fileCount = 0;
        for (Node child : node.children) {
            fileCount += countFiles(child);
        }
        return fileCount;
    }

    // --- 2. Recursively Print a Multi-level Menu ---
    public static void printMenu(Node node, String indent) {
        System.out.println(indent + "- " + node.name);
        
        // Recursive step: If the node is a container, print its children with more indentation.
        if (node.isContainer) {
            for (Node child : node.children) {
                printMenu(child, indent + "  ");
            }
        }
    }

    // --- 3. Recursively Flatten a Nested List ---
    // A nested list is represented as a List<Object>, where an Object can be
    // an Integer or another List<Object>.
    public static List<Integer> flattenList(List<Object> nestedList) {
        List<Integer> flatList = new ArrayList<>();
        for (Object item : nestedList) {
            flattenHelper(item, flatList);
        }
        return flatList;
    }

    private static void flattenHelper(Object item, List<Integer> flatList) {
        // Base case: If the item is an Integer, add it to the list.
        if (item instanceof Integer) {
            flatList.add((Integer) item);
        } 
        // Recursive step: If the item is a List, iterate through its elements and recurse.
        else if (item instanceof List) {
            for (Object innerItem : (List<Object>) item) {
                flattenHelper(innerItem, flatList);
            }
        }
    }

    // --- 4. Recursively Calculate Maximum Depth of a Nested List ---
    public static int maxDepth(Object item) {
        // Base case: A non-list item has a depth of 1.
        if (!(item instanceof List)) {
            return 1;
        }

        // Recursive step: If it's a list, its depth is 1 + the max depth of its children.
        int maxChildDepth = 0;
        List<Object> list = (List<Object>) item;
        if (list.isEmpty()) {
            return 1; // Depth of an empty list is 1.
        }
        
        for (Object innerItem : list) {
            maxChildDepth = Math.max(maxChildDepth, maxDepth(innerItem));
        }
        return 1 + maxChildDepth;
    }


    public static void main(String[] args) {
        System.out.println("====== Recursive Tree Preview Demo ======");

        // --- Demo for Requirements 1 & 2 ---
        Node root = new Node("C:");
        Node docs = new Node("Documents");
        Node pics = new Node("Pictures");
        root.addChild(docs);
        root.addChild(pics);
        
        docs.addChild(new Node("report.docx", false));
        Node projects = new Node("Projects");
        docs.addChild(projects);
        projects.addChild(new Node("project1.java", false));
        
        pics.addChild(new Node("cat.jpg", false));
        pics.addChild(new Node("dog.png", false));

        System.out.println("\n--- 1 & 2. File System / Menu Simulation ---");
        System.out.println("Menu Structure:");
        printMenu(root, "");
        System.out.println("\nTotal number of files: " + countFiles(root));

        // --- Demo for Requirements 3 & 4 ---
        // Create a nested list: [1, [2, 3], 4, [5, [6, 7]]]
        List<Object> nestedList = new ArrayList<>(Arrays.asList(
            1,
            new ArrayList<>(Arrays.asList(2, 3)),
            4,
            new ArrayList<>(Arrays.asList(5, new ArrayList<>(Arrays.asList(6, 7))))
        ));

        System.out.println("\n--- 3. Flatten a Nested List ---");
        System.out.println("Original nested list: " + nestedList.toString());
        List<Integer> flattened = flattenList(nestedList);
        System.out.println("Flattened list: " + flattened.toString());
        
        System.out.println("\n--- 4. Maximum Depth of a Nested List ---");
        System.out.println("Original nested list: " + nestedList.toString());
        // We subtract 1 because our function calculates depth of nodes, 
        // and here we want the nesting depth.
        System.out.println("Maximum depth: " + (maxDepth(nestedList) - 1));

        System.out.println("\n==========================================");
    }
}
