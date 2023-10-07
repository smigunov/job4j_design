package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
        size = 0;
        modCount = 0;
    }

    @Override
    public void add(T value) {
        size++;
        if (size >= container.length) {
            T[] newContainer = (T[]) new Object[size * 2];
            if (size > 1) {
                System.arraycopy(container, 0, newContainer, 0, size);
            }
            container = newContainer;
        }
        container[size - 1] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T oldValue = container[index];
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T removedElement = container[index];
        System.arraycopy(container, index + 1, container, index, size - index - 1);
        size--;
        modCount++;
        return removedElement;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pos = 0;
            int primaryModCount = modCount;

            @Override
            public boolean hasNext() {
                if (primaryModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (pos < size) {
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (primaryModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                T result = container[pos];
                pos++;
                return result;
            }
        };
    }
}
