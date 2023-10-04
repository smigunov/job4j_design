package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    /* здесь разместите поля класса, если они будут нужны  */
    private int index;

    public CyclicIterator(List<T> data) {
        /* код конструктора */
        this.data = data;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        if (data.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        T result;
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        result = this.data.get(index);
        index++;
        if (index >= this.data.size()) {
            index = 0;
        }
        return result;
    }
}