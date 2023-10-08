package ru.job4j.list;

public interface SimpleLinked<E> extends Iterable<E> {

    void add(E value);

    E get(int index);
}
