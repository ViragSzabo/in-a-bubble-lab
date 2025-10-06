package phase1_teams;

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
}