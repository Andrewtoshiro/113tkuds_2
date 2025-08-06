import java.util.ArrayList;
import java.util.List;

public class AdvancedStringRecursion {

    public static List<String> generatePermutations(String str) {
        List<String> results = new ArrayList<>();
        if (str == null || str.isEmpty()) {
            return results;
        }
        permutationHelper("", str, results);
        return results;
    }

    private static void permutationHelper(String prefix, String suffix, List<String> results) {
        if (suffix.isEmpty()) {
            results.add(prefix);
            return;
        }

        for (int i = 0; i < suffix.length(); i++) {
            char currentChar = suffix.charAt(i);
            String newPrefix = prefix + currentChar;
            String newSuffix = suffix.substring(0, i) + suffix.substring(i + 1);
            permutationHelper(newPrefix, newSuffix, results);
        }
    }

    public static boolean contains(String text, String pattern) {
        if (pattern.isEmpty()) {
            return true;
        }
        if (text.isEmpty()) {
            return false;
        }

        if (text.startsWith(pattern)) {
            return true;
        }

        return contains(text.substring(1), pattern);
    }

    public static String removeDuplicates(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        char firstChar = str.charAt(0);
        String restOfString = str.substring(1);

        String restWithoutDuplicates = restOfString.replace(String.valueOf(firstChar), "");
        
        return firstChar + removeDuplicates(restWithoutDuplicates);
    }

    public static List<String> generateSubstrings(String str) {
        List<String> results = new ArrayList<>();
        if (str == null) {
            return results;
        }
        substringHelper(str, results);
        return results;
    }

    private static void substringHelper(String str, List<String> results) {
        if (str.isEmpty()) {
            return;
        }

        for (int i = 1; i <= str.length(); i++) {
            results.add(str.substring(0, i));
        }

        substringHelper(str.substring(1), results);
    }


    public static void main(String[] args) {
        System.out.println("====== Advanced String Recursion Demo ======");

        String permuteStr = "ABC";
        System.out.println("\n1. Permutations of \"" + permuteStr + "\":");
        List<String> permutations = generatePermutations(permuteStr);
        System.out.println(permutations);

        String text = "hello world";
        String pattern1 = "world";
        String pattern2 = "java";
        System.out.println("\n2. String Matching");
        System.out.printf("Does \"%s\" contain \"%s\"? %s\n", text, pattern1, contains(text, pattern1));
        System.out.printf("Does \"%s\" contain \"%s\"? %s\n", text, pattern2, contains(text, pattern2));

        String duplicateStr = "programming";
        System.out.println("\n3. Remove Duplicates");
        System.out.println("Original string: " + duplicateStr);
        String uniqueStr = removeDuplicates(duplicateStr);
        System.out.println("String with duplicates removed: " + uniqueStr);
        
        String subStr = "abc";
        System.out.println("\n4. Substrings of \"" + subStr + "\":");
        List<String> substrings = generateSubstrings(subStr);
        System.out.println(substrings);
        
        System.out.println("\n============================================");
    }
}
