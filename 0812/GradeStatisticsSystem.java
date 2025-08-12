import java.util.Arrays;

public class GradeStatisticsSystem {

    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 96, 87, 73, 89, 94, 82, 90};

        if (scores == null || scores.length == 0) {
            System.out.println("Error: The scores array is empty or null. Cannot generate a report.");
            return;
        }

        int sum = 0;
        int highestScore = scores[0];
        int lowestScore = scores[0];

        for (int score : scores) {
            sum += score;
            if (score > highestScore) {
                highestScore = score;
            }
            if (score < lowestScore) {
                lowestScore = score;
            }
        }

        double averageScore = (double) sum / scores.length;

        int[] gradeCounts = new int[5];
        for (int score : scores) {
            if (score >= 90) {
                gradeCounts[0]++;
            } else if (score >= 80) {
                gradeCounts[1]++;
            } else if (score >= 70) {
                gradeCounts[2]++;
            } else if (score >= 60) {
                gradeCounts[3]++;
            } else {
                gradeCounts[4]++;
            }
        }

        int aboveAverageCount = 0;
        for (int score : scores) {
            if (score > averageScore) {
                aboveAverageCount++;
            }
        }

        printReport(scores, averageScore, highestScore, lowestScore, gradeCounts, aboveAverageCount);
    }

    public static void printReport(int[] scores, double average, int highest, int lowest, int[] gradeCounts, int aboveAverageCount) {
        System.out.println("====== Grade Statistics Report ======");
        System.out.println("Total Number of Students: " + scores.length);
        System.out.println("-------------------------------------");

        System.out.println("📊 Overall Performance:");
        System.out.printf("   - Average Score: %.2f\n", average);
        System.out.println("   - Highest Score: " + highest + " 🏆");
        System.out.println("   - Lowest Score:  " + lowest);
        System.out.println("   - Students Above Average: " + aboveAverageCount);
        
        System.out.println("\n📚 Grade Distribution:");
        System.out.println("   - Grade A (90-100): " + gradeCounts[0] + " students");
        System.out.println("   - Grade B (80-89):  " + gradeCounts[1] + " students");
        System.out.println("   - Grade C (70-79):  " + gradeCounts[2] + " students");
        System.out.println("   - Grade D (60-69):  " + gradeCounts[3] + " students");
        System.out.println("   - Grade F (<60):    " + gradeCounts[4] + " students");

        System.out.println("\n📋 Full Score List:");
        System.out.println("   " + Arrays.toString(scores));
        System.out.println("=====================================");
    }
}
