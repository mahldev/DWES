package org.example;

public class Main {

    public static void main(String[] args) {
        ListaOrdenada<Integer> lista = new ListaOrdenada<>();

        lista.add(5);
        lista.add(-2);
        lista.add(-532);
        lista.add(-532);
        lista.add(9);
        lista.add(-2);
        lista.add(42);
        lista.add(2);
        lista.add(0);
        lista.add(23);
        lista.add(91238);

        System.out.println(lista);
    }
}
