package org.iesbelen.filter;

import java.io.IOException;
import java.util.Optional;

import org.iesbelen.model.Usuario;

import static org.iesbelen.util.HTTPRequestUtil.*;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(filterName = "loginFilter", urlPatterns = { "/tienda/login/*" }, initParams = {
        @WebInitParam(name = "rol-de-acceso", value = "administrador"),
})
public class LoginFilter extends HttpFilter {

    String rolAcceso;

    @Override
    public void init(FilterConfig filterConfig) {
        rolAcceso = filterConfig.getInitParameter("rol-de-acceso");
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        Optional<Usuario> usuario = recuperarSesion(req, rolAcceso);
        String url = req.getRequestURI().substring(req.getContextPath().length());        
         
        usuario.ifPresentOrElse(
                        u -> {
                            u.esAdministrador((admin) -> {
                                ejecutarRequest(req, res, chain);
                            })
                            .esCliente((cliente) -> {
                                
                            })
                            .esVendedor((vendedor) -> {

                                    
                            });

                            try {
                                chain.doFilter(req, res);
                            } catch (IOException | ServletException e) {
                                e.printStackTrace();
                            }
                        },
                        () -> {
                            sendRedirectTo("/tienda/login/", req, res);
                        });

    }

    
}
