package org.example;

import java.util.LinkedList;

public class Pila<T> implements ColeccionSimpleGenerica<T> {

    private LinkedList<T> list;

    public Pila() {
        this.list = new LinkedList<>();
    }

    @Override
    public boolean estaVacia() {
        return list.isEmpty();
    }

    @Override
    public T extraer() {
        if (list.isEmpty())
            return null;

        T res = list.get(list.size() - 1);
        list.remove(res);
        return res;
    }

    @Override
    public T primero() {
        if (list.isEmpty())
            return null;

        return list.get(list.size() - 1);
    }

    @Override
    public boolean aniadir(T e) {
        return list.add(e);
    }

    @Override
    public String toString() {
        return list.stream()
                .toString();
    }
}
