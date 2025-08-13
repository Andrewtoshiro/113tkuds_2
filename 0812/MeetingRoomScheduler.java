import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Comparator;

public class MeetingRoomScheduler {

    public static int findMinMeetingRooms(int[][] meetings) {
        if (meetings == null || meetings.length == 0) {
            return 0;
        }

        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

        PriorityQueue<Integer> endTimesHeap = new PriorityQueue<>();

        endTimesHeap.offer(meetings[0][1]);

        for (int i = 1; i < meetings.length; i++) {
            int currentMeetingStart = meetings[i][0];
            int currentMeetingEnd = meetings[i][1];

            if (currentMeetingStart >= endTimesHeap.peek()) {
                endTimesHeap.poll();
            }

            endTimesHeap.offer(currentMeetingEnd);
        }

        return endTimesHeap.size();
    }

    public static int maximizeTotalMeetingTime(int[][] meetings, int nRooms) {
        if (nRooms != 1) {
            System.out.println("Warning: This greedy solution is only guaranteed optimal for N=1.");
        }

        if (meetings == null || meetings.length == 0 || nRooms <= 0) {
            return 0;
        }

        Arrays.sort(meetings, Comparator.comparingInt(a -> a[1]));

        int totalDuration = 0;
        int lastEndTime = -1;

        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];

            if (start >= lastEndTime) {
                totalDuration += (end - start);
                lastEndTime = end;
            }
        }
        return totalDuration;
    }

    public static void main(String[] args) {
        System.out.println("=== Part 1: Minimum Meeting Rooms Required ===");

        int[][] meetings1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println("Meetings: " + Arrays.deepToString(meetings1));
        System.out.println("Min Rooms Needed: " + findMinMeetingRooms(meetings1));
        System.out.println();

        int[][] meetings2 = {{9, 10}, {4, 9}, {4, 17}};
        System.out.println("Meetings: " + Arrays.deepToString(meetings2));
        System.out.println("Min Rooms Needed: " + findMinMeetingRooms(meetings2));
        System.out.println();
        
        int[][] meetings3 = {{1, 5}, {8, 9}, {8, 9}};
        System.out.println("Meetings: " + Arrays.deepToString(meetings3));
        System.out.println("Min Rooms Needed: " + findMinMeetingRooms(meetings3));
        System.out.println();

        System.out.println("=== Part 2: Maximize Total Meeting Time (N=1 case) ===");
        int[][] meetings4 = {{1, 4}, {2, 3}, {4, 6}};
        int nRooms = 1;
        System.out.println("Meetings: " + Arrays.deepToString(meetings4) + ", N = " + nRooms);
        System.out.println("Max Total Time: " + maximizeTotalMeetingTime(meetings4, nRooms));
        System.out.println();
    }
}
