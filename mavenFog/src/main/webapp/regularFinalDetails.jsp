<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Place Order</title>
        <style>
            .w3-lobster {
                font-family: 'Lobster', cursive;
            }
        </style>
    </head>
    <body>
        <!-- NAVBAR Suite START-->
        <jsp:include page="navbarSuite.jsp" />  
        <!-- NAVBAR Suite END-->

        <div class="w3-container w3-padding-32 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2 class="w3-lobster">Your new carport details</h2>
                <p>Take a look at what you've chosen before you place your order.</p>
                <form class="modal-content animate">
                    <div class="imgcontainer">
                        <h1 class="w3-container w3-lobster">Details:</h1>
                    </div>

                    <!-- LOGIC TO SHOW THE RIGHT MODAL AND TAKE MEASURES, ACCORDING TO USER's DECISIONS FROM PREVIOUS SUBMITTED FORMS(PRECEDE 2 PAGES) -->                    
                    <div class="loginContainer">
                        
                        <label><b>Your Carport Price:</b></label>
                        <p><%= session.getAttribute("productPrice") %></p>
                        <label><b>Delivery price:</b></label>
                        <p><%=request.getParameter("deliveryPrice")%></p>
                        <label><b>Total Price:</b></label>
                        <p><%= Double.parseDouble(request.getParameter("deliveryPrice")) + Double.parseDouble((String)session.getAttribute("productPrice"))%></p>
                        <br>
                        <% session.setAttribute("deliveryPrice", request.getParameter("deliveryPrice")); %>
                        <button type="button" onclick="document.getElementById('cardAPI').style.display = 'block'">Place Order&nbsp;<span class="glyphicon glyphicon-thumbs-up"></span></button>
                    </div>
                </form>
            </div>
        </div>

        <!-- FOOTER Suite-->
        <jsp:include page="footer.jsp" />

    </body>
</html>
