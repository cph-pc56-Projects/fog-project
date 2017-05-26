<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home page</title>
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

        <div class="wide">
            <div class="container text-center pad">
                <h1 style="margin-top: 77px"><span class="box">My First Carport</span></h1>      
                <p><span class="box">Find your match at Johannes Fog</span></p>
            </div>
        </div>

        <!-- Custom port here -->
        <div class="w3-row w3-padding-16">
            <div class="w3-container w3-half">
                <h1 class="w3-center w3-lobster">Custom made carports</h1><br>
                <p>Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram, der tilpasses dine specifikke ønsker.
                    Tilbud og skitsetegning fremsendes med post hurtigst muligt.
                    Ved bestilling medfølger standardbyggevejledning.
                </p>
                <br>
                <!-- GO TO customType.jsp only after Login-->
                <%
                    //onclick the button will open a pop-up with Log in if there is no session was opened, else leads the user to customType.jsp
                    if (session.getAttribute("user") == null) { %>
                <a onclick="document.getElementById('id01').style.display = 'block'" href="#" class="w3-button w3-teal w3-block w3-xlarge">Build your very own carport</a>
                <% } else { %>
                <a href="customType.jsp" class="w3-button w3-teal w3-block w3-xlarge">Build your very own carport</a>
                <% }%>
                
            </div>
            <div class="w3-container w3-half w3-center">
                <img src="media/pictures/custom2.png" class="w3-padding-16" style="width:100%;max-width:500px">
            </div>
        </div>
        <!-- Custom ends here -->

        <!-- Product line -->
        <div class="w3-container w3-padding-32 w3-center" id="Contact">
            <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Our Product line</h3>
        </div>

        <div class="w3-row w3-container">
            <!--1st Product -->
            <div class="w3-half">
                <div class="w3-card-4 w3-margin " >
                    <div class="w3-display-container w3-text-white">
                        <img src="media/pictures/premadeCarports/CAR01/CAR01.png" alt="CAR01" style="width:100%">
                        <div class="w3-xlarge w3-display-bottomleft w3-padding"><h3><span class="w3-black box">CAR01 FLADT TAG</span></h3></div>
                    </div> 
                    <div class="w3-padding-16  w3-container">
                        <p class="w3-threequarter">3,00 x 4,80 mtr. Højde; 2,25 mtr. Trykimprægnerede stolper og stern. 
                            Leveres med: søm, skruer, beslag og plasttrapez tag m/topskruer.
                            NB! Leveres som Byg-selv sæt - usamlet og ubehandlet!</p>
                        <h3 class="w3-threequarter">Price : pr. stk. 3.495,-</h3>
                        <a href="carport1.jsp" class="w3-col w3-button w3-blue ">Check me out</a>
                    </div>                    
                </div>
            </div>
            <!--2nd Product -->
            <div class="w3-half">
                <div class="w3-card-4 w3-margin " >
                    <div class="w3-display-container w3-text-white">
                        <img src="media/pictures/premadeCarports/CAR01H/CAR01H.png" alt="CAR01HR" style="width:100%">
                        <div class="w3-xlarge w3-display-bottomleft w3-padding"><span class=" w3-black box">CAR01H HØJ REJSNING</span></div>
                    </div> 
                    <div class="w3-padding-16  w3-container">
                        <p class="w3-threequarter">3,60 x 5,40 mtr. 
                            Uden redskabsrum
                            Trykimprægnerede stolper & stern.
                            Leveres med: Søm, skruer, beslag og betontagstenstag.
                            Udførlig byggevejledning til carport og spær medfølger.
                            Betontagsten i sort med 30 års garanti.
                            NB! Leveres som Byg-selv sæt - usamlet og ubehandlet!</p>
                        <h3 class="w3-threequarter">Price : pr. stk. 12.995,-</h3>
                        <a href="carport2.jsp" class="w3-col w3-button w3-blue ">Check me out</a>
                    </div>                    
                </div>
            </div>
            <!--3rd Product -->
            <div class="w3-half">
                <div class="w3-card-4 w3-margin " >
                    <div class="w3-display-container w3-text-white">
                        <img src="media/pictures/premadeCarports/CAR01R/CAR01R.png" alt="Lights" style="width:100%">
                        <div class="w3-xlarge w3-display-bottomleft w3-padding"><span class=" w3-black box">CAR01R MED REDSKABSRUM</span></div>
                    </div> 
                    <div class="w3-padding-16  w3-container">
                        <p class="w3-threequarter">3,00 x 6,20 mtr. incl. 1,50 x 2,70 mtr. redskabsrum. Højde; 2,25 mtr.
                            Trykimprægnerede stolper, stern og beklædning.
                            Leveres med: søm, skruer, beslag og plasttrapez tag m/topskruer.
                            Udførlig byggevejledning medfølger.
                            NB! Leveres som Byg-selv sæt - usamlet og ubehandlet!</p>
                        <h3 class="w3-threequarter">Price : pr. stk. 7.995,-</h3>
                        <a href="carport3.jsp" class="w3-col w3-button w3-blue ">Check me out</a>
                    </div>                    
                </div>
            </div>
            <!--4th Product -->
            <div class="w3-half">
                <div class="w3-card-4 w3-margin " >
                    <div class="w3-display-container w3-text-white">
                        <img src="media/pictures/premadeCarports/CAR01HR/CAR01HR.png" alt="Lights" style="width:100%">
                        <div class="w3-xlarge w3-display-bottomleft w3-padding"><span class=" w3-black box">CAR01HR MED REDSKABSRUM </span></div>
                    </div> 
                    <div class="w3-padding-16  w3-container">
                        <p class="w3-threequarter">Enkelt carport med høj rejsning. 3,60 x 7,20 m. m/Byg-selv spær. Inkl. 3,20 x 2,25 m. redskabsrum. Højde; 3,05 mtr.
                            Trykimprægnerede stolper, stern og beklædning.
                            Leveres med: søm, skruer beslag og betontagstens tag.
                            Udførlig byggevejledning til carport og spær medfølger.
                            Betontagsten i sort med 30 års garanti.
                            NB! Leveres som Byg-selv sæt - usamlet og ubehandlet!
                            Varen kan ses udstillet i følgende afdelinger:
                            Værebro</p>
                        <h3 class="w3-threequarter">Price : pr. stk. 18.995,-</h3>
                        <a href="carport4.jsp" class="w3-col w3-button w3-blue ">Check me out</a>
                    </div>                    
                </div>
            </div>
        </div>
        <div class="w3-container">
            <p class="w3-border-bottom w3-border-light-grey w3-padding-16"></p>
        </div>
        <!-- /Product line -->

        <!-- FOOTER Suite-->
        <jsp:include page="footer.jsp" />

    </body>
</html>

