import java.util.Arrays;

public class GradeStatisticsSystem {

    public static void main(String[] args) {
        // Test Data
        int[] grades = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        // --- 1. Calculate Average, Highest, and Lowest Scores ---
        if (grades.length == 0) {
            System.out.println("No grades to process.");
            return;
        }

        int highestScore = grades[0];
        int lowestScore = grades[0];
        double totalScore = 0;

        for (int grade : grades) {
            if (grade > highestScore) {
                highestScore = grade;
            }
            if (grade < lowestScore) {
                lowestScore = grade;
            }
            totalScore += grade;
        }

        double averageScore = totalScore / grades.length;

        // --- 2. Count Students in Each Grade Category ---
        int[] gradeCounts = new int[5]; // Index 0:A, 1:B, 2:C, 3:D, 4:F
        for (int grade : grades) {
            if (grade >= 90) {
                gradeCounts[0]++; // A
            } else if (grade >= 80) {
                gradeCounts[1]++; // B
            } else if (grade >= 70) {
                gradeCounts[2]++; // C
            } else if (grade >= 60) {
                gradeCounts[3]++; // D
            } else {
                gradeCounts[4]++; // F
            }
        }

        // --- 3. Find Number of Students Above Average ---
        int aboveAverageCount = 0;
        for (int grade : grades) {
            if (grade > averageScore) {
                aboveAverageCount++;
            }
        }

        // --- 4. Print Complete Grade Report ---
        System.out.println("====== Grade Statistics Report ======");
        System.out.println("Original Grades: " + Arrays.toString(grades));
        System.out.println("-------------------------------------");
        System.out.println("Total Students: " + grades.length);
        System.out.printf("Average Score: %.2f\n", averageScore);
        System.out.println("Highest Score: " + highestScore);
        System.out.println("Lowest Score: " + lowestScore);
        System.out.println("-------------------------------------");
        System.out.println("Grade Distribution:");
        System.out.println("A (90-100): " + gradeCounts[0] + " students");
        System.out.println("B (80-89):  " + gradeCounts[1] + " students");
        System.out.println("C (70-79):  " + gradeCounts[2] + " students");
        System.out.println("D (60-69):  " + gradeCounts[3] + " students");
        System.out.println("F (<60):    " + gradeCounts[4] + " students");
        System.out.println("-------------------------------------");
        System.out.println("Number of students above average: " + aboveAverageCount);
        System.out.println("=====================================");
    }
}
