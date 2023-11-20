package org.iesbelen.servlet;

import static org.iesbelen.util.HTTPRequestUtil.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.iesbelen.model.Usuario;
import org.iesbelen.util.ResultadoDeCreacion;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "loginServlet", value = "/tienda/login/*")
public class LoginServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasGET = new HashMap<>();
  private Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasPOST = new HashMap<>();

  @Override
  public void init() {
    // GET
    rutasGET.put("/tienda/login/login/",
        (req, res) -> forwardToJsp("/WEB-INF/jsp/login.jsp", req, res));
    // POST
    rutasPOST.put("/tienda/login/login/", this::login);
    rutasPOST.put("/tienda/login/logout/", this::logout);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) {
    manejarRequest(req, res, rutasGET);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) {
    manejarRequest(req, res, rutasPOST);
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
}
