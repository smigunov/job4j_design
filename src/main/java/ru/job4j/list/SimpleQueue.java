package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /* добавьте переменные, если они требуются */

    private void doNothing() {
        int i = 0;
    }

    private void moveStack2Stack(SimpleStack<T> inStack, SimpleStack<T> outStack) {
        try {
            while (true) {
                outStack.push(inStack.pop());
            }
        } catch (NoSuchElementException ignore) {
            doNothing();
        }
    }

    public T poll() {
        moveStack2Stack(in, out);
        T result;
        try {
            result = out.pop();
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Queue is empty");
        }
        moveStack2Stack(out, in);
        return result;
    }

    public void push(T value) {
        in.push(value);
    }
}