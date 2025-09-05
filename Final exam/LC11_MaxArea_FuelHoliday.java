import java.util.Scanner;

/**
 * Solves the "Container with Most Water" problem.
 * This is framed as a fuel promotion scenario where the goal is to maximize the
 * "output bandwidth" formed by two temporary oil tanks and the distance between them.
 */
public class LC11_MaxArea_FuelHoliday {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of tank positions
        int n = scanner.nextInt();
        
        // Read the heights (output capabilities) of each tank
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = scanner.nextInt();
        }
        
        int maxArea = 0;
        int left = 0; // Left pointer starts at the beginning
        int right = n - 1; // Right pointer starts at the end

        // Loop until the pointers meet
        while (left < right) {
            // Calculate the width between the two pointers
            int width = right - left;
            
            // The height of the container is limited by the shorter of the two lines
            int height = Math.min(heights[left], heights[right]);
            
            // Calculate the current area
            int currentArea = width * height;
            
            // Update the maximum area found so far
            maxArea = Math.max(maxArea, currentArea);
            
            // The key insight: move the pointer of the shorter line inward.
            // This is because moving the taller line's pointer will always result in
            // a smaller or equal area, as the width decreases and the height is
            // still constrained by the shorter line.
            if (heights[left] < heights[right]) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(maxArea);
        scanner.close();
    }
}
// This solution uses the two-pointer (or two-finger) greedy approach.
// By starting with maximum width and moving the shorter side, it guarantees an optimal O(n) time complexity.
// The space complexity is O(1) as no extra storage is needed.
