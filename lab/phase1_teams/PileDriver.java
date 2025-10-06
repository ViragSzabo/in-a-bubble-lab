package phase1_teams;

import java.util.NoSuchElementException;

public class PileDriver<T> {
    private Node<T> top;
    private int size;

    private static class Node<T>{
        T data;
        Node<T> next;
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    public PileDriver() {
        top = null;
        size = 0;
    }

    public void push(T data) {
        Node<T> newNode = new Node<T>(data, top);
        top = newNode;
        size++;
    }

    public T pop(){
        if(isEmpty()) throw new NoSuchElementException("Stack is empty");
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> current = top;
        while(current != null) {
            sb.append(current.data).append(" -> ");
            current = current.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public T peek() {
        if(isEmpty()) throw new NoSuchElementException("Stack is empty");
        return top.data;
    }

    public T peekAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        Node<T> current = top;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
}