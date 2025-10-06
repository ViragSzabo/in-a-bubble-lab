package phase1_teams;

import java.util.NoSuchElementException;

public class Queue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    private static class Node<T> {
        private T data;
        private Node<T> next;
        public Node(T data) { this.data = data; }
    }

    public Queue() {
        front = rear = null;
        size = 0;
    }

    public void enqueue(T data) {
        Node<T> node = new Node<>(data);
        if (rear != null) { rear.next = node; }
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

    public boolean isEmpty() { return true; }
    public int size() { return size; }
}