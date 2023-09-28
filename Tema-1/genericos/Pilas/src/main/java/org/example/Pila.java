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
        return list.removeLast();
    }

    @Override
    public T primero() {
        return list.getLast();
    }

    @Override
    public boolean aniadir(T e) {
        return list.add(e);
    }

    private String format(String str1, String str2) {
        return str1 + " " + str2;
    }

    @Override
    public String toString() {
        return list.stream()
                .map(T::toString)
                .reduce(this::format)
                .orElse("Vacia");
    }
}
