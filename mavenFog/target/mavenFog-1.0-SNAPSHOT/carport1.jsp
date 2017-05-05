<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <title>Carport1</title>
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
    <body class="w3-content w3-border-left w3-border-right">


        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="index.jsp">HOME</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">                        
                        <li><a href="beforeyoubuy.jsp">Before you buy</a></li>
                    </ul>      
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#" onclick="document.getElementById('id01').style.display = 'block'">Login</a></li>
                        <li><a href="support.jsp" >Support</a></li>
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
                <% if ("login".equals(request.getAttribute("error"))) {%>
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
                    <input type="text" placeholder="Enter Email" name="email" required>

                    <label><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="password" required>

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
                    <input type="text" placeholder="Email" name="email" required>
                    <label><b>Password</b></label>
                    <input type="password" placeholder="Password" name="password" required>
                    <label><b>Re-type Password</b></label>
                    <input type="password" placeholder="Re-type Password" name="repassword" required>
                    <label><b>First name</b></label>
                    <input type="text" placeholder="First name" name="fName" required>
                    <label><b>Last name</b></label>
                    <input type="text" placeholder="Second name" name="lName" required>
                    <label><b>Phone number</b></label>
                    <input type="text" placeholder="Mobile,Fax,Landline, etc." name="phone" required>
                    <label><b>Address</b></label>
                    <input type="text" placeholder="Street Address" name="adress" required>
                    <label><b>Zip code</b></label>
                    <input type="text" placeholder="Local post code" name="zipCode" required>

                    <button type="submit">Register</button>
                    <button type="button" onclick="document.getElementById('id02').style.display = 'none'" class="cancelbtn">Close</button>
                </div>
            </form>
        </div><!-- Register END -->

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


        <!-- Top menu on small screens -->
        <header class="w3-bar w3-top w3-hide-large w3-black w3-xlarge">
            <span class="w3-bar-item">Rental</span>
            <a href="javascript:void(0)" class="w3-right w3-bar-item w3-button" onclick="w3_open()"><i class="fa fa-bars"></i></a>
        </header>

        <div style="padding: 50px;"></div>

        <!-- !PAGE CONTENT! -->
        <div class="w3-main w3-white w3-padding-16 w3-card-2">
            <h1 class="w3-center">Carport 1</h1>   

            <!-- Slideshow Header -->
            <div class="w3-container w3-content" id="apartment">
               
                <div class="w3-display-container mySlides">
                    <img src="media/pictures/CAR01.png" style="width:100%;margin-bottom:-6px">
                    <div class="w3-display-bottomleft w3-container w3-black">
                        <p>Real Picture</p>
                    </div>
                </div>
                <div class="w3-display-container mySlides">
                    <img src="media/pictures/flat.png" style="width:100%;margin-bottom:-6px">
                    <div class="w3-display-bottomleft w3-container w3-black">
                        <p>Scheme</p>
                    </div>
                </div>
            </div>
            <div class="w3-row-padding w3-section">
                <div class="w3-col s3">
                    <img class="demo w3-opacity w3-hover-opacity-off" src="media/pictures/CAR01.png" style="width:100%;cursor:pointer" onclick="currentDiv(1)" title="Living room">
                </div>
                <div class="w3-col s3">
                    <img class="demo w3-opacity w3-hover-opacity-off" src="media/pictures/flat.png" style="width:100%;cursor:pointer" onclick="currentDiv(2)" title="Dining room">
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
                <p>We accept: <i class="fa fa-credit-card w3-large"></i> <i class="fa fa-cc-mastercard w3-large"></i> <i class="fa fa-cc-amex w3-large"></i> <i class="fa fa-cc-cc-visa w3-large"></i><i class="fa fa-cc-paypal w3-large"></i></p>
                <hr>

                <h4><strong>Buy this beauty</strong></h4>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>

                <p><button class="w3-button w3-green w3-third" onclick=
                           <%
                               if (session.getAttribute("username") == null) {
                                   String buyLink = "document.getElementById('cardAPI').style.display = 'block'";
                                   session.setAttribute("buyLink", buyLink);
                               } else {
                                   String buyLink = "document.getElementById('cardAPI').style.display = 'block'";
                                   session.setAttribute("buyLink", buyLink);
                               }
                           %>
                           "<%= session.getAttribute("buyLink")%>">Buy</button></p>
            </div>
            <!-- Credit Card -->
            <div id="cardAPI" class="modal">
                <form class="modal-content animate" action="thankyou.jsp">
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('cardAPI').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                        <h1 class="w3-container ">Credit Card Details</h1>
                    </div>

                    <div class="loginContainer">
                        <label><b>Cardholder's name:</b></label>
                        <input type="text" placeholder="the same as the card" name="cardname" required>

                        <label><b>Credit Card No. :</b></label>
                        <input type="password" placeholder="Enter all numericals" name="cardnumber" required>
                        
                        <label><b>Valid Date:</b></label>
                        <input type="password" placeholder="Month / Year" name="carddate" required>
                        
                        <label><b>CCV No. :</b></label>
                        <input type="password" placeholder="on the back" name="cardccv" required>

                        <button type="submit">Buy</button>
                    </div>

                    <div class="loginContainer" style="background-color:#f1f1f1">
                        <button type="button" onclick="document.getElementById('cardAPI').style.display = 'none'" class="cancelbtn">Cancel</button>
                        <span class="psw"><a href="#">Forgot password?</a></span>
                    </div>
                </form>
            </div> <!-- CreditCard END HERE -->
            <hr>

            

            <!-- End page content -->
        </div>

        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-light-grey w3-xlarge">          
            <h3>Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</h3>
        </footer>
        <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">

        </script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>



        <script>// Slideshow Apartment Images
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

    </body>
</html>

