<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%User user = null;%>
<%user = (User) session.getAttribute("user");%>
<html>
    <head>
        <title>Place Order</title>
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
                        <% if (request.getParameter("roofType").equals("flat") && request.getParameter("hasShed").equals("no")) {%>
                        <label><b>Inner Heigh</b></label>
                        <p><%=request.getParameter("height")%></p>
                        <label><b>Width</b></label>
                        <p><%=request.getParameter("width")%></p>
                        <label><b>Length</b></label>
                        <p><%=request.getParameter("length")%></p>
                        <% } else if (request.getParameter("roofType").equals("flat") && request.getParameter("hasShed").equals("yes")) {%>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>General: </h4>
                        </div>
                        <label><b>Inner Heigh</b></label>
                        <p><%=request.getParameter("height")%></p>
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
                        <% } else if (request.getParameter("roofType").equals("hipped") && request.getParameter("hasShed").equals("no")) {%>
                        <label><b>Inner Heigh</b></label>
                        <p><%=request.getParameter("innerHeight")%></p>
                        <label><b>Rooftop Heigh</b></label>
                        <p><%=request.getParameter("rooftopHeight")%></p>
                        <label><b>Width</b></label>
                        <p><%=request.getParameter("width")%></p>
                        <label><b>Length</b></label>
                        <p><%=request.getParameter("length")%></p>
                        <% } else if (request.getParameter("roofType").equals("hipped") && request.getParameter("hasShed").equals("yes")) {%>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>General: </h4>
                        </div>
                        <label><b>Inner Heigh</b></label>
                        <p><%=request.getParameter("innerHeight")%> meters</p>
                        <label><b>Rooftop Heigh</b></label>
                        <p><%=request.getParameter("rooftopHeight")%> meters</p>
                        <label><b>Overall Width</b></label>
                        <p><%=request.getParameter("width")%> meters</p>
                        <label><b>Overall Length</b></label>
                        <p><%=request.getParameter("length")%> meters</p>
                        <div class="well well-sm" style="background-color: #222; color: white">
                            <h4>Tools/Shed Room: </h4>
                            <p>(The Room's measures are included in the General Measures)</p>
                        </div>
                        <label><b>Shed Width</b></label>
                        <p><%=request.getParameter("widthShed")%> meters</p>
                        <label><b>Shed Length</b></label>
                        <p><%=request.getParameter("lengthShed")%> meters</p>
                        <% } else { %>
                        <h3>You need to input something, developer!!</h3>
                        <% }%>
                        <label><b>Your new baby's name:</b></label>
                        <p><%=request.getParameter("customName")%></p>
                        <input type="hidden" name="roofType" value="<%= request.getParameter("roofType")%>">
                        <input type="hidden" name="roofType" value="<%= request.getParameter("hasShed")%>">
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

        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-light-grey w3-xlarge">          
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
