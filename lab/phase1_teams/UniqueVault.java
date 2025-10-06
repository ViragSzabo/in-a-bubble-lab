package phase1_teams;

import java.util.ArrayList;
import java.util.List;

public class UniqueVault<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private List<T>[] buckets;

    public UniqueVault() {
        this.buckets = new List[DEFAULT_CAPACITY];
        for(int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    private int getBucketIndex(T data) {
        return (data.hashCode() & 0x7fffffff) % this.buckets.length;
    }

    public boolean add(T data) {
        int index = getBucketIndex(data);
        if(buckets[index].contains(data)) {
            return false;
        } else {
            buckets[index].add(data);
            return true;
        }
    }

    public boolean remove(T data) {
        return buckets[getBucketIndex(data)].remove(data);
    }

    public boolean contains(T data) {
        return buckets[getBucketIndex(data)].contains(data);
    }

    public int size() {
        int total = 0;
        for (List<T> list : buckets) total += list.size();
        return total;
    }

    public boolean isEmpty() { return size() == 0; }
}