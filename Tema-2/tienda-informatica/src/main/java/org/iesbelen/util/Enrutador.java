package org.iesbelen.util;

import static org.iesbelen.util.HttpRequestUtils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.HttpMethod;

public class Enrutador {

    private final String RUTA_NO_ENCONTRADA = "Ruta no encontrada";
    private final String GET = HttpMethod.GET;
    private final String POST = HttpMethod.POST;
    private final String PUT = HttpMethod.PUT;
    private final String DELETE = HttpMethod.DELETE;

    private final Map<String, Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>>> rutas = new HashMap<>();

    public Enrutador() {
        rutas.put(GET, new HashMap<>());
        rutas.put(POST, new HashMap<>());
        rutas.put(PUT, new HashMap<>());
        rutas.put(DELETE, new HashMap<>());
    }

    public void addRuta(String metodo, String ruta, BiConsumer<HttpServletRequest, HttpServletResponse> consumidor) {
        rutas.get(metodo).put(ruta, consumidor);
    }

    public void manejarRequest(
            HttpServletRequest req,
            HttpServletResponse res) {

        String path = obtenerPath(req);
        String metodo = obtenerMetodo(req);

        encontrarManejador(path, rutas.get(metodo))
                .ifPresentOrElse((manejador) -> manejador.accept(req, res),
                        () -> manejarError(res, 404, RUTA_NO_ENCONTRADA + " " + path));
    }

    public Optional<BiConsumer<HttpServletRequest, HttpServletResponse>> encontrarManejador(
            String path, Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutas) {
        return rutas.entrySet().stream()
                .filter(entry -> path.matches(entry.getKey()))
                .findFirst()
                .map(Map.Entry::getValue);
    }

    private String obtenerMetodo(HttpServletRequest req) {
        String metodo = req.getParameter("__method__");
        return metodo != null ? metodo.toUpperCase() : req.getMethod();
    }
}
