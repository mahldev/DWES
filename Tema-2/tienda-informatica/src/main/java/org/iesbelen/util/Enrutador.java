package org.iesbelen.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Enrutador {

    Map<String, Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>>> rutas = new HashMap<>();

    public Enrutador() {
        rutas.put("GET", new HashMap<>());
        rutas.put("POST", new HashMap<>());
    }

    public void addRuta(String metodo, String ruta, BiConsumer<HttpServletRequest, HttpServletResponse> consumidor) {
        rutas.get(metodo).put(ruta, consumidor);
    }

    public void manejarRequest(
            HttpServletRequest req,
            HttpServletResponse res,
            Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutas) {

        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (!path.endsWith("/")) {
            path += "/";
        }

        encontrarManejador(path, rutas)
                .ifPresentOrElse((manejador) -> manejador.accept(req, res),
                        () -> {
                            try {
                                res.sendError(404, "No se ha encontrado la ruta solicitada");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
    }

    public Optional<BiConsumer<HttpServletRequest, HttpServletResponse>> encontrarManejador(
            String path, Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutas) {
        return rutas.entrySet().stream()
                .filter(entry -> path.matches(entry.getKey()))
                .findFirst()
                .map(Map.Entry::getValue);
    }
}
