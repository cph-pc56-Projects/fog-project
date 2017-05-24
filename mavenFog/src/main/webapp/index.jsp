<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%User user = null;%>
<%user = (User) session.getAttribute("user");%>
<html>
    <head>
        <title>Home page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet"> <!-- Custom FONT -->
        <style>
            .w3-lobster {
                font-family: 'Lobster', cursive;
            }
        </style>
        <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">

        </script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>      
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="index.jsp">FOG</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">                        
                        <li><a href="beforeyoubuy.jsp">Before you buy</a></li>
                    </ul>      
                    <ul class="nav navbar-nav navbar-right">
                        <% if (user != null) {%>
                        <!-- HERE WHEN LOGGED IN DIV -->
                        <li>
                            <a href="#" id="dropdownMenu1" data-toggle="dropdown"><%=user.getEmail()%>&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a href="profile/profile.jsp">Profile page</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li class="divider"></li>
                                <li><a id="logoutFunction" href="#">Log out</a></li>
                            </ul>
                        </li>
                        <% } else {%>
                        <!-- ELSE THE COMMON ONE WITH LOGIN -->
                        <li><a href="#" onclick="document.getElementById('id01').style.display = 'block'">Login</a></li>
                            <% } %>
                        <li><a href="support.jsp">Support</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!--Login form -->
        <div id="id01" class="modal">
            <form class="modal-content animate" action="Login" method = "POST">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id01').style.display = 'none'" class="close"  title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Log In</h1>
                </div>
                <% if ("login".equals(session.getAttribute("error"))) {
                                      session.invalidate(); %>
                <script>
                    // Get the modal
                    var modal = document.getElementById('id01');
                    // Make modal Login to be visible if the first login attempt was failed
                    modal.style.display = 'block';

                    // When the user clicks anywhere outside of the modal, close it
                    window.onclick = function (event) {
                        if (event.target == modal) {
                            modal.style.display = 'block';
                        }
                    }
                </script>
                <div class="imgcontainer alert alert-danger">
                    <strong> Wrong Log In details </strong>
                </div>
                <%}%>


                <div class="loginContainer">
                    <label><b>Username</b></label>
                    <input type="text" placeholder="Enter Email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="customer@fog.dk" id="emailLog" required>

                    <label><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="password" title="at least 8 characters" pattern=".{8,}" id="passwordLog" required>
                    <!-- current URL passed like a hidden field, so after Log in, the Servlet will redirect the user back here -->
                    <input type="hidden" name="from" value="${pageContext.request.requestURI}">

                    <button type="submit" >Login</button>
                    <input type="checkbox" checked="checked"> Remember me
                    <button type="button" onclick="document.getElementById('id02').style.display = 'block'" class="w3-button w3-black">Register</button>
                </div>

                <div class="loginContainer" style="background-color:#f1f1f1">
                    <button type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancelbtn">Cancel</button>
                    <span class="psw"><a href="#">Forgot password?</a></span>
                </div>
            </form>
        </div><!-- Login END -->

        <!--Register form START-->
        <div id="id02" class="modal" style="overflow-y: scroll">
            <form class="modal-content animate" action="Register" method="post">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id02').style.display = 'none'" class="close"  title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Register</h1>
                </div>
                <script>
                    // Get the modal
                    var modal2 = document.getElementById('id02');

                    // When the user clicks anywhere outside of the modal, close it
                    window.onclick = function (event) {
                        if (event.target == modal2) {
                            modal2.style.display = 'block';
                        }
                    }
                </script>


                <div class="loginContainer">
                    <label><b>Email</b></label>
                    <input type="text" placeholder="customer@fog.dk" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="customer@fog.dk" required>
                    <label><b>Password</b></label>
                    <input type="password" placeholder="Best min. 8 characters" name="password" title="at least 8 characters" pattern=".{8,}" id="passwordReg" required>
                    <label><b>Re-type Password</b></label>
                    <input type="password" placeholder="Just to check ;)" name="repassword" title="type the same password" pattern=".{8,}" id="repasswordReg" required>
                    <div><p id="pCheckPassword" style="color: red"></p></div>
                    <label><b>First name</b></label>
                    <input type="text" placeholder="Johannes" name="fName" required>
                    <label><b>Last name</b></label>
                    <input type="text" placeholder="Fog" name="lName" required>
                    <label><b>Phone number</b></label>
                    <input type="text" placeholder="e.g. Mobile min. 8 numericals" name="phone" title="e.g. 45871001 (8 numericals)" pattern="[0-9]{8,8}" required>
                    <label><b>Address</b></label>
                    <input type="text" placeholder="Firskovvej 20" name="adress" title="e.g. Street" required>
                    <label><b>Zip code</b></label>
                    <input type="text" placeholder="Local post code" name="zipCode" title="e.g. 2800" pattern="[0-9]{4}" required>

                    <button type="submit" class="btn" id="RegButton">Register</button>
                    <button type="button" onclick="document.getElementById('id02').style.display = 'none'" class="cancelbtn">Close</button>
                </div>
            </form>
        </div><!-- Register END -->

        <!--Logout modal -->
        <div id="logout" class="modal">
            <form class="modal-content animate">
                <div class="imgcontainer">

                    <h1 class="w3-container ">You are logged out!</h1>
                    <p class="w3-container ">(You will be redirected after 3 seconds...)</p>
                </div>
            </form>
        </div><!-- Logout END -->

        <script>
            // Get the modal
            var modal = document.getElementById('id01');
            var modal2 = document.getElementById('id02');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal || event.target == modal2) {
                    modal.style.display = "none";
                    modal2.style.display = "none";
                }
            }
        </script>
        <!-- NAVBAR Suite ENDS-->

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
                <a href="customType.jsp" class="w3-button w3-teal w3-block w3-xlarge">Build your very own carport</a>
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

        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-white w3-xlarge">          
            <h3>Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</h3>
        </footer> 

        <!-- Prevent "space" button script -->
        <script>
            $(function () {
                $('#emailLog').on('keypress', function (e) {
                    if (e.which == 32)
                        return false;
                });
                $('#passwordLog').on('keypress', function (e) {
                    if (e.which == 32)
                        return false;
                });
                $('#passwordReg').on('keypress', function (e) {
                    if (e.which == 32)
                        return false;
                });
                $('#repasswordReg').on('keypress', function (e) {
                    if (e.which == 32)
                        return false;
                });
            });
        </script>

        <!-- Checks the Re-type of password -->
        <script>
            $(function () {

                $('#repasswordReg').on('keyup', function () {
                    var password = $("#passwordReg").val();
                    var confirmPassword = $("#repasswordReg").val();

                    if (password != confirmPassword) {
                        $("#pCheckPassword").html("Passwords do not match!");
                        $('#RegButton').prop('disabled', true);
                    } else {
                        $("#pCheckPassword").html("Passwords match.");
                        $('#RegButton').prop('disabled', false);

                    }
                });
            });
        </script>

        <!-- Calls logout on button click -->
        <script>
            $('#logoutFunction').click(function ()
            {
                setTimeout(function () {
                    document.getElementById('logout').style.display = 'block';
                }, 800);
                var delay = 3000;
                setTimeout(function () {
                    window.location = 'logout.jsp';
                }, delay);
                return false;
            });

        </script>
    </body>
</html>
