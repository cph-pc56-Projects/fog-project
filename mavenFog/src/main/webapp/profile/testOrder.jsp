<%-- 
    Document   : testOrder
    Created on : May 15, 2017, 8:20:49 PM
    Author     : trez__000
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%ArrayList<Order> orders = null; %>
<%orders = (ArrayList<Order>) session.getAttribute("orders");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
        <h1>Displaying order!</h1>
        
        <p>Product PRice:: <%orders.get(0).getPrice(); %></p>
        
    </body>
</html>
