package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private Integer[] data;
    private int index;

    public NonNullIterator(Integer[] data) {
        this.data = data;
        index = 0;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        if (data.length > 0) {
            while (index <= data.length - 1 && data[index] == null) {
                index++;
            }

            if (index <= data.length - 1 && data[index] != null) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer result = data[index];
        index++;
        return result;
    }

}