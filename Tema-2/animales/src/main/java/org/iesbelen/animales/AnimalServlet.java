package org.iesbelen.animales;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

@WebServlet(name = "animal-servlet", value = "/animales")
public class AnimalServlet extends HttpServlet {

    private List<String> animales;

    public void init() {
        animales = List.of(
                "ballena",
                "caballito-mar",
                "camello",
                "cebra",
                "elefante",
                "hipopotamo",
                "jirafa",
                "leon",
                "leopardo",
                "medusa",
                "mono",
                "oso",
                "oso-blanco",
                "pajaro",
                "pinguino",
                "rinoceronte",
                "serpiente",
                "tigre",
                "tortuga",
                "tortuga-marina"
        );


    }

    private String formattedAnimalName(String a) {
        final String firstLetterMayus = String.valueOf(a.charAt(0)).toUpperCase();
        final String cleanAnimalName = a.replace("-", " ")
                .substring(1);

        return firstLetterMayus.concat(cleanAnimalName);
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int random = (int) (Math.random() * animales.size());
        String animal = animales.get(random);

        req.setAttribute("animal-img", animal);
        req.setAttribute("animal-name", formattedAnimalName(animal));
        req.getRequestDispatcher("./WEB-INF/animales.jsp").forward(req, resp);
    }
}
