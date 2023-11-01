package org.iesbelen.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.entitys.Person;

import java.io.IOException;

@WebServlet(name = "person-servlet", value = "/person")
public class PersonServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        final var creationPersonResult = Person.createPersonResultFromRequest(req);

        if (creationPersonResult.getErrors().isValid()) {
            forwardToPage(req, res,
                    "./WEB-INF/showDataPerson.jsp",
                    "person",
                    creationPersonResult.getPerson());
            return;
        }

        forwardToPage(req, res,
                "./index.jsp",
                "creationResult",
                creationPersonResult);
    }

    private void forwardToPage(HttpServletRequest req, HttpServletResponse res,
                               String page,
                               String attributeName,
                               Object attributeValue)
            throws ServletException, IOException {
        req.setAttribute(attributeName, attributeValue);
        req.getRequestDispatcher(page).forward(req, res);
    }
}
