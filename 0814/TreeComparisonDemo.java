import java.util.Random;

public class TreeComparisonDemo {

    public static void main(String[] args) {
        System.out.println("=== AVL 樹 vs 紅黑樹性能比較 ===");

        AVLTree avlTree = new AVLTree();
        RedBlackTree rbTree = new RedBlackTree();
        Random random = new Random(42);
        int n = 50000;
        int[] testData = new int[n];
        for (int i = 0; i < n; i++) {
            testData[i] = random.nextInt(100000);
        }

        long startTime = System.nanoTime();
        for (int value : testData) {
            avlTree.insert(value);
        }
        long avlInsertTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int value : testData) {
            rbTree.insert(value);
        }
        long rbInsertTime = System.nanoTime() - startTime;

        int searchCount = 10000;
        int[] searchData = new int[searchCount];
        for (int i = 0; i < searchCount; i++) {
            searchData[i] = random.nextInt(100000);
        }

        startTime = System.nanoTime();
        for (int value : searchData) {
            avlTree.search(value);
        }
        long avlSearchTime = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int value : searchData) {
            rbTree.search(value);
        }
        long rbSearchTime = System.nanoTime() - startTime;

        System.out.println("插入 " + n + " 個元素:");
        System.out.println("AVL 樹耗時: " + avlInsertTime / 1_000_000 + " ms");
        System.out.println("紅黑樹耗時: " + rbInsertTime / 1_000_000 + " ms");
        System.out.println("插入性能比 (AVL/RB): " + String.format("%.2f", (double) avlInsertTime / rbInsertTime));

        System.out.println("\n搜尋 " + searchCount + " 次:");
        System.out.println("AVL 樹耗時: " + avlSearchTime / 1_000_000 + " ms");
        System.out.println("紅黑樹耗時: " + rbSearchTime / 1_000_000 + " ms");
        System.out.println("搜尋性能比 (AVL/RB): " + String.format("%.2f", (double) avlSearchTime / rbSearchTime));
    }
}
