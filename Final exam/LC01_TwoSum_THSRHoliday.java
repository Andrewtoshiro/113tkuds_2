import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Solves the Two Sum problem: finds two numbers in an array that add up to a specific target.
 * This is analogous to finding two train services that can accommodate a target number of passengers.
 */
public class LC01_TwoSum_THSRHoliday {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of elements and the target sum
        int n = scanner.nextInt();
        int target = scanner.nextInt();

        // Read the array of seat counts
        int[] seats = new int[n];
        for (int i = 0; i < n; i++) {
            seats[i] = scanner.nextInt();
        }
        
        // A map to store the number needed and the index of the number that created this need.
        // Key: The number needed to reach the target.
        // Value: The index of the number we've already seen.
        Map<Integer, Integer> neededNumbers = new HashMap<>();

        // Iterate through the array to find the pair
        for (int i = 0; i < seats.length; i++) {
            int currentSeatCount = seats[i];
            
            // Check if the current number is the one a previous number was waiting for
            if (neededNumbers.containsKey(currentSeatCount)) {
                // If yes, we found a pair. Print the index of the complement and the current index.
                System.out.println(neededNumbers.get(currentSeatCount) + " " + i);
                scanner.close();
                return;
            }
            
            // If no pair is found yet, calculate the complement needed for the current number
            // and store it in the map with the current index.
            int complement = target - currentSeatCount;
            neededNumbers.put(complement, i);
        }

        // If the loop completes without finding a solution, print -1 -1.
        System.out.println("-1 -1");
        scanner.close();
    }
}
// This implementation uses a HashMap for efficient O(1) lookups.
// The overall time complexity is O(n) as we iterate through the array once.
// This approach effectively balances time and space for a quick solution.
