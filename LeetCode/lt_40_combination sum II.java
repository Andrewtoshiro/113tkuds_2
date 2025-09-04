import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // Sort to handle duplicates
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path)); // Found a valid combination
            return;
        }
        if (target < 0) {
            return; // Invalid path
        }

        for (int i = start; i < candidates.length; i++) {
            // Skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.add(candidates[i]); // Choose
            backtrack(candidates, target - candidates[i], i + 1, path, result); // Explore
            path.remove(path.size() - 1); // Unchoose (backtrack)
        }
    }
}
/*
1. The initial sorting of the `candidates` array is crucial. It groups all duplicate numbers together, which is essential for the logic that prevents the formation of duplicate combinations.
2. The condition `if (i > start && candidates[i] == candidates[i - 1])` is the core of the duplicate handling. It ensures that for a set of identical numbers, only the first one is picked to start a new combination path at any given level of recursion, thus avoiding permutations of the same numbers.
3. Unlike the standard "Combination Sum" problem, the recursive call here is `backtrack(..., i + 1, ...)` instead of `backtrack(..., i, ...)`. This prevents the same element from being used more than once within a single combination, adhering to the problem's constraints.
*/


