package phase1_teams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * UniqueVault is a simplified HashSet-like structure.
 * Maintains unique elements using hashing and bucket lists.
 *
 * @param <T> the type of elements stored
 */
public class UniqueVault<T> {
    private static final int DEFAULT_CAPACITY = 16;
    private final List<T>[] buckets;
    private Comparator<T> comparator;

    @SuppressWarnings("unchecked")
    public UniqueVault() {
        this.buckets = new List[DEFAULT_CAPACITY];
        for(int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public UniqueVault(Comparator<T> comparator) {
        this.buckets = new List[DEFAULT_CAPACITY];
        for(int i = 0; i < DEFAULT_CAPACITY; i++) {
            buckets[i] = new ArrayList<>();
        }
        this.comparator = comparator;
    }

    /** Hashes element to correct bucket */
    private int getBucketIndex(T data) {
        return (data.hashCode() & 0x7fffffff) % this.buckets.length;
    }

    /** Adds element if not already present */
    public boolean add(T data) {
        int index = getBucketIndex(data);
        if(buckets[index].contains(data)) {
            return false;
        } else {
            buckets[index].add(data);
            return true;
        }
    }

    /** Removes element */
    public boolean remove(T data) {
        return buckets[getBucketIndex(data)].remove(data);
    }

    /** Checks existence */
    public boolean contains(T data) {
        return buckets[getBucketIndex(data)].contains(data);
    }

    /** Checks the current size of the hash set */
    public int size() {
        int total = 0;
        for (List<T> list : buckets) total += list.size();
        return total;
    }

    /** Checks if it is empty or not */
    public boolean isEmpty() { return size() == 0; }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (List<T> bucket : buckets) list.addAll(bucket);
        return list;
    }

    /** Clear all elements */
    public void clear() { for(List<T> bucket : buckets) bucket.clear(); }

    /** Convert set contents to list */
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

    /** Generic element comparison */
    public int compare(T a, T b) {
        if (comparator != null) return comparator.compare(a, b);
        if (a instanceof Comparable) return ((Comparable<T>) a).compareTo(b);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size(); i++) {
            sb.append(getAt(i));
            if (i < size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}