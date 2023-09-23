package org.example;

import java.util.Objects;

public class Celda<T> implements Comparable<Celda<T>> {

    private Integer fila;
    private Integer columna;
    private T valor;

    public Celda(Integer fila, Integer columna, T valor) {
        this.fila = fila;
        this.columna = columna;
        this.valor = valor;
    }

    public Celda(Integer fila, Integer columna) {
        this.fila = fila;
        this.columna = columna;
        this.valor = null;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public Integer getColumna() {
        return columna;
    }

    public void setColumna(Integer columna) {
        this.columna = columna;
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Celda<?> celda = (Celda<?>) o;
        return Objects.equals(fila, celda.fila) && Objects.equals(columna, celda.columna);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fila, columna);
    }

    @Override
    public int compareTo(Celda<T> o) {
        int res;
        if ((res = this.fila - o.fila) == 0)
            return this.columna - o.columna;

        return res;
    }

    @Override
    public String toString() {
        return String.format("Fila: %d - Columna: %d - Valor: %s", fila, columna, valor);
    }
}
