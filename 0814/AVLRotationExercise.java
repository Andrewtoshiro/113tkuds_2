public class AVLRotationExercise {

    public static void main(String[] args) {
        testRotation("LL Case (Right Rotation)", new int[]{30, 20, 10});
        testRotation("RR Case (Left Rotation)", new int[]{10, 20, 30});
        testRotation("LR Case (Left-Right Rotation)", new int[]{30, 10, 20});
        testRotation("RL Case (Right-Left Rotation)", new int[]{10, 30, 20});
    }

    private static void testRotation(String caseName, int[] values) {
        System.out.println("=== Testing: " + caseName + " ===");
        AVLTree tree = new AVLTree();
        for (int value : values) {
            System.out.println("Inserting: " + value);
            tree.insert(value);
            System.out.print("Tree state: ");
            tree.printTree();
        }
        System.out.println("====================================\n");
    }
}
