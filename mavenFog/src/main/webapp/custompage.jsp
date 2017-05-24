<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%User user = null;%>
<%user = (User) session.getAttribute("user");%>
<html>
    <head>
        <title>Custom Measures</title>
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
    <body class="w3-light-grey">
        <<nav class="navbar navbar-inverse navbar-fixed-top">
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

        <div class="w3-container w3-padding-32 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2 class="w3-lobster">Custom Carports</h2>
                <p>Here you can make your own carport, just input the measures that fits your needs.</p>
                <a class="btn btn-default" role="button" data-toggle="collapse" href="#collapseExample">
                    Hint how to take the right measures<br>(Click me and see the bottom)
                </a>
                <form class="modal-content animate" action="customFinalDetails.jsp" method="post">
                    <div class="imgcontainer">
                        <h1 class="w3-container w3-lobster">Measures</h1>
                    </div>

                    <!-- LOGIC TO SHOW THE RIGHT MODAL AND TAKE MEASURES, ACCORDING TO USER's DECISIONS FROM PREVIOUS SUBMITTED FORMS(PRECEDE 2 PAGES) -->                    
                    <div class="loginContainer">
                        <% if (request.getParameter("roofType").equals("flat") && request.getParameter("hasShed").equals("no")) { %>
                        <label><b>Inner Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="height" title="e.g. 2.10" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Width</b></label>
                        <input type="text" placeholder="Width in meters" name="width" title="e.g. 3.00" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Length</b></label>
                        <input type="text" placeholder="Length in meters" name="length" title="e.g. 4.80" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <% } else if (request.getParameter("roofType").equals("flat") && request.getParameter("hasShed").equals("yes")) { %>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>General: </h4>
                        </div>
                        <label><b>Inner Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="height" title="e.g. 2.10" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Overall Width</b></label>
                        <input type="text" placeholder="Width in meters" name="width" title="e.g. 3.00" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Overall Length</b></label>
                        <input type="text" placeholder="Length in meters" name="length" title="e.g. 6.20" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>Tools/Shed Room: </h4>
                            <p>(The Room's measures are included in the General Measures)</p>
                        </div>
                        <label><b>Shed Width</b></label>
                        <input type="text" placeholder="Width in meters" name="widthShed" title="e.g. 2.70" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Shed Length</b></label>
                        <input type="text" placeholder="Length in meters" name="lengthShed" title="e.g. 1.50" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <% } else if (request.getParameter("roofType").equals("hipped") && request.getParameter("hasShed").equals("no")) { %>
                        <label><b>Inner Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="innerHeight" title="e.g. 2.10" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Rooftop Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="rooftopHeight" title="e.g. 0.95" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Width</b></label>
                        <input type="text" placeholder="Width in meters" name="width" title="e.g. 3.60" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Length</b></label>
                        <input type="text" placeholder="Length in meters" name="length" title="e.g. 5.40" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <% } else if (request.getParameter("roofType").equals("hipped") && request.getParameter("hasShed").equals("yes")) { %>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>General: </h4>
                        </div>
                        <label><b>Inner Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="innerHeight" title="e.g. 2.10" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Rooftop Heigh</b></label>
                        <input type="text" placeholder="Heigh in meters" name="rooftopHeight" title="e.g. 0.95" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Overall Width</b></label>
                        <input type="text" placeholder="Width in meters" name="width" title="e.g. 3.60" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Overall Length</b></label>
                        <input type="text" placeholder="Length in meters" name="length" title="e.g. 7.30" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>Tools/Shed Room: </h4>
                            <p>(The Room's measures are included in the General Measures)</p>
                        </div>
                        <label><b>Shed Width</b></label>
                        <input type="text" placeholder="Width in meters" name="widthShed" title="e.g. 3.20" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <label><b>Shed Length</b></label>
                        <input type="text" placeholder="Length in meters" name="lengthShed" title="e.g. 2.25" maxlength="5" onkeypress="return isNumberKey(event)" pattern="[0-9]+\.[0-9]{2,2}$" required>
                        <% } else { %>
                        <h3>You need to input something, developer!!</h3>
                        <% } %>
                        <label><b>Give your baby a name:</b></label>
                        <input type="text" placeholder="Custom name" name="customName" title="e.g. MyBeauty#1 (min. 3 and max. 40 chars)" maxlength="40" pattern="(?=.*[a-z])(?=.*[A-Z]).{3,}" required>
                        <input type="hidden" name="roofType" value="<%= request.getParameter("roofType") %>">
                        <input type="hidden" name="hasShed" value="<%= request.getParameter("hasShed") %>">
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

        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-light-grey w3-xlarge">          
            <h3>Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</h3>
        </footer>

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
