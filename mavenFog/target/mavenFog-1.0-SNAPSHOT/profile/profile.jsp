<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%User user = null;%>
<%user = (User)session.getAttribute("user");%>
<html>
    <head>
        <title>Home page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Import end here -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <!-- External links to documents -->
        <link rel="stylesheet" href="../css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body class="w3-light-grey">      
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="#">FOG</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">                        
                        <li><a href="#About">My Profile&nbsp;<span class="glyphicon glyphicon-plus"></span></a></li>

                    </ul>                   
                    <ul class="nav navbar-nav navbar-right">
                        <% if (user != null) {%>
                        <!-- HERE WHEN LOGGED IN DIV -->
                        <li>
                            <a href="#" id="dropdownMenu1" data-toggle="dropdown"><%=user.getEmail()%>&nbsp;<span class="glyphicon glyphicon-cog"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">                                
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li class="divider"></li>
                                <li><a href="../index.jsp">Log out</a></li>
                            </ul>
                        </li>

                        <!--<a href="#" onclick="document.getElementById('logout').style.display = 'block'">Log out</a>-->

                        <% } else {%>
                        <!-- ELSE THE COMMON ONE WITH LOGIN -->                        
                        <li>Unauthorized</li>
                        <%}%>
                    
                </div>
            </div>
        </nav>         
        <div style="margin-top: 70px;"></div>
        <div class="w3-white w3-card-2 w3-container w3-margin w3-padding-32">
            <h1>Your Profile Info:</h1>
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#pending" id="pending-tab" role="tab" data-toggle="tab"> Profile Info&nbsp;</a></li>
                <li role="presentation" class=""><a href="#completed" role="tab" id="profile-tab" data-toggle="tab">Orders&nbsp;</a></li>
                <li role="presentation" class=""><a href="#cancelled" role="tab" id="profile-tab" data-toggle="tab">Add info&nbsp;</a></li>
               </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active in" role="tabpanel" id="pending">
                    <h1>Profile Info</h1>                    
                    <h2><%= user.getEmail()%></h2>
                    <h2><%= user.getfName()%></h2>
                    <h2><%= user.getlName()%></h2>
                    <h2><%= user.getPhone()%></h2>
                    <h2><%= user.getAdress()%></h2>
                    <h2><%= user.getZipCode()%></h2>
                    <h2><%= user.getRole()%></h2>
                    
                </div>
                
                    <div class="tab-pane fade" role="tabpanel" id="completed">
                        <h1>Orders</h1>
                        <table class="table table-hover">
                            <th>Number</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>More info</th>

                            <tr class="success">
                                <td>Order #11</td>
                                <td>Carport CUH02</td>
                                <td>Carport whit flat roof type, which can hold up to 2 compact vehicles.</td>
                                <td><button type="button" class="btn btn-info">Info</button></td>
                            </tr>
                            <tr class="success">
                                <td>Order #22</td>
                                <td>Carport CRH15</td>
                                <td>Carport with "erected" roof for 1 car with max length 3.15 meters</td>
                                <td><button type="button" class="btn btn-info">Info</button></td>
                            </tr>
                            <tr class="success">
                                <td>Order #33</td>
                                <td>Carport HXYF4</td>
                                <td>Carport for 2 cars</td>
                                <td><button type="button" class="btn btn-info">Info</button></td>
                            </tr>
                            <tr class="success">
                                <td>Order #43</td>
                                <td>Carport HXYF4</td>
                                <td>Carport for 2 cars</td>
                                <td><button type="button" class="btn btn-info">Info</button></td>
                            </tr>
                            <tr class="success">
                                <td>Order #53</td>
                                <td>Carport GASYF4</td>
                                <td>Carport for blq blq...</td>
                                <td><button type="button" class="btn btn-info">Info</button></td>
                            </tr>
                            <tr class="success">
                                <td>Order #63</td>
                                <td>Carport ABC14</td>
                                <td>Carport for blq blq.</td>
                                <td><button type="button" class="btn btn-info">Info</button></td>
                            </tr>
                            <tr class="success">
                                <td>Order #73</td>
                                <td>Carport HXYF14</td>
                                <td>Carport for blq blq...</td>
                                <td><button type="button" class="btn btn-info">Info</button></td>
                            </tr>
                        </table>
                    </div>
                
                <div class="tab-pane fade" role="tabpanel" id="cancelled">
                    <h1>Upload your carport picture</h1>
                    
                </div>
                <div class="tab-pane fade" role="tabpanel" id="dashboard">
                    <h1>Add your photo:</h1>
                </div>
            </div>
        </div>

        

        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-light-grey w3-xlarge w3-bottom">          
            <h3>Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</h3>

        </footer>

        
    </body>
</html>

