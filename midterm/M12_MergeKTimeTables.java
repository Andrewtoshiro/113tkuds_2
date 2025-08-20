import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

record TimeEntry(int time, int listIndex, int elementIndex) implements Comparable<TimeEntry> {
    @Override
    public int compareTo(TimeEntry other) {
        return Integer.compare(this.time, other.time);
    }
}

public class M12_MergeKTimeTables {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();

        List<int[]> timetables = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            int len = sc.nextInt();
            int[] arr = new int[len];
            for (int j = 0; j < len; j++) {
                arr[j] = sc.nextInt();
            }
            timetables.add(arr);
        }
        sc.close();

        List<Integer> mergedList = mergeKLists(timetables);

        for (int i = 0; i < mergedList.size(); i++) {
            System.out.print(mergedList.get(i) + (i == mergedList.size() - 1 ? "" : " "));
        }
        System.out.println();
    }

    public static List<Integer> mergeKLists(List<int[]> lists) {
        PriorityQueue<TimeEntry> minHeap = new PriorityQueue<>();

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null && lists.get(i).length > 0) {
                minHeap.add(new TimeEntry(lists.get(i)[0], i, 0));
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            TimeEntry current = minHeap.poll();
            result.add(current.time());

            int currentListIndex = current.listIndex();
            int nextElementIndex = current.elementIndex() + 1;

            if (nextElementIndex < lists.get(currentListIndex).length) {
                int nextTime = lists.get(currentListIndex)[nextElementIndex];
                minHeap.add(new TimeEntry(nextTime, currentListIndex, nextElementIndex));
            }
        }
        return result;
    }
}
