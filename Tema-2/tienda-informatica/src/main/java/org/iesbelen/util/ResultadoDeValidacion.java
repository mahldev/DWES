package org.iesbelen.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ResultadoDeValidacion {

    private final Map<String, String> errores;

    public ResultadoDeValidacion() {
        this.errores = new HashMap<>();
    }

    public boolean esValido() {
        return errores.isEmpty();
    }

    public void addError(String key, String value) {
        errores.put(key, value);
    }

    public Map<String, String> getErrores() {
        return errores;
    }

    public ResultadoDeValidacion esCorrecto(Runnable run) {
        if (esValido()) {
            run.run();
        }

        return this;
    }

    public void esIncorrecto(Consumer<Map<String, String>> consumer) {
        if (!esValido()) {
            consumer.accept(errores);
        }
    }

    @Override
    public String toString() {
        return errores.values().stream()
                .collect(Collectors.joining(" "));
    }
}
