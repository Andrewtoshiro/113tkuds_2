import java.util.Scanner;

public class M04_TieredTaxSimple {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        double totalTax = 0;

        for (int i = 0; i < n; i++) {
            int income = sc.nextInt();
            double currentTax = calculateTax(income);
            totalTax += currentTax;
            System.out.println("Tax: " + Math.round(currentTax));
        }
        sc.close();

        if (n > 0) {
            double averageTax = totalTax / n;
            System.out.println("Average: " + Math.round(averageTax));
        } else {
            System.out.println("Average: 0");
        }
    }

    public static double calculateTax(int income) {
        double tax = 0.0;
        int originalIncome = income;

        if (originalIncome > 1000000) {
            tax += (originalIncome - 1000000) * 0.30;
        }

        if (originalIncome > 500000) {
            int taxableInThisBracket = Math.min(originalIncome, 1000000) - 500000;
            tax += taxableInThisBracket * 0.20;
        }

        if (originalIncome > 120000) {
            int taxableInThisBracket = Math.min(originalIncome, 500000) - 120000;
            tax += taxableInThisBracket * 0.12;
        }

        if (originalIncome > 0) {
            int taxableInThisBracket = Math.min(originalIncome, 120000);
            tax += taxableInThisBracket * 0.05;
        }

        return tax;
    }

    /*
     * Time Complexity: O(n)
     * 說明:
     * 1. 程式的主體是一個迴圈，該迴圈根據輸入的數量 n 執行 n 次。
     * 2. 在迴圈內部，calculateTax 函式的執行時間是固定的，因為它僅包含幾個條件判斷與數學運算，其複雜度為 O(1)。
     * 3. 因此，總時間複雜度由迴圈的執行次數決定，為 n * O(1)，即 O(n)。
     */
}
