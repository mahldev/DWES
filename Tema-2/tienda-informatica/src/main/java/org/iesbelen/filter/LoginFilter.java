package org.iesbelen.filter;

import java.io.IOException;
import java.util.Optional;

import org.iesbelen.auth.GestorDePermisos;
import org.iesbelen.model.Usuario;

import static org.iesbelen.util.HttpRequestUtils.*;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(filterName = "loginFilter", urlPatterns = { "/*" })
public class LoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Optional<Usuario> usuario = recuperarSesion(req, "usuario");
        String url = obtenerPath(req);

        usuario.ifPresentOrElse(
                u -> GestorDePermisos.gestiona(
                        () -> ejecutarRequest(req, res, chain),
                        () -> sendRedirectTo("/tienda/login/", req, res),
                        u, url),
                () -> {
                    if (url.startsWith("/tienda/login") || url.startsWith("/tienda/logout")) {
                        ejecutarRequest(req, res, chain);
                    } else {
                        forwardToJsp("/WEB-INF/jsp/login.jsp", req, res);
                    }
                });

    }

}
