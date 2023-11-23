package org.iesbelen.servlet;

import static org.iesbelen.util.HttpRequestUtils.*;

import java.io.IOException;

import org.iesbelen.util.Enrutador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.HttpMethod;

@WebServlet(name = "logoutServlet", value = "/tienda/logout/*")
public class LogoutServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  Enrutador enrutador = new Enrutador();

  @Override
  public void init() {
    enrutador.addRuta(HttpMethod.POST, "/tienda/logout/", this::logout);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    enrutador.manejarRequest(req, res);
  }

  private void logout(HttpServletRequest req, HttpServletResponse res) {
    HttpSession session = req.getSession();
    session.invalidate();
    sendToReferer(req, res);
  }
}
