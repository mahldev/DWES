package org.example;

public class Main {

    public static void main(String[] args) {

        Matriz<Integer> matriz = new Matriz<>(4, 2);
        Integer celda;

        rellenaMatriz(matriz);
        celda = matriz.get(1, 2);

        System.out.println(matriz.toString());
        System.out.println("\n" + celda);
    }

    private static void rellenaMatriz(Matriz<Integer> matriz) {
        int aux = 1;
        for (int i = 1; i <= matriz.filas(); i++) {
            for (int j = 1; j <= matriz.columnas(); j++) {
                matriz.set(i, j, aux++);
            }
        }
    }
}
