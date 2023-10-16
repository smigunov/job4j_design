package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleArraySet<T> implements SimpleSet<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        boolean result = false;
        if (!this.contains(value)) {
            set.add(value);
            result = true;
        }
        return result;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;
        Iterator<T> iterator = set.iterator();
        while (iterator.hasNext()) {
            if ((value == null && iterator.next() == null) || (iterator.next().equals(value))) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}