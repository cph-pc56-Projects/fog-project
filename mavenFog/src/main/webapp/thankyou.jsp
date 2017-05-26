<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Thank You</title>
    </head>
    <body>

        <!-- NAVBAR Suite START-->
        <jsp:include page="navbarSuite.jsp" />  
        <!-- NAVBAR Suite END-->

        <div class="w3-container w3-padding-64 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2>Thank you for your purchase!</h2>
                <p>One of our specialists will contact you in (?)days on this basis:</p>
                <ol class="w3-center" style="text-align: left;">
                    <li>Through email</li>
                    <li>On the phone that you have provided in your profile</li>
                </ol>
                <p>(Here's Why: You will speak about your new carport, on how to be delivered, if the bank transaction is verified, how your wife and kids are doing and other stuff.)</p>
                <p>Be ready and smile.</p>
                <a class="w3-button w3-green" href="index.jsp">Go to Home page.</a>  
            </div>
        </div>

        <!-- FOOTER Suite-->
        <jsp:include page="footer.jsp" />

    </body>
</html>
