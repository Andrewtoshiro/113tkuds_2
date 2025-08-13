import java.util.Iterator;
import java.util.PriorityQueue;

class Task {
    String name;
    int priority;
    long timestamp;

    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
        this.timestamp = System.nanoTime();
    }

    @Override
    public String toString() {
        return "Task(Name: " + name + ", Priority: " + priority + ")";
    }
}

public class PriorityQueueWithHeap {

    private PriorityQueue<Task> taskQueue;

    public PriorityQueueWithHeap() {
        this.taskQueue = new PriorityQueue<>((a, b) -> {
            if (a.priority != b.priority) {
                return Integer.compare(b.priority, a.priority);
            }
            return Long.compare(a.timestamp, b.timestamp);
        });
    }

    public void addTask(String name, int priority) {
        Task newTask = new Task(name, priority);
        taskQueue.offer(newTask);
        System.out.println("Added: " + newTask);
    }

    public Task executeNext() {
        Task nextTask = taskQueue.poll();
        if (nextTask != null) {
            System.out.println("Executing: " + nextTask);
        } else {
            System.out.println("No tasks to execute.");
        }
        return nextTask;
    }

    public Task peek() {
        return taskQueue.peek();
    }

    public boolean changePriority(String name, int newPriority) {
        Task taskToUpdate = null;
        Iterator<Task> iterator = taskQueue.iterator();
        while (iterator.hasNext()) {
            Task currentTask = iterator.next();
            if (currentTask.name.equals(name)) {
                taskToUpdate = currentTask;
                iterator.remove();
                break;
            }
        }

        if (taskToUpdate != null) {
            System.out.println("Updating priority for '" + name + "' from " + taskToUpdate.priority + " to " + newPriority);
            taskToUpdate.priority = newPriority;
            taskQueue.add(taskToUpdate);
            return true;
        } else {
            System.out.println("Task '" + name + "' not found for priority update.");
            return false;
        }
    }

    public void displayQueue() {
        System.out.println("Current Queue (top is next): " + taskQueue);
    }


    public static void main(String[] args) {
        System.out.println("=== Priority Queue Test Case ===");
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();

        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("\nNext task to execute (peek): " + pq.peek());
        pq.displayQueue();

        System.out.println("\n--- Executing all tasks ---");
        pq.executeNext();
        pq.executeNext();
        pq.executeNext();
        pq.executeNext();

        System.out.println("\n\n=== changePriority Demonstration ===");
        pq.addTask("資料庫清理", 2);
        pq.addTask("用戶認證服務", 4);
        pq.addTask("日誌歸檔", 1);
        
        System.out.println("\nInitial state:");
        pq.displayQueue();
        System.out.println("Next to execute: " + pq.peek());

        System.out.println();
        pq.changePriority("日誌歸檔", 6);
        
        System.out.println("\nState after priority change:");
        pq.displayQueue();
        System.out.println("Next to execute: " + pq.peek());

        System.out.println("\n--- Executing remaining tasks ---");
        pq.executeNext();
        pq.executeNext();
        pq.executeNext();
    }
}
