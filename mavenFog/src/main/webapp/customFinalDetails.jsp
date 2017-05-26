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
                <h2 class="w3-lobster">Your custom carport details</h2>
                <p>Take a look at what you've chosen before you place your order.</p>
                <form class="modal-content animate" action="customFinalDetails.jsp" method="post">
                    <div class="imgcontainer">
                        <h1 class="w3-container w3-lobster">Measures</h1>
                    </div>

                    <!-- LOGIC TO SHOW THE RIGHT MODAL AND TAKE MEASURES, ACCORDING TO USER's DECISIONS FROM PREVIOUS SUBMITTED FORMS(PRECEDE 2 PAGES) -->                    
                    <div class="loginContainer">
                        <% if (request.getParameter("hasShed").equals("no")) { %>
                        <label><b>Inner Heigh</b></label>
                        <p><%=request.getParameter("height")%></p>
                        <!--NESTED IF-->
                        <% if (request.getParameter("roofType").equals("hipped")) { %>
                        <label><b>Rooftop Heigh</b></label>
                        <p><%=request.getParameter("rooftopHeight")%></p>
                        <% } %>
                        <label><b>Width</b></label>
                        <p><%=request.getParameter("width")%></p>
                        <label><b>Length</b></label>
                        <p><%=request.getParameter("length")%></p>
                        <% } else if (request.getParameter("hasShed").equals("yes")) { %>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>General: </h4>
                        </div>
                        <label><b>Inner Heigh</b></label>
                        <p><%=request.getParameter("height")%></p>
                        <!--NESTED IF-->
                        <% if (request.getParameter("roofType").equals("hipped")) { %>
                        <label><b>Rooftop Heigh</b></label>
                        <p><%=request.getParameter("rooftopHeight")%></p>
                        <% } %>
                        <label><b>Overall Width</b></label>
                        <p><%=request.getParameter("width")%></p>
                        <label><b>Overall Length</b></label>
                        <p><%=request.getParameter("length")%></p>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>Tools/Shed Room: </h4>
                            <p>(The Room's measures are included in the General Measures)</p>
                        </div>
                        <label><b>Shed Width</b></label>
                        <p><%=request.getParameter("widthShed")%></p>
                        <label><b>Shed Length</b></label>
                        <p><%=request.getParameter("lengthShed")%></p>
                        <% } else { %>
                        <h3>You need to input something, developer!!</h3>
                        <% }%>
                        <label><b>Your new baby's name:</b></label>
                        <p><%=request.getParameter("customName")%></p>
                        <input type="hidden" name="roofType" value="<%= request.getParameter("roofType")%>">
                        <input type="hidden" name="hasShed" value="<%= request.getParameter("hasShed")%>">
                        <button type="submit" onclick="alert('You need to be logged in ! CALL THE DEVELOPER !')">Place Order&nbsp;<span class="glyphicon glyphicon-thumbs-up"></span></button>
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
