package org.iesbelen.util;

import jakarta.servlet.http.HttpServletRequest;

public class HTTPRequestUtil {

    public static String getCadenaODefault(HttpServletRequest req, String nombre) {
        String valor = req.getParameter(nombre);
        return valor != null ? valor : "";
    }

    public static Double getDoubleODefault(HttpServletRequest req, String nombre) {
        String valor = req.getParameter(nombre);
        try {
            return valor != null && !valor.isBlank() ? Double.parseDouble(valor) : 0d;
        } catch (NumberFormatException e) {
            return 0d;
        }
    }
}
