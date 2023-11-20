package org.iesbelen.servlet;

import static java.util.Objects.isNull;
import static org.iesbelen.util.HTTPRequestUtil.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;
import org.iesbelen.model.Usuario;
import org.iesbelen.util.ResultadoDeCreacion;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "usuarioServlet", value = "/tienda/usuarios/*")
public class UsuarioServlet extends HttpServlet {

    Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasGET = new HashMap<>();
    Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasPOST = new HashMap<>();
    Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasPUT = new HashMap<>();
    Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasDELETE = new HashMap<>();

    @Override
    public void init() {
        // GET
        rutasGET.put("/tienda/usuarios/", this::mostrarUsuarios);
        rutasGET.put("/tienda/usuarios/editar/\\d+/?", this::devolverUsuario);
        rutasGET.put("/tienda/usuarios/crear/",
                (req, res) -> forwardToJsp("/WEB-INF/jsp/usuarios/crear-usuario.jsp", req, res));
        rutasGET.put("/tienda/usuarios/editar/",
                (req, res) -> forwardToJsp("/WEB-INF/jsp/usuarios/editar-usuario.jsp", req, res));

        // POST
        rutasPOST.put("/tienda/usuarios/", this::crearUsuario);

        // PUT
        rutasPUT.put("/tienda/usuarios/\\d+/?", this::actualizarUsuario);

        // DELETE
        rutasDELETE.put("/tienda/usuarios/borrar/", this::borrarUsuario);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        manejarRequest(req, res, rutasGET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        String __method__ = req.getParameter("__method__");

        if (isNull(__method__))
            manejarRequest(req, res, rutasPOST);

        if ("put".equals(__method__))
            doPut(req, res);

        if ("delete".equals(__method__))
            doDelete(req, res);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) {
        manejarRequest(req, res, rutasPUT);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) {
        manejarRequest(req, res, rutasDELETE);
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

    private void borrarUsuario(HttpServletRequest req, HttpServletResponse res) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        int userId = getIntegerODefault(req, "codigo");

        usuarioDAO.find(userId)
                .ifPresent((usuario) -> {
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

        Usuario.validar(usuario, password, rol)
                .esCorrecto(() -> {
                    usuarioDAO.find(userId)
                            .ifPresent(usuarioEncontrado -> {
                                usuarioEncontrado.setUsuario(usuario);
                                usuarioEncontrado.setPassword(password);
                                usuarioEncontrado.setRol(rol);
                                usuarioDAO.update(usuarioEncontrado);
                                sendRedirectTo("/tienda/usuarios/", req, res);
                            });
                }).esIncorrecto((errors) -> {
                    try {
                        res.sendError(400, errors.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

}