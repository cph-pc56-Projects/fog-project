<%-- 
    Document   : testjsp
    Created on : 05-May-2017, 11:02:46
    Author     : Alex
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% User user = null; %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>KYP</h1>
        
        <h1><%= user = (User) session.getAttribute("user") %></h1>
        <h2><%= user.getEmail() %></h2>
        <h2><%= user.getfName() %></h2>
        <h2><%= user.getlName() %></h2>
        <h2><%= user.getPhone() %></h2>
        <h2><%= user.getAdress() %></h2>
        <h2><%= user.getZipCode() %></h2>
        <h2><%= user.getRole() %></h2>
        
    </body>
</html>
