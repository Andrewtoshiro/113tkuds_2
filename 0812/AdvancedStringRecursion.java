import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Implements several advanced string operations using recursive algorithms.
 * This implementation is based on the requirements of Exercise 2.3 from the provided document.
 */
public class AdvancedStringRecursion {

    public static void main(String[] args) {
        System.out.println("====== Advanced String Recursion Demo ======");

        // --- 1. Generate All Permutations ---
        System.out.println("\n--- 1. Generate All Permutations of 'ABC' ---");
        String permuteStr = "ABC";
        List<String> permutations = generatePermutations(permuteStr);
        System.out.println("Permutations: " + permutations);
        System.out.println("Total permutations found: " + permutations.size());

        // --- 2. Recursive String Matching ---
        System.out.println("\n--- 2. Recursive String Matching ---");
        String text = "hello world";
        String pattern1 = "world";
        String pattern2 = "java";
        System.out.printf("Does '%s' contain '%s'? %s\n", text, pattern1, stringMatch(text, pattern1));
        System.out.printf("Does '%s' contain '%s'? %s\n", text, pattern2, stringMatch(text, pattern2));

        // --- 3. Recursive Removal of Duplicate Characters ---
        System.out.println("\n--- 3. Recursive Removal of Duplicate Characters ---");
        String duplicateStr = "programming";
        System.out.println("Original string: " + duplicateStr);
        String uniqueStr = removeDuplicates(duplicateStr);
        System.out.println("String with duplicates removed: " + uniqueStr);

        // --- 4. Recursive Generation of All Subsequences (Sub-string combinations) ---
        System.out.println("\n--- 4. Generate All Subsequences of 'AB' ---");
        String subseqStr = "AB";
        List<String> subsequences = generateSubsequences(subseqStr);
        System.out.println("Subsequences: " + subsequences);
    }

    // =================================================================
    // 1. Generate All Permutations
    // =================================================================
    public static List<String> generatePermutations(String str) {
        List<String> results = new ArrayList<>();
        permutationsRecursive("", str, results);
        return results;
    }

    private static void permutationsRecursive(String prefix, String suffix, List<String> results) {
        if (suffix.isEmpty()) {
            results.add(prefix);
            return;
        }
        for (int i = 0; i < suffix.length(); i++) {
            char currentChar = suffix.charAt(i);
            String newPrefix = prefix + currentChar;
            String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1);
            permutationsRecursive(newPrefix, newSuffix, results);
        }
    }

    // =================================================================
    // 2. Recursive String Matching
    // =================================================================
    public static boolean stringMatch(String text, String pattern) {
        // Base case 1: If pattern is empty, it's always found.
        if (pattern.isEmpty()) {
            return true;
        }
        // Base case 2: If text is empty but pattern isn't, it can't be found.
        if (text.isEmpty()) {
            return false;
        }
        // Recursive step: Check if text starts with the pattern. If not,
        // check the rest of the text by removing the first character.
        if (text.startsWith(pattern)) {
            return true;
        }
        return stringMatch(text.substring(1), pattern);
    }

    // =================================================================
    // 3. Remove Duplicate Characters
    // =================================================================
    public static String removeDuplicates(String str) {
        // A Set is used to keep track of characters we've already seen.
        return removeDuplicatesRecursive(str, new HashSet<>());
    }
    
    private static String removeDuplicatesRecursive(String str, Set<Character> seen) {
        // Base case: If the string is empty, we're done.
        if (str.isEmpty()) {
            return "";
        }
        
        char firstChar = str.charAt(0);
        String restOfString = str.substring(1);

        // Recursive step: If we've seen this character before, skip it.
        // Otherwise, include it and add it to the 'seen' set.
        if (seen.contains(firstChar)) {
            return removeDuplicatesRecursive(restOfString, seen);
        } else {
            seen.add(firstChar);
            return firstChar + removeDuplicatesRecursive(restOfString, seen);
        }
    }

    // =================================================================
    // 4. Generate All Subsequences (Power Set)
    // =================================================================
    public static List<String> generateSubsequences(String str) {
        List<String> results = new ArrayList<>();
        subsequencesRecursive(str, "", results);
        // We add the empty string manually as it's part of the power set.
        results.add(""); 
        return results;
    }

    private static void subsequencesRecursive(String unprocessed, String processed, List<String> results) {
        // Base case: when there's nothing left to process, add the result.
        if (unprocessed.isEmpty()) {
            if (!processed.isEmpty()) {
                results.add(processed);
            }
            return;
        }

        char firstChar = unprocessed.charAt(0);
        String restOfString = unprocessed.substring(1);

        // Recursive Step: Two choices for each character.
        // 1. Include the character in the subsequence.
        subsequencesRecursive(restOfString, processed + firstChar, results);
        // 2. Do NOT include the character in the subsequence.
        subsequencesRecursive(restOfString, processed, results);
    }
}
