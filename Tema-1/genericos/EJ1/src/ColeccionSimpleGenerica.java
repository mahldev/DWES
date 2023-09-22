public interface ColeccionSimpleGenerica<T> {

    boolean estaVacia();

    T extraer();

    T primero();

    boolean aniadir(T e);
}
