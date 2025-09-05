import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solves the LeetCode problem 40: Combination Sum II.
 * The goal is to find all unique combinations in a given array of candidates
 * that sum up to a specific target. Each number in the candidates array
 * may only be used once in each combination.
 */
public class LC40_CombinationSum2_Procurement {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sorting is crucial to handle duplicates and avoid duplicate combinations.
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    /**
     * A recursive helper function to find combinations via backtracking.
     *
     * @param result      The list to store all valid combinations.
     * @param tempList    The current combination being built.
     * @param candidates  The array of candidate numbers.
     * @param remain      The remaining target sum to achieve.
     * @param start       The starting index in the candidates array to consider for the current step.
     */
    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int[] candidates, int remain, int start) {
        // Base case: If the remaining sum is 0, we've found a valid combination.
        if (remain == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        
        // If the remaining sum is negative, this path is invalid.
        if (remain < 0) {
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // This is the key step to avoid duplicate combinations.
            // If the current element is the same as the previous one, and we are not
            // at the start of the consideration for this level, skip it.
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // Include the number in the current combination.
            tempList.add(candidates[i]);
            
            // Recurse with the next starting index and updated remaining sum.
            // We use i + 1 because each number can be used only once.
            backtrack(result, tempList, candidates, remain - candidates[i], i + 1);
            
            // Backtrack: Remove the number to explore other possibilities.
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC40_CombinationSum2_Procurement solver = new LC40_CombinationSum2_Procurement();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        List<List<Integer>> combinations = solver.combinationSum2(candidates, target);
        
        System.out.println("Input Candidates: " + Arrays.toString(candidates));
        System.out.println("Target: " + target);
        System.out.println("Unique Combinations:");
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }
        // Expected output:
        // [1, 1, 6]
        // [1, 2, 5]
        // [1, 7]
        // [2, 6]
    }
}

// Comment 1: The time complexity is dominated by the backtracking process, which is roughly O(2^N), where N is the number of candidates. Sorting the array initially adds an O(N log N) step.
// Comment 2: Sorting the 'candidates' array is the most critical step for this solution. It allows us to easily skip duplicate numbers in the loop with the condition `if (i > start && candidates[i] == candidates[i-1])`, thus preventing duplicate combinations in the final result.
// Comment 3: The recursive call uses `i + 1` as the new starting index. This ensures that each number from the input array is used at most once per combination, fulfilling a key requirement of the problem.
