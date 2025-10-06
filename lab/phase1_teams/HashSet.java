package phase1_teams;

import java.util.ArrayList;
import java.util.List;

public class HashSet<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private List<T>[] buckets;

    public HashSet() {
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
}