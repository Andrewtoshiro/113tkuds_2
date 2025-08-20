import java.util.Scanner;

public class M02_YouBikeNextArrival {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arrivalMinutes = new int[n];

        for (int i = 0; i < n; i++) {
            arrivalMinutes[i] = timeToMinutes(sc.next());
        }

        int queryMinutes = timeToMinutes(sc.next());
        sc.close();

        int resultIndex = findFirstGreater(arrivalMinutes, queryMinutes);

        if (resultIndex != -1) {
            System.out.println(minutesToTime(arrivalMinutes[resultIndex]));
        } else {
            System.out.println("No bike");
        }
    }

    public static int findFirstGreater(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        int resultIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > value) {
                resultIndex = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return resultIndex;
    }

    public static int timeToMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    public static String minutesToTime(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
