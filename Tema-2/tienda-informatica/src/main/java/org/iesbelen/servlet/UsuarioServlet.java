package org.iesbelen.servlet;

import static java.util.Objects.isNull;
import static org.iesbelen.util.HTTPRequestUtil.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import jakarta.servlet.annotation.WebServlet;
import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Usuario;
import org.iesbelen.util.ResultadoDeCreacion;
import org.iesbelen.util.ResultadoDeValidacion;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "usuarioServlet", value = "/tienda/usuarios/*")
public class UsuarioServlet extends HttpServlet {

    Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> getRoutes = new HashMap<>();
    Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> postRoutes = new HashMap<>();
    Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> putRoutes = new HashMap<>();
    Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> deleteRoutes = new HashMap<>();

    @Override
    public void init() {
        // GET
        getRoutes.put("/tienda/usuarios/", this::mostrarUsuarios);
        getRoutes.put("/tienda/usuarios/editar/\\d+/?", this::devolverUsuario);
        getRoutes.put("/tienda/usuarios/crear/",
                (req, res) -> forwardToJsp("/WEB-INF/jsp/usuarios/crear-usuario.jsp", req, res));
        getRoutes.put("/tienda/usuarios/editar/",
                (req, res) -> forwardToJsp("/WEB-INF/jsp/usuarios/editar-usuario.jsp", req, res));
        getRoutes.put("/tienda/usuarios/login/",
                (req, res) -> forwardToJsp("/WEB-INF/jsp/login.jsp", req, res));

        // POST
        postRoutes.put("/tienda/usuarios/", this::crearUsuario);
        postRoutes.put("/tienda/usuarios/login/", this::login);
        postRoutes.put("/tienda/usuarios/logout/", this::logout);

        // PUT
        putRoutes.put("/tienda/usuarios/\\d+/?", this::actualizarUsuario);

        // DELETE
        deleteRoutes.put("/tienda/usuarios/borrar/", this::borrarUsuario);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        manejarRequest(req, res, getRoutes);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        String __method__ = req.getParameter("__method__");

        if (isNull(__method__))
            manejarRequest(req, res, postRoutes);

        if ("put".equals(__method__))
            doPut(req, res);

        if ("delete".equals(__method__))
            doDelete(req, res);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) {
        manejarRequest(req, res, putRoutes);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) {
        manejarRequest(req, res, deleteRoutes);
    }

    private void mostrarUsuarios(HttpServletRequest req, HttpServletResponse res) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        req.setAttribute("listaUsuarios", usuarioDAO.getAll());
        forwardToJsp("/WEB-INF/jsp/usuarios/usuarios.jsp", req, res);
    }

    private void crearUsuario(HttpServletRequest req, HttpServletResponse res) {

        String usuario = getCadenaODefault(req, "usuario");
        String password = getCadenaODefault(req, "password");
        String rol = getCadenaODefault(req, "rol");

        ResultadoDeCreacion<Usuario> logCreacion = Usuario.crearUsuario(usuario, password, rol);

        logCreacion
                .esCorrecto(u -> {
                    UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
                    usuarioDAO.create(u);
                    sendRedirectTo("/tienda/usuarios/", req, res);
                })
                .esIncorrecto(e -> {
                    req.setAttribute("errores", e);
                    forwardToJsp("/WEB-INF/jsp/usuarios/crear-usuario.jsp", req, res);
                });
    }

    private void devolverUsuario(HttpServletRequest req, HttpServletResponse res) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
        String pathInfo = req.getPathInfo();
        int userId = Integer.parseInt(pathInfo.substring(pathInfo.lastIndexOf("/") + 1));

        usuarioDAO.find(userId)
                .ifPresentOrElse((usuario -> {
                    req.setAttribute("usuario", usuario);
                    forwardToJsp("/WEB-INF/jsp/usuarios/editar-usuario.jsp", req, res);
                }), () -> {
                    sendRedirectTo("/tienda/usuarios/", req, res);
                });

    }

    private void login(HttpServletRequest req, HttpServletResponse res) {
        String usuario = getCadenaODefault(req, "usuario");
        String password = getCadenaODefault(req, "password");

        ResultadoDeCreacion<Usuario> logCreacionUsuario = Usuario.login(usuario, password);

        logCreacionUsuario
                .esCorrecto(u -> {
                    HttpSession session = req.getSession();
                    session.setAttribute("usuario", u);
                    sendToReferer(req, res);
                }).esIncorrecto(e -> {
                    req.setAttribute("errores", e);
                    forwardToJsp("/WEB-INF/jsp/login.jsp", req, res);
                });
    }

    private void logout(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        session.invalidate();
        sendToReferer(req, res);
    }

    private void borrarUsuario(HttpServletRequest req, HttpServletResponse res) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        int userId = getIntegerODefault(req, "codigo");

        usuarioDAO.find(userId)
                .ifPresent(usuario -> {
                    usuarioDAO.delete(usuario.getIdUsuario());
                });

        sendRedirectTo("/tienda/usuarios/", req, res);
    }

    private void actualizarUsuario(HttpServletRequest req, HttpServletResponse res) {

        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        int userId = getIntegerODefault(req, "codigo");
        String usuario = getCadenaODefault(req, "usuario");
        String password = getCadenaODefault(req, "password");
        String rol = getCadenaODefault(req, "rol");

        ResultadoDeValidacion resDeValidacion = Usuario.validar(usuario, password, rol);

        if (resDeValidacion.esValido()) {
            usuarioDAO.find(userId)
                    .ifPresent(usuarioEncontrado -> {
                        usuarioEncontrado.setUsuario(usuario);
                        usuarioEncontrado.setPassword(password);
                        usuarioEncontrado.setRol(rol);
                        usuarioDAO.update(usuarioEncontrado);
                    });
        }

        sendRedirectTo("/tienda/usuarios/", req, res);
    }

}