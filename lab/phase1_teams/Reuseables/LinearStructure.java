package phase1_teams.Reuseables;

import java.util.List;
import java.util.Comparator;

/**
 * Abstract base class for linear data structures like Stack and Queue.
 * Encapsulates shared node definition, size handling, and traversal logic.
 *
 * @param <T> the type of elements stored
 */
public abstract class LinearStructure<T> {

    /** Node structure shared across subclasses */
    protected static class Node<T> {
        public T data;
        public Node<T> next;

        public Node(T data) { this.data = data; }
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    protected int size = 0;
    protected final Comparator<T> comparator;

    public LinearStructure() { this(null); }
    public LinearStructure(Comparator<T> comparator) { this.comparator = comparator; }

    /** Returns the size of the structure */
    public int size() { return size; }

    /** Returns true if empty */
    public boolean isEmpty() { return size == 0; }

    /** Compares two elements using comparator or natural ordering */
    public int compare(T a, T b) {
        if (comparator != null) return comparator.compare(a, b);
        if (a instanceof Comparable) return ((Comparable<T>) a).compareTo(b);
        throw new UnsupportedOperationException("No comparator or natural ordering defined");
    }

    /** Converts structure contents to a list (implemented by subclass) */
    public abstract List<T> toList();

    /** Retrieves element at a given index */
    public T peekAt(int index) {
        List<T> list = toList();
        if (index < 0 || index >= list.size())
            throw new IndexOutOfBoundsException(STR."Index: \{index}, Size: \{list.size()}");
        return list.get(index);
    }
}