package ru.job4j.list;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    private int sizeIn = 0;
    private int sizeOut = 0;

    public T poll() {
        if (sizeOut == 0) {
            if (sizeIn == 0) {
                throw new NoSuchElementException("Queue is empty");
            }
            for (int i = 0; i < sizeIn; i++) {
                out.push(in.pop());
                sizeOut++;
            }
            sizeIn = 0;
        }
        sizeOut--;
        return  out.pop();
    }

    public void push(T value) {
        in.push(value);
        sizeIn++;
    }
}