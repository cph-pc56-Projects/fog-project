<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Custom Measures</title>
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
                <h2 class="w3-lobster">Custom Carports</h2>
                <p>Here you can make your own carport, just input the measures that fits your needs.</p>
                <a class="btn btn-default" role="button" data-toggle="collapse" href="#collapseExample">
                    Hint how to take the right measures<br>(Click me and see the bottom)
                </a>
                <form class="modal-content animate" action="customFinalDetails.jsp" method="POST">
                    <div class="imgcontainer">
                        <h1 class="w3-container w3-lobster">Measures</h1>
                    </div>

                    <!-- LOGIC TO SHOW THE RIGHT MODAL AND TAKE MEASURES, ACCORDING TO USER's DECISIONS FROM PREVIOUS SUBMITTED FORMS(PRECEDE 2 PAGES) -->                    
                    <div class="loginContainer">
                        <% if (request.getParameter("hasShed").equals("no")) { %>
                        <label><b>Inner Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="innerHeight" title="e.g. 2.10" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <!--NESTED IF-->
                        <% if (request.getParameter("roofType").equals("hipped")) { %>
                        <label><b>Rooftop Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="rooftopHeight" title="e.g. 0.95" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <% } %>
                        <label><b>Width</b></label>
                        <input type="text" placeholder="Width in meters" name="width" title="e.g. 3.00" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Length</b></label>
                        <input type="text" placeholder="Length in meters" name="length" title="e.g. 4.80" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <% } else if (request.getParameter("hasShed").equals("yes")) { %>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>General: </h4>
                        </div>
                        <label><b>Inner Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="innerHeight" title="e.g. 2.10" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <!--NESTED IF-->
                        <% if (request.getParameter("roofType").equals("hipped")) { %>
                        <label><b>Rooftop Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="rooftopHeight" title="e.g. 0.95" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <% } %>
                        <label><b>Overall Width</b></label>
                        <input type="text" placeholder="Width in meters" name="width" title="e.g. 3.00" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Overall Length</b></label>
                        <input type="text" placeholder="Length in meters" name="length" title="e.g. 6.20" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>Tools/Shed Room: </h4>
                            <p>(The Room's measures are included in the General Measures)</p>
                        </div>
                        <label><b>Shed Width</b></label>
                        <input type="text" placeholder="Width in meters" name="shedWidth" title="e.g. 2.70" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Shed Length</b></label>
                        <input type="text" placeholder="Length in meters" name="shedLength" title="e.g. 1.50" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <% } else { %>
                        <h3>You need to input something, developer!!</h3>
                        <% }%>
                        <label><b>Give your baby a name:</b></label>
                        <input type="text" placeholder="Custom name" name="name" title="e.g. MyBeauty#1 (min. 3 and max. 40 chars)" maxlength="40" pattern="(?=.*[a-z])(?=.*[A-Z]).{3,}" required>
                        <label><b>Choose a region for the Delivery:</b></label>
                        <p>Sjælland - 250,00 pr. stk.</p>
                        <p>Fyn - 1.475,00 pr. stk.</p>
                        <p>Jylland - 1.875,00 pr. stk.</p>
                        <select class="form-control" id="sel1" name="deliveryPrice">
                            <option>250.00</option>
                            <option>1475.00</option>
                            <option>1875.00</option>
                        </select>
                        <br>
                        <input type="hidden" name="roofType" value="<%= request.getParameter("roofType")%>">
                        <input type="hidden" name="hasShed" value="<%= request.getParameter("hasShed")%>">
                        <button type="submit">Next step&nbsp;<span class="glyphicon glyphicon-chevron-right"></span></button>
                    </div>
                    <!-- MEASURE LOGIC ENDS HERE -->
                    <div class="loginContainer" style="background-color:#f1f1f1">
                        <button type="reset" style="background-color: red">Reset Form</button>
                    </div>
                    <div class="w3-light-grey">
                        <div class="w3-container w3-green w3-center" style="width:75%">Step 3/4</div>
                    </div>
                </form>
            </div>

        </div>
        <div class="collapse" id="collapseExample">
            <div class="well">
                <% if (request.getParameter("roofType").equals("flat") && request.getParameter("hasShed").equals("no")) { %>
                <img src="media/pictures/customCarports/FlatMeasures.png" style="width:100%;margin-bottom:-6px">
                <% } else if (request.getParameter("roofType").equals("flat") && request.getParameter("hasShed").equals("yes")) { %>
                <img src="media/pictures/customCarports/FlatMeasuresShed.png" style="width:100%;margin-bottom:-6px">
                <% } else if (request.getParameter("roofType").equals("hipped") && request.getParameter("hasShed").equals("no")) { %>
                <img src="media/pictures/customCarports/HippedMeasures.png" style="width:100%;margin-bottom:-6px">
                <% } else if (request.getParameter("roofType").equals("hipped") && request.getParameter("hasShed").equals("yes")) { %>
                <img src="media/pictures/customCarports/HippedMeasuresShed.png" style="width:100%;margin-bottom:-6px">
                <% }%>
            </div>
        </div>

        <!-- FOOTER Suite-->
        <jsp:include page="footer.jsp" />

        <!-- Checks if the input from the user is correct(only numbers from 0-9)-->
        <script language=Javascript>
            function isNumberKey(evt)
            {
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if (charCode != 46 && charCode > 31
                        && (charCode < 48 || charCode > 57))
                    return false;

                return true;
            }

        </script>
    </body>
</html>
