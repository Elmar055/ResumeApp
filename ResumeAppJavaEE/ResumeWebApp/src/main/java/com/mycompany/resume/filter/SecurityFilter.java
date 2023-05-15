package com.mycompany.resume.filter;

import com.mycompany.resume.util.ControllerUtil;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebFilter(filterName = "JspFileFilter", urlPatterns = {"*"})
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse res = (HttpServletResponse) response;

            // eger url login sehifesinde deyilse ve sessionda loggedInUser atribut nulldursa
            // loggedInUser LoginControllerin doPost metdounda set olunur
            if (!req.getRequestURI().contains("/login") && req.getSession().getAttribute("loggedInUser") == null) {
                // login sehifesine yonelt
                res.sendRedirect("login");
            } else {
                // eks halda isteyin hedefe catmasina icaze ver
                chain.doFilter(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
