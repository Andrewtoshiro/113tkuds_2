public class AVLBasicExercise {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        
        System.out.print("Current tree structure: ");
        tree.printTree();
        System.out.println();

        System.out.println("Searching for 20: " + tree.search(20));
        System.out.println("Searching for 99: " + tree.search(99));
        System.out.println();

        System.out.println("Is the tree a valid AVL tree? " + tree.isValidAVL());
    }
}
