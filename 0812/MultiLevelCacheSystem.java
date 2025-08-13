import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class CacheEntry {
    int key;
    String value;
    int frequency;
    long timestamp;

    public CacheEntry(int key, String value) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
        this.timestamp = System.nanoTime();
    }

    public void updateOnAccess(String... newValue) {
        this.frequency++;
        this.timestamp = System.nanoTime();
        if (newValue.length > 0) {
            this.value = newValue[0];
        }
    }

    @Override
    public String toString() {
        return "['" + value + "', freq=" + frequency + "]";
    }
}

class CacheLevel {
    private final String name;
    private final int capacity;
    private final Map<Integer, CacheEntry> storage;
    private final PriorityQueue<CacheEntry> priorityQueue;

    public CacheLevel(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.storage = new HashMap<>();
        this.priorityQueue = new PriorityQueue<>(
            Comparator.comparingInt((CacheEntry a) -> a.frequency)
                      .thenComparingLong(a -> a.timestamp)
        );
    }

    public boolean isFull() {
        return storage.size() >= capacity;
    }
    
    public boolean contains(int key) {
        return storage.containsKey(key);
    }

    public CacheEntry get(int key) {
        return storage.get(key);
    }
    
    public void add(CacheEntry entry) {
        storage.put(entry.key, entry);
        priorityQueue.offer(entry);
    }

    public CacheEntry remove(int key) {
        CacheEntry entry = storage.remove(key);
        if (entry != null) {
            priorityQueue.remove(entry);
        }
        return entry;
    }

    public CacheEntry evict() {
        CacheEntry evicted = priorityQueue.poll();
        if (evicted != null) {
            storage.remove(evicted.key);
        }
        return evicted;
    }

    @Override
    public String toString() {
        return name + ": " + storage.toString();
    }
}

public class MultiLevelCacheSystem {
    private final Map<Integer, CacheLevel> locationMap;
    private final CacheLevel l1;
    private final CacheLevel l2;
    private final CacheLevel l3;

    public MultiLevelCacheSystem() {
        this.l1 = new CacheLevel("L1", 2);
        this.l2 = new CacheLevel("L2", 5);
        this.l3 = new CacheLevel("L3", 10);
        this.locationMap = new HashMap<>();
    }

    public String get(int key) {
        System.out.println("--> GET(" + key + ")");
        if (!locationMap.containsKey(key)) {
            System.out.println("    MISS");
            return null;
        }

        CacheLevel currentLevel = locationMap.get(key);
        CacheEntry entry = currentLevel.get(key);
        
        entry.updateOnAccess();
        currentLevel.remove(key);
        currentLevel.add(entry);

        if (currentLevel != l1) {
            System.out.println("    Promoting key " + key + " from " + currentLevel.name);
            currentLevel.remove(key);
            placeEntry(entry, l1);
        }
        
        displayState();
        return entry.value;
    }

    public void put(int key, String value) {
        System.out.println("--> PUT(" + key + ", " + value + ")");
        if (locationMap.containsKey(key)) {
            CacheLevel level = locationMap.get(key);
            level.get(key).updateOnAccess(value);
            get(key);
        } else {
            CacheEntry newEntry = new CacheEntry(key, value);
            placeEntry(newEntry, l1);
        }
        displayState();
    }

    private void placeEntry(CacheEntry entry, CacheLevel level) {
        if (level == null) {
            System.out.println("    Key " + entry.key + " evicted from system.");
            locationMap.remove(entry.key);
            return;
        }

        if (level.isFull()) {
            CacheEntry evicted = level.evict();
            System.out.println("    " + level.name + " is full. Evicting key " + evicted.key + " to next level.");
            placeEntry(evicted, getNextLevel(level));
        }

        level.add(entry);
        locationMap.put(entry.key, level);
    }
    
    private CacheLevel getNextLevel(CacheLevel current) {
        if (current == l1) return l2;
        if (current == l2) return l3;
        return null;
    }
    
    public void displayState() {
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println("-------------------------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== Multi-Level Cache System Simulation ===");
        MultiLevelCacheSystem cache = new MultiLevelCacheSystem();

        cache.put(1, "A");
        cache.put(2, "B");
        cache.put(3, "C");

        cache.get(1);
        cache.get(1);
        cache.get(2);

        cache.put(4, "D");
        cache.put(5, "E");
        cache.put(6, "F");
    }
}
