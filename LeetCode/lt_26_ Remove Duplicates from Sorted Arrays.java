class Solution {
  public int removeDuplicates(int[] nums) {
    // This pointer 'i' keeps track of the position for the next unique element.
    int i = 0;

    // We iterate through each number in the input array.
    for (final int num : nums)
      // The condition checks if it's the very first element (i < 1)
      // or if the current number is greater than the last recorded unique number.
      if (i < 1 || num > nums[i - 1])
        // If it's a unique number, we place it at the 'i'-th position and increment 'i'.
        nums[i++] = num;

    // 'i' now holds the count of unique elements, which is the new length of the modified array.
    return i;
  }
}

// Comment 1: This algorithm efficiently removes duplicates from a sorted array by using a two-pointer approach, one for iterating ('num' in the for-each loop) and one for placing unique elements ('i').
// Comment 2: The operation is performed in-place, meaning it modifies the original array directly without using extra memory for a new array, achieving O(1) space complexity.
// Comment 3: The function returns the integer 'i', which represents the number of unique elements found and is the new logical length of the array.
