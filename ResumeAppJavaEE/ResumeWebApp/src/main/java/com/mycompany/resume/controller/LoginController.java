package com.mycompany.resume.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.entity.User;
import com.mycompany.main.Context;
import com.mycompany.resume.util.ControllerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// bu bir servletdir login sehifesinden gelen sorguya uygun metod ishledir
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();
    private static BCrypt.Verifyer verifyer = BCrypt.verifyer();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            System.out.printf("password="+password);
            User user = userDao.findByEmail(email);

            if (user == null) {
                throw new IllegalArgumentException("User does not exist");
            }
            BCrypt.Result rs=verifyer.verify(password.toCharArray(),user.getPassword().toCharArray());
            if(!rs.verified){
                throw new IllegalArgumentException("Password is incorrect");
            }

            // session-u al ve loggedInUser atributuna user-i elave et. daha sonra sessionun acilib acilmamagini yoxlamaq ucun
            request.getSession().setAttribute("loggedInUser", user);

            // sehifeyi users.jsp-e yonelt
            response.sendRedirect("users");
        }catch (Exception ex){
            ControllerUtil.errorPage(response,ex);
        }

    }
}
