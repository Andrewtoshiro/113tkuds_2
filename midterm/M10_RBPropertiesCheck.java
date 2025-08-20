import java.util.Scanner;

class RBNode {
    int value;
    char color;

    RBNode(int value, char color) {
        this.value = value;
        this.color = color;
    }
}

public class M10_RBPropertiesCheck {
    
    private static RBNode[] tree;
    private static int n;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        tree = new RBNode[n];

        for (int i = 0; i < n; i++) {
            int val = sc.nextInt();
            String colStr = sc.next();
            char col = colStr.charAt(0);
            if (val != -1) {
                tree[i] = new RBNode(val, col);
            }
        }
        sc.close();

        if (!isRootBlack()) {
            System.out.println("RootNotBlack");
            return;
        }
        
        int redRedViolationIndex = findRedRedViolation();
        if (redRedViolationIndex != -1) {
            System.out.println("RedRedViolation at index " + redRedViolationIndex);
            return;
        }

        if (!hasConsistentBlackHeight()) {
            System.out.println("BlackHeightMismatch");
            return;
        }

        System.out.println("RB Valid");
    }

    private static boolean isRootBlack() {
        return n == 0 || tree[0] == null || tree[0].color == 'B';
    }

    private static int findRedRedViolation() {
        for (int i = 0; i < n; i++) {
            if (tree[i] != null && tree[i].color == 'R') {
                int left = 2 * i + 1;
                int right = 2 * i + 2;

                if (left < n && tree[left] != null && tree[left].color == 'R') {
                    return left;
                }
                if (right < n && tree[right] != null && tree[right].color == 'R') {
                    return right;
                }
            }
        }
        return -1;
    }

    private static boolean hasConsistentBlackHeight() {
        return checkBlackHeight(0) != -1;
    }

    private static int checkBlackHeight(int index) {
        if (index >= n || tree[index] == null) {
            return 1;
        }

        int leftBlackHeight = checkBlackHeight(2 * index + 1);
        int rightBlackHeight = checkBlackHeight(2 * index + 2);

        if (leftBlackHeight == -1 || rightBlackHeight == -1) {
            return -1;
        }

        if (leftBlackHeight != rightBlackHeight) {
            return -1;
        }

        int currentBlackHeight = leftBlackHeight;
        if (tree[index].color == 'B') {
            currentBlackHeight++;
        }
        
        return currentBlackHeight;
    }
}
