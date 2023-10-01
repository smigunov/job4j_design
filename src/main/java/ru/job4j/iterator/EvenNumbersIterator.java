package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private int[] data;
    private int index;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
        index = -1;
        movePointToNext();
    }

    @Override
    public boolean hasNext() {
        return index <= data.length - 1;
    }

    private void movePointToNext() {
       index++;
       if (hasNext() && data[index] % 2 != 0) {
           movePointToNext();
       }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer nextValue = data[index];
        movePointToNext();
        return nextValue;
    }

}