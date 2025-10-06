package phase1_teams;

import java.util.ArrayList;
import java.util.List;

public class UniqueVault<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private final List<T>[] buckets;

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

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (List<T> bucket : buckets) list.addAll(bucket);
        return list;
    }

    public void clear() { for(List<T> bucket : buckets) bucket.clear(); }

    @Override
    public String toString() {
        return toList().toString();
    }

    public T getAt(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        int count = 0;
        for (List<T> bucket : buckets) {
            if (index < count + bucket.size()) {
                // The element is in this bucket
                return bucket.get(index - count);
            }
            count += bucket.size();
        }

        // This should never happen
        throw new IllegalStateException("Index calculation error");
    }
}