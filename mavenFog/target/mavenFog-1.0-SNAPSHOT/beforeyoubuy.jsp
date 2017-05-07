<%-- 
    Document   : beforeyoubuy
    Created on : Apr 12, 2017, 10:13:45 AM
    Author     : trez__000
--%>

<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%User user = null;%>
<%user = (User) session.getAttribute("user");%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Before you buy</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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
                    <a class="navbar-brand" href="index.jsp">HOME</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">                        
                        <li class="active"><a href="#">Before you buy</a></li>
                    </ul>      
                    <ul class="nav navbar-nav navbar-right">
                        <% if (user != null) {%>
                        <!-- HERE WHEN LOGGED IN DIV -->
                        <li>
                            <a href="#" id="dropdownMenu1" data-toggle="dropdown"><%=user.getEmail()%>&nbsp;<span class="glyphicon glyphicon-cog"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a href="profile/profile.jsp">Profile page</a></li>                                
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li class="divider"></li>
                                <li><a id="logoutFunction" href="#">Log out</a></li>
                            </ul>
                        </li>

                        <!--<a href="#" onclick="document.getElementById('logout').style.display = 'block'">Log out</a>-->

                        <% } else {%>
                        <!-- ELSE THE COMMON ONE WITH LOGIN -->                        
                        <li><a href="#" onclick="document.getElementById('id01').style.display = 'block'">Login</a></li>
                            <%}%>
                        <li> <a href="support.jsp">Support</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--Logout modal -->
        <div id="logout" class="modal">
            <form class="modal-content animate">
                <div class="imgcontainer">

                    <h1 class="w3-container ">You are logged out!</h1>
                    <p class="w3-container ">(You will be redirected after 5 seconds...)</p>
                </div>
            </form>
        </div><!-- Logout END -->

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

        <div class="w3-container w3-padding-64 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2>Before you buy you may consider :</h2>
                <p>Lorem ipusm bla blabla</p>
                <ol style="text-align: left;">
                    <li>Visit Local Regulations first. (link <span style="color: blue"><a href="#">here</a></span>)</li>
                    <li>If bored from the first step, call us now on 555-37 37. (or go to <span style="color: blue"><a href="support.jsp">Support</a></span> page)</li>
                    <li>Know the right measures: </li>
                </ol>

                <p>MEASURES</p>
            </div>
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
        <!--Logout function -->
        <script>
            $('#logoutFunction').click(function ()
            {
                setTimeout(function () {
                    document.getElementById('logout').style.display = 'block';
                }, 800);
                var delay = 5000;
                setTimeout(function () {
                    window.location = 'logout.jsp';
                }, delay);
                return false;
            });

        </script>   
    </body>
</html>
