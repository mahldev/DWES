package org.example;

import java.util.Set;
import java.util.TreeSet;

public class Matriz<T> {

    private final Integer NUM_FILAS;
    private final Integer NUM_COLUMNAS;
    private Set<Celda<T>> matriz;

    public Matriz(Integer numFilas, Integer numColumnas) {
        this.matriz = new TreeSet<>();
        this.NUM_FILAS = numFilas;
        this.NUM_COLUMNAS = numColumnas;
    }

    public boolean set(Integer fila, Integer columna, T elemento) {
        if (this.NUM_FILAS >= fila && fila > 0
                && this.NUM_COLUMNAS >= columna && columna > 0)
            return matriz.add(new Celda<T>(fila, columna, elemento));

        return false;
    }

    public T get(Integer fila, Integer columna) {
        Celda<T> ce = matriz.stream()
                .filter(c -> c.equals(new Celda<T>(fila, columna, null)))
                .findFirst().orElse(null);
        if (ce != null)
            return ce.getValor();

        return null;
    }

    public Integer columnas() {
        return this.NUM_COLUMNAS;
    }

    public Integer filas() {
        return this.NUM_FILAS;
    }

    @Override
    public String toString() {
        return matriz.stream()
                .map(t -> t.toString() + "\n")
                .reduce((t, u) -> t + u)
                .orElse("Vacio");
    }
}
