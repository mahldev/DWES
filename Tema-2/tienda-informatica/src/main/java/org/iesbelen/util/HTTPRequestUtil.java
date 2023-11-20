package org.iesbelen.util;

import static java.util.Objects.nonNull;

import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    public static Integer getIntegerODefault(HttpServletRequest req, String nombre) {
        String valor = req.getParameter(nombre);
        try {
            return valor != null && !valor.isBlank() ? Integer.parseInt(valor) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void forwardToJsp(String jspPath, HttpServletRequest req, HttpServletResponse res) {
        try {
            req.getRequestDispatcher(jspPath).forward(req, res);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendRedirectTo(String path, HttpServletRequest req, HttpServletResponse res) {
        try {
            res.sendRedirect(req.getContextPath().concat(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void manejarRequest(HttpServletRequest req, HttpServletResponse resp,
            Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> routes) {

        String path = req.getRequestURI().substring(req.getContextPath().length());
        String method = req.getParameter("__method__");

        System.out.println(req.getMethod());
        System.out.println(method);
        System.out.println(path);

        if (!path.endsWith("/")) {
            path += "/";
        }

        BiConsumer<HttpServletRequest, HttpServletResponse> handler = encontrarManejador(path, routes);

        if (nonNull(handler)) {
            handler.accept(req, resp);
        } 
    }

    private static BiConsumer<HttpServletRequest, HttpServletResponse> encontrarManejador(
            String path, Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> routes) {
        return routes.entrySet().stream()
                .filter(entry -> path.matches(entry.getKey()))
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(null);
    }

    public static void sendToReferer(HttpServletRequest req, HttpServletResponse res) {
        String referer = req.getHeader("Referer").replace("?", "");
        String currentUrl = req.getRequestURL().toString();

        System.out.println(referer);
        System.out.println(currentUrl);
        try {
            res.sendRedirect(currentUrl.equals(referer) ? req.getContextPath().concat("/") : referer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}