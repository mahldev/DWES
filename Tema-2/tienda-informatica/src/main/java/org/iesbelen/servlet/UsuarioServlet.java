package org.iesbelen.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.dao.UsuarioDAO;
import org.iesbelen.dao.UsuarioDAOImpl;

import java.io.IOException;

@WebServlet(name = "usuarioServlet", value = "/tienda/usuarios/*")
public class UsuarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        req.setAttribute("listaUsuarios", usuarioDAO.getAll());
        req.getRequestDispatcher("/WEB-INF/jsp/usuarios/usuarios.jsp")
                .forward(req, res);
    }
}
