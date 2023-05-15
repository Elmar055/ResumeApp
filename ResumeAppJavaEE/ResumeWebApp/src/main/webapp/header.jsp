<%@ page import="com.mycompany.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: GASIMOV
  Date: 4/19/2023
  Time: 1:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User user = (User) session.getAttribute("loggedInUser");
%>
<%="Welcome, "+user.getName() %>
