<%@page import="exceptions.ConnectionException"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%User user = null;%>
<%user = (User) session.getAttribute("user");%>
<% String errorMessage = ""; %>
<%
    if (session.getAttribute("error") != null) {
        errorMessage = ConnectionException.getExceptionMessage((String) session.getAttribute("error"), session); %>
<!--Make modal Login to be visible if the first login attempt was failed-->
<script>
    // Get the modal
    var modal = document.getElementById('exceptionModal');
    modal.style.display = 'block';
</script>
<% } %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <link href="https://fonts.googleapis.com/css?family=Lobster" rel="stylesheet"> <!-- Custom FONT -->
        <script
            src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous">
        </script>
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <!-- NAVBAR START -->        
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
        <!-- NAVBAR END -->
        <!-- LOGIN FORM START -->
        <div id="id01" class="modal" style="overflow-y: scroll">
            <form class="modal-content animate" action="Login" method = "POST">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id01').style.display = 'none'" class="close"  title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Log In</h1>
                </div>
                <% if ("LoginError".equals(session.getAttribute("error"))) {
                        session.removeAttribute("error"); %>
                <!--Make modal Login to be visible if the first login attempt was failed-->
                <script>
                    // Get the modal
                    var modal = document.getElementById('id01');
                    modal.style.display = 'block';
                </script>
                <div class="imgcontainer alert alert-danger">
                    <strong> Wrong Log In details </strong>
                </div>
                <%} else if ("QueryException".equals(session.getAttribute("error"))) {
                    session.removeAttribute("error"); %>
                <script>
                    // Get the modal
                    var modal = document.getElementById('id01');
                    // Make modal Login to be visible if the first login attempt was failed
                    modal.style.display = 'block';
                </script>
                <div class="imgcontainer alert alert-danger">
                    <strong> We can`t process your request at the moment!<br> Error code: Query Exception  </strong>
                </div>
                <%} else if ("ConnectionException".equals(session.getAttribute("error"))) {
                    session.removeAttribute("error"); %>
                <script>
                    // Get the modal
                    var modal = document.getElementById('id01');
                    // Make modal Login to be visible if the first login attempt was failed
                    modal.style.display = 'block';
                </script>
                <div class="imgcontainer alert alert-danger">
                    <strong> We can`t access the DataBase at the moment!<br> Error code: Connection Exception  </strong>
                </div>              
                <% }%>


                <div class="loginContainer">
                    <label><b>Username</b></label>
                    <input type="text" placeholder="Enter Email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="customer@fog.dk" class="inputFields" required>

                    <label><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="password" title="at least 8 characters" pattern=".{8,}" class="inputFields" required>
                    <!-- current URL passed like a hidden field, so after Log in, the Servlet will redirect the user back to the same page -->
                    <input type="hidden" name="from" value="${pageContext.request.requestURI}">

                    <button type="submit" >Login</button>
                    <button type="button" onclick="document.getElementById('id02').style.display = 'block'" class="w3-black w3-right">Register</button>
                </div>

                <div class="loginContainer" style="background-color:#f1f1f1">
                    <button type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancelbtn">Cancel</button>
                    <span class="psw"><a href="#">Forgot password?</a></span>
                </div>
            </form>
        </div>
        <!-- LOGIN FORM END -->
        <!-- REGISTER FORM START -->
        <div id="id02" class="modal" style="overflow-y: scroll">
            <form class="modal-content animate" action="Register" method="POST">
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
                    <input type="text" placeholder="customer@fog.dk" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="customer@fog.dk" class="inputFields" required>
                    <label><b>Password</b></label>
                    <input type="password" placeholder="Best min. 8 characters" name="password" title="at least 8 characters" pattern=".{8,}" class="inputFields" id="passwordReg" required>
                    <label><b>Re-type Password</b></label>
                    <input type="password" placeholder="Just to check ;)" name="repassword" title="type the same password" pattern=".{8,}" class="inputFields" id="repasswordReg" required>
                    <div><p id="pCheckPassword" style="color: red"></p></div>
                    <label><b>First name</b></label>
                    <input type="text" placeholder="Johannes" name="fName" class="inputFields" required>
                    <label><b>Last name</b></label>
                    <input type="text" placeholder="Fog" name="lName" class="inputFields" required>
                    <label><b>Phone number</b></label>
                    <input type="text" placeholder="e.g. Mobile min. 8 numericals" name="phone" title="e.g. 45871001 (8 numericals)" pattern="[0-9]{8,8}" class="inputFields" required>
                    <label><b>Address</b></label>
                    <input type="text" placeholder="Firskovvej 20" name="adress" title="e.g. Street" class="inputFields" required>
                    <label><b>Zip code</b></label>
                    <input type="text" placeholder="Local post code" name="zipCode" title="e.g. 2800" pattern="[0-9]{4}" class="inputFields" required>
                    <!-- current URL passed like a hidden field, so after Register, the Servlet will redirect the user back to the same page -->
                    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
                    <input type="hidden" name="userType" value="0">

                    <button type="submit" class="btn" id="RegButton">Register</button>
                    <button type="button" onclick="document.getElementById('id02').style.display = 'none'" class="cancelbtn">Close</button>
                </div>
            </form>
        </div>
        <!-- REGISTER FORM END -->
        <!-- Credit Card modal-->
        <div id="cardAPI" class="modal">
            <form class="modal-content animate" action="Carport">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('cardAPI').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Credit Card Details</h1>
                </div>
                <div class="loginContainer">
                    <label><b>Cardholder's name:</b></label>
                    <input type="text" placeholder="the same as the card" name="cardname" class="inputFields" required>
                    <label><b>Credit Card No. :</b></label>
                    <input type="password" placeholder="Enter all numericals" name="cardnumber" class="inputFields" required>
                    <label><b>Valid until:</b></label>
                    <input type="password" placeholder="Month / Year" name="carddate" class="inputFields" required>
                    <label><b>CCV No. :</b></label>
                    <input type="password" placeholder="3 numbers on the back" name="cardccv" class="inputFields" required>
                    <button type="submit">Buy</button>
                    <button type="button" onclick="document.getElementById('cardAPI').style.display = 'none'" class="cancelbtn">Cancel</button>
                </div>
            </form>
        </div>
        <!-- Credit Card END -->
        <!--LOGOUT modal -->
        <div id="logout" class="modal">
            <form class="modal-content animate">
                <div class="imgcontainer">
                    <h1 class="w3-container ">You are logged out!</h1>
                    <p class="w3-container ">(You will be redirected after 3 seconds...)</p>
                </div>
            </form>
        </div>
        <!-- LOGOUT END -->
        <!-- Exception modal-->
        <div id="exceptionModal" class="modal">
            <form class="modal-content animate">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('exceptionModal').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Exception Occured!</h1>
                    <div class="imgcontainer alert alert-danger">
                        <strong><%= errorMessage%></strong>
                    </div>
                </div>
                <div class="loginContainer">
                </div>
            </form>
        </div>
        <!-- Exception modal END -->

        <!-- ### SCRIPTS ### -->
        <!-- Close LOGIN ,REGISTER and CARD DETAILS modals -->
        <script>
            // Get the modal
            var modal = document.getElementById('id01');
            var modal2 = document.getElementById('id02');
            var modal3 = document.getElementById('cardAPI');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == modal || event.target == modal2 || event.target === modal3) {
                    modal.style.display = "none";
                    modal2.style.display = "none";
                    modal3.style.display = "none";
                }
            }
        </script>
        <!-- Prevent "space" button script -->
        <script>
            $(function () {
                $('.inputFields').on('keypress', function (e) {
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
        <!-- ### SCRIPTS END### -->
    </body>
</html>
