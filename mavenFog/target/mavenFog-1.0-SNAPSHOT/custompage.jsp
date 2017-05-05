<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Custom Carport</title>
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
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

        <div class="w3-container w3-padding-32 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2>Custom Carports</h2>
                <p>Here you can make your own carport, just input the measures that fits your needs.</p>
                <form class="modal-content animate" action="custompage2.jsp" method="post">
                    <div class="imgcontainer">
                        <span onclick="document.getElementById('id01').style.display = 'none'" class="close"  title="Close Modal">&times;</span>
                        <h1 class="w3-container ">Measures</h1>
                    </div>

                    <div class="loginContainer">
                        <label><b>Width</b></label>
                        <input type="text" placeholder="Width in centimeters" name="width" required>

                        <label><b>Length</b></label>
                        <input type="text" placeholder="Length in centimeters" name="length" required>

                        <label><b>Heigh</b></label>
                        <input type="text" placeholder="Heigh in centimeters" name="heigh" required>

                        <button type="submit">Next</button>
                    </div>

                    <div class="loginContainer" style="background-color:#f1f1f1">
                        <button type="reset" style="background-color: red">Reset</button>
                    </div>
                    <div class="w3-light-grey">
                        <div class="w3-container w3-green w3-center" style="width:25%">Step 1/3</div>
                    </div>
                </form>
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
    </body>
</html>
