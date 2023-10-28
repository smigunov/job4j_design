package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class NonCollisionMap<K, V> implements SimpleMap<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean result = false;
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int idx = getIndex(key);

        if (table[idx] == null) {
            count++;
            table[idx] = new MapEntry(key, value);
            modCount++;
            result = true;
        }
        return result;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return (capacity - 1) & hash;
    }

    private int getIndex(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> elm : table) {
            if (elm != null) {
                newTable[getIndex(elm.key)] = elm;
            }
        }
        table = newTable;
    }

    private boolean keysAreEqual(K key1, K key2) {
        return Objects.hashCode(key1) == Objects.hashCode(key2) && Objects.equals(key1, key2);
    }

    @Override
    public V get(K key) {
        V result = null;
        int idx = getIndex(key);
        if (table[idx] != null && keysAreEqual(table[idx].key, key)) {
            result = table[idx].value;
        }
        return result;
    }

    @Override
    public boolean remove(K key) {
        int idx = getIndex(key);
        boolean result = false;
        if (table[idx] != null) {
            if (keysAreEqual(table[idx].key, key)) {
                table[idx] = null;
                modCount++;
                result = true;
            }
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final int primaryModCount = modCount;
            int curIndex = 0;

            @Override
            public boolean hasNext() {
                if (modCount != primaryModCount) {
                    throw new ConcurrentModificationException();
                }
                while (curIndex < capacity && table[curIndex] == null) {
                    curIndex++;
                }
                return curIndex < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[curIndex++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}