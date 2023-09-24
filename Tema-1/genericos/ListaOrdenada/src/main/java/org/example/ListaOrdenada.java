package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListaOrdenada<E extends Comparable<E>> {

    List<E> list;

    public ListaOrdenada() {
        list = new ArrayList<>();
    }

    public ListaOrdenada(List<E> lista) {
        list = new ArrayList<>(lista);
    }

    public int getIndex(E o) {
        int index = Collections.binarySearch(list, o);

        if (index >= 0)
            return index;

        return -(index) - 1;
    }

    public void add(E o) {
        list.add(getIndex(o), o);
    }

    public E get(int index) {
        if (isEmpty())
            return null;

        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean remove(E o) {
        return list.remove(o);
    }

    public int indexOf(E o) {
        return list.indexOf(o);
    }

    @Override
    public String toString() {
        return list.stream()
                .map(e -> e.toString() + " ")
                .reduce((t, u) -> t + u)
                .orElse("Lista Vacia");
    }
}
