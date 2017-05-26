<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home page</title>
        <style>
            .cc-selector input{
                margin:0;padding:0;
                -webkit-appearance:none;
                -moz-appearance:none;
                appearance:none;
            }
            .flatRoof{background-image:url(media/pictures/customCarports/flat2.png);}
            .hippedRoof{background-image:url(media/pictures/customCarports/hipped2.png);}

            .cc-selector input:active +.drinkcard-cc{opacity: .9;}
            .cc-selector input:checked +.drinkcard-cc{
                -webkit-filter: none;
                -moz-filter: none;
                filter: none;
            }
            .drinkcard-cc{
                cursor:pointer;
                background-size:contain;
                background-repeat:no-repeat;
                display:inline-block;
                width:400px;height:350px;
                -webkit-transition: all 100ms ease-in;
                -moz-transition: all 100ms ease-in;
                transition: all 100ms ease-in;
                -webkit-filter: brightness(1.8) grayscale(1) opacity(.7);
                -moz-filter: brightness(1.8) grayscale(1) opacity(.7);
                filter: brightness(1.8) grayscale(1) opacity(.7);
            }
            .drinkcard-cc:hover{
                -webkit-filter: brightness(1.2) grayscale(.5) opacity(.9);
                -moz-filter: brightness(1.2) grayscale(.5) opacity(.9);
                filter: brightness(1.2) grayscale(.5) opacity(.9);
            }

            /* Extras */
            a:visited{color:#888}
            a{color:#444;text-decoration:none;}
            p{margin-bottom:.3em;}
            
            /* Custom font */
            .w3-lobster {
                font-family: 'Lobster', cursive;
            }
        </style>
    </head>
    <body>
        
        <!-- NAVBAR Suite START-->
        <jsp:include page="navbarSuite.jsp" />  
        <!-- NAVBAR Suite END-->

        <div class="w3-container w3-padding-64 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2 class="w3-lobster">Choose type for your carport :</h2><br>
                <form action="customShed.jsp" method="get">
                    <div class="cc-selector">
                        <input id="flatRoof" type="radio" name="roofType" value="flat" required>
                        <label class="drinkcard-cc flatRoof" for="flatRoof"></label>
                        <input id="hippedRoof" type="radio" name="roofType" value="hipped" required>
                        <label class="drinkcard-cc hippedRoof" for="hippedRoof"></label>
                    </div>
                    <button type="submit">Next step&nbsp;<span class="glyphicon glyphicon-chevron-right"></span></button>
                    <br><br>
                    <div class="w3-light-gray">
                        <div class="w3-container w3-green w3-center" style="width:25%">Step 1/4</div>
                    </div>
                </form>
            </div>
        </div>

        <!-- FOOTER Suite-->
        <jsp:include page="footer.jsp" />

    </body>
</html>
