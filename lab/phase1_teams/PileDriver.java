package phase1_teams;

import phase1_teams.Reuseables.LinearStructure;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * PileDriver is a generic Stack (LIFO) implementation.
 *
 * @param <T> the type of elements stored in the stack.
 */
public class PileDriver<T> extends LinearStructure<T> {
    /** The top of the stack. */
    private Node<T> top;

    /** Construction for an empty queue with/without custom comparator */
    public PileDriver() { super(); }
    public PileDriver(Comparator<T> comparator) { super(comparator); }

    /** Adds an element to the top of the stack */
    public void push(T data) {
        top = new Node<>(data, top);
        size++;
    }

    /** Removes and returns the top  element of the stack */
    public T pop(){
        if(isEmpty()) throw new NoSuchElementException("Stack is empty");
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    /** Returns the top element without removing it */
    public T peek() {
        if(isEmpty()) throw new NoSuchElementException("Stack is empty");
        return top.data;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        Node<T> current = top;
        while (current != null) {
            list.add(current.data);
            current = current.next;
        }
        return list;
    }
}