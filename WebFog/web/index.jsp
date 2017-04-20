<%-- 
    Document   : index
    Created on : 17-Apr-2017, 10:56:25
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Home page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
                        <li><a href="beforeyoubuy.jsp">Before you buy</a></li>
                    </ul>      
                    <ul class="nav navbar-nav navbar-right">
                        <li><a onclick="document.getElementById('id01').style.display = 'block'">Login</a></li>
                        <li><a href="support.jsp">Support</a></li>
                    </ul>
                </div>
            </div>
        </nav>

        <!--Login form -->
        <div id="id01" class="modal">

            <form class="modal-content animate" action="Login">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Log In</h1>
                </div>
                <% if ( "login".equals(request.getAttribute("error"))) {%>
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
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
        </script>

        <div class="wide">
            <div class="container text-center pad">
                <h1><span class="box">My First Carport</span></h1>      
                <p><span class="box">Find your match</span></p>
            </div>
        </div>

        <!-- Custom port here -->
        <div class="w3-container w3-padding-16">
            <div class="w3-container w3-half">
                <h1>Custom made carports</h1>
                <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
                </p>
                <button class="w3-button w3-teal w3-block w3-xlarge ">Build your carport</button>
            </div>
            <div class="w3-container w3-half w3-center">
                <img src="media/pictures/custom2.png" class="w3-padding-16" style="width:100%;max-width:400px">
            </div>

        </div>
        <!-- Custom ends here -->

        <div class="w3-container w3-padding-32 w3-center" id="Contact">
            <h3 class="w3-border-bottom w3-border-light-grey w3-padding-16">Our Product line</h3>
        </div>




        <div class="w3-row w3-container">
            <div class="w3-half">
                <div class="w3-card-4 w3-margin " >
                    <div class="w3-display-container w3-text-white">
                        <img src="http://placehold.it/350x150" alt="Lights" style="width:100%">
                        <div class="w3-xlarge w3-display-bottomleft w3-padding"><h3>Carport Name</h3></div>
                    </div> 
                    <div class="w3-padding-16  w3-container">
                        <p class="w3-threequarter">Some details here and there we are here you boommmm chaka lkakaand here ahee as ad asd asdas das d </p>
                        <ul class="w3-threequarter">
                            <li>One</li>
                            <li>One</li>
                            <li>One</li>
                            <li>One</li>
                        </ul>  
                        <button class="w3-col w3-button w3-blue ">Check me out</button>
                    </div>                    
                </div>
            </div>
            <div class="w3-half">
                <div class="w3-card-4 w3-margin " >
                    <div class="w3-display-container w3-text-white">
                        <img src="http://placehold.it/350x150" alt="Lights" style="width:100%">
                        <div class="w3-xlarge w3-display-bottomleft w3-padding">LONDON 60&deg; F</div>
                    </div> 
                    <div class="w3-padding-16  w3-container">
                        <p class="w3-threequarter">Some details here and there we are here you boommmm chaka lkakaand here ahee as ad asd asdas das d </p>
                        <ul class="w3-threequarter">
                            <li>One</li>
                            <li>One</li>
                            <li>One</li>
                            <li>One</li>
                        </ul>  
                        <button class="w3-col w3-button w3-blue ">Check me out</button>
                    </div>                    
                </div>
            </div>
            <div class="w3-half">
                <div class="w3-card-4 w3-margin " >
                    <div class="w3-display-container w3-text-white">
                        <img src="http://placehold.it/350x150" alt="Lights" style="width:100%">
                        <div class="w3-xlarge w3-display-bottomleft w3-padding">LONDON 60&deg; F</div>
                    </div> 
                    <div class="w3-padding-16  w3-container">
                        <p class="w3-threequarter">Some details here and there we are here you boommmm chaka lkakaand here ahee as ad asd asdas das d </p>
                        <ul class="w3-threequarter">
                            <li>One</li>
                            <li>One</li>
                            <li>One</li>
                            <li>One</li>
                        </ul>  
                        <button class="w3-col w3-button w3-blue ">Check me out</button>
                    </div>                    
                </div>
            </div>
            <div class="w3-half">
                <div class="w3-card-4 w3-margin " >
                    <div class="w3-display-container w3-text-white">
                        <img src="http://placehold.it/350x150" alt="Lights" style="width:100%">
                        <div class="w3-xlarge w3-display-bottomleft w3-padding">LONDON 60&deg; F</div>
                    </div> 
                    <div class="w3-padding-16  w3-container">
                        <p class="w3-threequarter">Some details here and there we are here you boommmm chaka lkakaand here ahee as ad asd asdas das d </p>
                        <ul class="w3-threequarter">
                            <li>One</li>
                            <li>One</li>
                            <li>One</li>
                            <li>One</li>
                        </ul>  
                        <button class="w3-col w3-button w3-blue ">Check me out</button>
                    </div>                    
                </div>
            </div>
        </div>
        <div class="w3-container">
            <p class="w3-border-bottom w3-border-light-grey w3-padding-16"></p>
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

