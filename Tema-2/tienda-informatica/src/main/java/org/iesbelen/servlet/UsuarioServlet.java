package org.iesbelen.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Usuario;
import org.iesbelen.util.HTTPRequestUtil;
import org.iesbelen.util.ResultadoDeCreacion;
import org.iesbelen.util.ResultadoDeValidacion;

import java.io.IOException;
import java.util.Arrays;

import static org.iesbelen.util.HTTPRequestUtil.getCadenaODefault;
import static org.iesbelen.util.HTTPRequestUtil.getIntegerODefault;

@WebServlet(name = "usuarioServlet", value = "/tienda/usuarios/*")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {


        String pathInfo = req.getPathInfo();
        String path = req.getServletPath().concat(pathInfo == null ? "/" : pathInfo);

        System.out.println(path);

        if ("/tienda/usuarios/".equals(path))
            mostrarUsuarios(req, res);

        if ("/tienda/usuarios/crear".equals(path))
            crearUsuarioJSP(req, res);

        if (path.startsWith("/tienda/usuarios/editar/"))
            editarUsuarioJSP(req, res);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String __method__ = req.getParameter("__method__");

        if (__method__ == null)
            crearUsuario(req, res);

        else if (__method__.equals("put"))
            doPut(req, res);

        else if (__method__.equals("delete"))
            doDelete(req, res);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        int id = getIntegerODefault(req, "codigo");
        usuarioDAO.delete(id);
        res.sendRedirect(req.getContextPath().concat("/tienda/usuarios/"));
    }

    private static void crearUsuario(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        String usuario = getCadenaODefault(req, "usuario");
        String password = getCadenaODefault(req, "password");
        String rol = getCadenaODefault(req, "rol");

        ResultadoDeCreacion<Usuario> resultadoDeCreacionDelUsuario =
                Usuario.crearUsuario(usuario, password, rol);

        if (resultadoDeCreacionDelUsuario.esValido())
            usuarioDAO.create(resultadoDeCreacionDelUsuario.get());

        res.sendRedirect(req.getContextPath().concat("/tienda/usuarios/"));
    }

    private void mostrarUsuarios(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        req.setAttribute("listaUsuarios", usuarioDAO.getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp").forward(req, res);
    }

    private void crearUsuarioJSP(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/jsp/usuarios/crear-usuario.jsp").forward(req, res);
    }

    private void editarUsuarioJSP(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        String pathInfo = req.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        String id;

        if (pathParts.length == 3) {
            try {
                id = pathParts[2];
                req.setAttribute("usuario", usuarioDAO.find(Integer.parseInt(id)));
                req.getRequestDispatcher("/WEB-INF/jsp/usuarios/editar-usuario.jsp").forward(req, res);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
                req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp").forward(req, res);
            }
        }
    }
}
