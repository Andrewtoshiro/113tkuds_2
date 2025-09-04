import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentCombination = new ArrayList<>();
        findCombinations(0, target, candidates, currentCombination, result);
        return result;
    }

    private void findCombinations(int index, int target, int[] candidates,
                                  List<Integer> currentCombination, List<List<Integer>> result) {
        // Base Case 1: Target reached
        if (target == 0) {
            result.add(new ArrayList<>(currentCombination)); // Add a copy
            return;
        }

        // Base Case 2: Invalid path (target exceeded or all candidates explored)
        if (target < 0 || index == candidates.length) {
            return;
        }

        // Option 1: Include the current candidate (allow multiple uses)
        // This recursive call stays at the same index to allow the current number to be reused.
        currentCombination.add(candidates[index]);
        findCombinations(index, target - candidates[index], candidates, currentCombination, result);
        currentCombination.remove(currentCombination.size() - 1); // Backtrack

        // Option 2: Exclude the current candidate (move to the next)
        // This recursive call moves to the next candidate (index + 1).
        findCombinations(index + 1, target, candidates, currentCombination, result);
    }
}
/*
1. This solution employs a recursive backtracking algorithm, which systematically explores all potential combinations by making choices and undoing them (backtracking) if they lead to an invalid or already explored path.
2. A key feature of this problem is that candidates can be reused. This is handled by the first recursive call, `findCombinations(index, ...)` which does not increment the index, allowing the same number to be chosen again for the combination.
3. The line `result.add(new ArrayList<>(currentCombination));` is crucial. It creates a deep copy of the list when a valid combination is found. Without this, all entries in the final `result` list would point to the same `currentCombination` object, which would be empty by the end of the process due to backtracking.
*/


