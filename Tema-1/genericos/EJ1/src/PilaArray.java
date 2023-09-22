public class PilaArray<T> implements ColeccionSimpleGenerica<T> {

    private T[] array;
    private int numeroElementos;

    public PilaArray() {
        this.array = (T[]) new Object();
        this.numeroElementos = 0;
    }

    @Override
    public boolean estaVacia() {
        return numeroElementos == 0;
    }

    @Override
    public T extraer() {
        if (estaVacia())
            return null;

        T res = array[numeroElementos];
        numeroElementos--;
        return res;
    }

    @Override
    public T primero() {
        return array[numeroElementos];
    }

    @Override
    public boolean aniadir(T e) {
        if (numeroElementos < array.length) {
            array[numeroElementos++] = e;
            return true;
        }
        return false;
    }

}
