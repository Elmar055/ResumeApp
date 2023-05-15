
package com.mycompany.resume.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// bu bir servletdir ve users.jsp-i users-e yoneldir
@WebServlet(name = "UserController", urlPatterns = {"/users"})
public class UserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            // request ve response-dan request dispatcher alir ve forward user.jsp sehifesine yoneldir
            request.getRequestDispatcher("users.jsp").forward(request, response);

    }


}
