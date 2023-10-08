package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements SimpleLinked<E> {

    private int size;
    private int modCount;
    private Node<E> head;

    @Override
    public void add(E value) {
        Node<E> curNode = new Node<>(value, null);
        if (head == null) {
            head = curNode;
        } else {
            Node<E> node = head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = curNode;
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> curNode = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                break;
            }
            curNode = curNode.next;
        }
        return curNode.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int primaryModCount = modCount;
            Node<E> curNode = head;

            @Override
            public boolean hasNext() {
                if (primaryModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return curNode != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = curNode.item;
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
    }
}