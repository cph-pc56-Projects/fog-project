<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%User user = null;%>
<%user = (User) session.getAttribute("user");%>
<html>
    <head>
        <title>CAR01 FLADT TAG</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <style>
            body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
            .mySlides {display:none}
        </style>
        <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">

        </script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body class="w3-content w3-border-left w3-border-right">


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

        <!-- Credit Card -->
            <div id="cardAPI" class="modal">
                <form class="modal-content animate" action="Carport">
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('cardAPI').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                        <h1 class="w3-container ">Credit Card Details</h1>
                    </div>

                    <div class="loginContainer">
                        <label><b>Cardholder's name:</b></label>
                        <input type="text" placeholder="the same as the card" name="cardname" required>

                        <label><b>Credit Card No. :</b></label>
                        <input type="password" placeholder="Enter all numericals" name="cardnumber" required>

                        <label><b>Valid until:</b></label>
                        <input type="password" placeholder="Month / Year" name="carddate" required>

                        <label><b>CCV No. :</b></label>
                        <input type="password" placeholder="3 numbers on the back" name="cardccv" required>
                        
                        <input type="hidden" name="price" value="3495">
                        <button type="submit">Buy</button>
                        <button type="button" onclick="document.getElementById('cardAPI').style.display = 'none'" class="cancelbtn">Cancel</button>
                    </div>
                </form>
            </div> <!-- CreditCard END HERE -->
        
        <script>
            // Get the modal
            var modal = document.getElementById('id01');
            var modal2 = document.getElementById('id02');
            var modal3 = document.getElementById('cardAPI');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target === modal || event.target === modal2 || event.target === modal3) {
                    modal.style.display = "none";
                    modal2.style.display = "none";
                    modal3.style.display = "none";
                }
            }
        </script>

        <div style="margin-top: 50px;"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main w3-white w3-padding-16 w3-card-2">
            <h1 class="w3-center">CAR01 FLADT TAG</h1>   

            <!-- Slideshow Header -->
            <div class="w3-container w3-content" id="apartment">

                <div class="w3-display-container mySlides">
                    <img src="media/pictures/premadeCarports/CAR01/CAR01.png" style="width:100%;margin-bottom:-6px">
                    <div class="w3-display-bottomleft w3-container w3-black">
                        <p>Real Picture</p>
                    </div>
                </div>
                <div class="w3-display-container mySlides">
                    <img src="media/pictures/premadeCarports/CAR01/CAR01Sheme1.png" style="width:100%;margin-bottom:-6px">
                    <div class="w3-display-bottomleft w3-container w3-black">
                        <p>Scheme</p>
                    </div>
                </div>
                <div class="w3-display-container mySlides">
                    <img src="media/pictures/premadeCarports/CAR01/CAR01Sheme2.png" style="width:100%;margin-bottom:-6px">
                    <div class="w3-display-bottomleft w3-container w3-black">
                        <p>Scheme</p>
                    </div>
                </div>
                <div class="w3-display-container mySlides">
                    <img src="media/pictures/premadeCarports/CAR01/CAR01Sheme3.png" style="width:100%;margin-bottom:-6px">
                    <div class="w3-display-bottomleft w3-container w3-black">
                        <p>Scheme</p>
                    </div>
                </div>
            </div>
            <div class="w3-row-padding w3-section">
                <div class="w3-col s3">
                    <img class="demo w3-opacity w3-hover-opacity-off" src="media/pictures/premadeCarports/CAR01/CAR01.png" style="width:100%;cursor:pointer" onclick="currentDiv(1)" title="Real picture">
                </div>
                <div class="w3-col s3">
                    <img class="demo w3-opacity w3-hover-opacity-off" src="media/pictures/premadeCarports/CAR01/CAR01Sheme1.png" style="width:100%;cursor:pointer" onclick="currentDiv(2)" title="Scheme1">
                </div>
                <div class="w3-col s3">
                    <img class="demo w3-opacity w3-hover-opacity-off" src="media/pictures/premadeCarports/CAR01/CAR01Sheme2.png" style="width:100%;cursor:pointer" onclick="currentDiv(3)" title="Scheme2">
                </div>
                <div class="w3-col s3">
                    <img class="demo w3-opacity w3-hover-opacity-off" src="media/pictures/premadeCarports/CAR01/CAR01Sheme3.png" style="width:100%;cursor:pointer" onclick="currentDiv(4)" title="Scheme3">
                </div>
            </div>

            <div class="w3-container">
                <h4><strong>Description</strong></h4>
                <div class="w3-row w3-large">
                    <div class="w3-col s12">
                        <p>3,00 x 4,80 mtr. Højde; 2,25 mtr. Trykimprægnerede stolper og stern. 
                            Leveres med: søm, skruer, beslag og plasttrapez tag m/topskruer.
                            NB! Leveres som Byg-selv sæt - usamlet og ubehandlet!</p>
                    </div>
                </div>
                <hr>

                <h4><strong>Price :</strong></h4>
                <div class="w3-row w3-large">
                    <div class="w3-col s6">
                        <p>Negotiable pr. stk. 3.495,-</p>
                    </div>
                </div>
                <hr>

                <h4><strong>Extra Info</strong></h4>
                <p><i class="fa fa-check w3-large"></i> 14 dages returret</p>
                <p><i class="fa fa-check w3-large"></i> Sikker betaling</p>
                <p>We accept: <i class="fa fa-credit-card w3-large"></i> <i class="fa fa-cc-mastercard w3-large"></i> <i class="fa fa-cc-amex w3-large"></i> <i class="fa fa-cc-cc-visa w3-large"></i><i class="fa fa-cc-paypal w3-large"></i></p>
                <ul class="nav nav-tabs">
                    <li role="presentation" class="active"><a href="#shiping" role="tab" data-toggle="tab">Yderligere leveringsinformation&nbsp;</a></li>
                    <li role="presentation" class=""><a href="#dokument" role="tab" data-toggle="tab">Dokumenter&nbsp;</a></li>
                </ul>
                <!-- TABS CONTENT -->
                <div class="tab-content">
                    <div class="tab-pane fade active in" role="tabpanel" id="shiping">
                        <h4>Leveringstype: Særlige leveringsbetingelser</h4>  
                        <h1>Fragtvilkår for denne vare</h1>  
                        <div class="w3-padding-16 w3-margin-32">
                            <table class="w3-table w3-bordered w3-padding" cellpadding="5">
                                <tr>
                                    <td>Levering - Sjælland:</td>
                                    <td>250,00 pr. stk.</td>
                                </tr>
                                <tr>
                                    <td>Levering - Fyn:</td>
                                    <td>1.475,00 pr. stk.</td>
                                </tr>
                                <tr>
                                    <td>Levering - Jylland:</td>
                                    <td>1.875,00 pr. stk.</td>
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

                <h4><strong>Buy this beauty</strong></h4>
                <p>When you click the "Buy" button that means you accept our Terms&Conditions.</p>
                <p>You will be lead through the process by our system. Happy buying.</p>

                <p><button class="w3-button w3-green w3-third" onclick=
                           <%
                               //onclick the button will open a pop-up with Log in if there is no session was opened, or Credit Details modal if the user already was logged in
                               if (user == null) {
                                   String buyLink = "document.getElementById('id01').style.display = 'block'";
                                   session.setAttribute("buyLink", buyLink);
                               } else {
                                   String buyLink = "document.getElementById('cardAPI').style.display = 'block'";
                                   session.setAttribute("buyLink", buyLink);
                               }
                           %>
                           "<%= session.getAttribute("buyLink")%>">Buy</button></p>
            </div>
        </div><!-- End page content -->

        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-white w3-xlarge">          
            <h3>Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</h3>
        </footer> 

        <script>// Slideshow/Switch Carport Images
            var slideIndex = 1;
            showDivs(slideIndex);

            function plusDivs(n) {
                showDivs(slideIndex += n);
            }

            function currentDiv(n) {
                showDivs(slideIndex = n);
            }

            function showDivs(n) {
                var i;
                var x = document.getElementsByClassName("mySlides");
                var dots = document.getElementsByClassName("demo");
                if (n > x.length) {
                    slideIndex = 1
                }
                if (n < 1) {
                    slideIndex = x.length
                }
                for (i = 0; i < x.length; i++) {
                    x[i].style.display = "none";
                }
                for (i = 0; i < dots.length; i++) {
                    dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
                }
                x[slideIndex - 1].style.display = "block";
                dots[slideIndex - 1].className += " w3-opacity-off";
            }
        </script>

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

