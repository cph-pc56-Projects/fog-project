<%-- 
    Document   : support
    Created on : Apr 10, 2017, 9:09:41 AM
    Author     : trez__000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                    <a class="navbar-brand" href="index.html">HOME</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">                        
                        <li><a href="beforeyoubuy.jsp">Before you buy</a></li>
                    </ul>      
                    <ul class="nav navbar-nav navbar-right">
                        <li><a class="w3-button"  onclick="document.getElementById('id01').style.display = 'block'"
                               style="width:auto; background-color: #222222; color: grey;">Login</a></li>
                        <li class="active"> <a  href="#" >Support</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--Login form -->
        <div id="id01" class="modal">

            <form class="modal-content animate" action="/action_page.php">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Log In</h1>
                </div>

                <div class="loginContainer">
                    <label><b>Username</b></label>
                    <input type="text" placeholder="Enter Username" name="uname" required>

                    <label><b>Password</b></label>
                    <input type="password" placeholder="Enter Password" name="psw" required>

                    <button type="submit">Login</button>
                    <input type="checkbox" checked="checked"> Remember me
                </div>

                <div class="loginContainer" style="background-color:#f1f1f1">
                    <button type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancelbtn">Cancel</button>
                    <span class="psw">Forgot <a href="#">password?</a></span>
                </div>
            </form>
        </div>

        <script>
            // Get the modal
            var modal = document.getElementById('id01');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            }
        </script>
        
        <div class="w3-container w3-padding-32 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2>Carport regulations</h2>
                <p>Here you will find all the necessary regulations for you to check to see if you're compliant to the law.</p>
            </div>
            <div class="w3-card-2 w3-container w3-margin">
                <h2>Minimum requirements</h2>
                <p>Lorem ipusm bla blabla</p>
            </div>
        </div>
        
        
        
        
        
        
        
        
        
        
        
        
       <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
     </body>
</html>
