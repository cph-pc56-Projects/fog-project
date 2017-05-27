
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String shaLi = request.getParameter("Invoice"); %>
<% String deleteOrder = request.getParameter("deleteOrder"); %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        
        <h1>Delete Order:</h1>
        <h2>Are you sure? : <% if (deleteOrder.equals("yes")) {
        out.print("Yes");
        }%></h2>
        
    </body>
</html>
