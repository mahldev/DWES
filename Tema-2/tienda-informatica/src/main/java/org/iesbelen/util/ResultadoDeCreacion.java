package org.iesbelen.util;

public class ResultadoDeCreacion<T> {

    private final T objeto;
    private final ResultadoDeValidacion errores;

    public ResultadoDeCreacion(T objeto) {
        this.objeto = objeto;
        this.errores = new ResultadoDeValidacion();
    }

    public ResultadoDeCreacion(T objeto, ResultadoDeValidacion errors) {
        this.objeto = objeto;
        this.errores = errors;
    }

    public boolean esValido() {
        return errores.esValido();
    }

    public T get() {
        return objeto;
    }

    public ResultadoDeValidacion getErrores() {
        return errores;
    }
}
