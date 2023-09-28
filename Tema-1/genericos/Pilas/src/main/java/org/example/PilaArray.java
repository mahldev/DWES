package org.example;

public class PilaArray<T> implements ColeccionSimpleGenerica<T> {

    private T[] array;
    private int numeroElementos;

    public PilaArray() {
        this.array = (T[]) new Object[20];
        this.numeroElementos = 0;
    }

    @Override
    public boolean estaVacia() {
        return numeroElementos == 0;
    }

    @Override
    public T extraer() {
        T res = array[numeroElementos - 1];
        numeroElementos--;
        return res;
    }

    @Override
    public T primero() {
        return array[numeroElementos - 1];
    }

    @Override
    public boolean aniadir(T e) {
        if (numeroElementos < array.length) {
            array[numeroElementos++] = e;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String res = "";
        for (T e : array) {
            res += e.toString() + " ";
        }
        return res;
    }

    void showT() {
        System.out.println(array[0]);
        System.out.println(numeroElementos);
    }
}
