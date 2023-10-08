package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArrayList(int capacity) {
        container = (T[]) new Object[capacity];
    }

    private void grow() {
        if (size >= container.length || size == 0) {
            T[] newContainer = (T[]) new Object[(size == 0) ? 1 : (size * 2)];
            System.arraycopy(container, 0, newContainer, 0, size);
            container = newContainer;
        }
    }

    @Override
    public void add(T value) {
        grow();
        container[size++] = value;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T oldValue = get(index);
        container[index] = newValue;
        return oldValue;
    }

    @Override
    public T remove(int index) {
        T removedElement = get(index);
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
                return pos < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[pos++];
            }
        };
    }
}
