package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size;
    private int modCount;
    private Node<T> head;

    public void add(T value) {
        Node<T> curNode = new Node<>(value, null);
        if (head == null) {
            head = curNode;
        } else {
            Node<T> node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = curNode;
        }
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        T result = null;
        int i = 0;
        for (T curVal : this) {
            if (i == index) {
                result = curVal;
                break;
            }
            i++;
        }
        return result;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.getValue();
        head = head.next;
        return result;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int primaryModCount = modCount;
            Node<T> curNode = head;

            @Override
            public boolean hasNext() {
                if (primaryModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return curNode != null;
            }

            @Override
            public T next() {
                T result = curNode.item;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                curNode = curNode.next;
                return result;
            }
        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        public E getValue() {
            return item;
        }
    }
}