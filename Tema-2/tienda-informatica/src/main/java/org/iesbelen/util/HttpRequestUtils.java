package org.iesbelen.util;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HttpRequestUtils {

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

    public static void sendToReferer(HttpServletRequest req, HttpServletResponse res) {
        String referer = req.getHeader("Referer").replace("?", "");
        String currentUrl = req.getRequestURL().toString();

        if (!currentUrl.endsWith("/")) {
            currentUrl += "/";
        }

        try {
            res.sendRedirect(currentUrl.equals(referer) ? req.getContextPath().concat("/") : referer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Optional<T> recuperarSesion(HttpServletRequest req, String nombre) {
        return Optional.ofNullable(req.getSession().getAttribute(nombre)).map(object -> (T) object);
    }

    public static void ejecutarRequest(HttpServletRequest req, HttpServletResponse res, FilterChain chain) {
        try {
            chain.doFilter(req, res);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    public static String obtenerPath(HttpServletRequest req) {
        String path = req.getRequestURI().substring(req.getContextPath().length());

        if (!path.endsWith("/")) {
            path += "/";
        }

        return path;
    }

    public static void manejarError(HttpServletResponse res, int codigoError, String message) {
        try {
            res.sendError(codigoError, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}