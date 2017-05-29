<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! Product product = null;
    int rooftopType, hasShed, roofAngle;
    String productID, name;
    double productPrice, innerHeight, width, length, shedLength, shedWidth, rooftopHeight, overallHeight;
%>
<%
    innerHeight = Double.parseDouble(request.getParameter("innerHeight"));
    rooftopType = 0;
    hasShed = 0;
    width = Double.parseDouble(request.getParameter("width"));
    length = Double.parseDouble(request.getParameter("length"));
    name = request.getParameter("name");
    productID = "0";
    
    
%>
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
                <h2 class="w3-lobster">Your custom carport details</h2>
                <p>Take a look at what you've chosen before you place your order.</p>
                <form class="modal-content animate">
                    <div class="imgcontainer">
                        <h1 class="w3-container w3-lobster">Measures</h1>
                    </div>

                    <!-- LOGIC TO SHOW THE RIGHT MODAL AND TAKE MEASURES, ACCORDING TO USER's DECISIONS FROM PREVIOUS SUBMITTED FORMS(PRECEDE 2 PAGES) -->                    
                    <div class="loginContainer">
                        <% if (request.getParameter("hasShed").equals("no")) { %>
                        <label><b>Inner Heigh</b></label>
                        <p><%=request.getParameter("innerHeight")%></p>
                        <!--NESTED IF-->
                        <% if (request.getParameter("roofType").equals("hipped")) {
                                rooftopType = 1;
                                rooftopHeight = Double.parseDouble(request.getParameter("rooftopHeight"));%>
                        <label><b>Rooftop Heigh</b></label>
                        <p><%=request.getParameter("rooftopHeight")%></p>
                        <% }%>
                        <label><b>Width</b></label>
                        <p><%=request.getParameter("width")%></p>
                        <label><b>Length</b></label>
                        <p><%=request.getParameter("length")%></p>
                        <% } else if (request.getParameter("hasShed").equals("yes")) {
                            rooftopType = 0;
                            hasShed = 1;
                            shedWidth = Double.parseDouble(request.getParameter("shedWidth"));
                            shedLength = Double.parseDouble(request.getParameter("shedLength"));
                        %>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>General: </h4>
                        </div>
                        <label><b>Inner Heigh</b></label>
                        <p><%=request.getParameter("innerHeight")%></p>
                        <!--NESTED IF-->
                        <% if (request.getParameter("roofType").equals("hipped")) {
                                rooftopType = 1;
                                rooftopHeight = Double.parseDouble(request.getParameter("rooftopHeight"));%>
                        <label><b>Rooftop Heigh</b></label>
                        <p><%=request.getParameter("rooftopHeight")%></p>
                        <% }%>
                        <label><b>Overall Width</b></label>
                        <p><%=request.getParameter("width")%></p>
                        <label><b>Overall Length</b></label>
                        <p><%=request.getParameter("length")%></p>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>Tools/Shed Room: </h4>
                            <p>(The Room's measures are included in the General Measures)</p>
                        </div>
                        <label><b>Shed Width</b></label>
                        <p><%=request.getParameter("shedWidth")%></p>
                        <label><b>Shed Length</b></label>
                        <p><%=request.getParameter("shedLength")%></p>
                        <% } else { %>
                        <h3>You need to input something, developer!!</h3>
                        <% }%>
                        <%
                            double adjacent = width / 2;
                            double opposite = rooftopHeight;

                            roofAngle = (int) Math.toDegrees(Math.atan((double) opposite / adjacent));

                            overallHeight = innerHeight + rooftopHeight;
                            productPrice = Product.calculatePrice(overallHeight, length, width, rooftopType, hasShed, shedLength);
                        %>
                        
                        <label><b>Your new baby's name:</b></label>
                        <p><%=request.getParameter("name")%></p>
                        <label><b>Your <span class="w3-lobster">Custom</span> Carport Price:</b></label>
                        <p><%= productPrice %></p>
                        <label><b>Delivery price:</b></label>
                        <p><%=request.getParameter("deliveryPrice")%></p>
                        <label><b>Total Price:</b></label>
                        <p>(including Carport and Delivery)</p>
                        <p><%= productPrice + Double.parseDouble(request.getParameter("deliveryPrice")) %></p>

                        <!--Shaping Product object-->
                        <%
                            product = new Product(productID, rooftopType, hasShed, roofAngle, productPrice, innerHeight, width, length, shedLength, shedWidth, rooftopHeight, name);
                            session.setAttribute("product", product);
                            session.setAttribute("productID", "0");
                            String deliveryPrice = (String)request.getParameter("deliveryPrice");
                            session.setAttribute("deliveryPrice", deliveryPrice);
                        %>
                        <button type="button" onclick="document.getElementById('cardAPI').style.display = 'block'">Place Order&nbsp;<span class="glyphicon glyphicon-thumbs-up"></span></button>
                    </div>
                    <!-- MEASURE LOGIC ENDS HERE -->
                    <div class="loginContainer" style="background-color:#f1f1f1">
                        <a href="customType.jsp" class="w3-button" style="background-color: red">Start from scratch</a>
                        <p>(Or you can click back several times on your browser)</p>
                    </div>
                    <div class="w3-light-grey">
                        <div class="w3-container w3-green w3-center" style="width:99%">Step 3.9/4</div>
                    </div>
                </form>
            </div>
        </div>

        <!-- FOOTER Suite-->
        <jsp:include page="footer.jsp" />

    </body>
</html>
