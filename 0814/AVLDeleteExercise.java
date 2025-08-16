public class AVLDeleteExercise {

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();

        int[] initialValues = {50, 30, 70, 20, 40, 60, 80};
        for (int value : initialValues) {
            tree.insert(value);
        }
        System.out.print("Initial tree state: ");
        tree.printTree();
        System.out.println("========================================\n");

        System.out.println("Before deleting 20:");
        tree.printTree();
        tree.delete(20);
        System.out.println("After deleting 20:");
        tree.printTree();
        System.out.println("========================================\n");

        System.out.println("Before deleting 30:");
        tree.printTree();
        tree.delete(30);
        System.out.println("After deleting 30:");
        tree.printTree();
        System.out.println("========================================\n");

        System.out.println("Before deleting 50:");
        tree.printTree();
        tree.delete(50);
        System.out.println("After deleting 50 (replaced by successor 60):");
        tree.printTree();
        System.out.println("========================================\n");
    }
}
