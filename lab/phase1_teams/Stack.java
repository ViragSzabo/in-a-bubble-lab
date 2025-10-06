package phase1_teams;

import java.util.NoSuchElementException;

public class Stack<T> {
    private Node<T> top;
    private int size;

    private static class Node<T>{
        private T data;
        private Node<T> next;
        public Node(T data, Node<T> next) { this.data = data; }
    }

    public Stack() {
        top = null;
        size = 0;
    }

    public T pop(){
        if(isEmpty()) throw new NoSuchElementException("Stack is empty");
        T data = top.data;
        size--;
        return top.data;
    }

    public boolean isEmpty() { return true; }
    public int size() { return size; }
}