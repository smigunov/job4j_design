package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row;
    private int column;

    public MatrixIt(int[][] data) {
        this.data = data;
        row = 0;
        column = 0;
        if (data[row].length == 0) {
            movePointToNext();
        }
    }

    @Override
    public boolean hasNext() {
        return !(row > data.length - 1);
    }

    private void movePointToNext() {
        if (column >= data[row].length - 1) {    //Если стоим на последнем элементе строки, то перемещаем указатель на след. строку
            column = 0;
            row++;

            //Если встретился пустой массив, то переместиться на следующую позицию
            if (row < data.length && data[row].length == 0) {
                movePointToNext();
            }
        } else {
            column++;
        }
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        //Запоминаем текущее значение для возврата
        Integer nextValue = data[row][column];

        //Двигаем указатель на след. элемент
        movePointToNext();
        return nextValue;
    }
}
