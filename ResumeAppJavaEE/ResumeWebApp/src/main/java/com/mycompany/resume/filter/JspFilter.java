package com.mycompany.resume.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//gelen her hansi bir request-i not found olaraq goster
@WebFilter(filterName = "JspFileFilter",urlPatterns = {"*.jsp"})
public class JspFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain){
        HttpServletResponse res = (HttpServletResponse) response;
        try {
                res.sendRedirect("error?msg=not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
