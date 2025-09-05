import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Solves the "Combination Sum" problem.
 * This simulates finding all possible combinations of Personal Protective Equipment (PPE)
 * items that add up to a specific procurement target. The same item can be chosen multiple times.
 */
public class LC39_CombinationSum_PPE {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of item types and the target value
        int n = scanner.nextInt();
        int target = scanner.nextInt();

        // Read the prices/values of the items
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = scanner.nextInt();
        }

        List<List<Integer>> result = combinationSum(candidates, target);

        // Print the resulting combinations
        for (List<Integer> combination : result) {
            for (int i = 0; i < combination.size(); i++) {
                System.out.print(combination.get(i) + (i == combination.size() - 1 ? "" : " "));
            }
            System.out.println();
        }

        scanner.close();
    }

    /**
     * Finds all unique combinations in candidates where the candidate numbers sum to target.
     * @param candidates The array of available item prices.
     * @param target The target sum.
     * @return A list of all valid combinations.
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // Sorting allows us to prune the search space later.
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    /**
     * The recursive backtracking helper function.
     * @param resultList The list to store all valid combinations.
     * @param tempList The current combination being built.
     * @param candidates The array of available numbers.
     * @param remaining The remaining sum needed to reach the target.
     * @param start The starting index in the candidates array for the current exploration.
     */
    private static void backtrack(List<List<Integer>> resultList, List<Integer> tempList, int[] candidates, int remaining, int start) {
        // Base case 1: A valid combination is found.
        if (remaining == 0) {
            resultList.add(new ArrayList<>(tempList));
            return;
        }

        // Explore candidates starting from the 'start' index.
        for (int i = start; i < candidates.length; i++) {
            // Pruning: If the current candidate is greater than the remaining target,
            // no further candidates in the sorted array can be part of a solution.
            if (candidates[i] > remaining) {
                break;
            }
            
            // 1. Choose: Add the candidate to the current combination.
            tempList.add(candidates[i]);
            // 2. Explore: Recurse with the updated remaining target.
            // We pass 'i' as the new start index, allowing the same number to be reused.
            backtrack(resultList, tempList, candidates, remaining - candidates[i], i);
            // 3. Unchoose (Backtrack): Remove the candidate to explore other paths.
            tempList.remove(tempList.size() - 1);
        }
    }
}
// This is a classic example of a backtracking algorithm to solve a combination problem.
// The key is the "choose, explore, unchoose" pattern within the recursion.
// Sorting the input array enables an important pruning optimization to
