package org.iesbelen.util;

import java.util.HashMap;
import java.util.Map;

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
}
