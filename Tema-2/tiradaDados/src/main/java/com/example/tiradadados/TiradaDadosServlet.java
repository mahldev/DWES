package com.example.tiradadados;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/tirada")
public class TiradaDadosServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Integer numeroDeDados = (int) (Math.random() * 6) + 2;
        List<Integer> dados = IntStream.range(0, numeroDeDados)
                .mapToObj(i -> (int) (Math.random() * 6 + 1))
                .toList();

        request.setAttribute("listaDados", dados);
        request.setAttribute("listaDadosOrdenada", dados.stream().sorted().toList());
        request.setAttribute("numeroDados", numeroDeDados);

        try {
            request.getRequestDispatcher("./WEB-INF/tiradaDados.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
    }
}