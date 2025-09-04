class Solution {
  public int removeElement(int[] nums, int val) {
    // This index 'i' tracks the position where the next valid element should be placed.
    // It acts as a "slow pointer."
    int i = 0;

    // We iterate through the array with an enhanced for-loop, where 'num' is the "fast pointer."
    for (final int num : nums)
      // If the current element is not the one to be removed...
      if (num != val)
        // ...we place it at the 'i'th position and increment 'i'.
        nums[i++] = num;

    // The final value of 'i' is the new length of the array without the removed elements.
    return i;
  }
}

/*
1. This function efficiently removes all occurrences of 'val' from the array 'nums' in-place.
2. The variable 'i' acts as a write pointer, only advancing when a non-'val' element is found and copied.
3. The method returns the integer 'i', which represents the new logical length of the modified array.
*/
