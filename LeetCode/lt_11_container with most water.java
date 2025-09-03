class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            // Calculate the current area
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            
            // Update the maximum area found so far
            maxArea = Math.max(maxArea, currentArea);

            // Move the pointer of the shorter line inward
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}

// 1. This solution uses the two-pointer technique, starting with the widest possible
//    container and iteratively narrowing it to find the maximum area in O(n) time.

// 2. In each step, the area is calculated as the distance between the pointers
//    multiplied by the height of the shorter of the two lines.

// 3. The key optimization is to always move the pointer of the shorter line inward,
//    as this is the only move that offers the potential to form a larger area.
