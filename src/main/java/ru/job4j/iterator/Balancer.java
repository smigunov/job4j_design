package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int numArLst = 0;     // Индекс-порядковый номер ArrayLis
        if (nodes.size() > 0) {
            while (source.hasNext())  {
                nodes.get(numArLst++).add(source.next());

                // Если достигли конца списка ArrayList'ов, то перемещаемся в начало.
                if (numArLst >= nodes.size()) {
                    numArLst = 0;
                }
            }
        }
    }
}