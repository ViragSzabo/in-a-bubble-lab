package phase1_teams;

import phase1_teams.Reuseables.LinearStructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * FlowMaster is a generic Queue (FIFO) implementation.
 * Demonstrates data structure manipulation, generics, and mutual comparison.
 *
 * @param <T> the type of elements stored in the queue.
 */
public class FlowMaster<T> extends LinearStructure<T> {

    /** The pointer of the front (head) and the rear (tail) of the queue. */
    private Node<T> front, rear;

    /** Construction for an empty queue with/without custom comparator */
    public FlowMaster(Comparator<T> comparator) { super(comparator); }
    public FlowMaster() { super(); }

    /** Add an element to the rear (tail) of the queue */
    public void enqueue(T data) {
        Node<T> node = new Node<>(data);
        if (rear != null) rear.next = node;
        rear = node;
        if (front == null) front = rear;
        size++;
    }

    /** Remove and return the front (head) element */
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }

    /** Return the front (head) element without removing it */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return front.data;
    }

    /** Convert queue contents to a List */
    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node<T> current = front;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            sb.append(peekAt(i)).append(" -> ");
        }
        sb.append("null");
        return sb.toString();
    }
}