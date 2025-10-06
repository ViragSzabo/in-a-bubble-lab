package phase1_teams;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FlowMaster<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    private static class Node<T> {
        final T data;
        Node<T> next;
        public Node(T data) { this.data = data; }
    }

    public FlowMaster() {
        front = rear = null;
        size = 0;
    }

    public void enqueue(T data) {
        Node<T> node = new Node<>(data);
        if (rear != null) { rear.next = node; }
        rear = node; // always point to the last node in the queue
        if (front == null) { front = rear; }
        size++;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        T data = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return data;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty");
        return front.data;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = front;
        while(current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public T peekAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        Node<T> current = front; // start from the front
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node<T> current = front;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }
}