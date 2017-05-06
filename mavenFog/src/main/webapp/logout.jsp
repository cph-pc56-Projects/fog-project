<%
session.setAttribute("username", null);
session.invalidate();
response.sendRedirect("index.jsp");
%>