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
                <form action="regularFinalDetails.jsp" method="GET">
                    <!-- TABS CONTENT -->
                    <div class="tab-content">
                        <div class="tab-pane fade active in" role="tabpanel" id="shiping">
                            <h4>Choose a Delivery Region:</h4>
                            <div class="w3-padding-16 w3-margin-32">
                                <table class="w3-table w3-bordered w3-padding" cellpadding="5">
                                    <tr>
                                        <td>Levering - Sjælland:</td>
                                        <td>
                                            <div class="col-lg-6">
                                                <div class="checkbox">
                                                    <label><input type="radio" name="deliveryPrice" value="250.00" required>&nbsp;&nbsp;250,00 pr. stk.</label>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Levering - Fyn:</td>
                                        <td>
                                            <div class="col-lg-6">
                                                <div class="checkbox">
                                                    <label><input type="radio" name="deliveryPrice" value="1475.00" required>&nbsp;&nbsp;1.475,00 pr. stk.</label>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Levering - Jylland:</td>
                                        <td>
                                            <div class="col-lg-6">
                                                <div class="checkbox">
                                                    <label><input type="radio" name="deliveryPrice" value="1875.00" required>&nbsp;&nbsp;1.875,00 pr. stk.</label>
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Særlige leveringsdage:</td>
                                        <td>Fredag</td>
                                    </tr>
                                </table>
                                <img src="media/pictures/levering900_b2c.png">
                            </div>
                        </div>

                        <div class="tab-pane fade" role="tabpanel" id="dokument">
                            <h1><a href="https://www.johannesfog.dk/globalassets/inriver/resources/9116194_car01-2016.pdf" target="_blank">CAR01 Tegninger (PDF)</a></h1>
                        </div>
                    </div>
                    <!-- TABS CONTENT END-->

                    <hr>
                    <p><button type="submit" class="w3-button w3-green w3-third">Place Order&nbsp;<span class="glyphicon glyphicon-thumbs-up"></span></button></p>
                </form>
            </div>
        </div>

        <!-- FOOTER Suite-->
        <jsp:include page="footer.jsp" />

    </body>
</html>
