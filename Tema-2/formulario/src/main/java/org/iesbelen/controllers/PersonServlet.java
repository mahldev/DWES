package org.iesbelen.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesbelen.entitys.Person;

import java.io.IOException;
import java.util.Map;

@WebServlet(name = "person-servlet", value = "/person")
public class PersonServlet extends HttpServlet {

    private Map<String, String> validatePerson(String name,
                                         String surnnames,
                                         Integer age,
                                         Integer weight,
                                         String gender,
                                         String maritalStatus,
                                         String[] hobbies) {


    }
    private Person createAPersonfromRequest(HttpServletRequest req) {
        final String name = req.getParameter("name");
        final String surnames = req.getParameter("surnames");
        final Integer age = Integer.valueOf(req.getParameter("age"));
        final Integer weight = Integer.valueOf(req.getParameter("weight"));
        final String gender = req.getParameter("gender");
        final String maritalStatus = req.getParameter("marital");
        final String[] hobbies = req.getParameterValues("hobbies");


        return new Person(name, surnames, age, weight, gender, maritalStatus, hobbies);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setAttribute("person", createAPersonfromRequest(req));
        req.getRequestDispatcher("./WEB-INF/showDataPerson.jsp").forward(req, res);
    }
}
