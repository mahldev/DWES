package org.iesbelen.servlet;

import static org.iesbelen.util.HTTPRequestUtil.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "logoutServlet", value = "/tienda/logout/*")
public class LogoutServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  private Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> rutasPOST = new HashMap<>();

  @Override
  public void init() {
    // POST
    rutasPOST.put("/tienda/login/logout/", this::logout);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    manejarRequest(req, resp, rutasPOST);
  }

  private void logout(HttpServletRequest req, HttpServletResponse res) {
    HttpSession session = req.getSession();
    session.invalidate();
    sendToReferer(req, res);
  }
}
