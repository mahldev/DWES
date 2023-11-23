package org.iesbelen.servlet;

import static org.iesbelen.util.HttpRequestUtils.*;

import org.iesbelen.model.Usuario;
import org.iesbelen.util.Enrutador;
import org.iesbelen.util.ResultadoDeCreacion;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.HttpMethod;

@WebServlet(name = "loginServlet", value = "/tienda/login/*")
public class LoginServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private Enrutador enrutador;

  @Override
  public void init() {
    enrutador = new Enrutador();

    enrutador.addRuta(
        HttpMethod.GET,
        "/tienda/login/",
        (req, res) -> forwardToJsp("/WEB-INF/jsp/login.jsp", req, res));

    enrutador.addRuta(HttpMethod.POST, "/tienda/login/", this::login);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) {
    enrutador.manejarRequest(req, res);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) {
    enrutador.manejarRequest(req, res);
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

}
