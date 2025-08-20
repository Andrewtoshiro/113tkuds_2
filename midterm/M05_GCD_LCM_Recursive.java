import java.util.Scanner;

public class M05_GCD_LCM_Recursive {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        sc.close();

        long gcdResult = gcd(a, b);
        long lcmResult = (a / gcdResult) * b;

        System.out.println("GCD: " + gcdResult);
        System.out.println("LCM: " + lcmResult);
    }

    public static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /*
     * Time Complexity: O(log(min(a, b)))
     * 說明:
     * 1. 程式主要依賴遞迴的 gcd 函式，此函式實作了歐幾里得演算法。
     * 2. 在每次遞迴呼叫中，參數 (a, b) 會變為 (b, a % b)，其數值呈指數級下降。
     * 3. 由於輸入數值快速縮小，遞迴的深度與較小輸入值的對數成正比，因此時間複雜度為 O(log(min(a,b)))。
     */
}
