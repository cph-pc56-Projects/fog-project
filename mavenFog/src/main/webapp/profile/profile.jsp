<%@page import="model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%User user = null;%>
<%user = (User) session.getAttribute("user");%>
<%ArrayList<Order> orders = null; %>
<%orders = (ArrayList<Order>) session.getAttribute("orders");%>
<html>
    <head>
        <title>Profile page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Import end here -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <!-- External links to documents -->
        <link rel="stylesheet" href="../css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <style>    
            #id02 {
                display: none;
            }
            .red {
                background-color: red !important;    
            } 
        </style>
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
                    <a class="navbar-brand" href="../index.jsp">FOG</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">                        
                        <li><a href="../beforeyoubuy.jsp">Before you buy</a></li>
                    </ul>      
                    <ul class="nav navbar-nav navbar-right">
                        <% if (user != null) {%>
                        <!-- HERE WHEN LOGGED IN DIV -->
                        <li>
                            <a href="#" id="dropdownMenu1" data-toggle="dropdown"><%=user.getEmail()%>&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">                                
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
                        <li><a href="../support.jsp">Support</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--Logout modal -->
        <div id="logout" class="modal">
            <form class="modal-content animate">
                <div class="imgcontainer">

                    <h1 class="w3-container ">You are logged out!</h1>
                    <p class="w3-container ">(You will be redirected after 3 seconds...)</p>
                </div>
            </form>
        </div><!-- Logout END -->

        <div style="margin-top: 70px;"></div>
        <div class="w3-white w3-card-2 w3-container w3-margin w3-padding-32">
            <h1>Your page:</h1>
            <ul class="nav nav-tabs">
                 
                <li role="presentation" class="active"><a href="#pending" id="pending-tab" role="tab" data-toggle="tab"> Profile Info&nbsp;</a></li>
                
                
                <li role="presentation" class=""><a href="#completed" role="tab" id="profile" data-toggle="tab" >Orders&nbsp;</a></li>
                       
                <li role="presentation" class=""><a href="#cancelled" role="tab" id="profile-tab" data-toggle="tab">Add info&nbsp;</a></li>
            </ul>
                
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active in" role="tabpanel" id="pending">
                    <h1>Profile Info:</h1>  
                    <div id="id01" class="w3-padding-16 w3-margin-32 w3-left">
                        <table class="w3-table w3-bordered w3-padding" cellpadding="5">
                            <tr>
                                <th>First Name:</th>
                                <td><%= user.getfName()%></td>
                            </tr>
                            <tr>
                                <th>Last Name:</th>
                                <td><%= user.getlName()%></td>
                            </tr>
                            <tr>
                                <th>Email:</th>
                                <td><%= user.getEmail()%></td>
                            </tr>                        
                            <tr>
                                <th>Address:</th>
                                <td><%= user.getAdress()%></td>
                            </tr>
                            <tr>
                                <th>ZipCode:</th>
                                <td><%= user.getZipCode()%></td>
                            </tr>
                            <tr>
                                <th>Phone number:</th>
                                <td><%= user.getPhone()%></td>
                            </tr>
                            <tr>
                                <th>Account ID: </th>
                                <td><%= user.getAccountID()%></td>
                            </tr>
                        </table>
                    </div>
                
                      
                    <div id="id02">
                        <form action="../Profile" method="post">
                            <table  cellpadding="5">
                                <tr>
                                    <td>Email: </td>        
                                    <td><input type="text" name="email"></td>
                                </tr>
                                <tr>
                                    <td>Password: </td>
                                    <td><input type="text" name="password"></td>        
                                </tr>
                                <tr>
                                    <td>Address: </td>
                                    <td><input type="text" name="address"></td>        
                                </tr>
                                <tr>
                                    <td>Phone: </td>
                                    <td><input type="number" name="phone"></td>        
                                </tr>
                                <tr>
                                    <td>ZipCode: </td>
                                    <td><input type="number" name="zipcode"></td>        
                                </tr> 
                            </table>
                            <button class="w3-button w3-green" type="submit"> Submit</button>
                        </form>
                    </div>
                            <div class="w3-padding-16 w3-margin-32 w3-left">
                        <a id="edit" class="w3-button w3-yellow" onclick="toggle()">Edit</a>
                     </div>
                    <script>
                        function toggle() {
                            var x = document.getElementById('id01');
                            var y = document.getElementById('id02');
                            var edit = document.getElementById("edit");
                            if (y.style.display === 'none') {
                                edit.innerHTML = "Cancel";
                                edit.classList.toggle("red");
                                y.style.display = 'block';
                                x.style.display = "none";
                            } else {
                                y.style.display = 'none';
                                edit.innerHTML = "Edit";
                                edit.classList.toggle("red", false);
                                x.style.display = "block";
                            }
                        }
                    </script>
                    
                </div>
                <div class="tab-pane fade col-xs-12" role="tabpanel" id="completed">
                    <div class="table-responsive">
                    <h1>Orders</h1>
                    <table class="table table-hover">
                        <th>Order ID</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>More info</th>                         
                        <% for (Order order: orders) {%>                      
                                <tr class="<%if (order.getOrderStatus() == 0) {out.print("warning");} else if (order.getOrderStatus() == 1) {out.print("success");} else {out.print("danger");};%>">
                                    <td>Order ID: <%=order.getOrderID()%></td>
                                    <td>Price: <%=order.getPrice()%></td>
                                    <td><p>Carport whit flat roof type, which can hold up to 2 compact vehicles.</p>
                                    <div class="collapse" id="<%=order.getOrderID()%>">                                           
                                           <p>Date: <%=order.getDate()%></p>                                           
                                           <p>Product ID: <%=order.getProductID()%></p>                                           
                                           <p>Customer ID:<%=order.getCustomerID()%></p>
                                           <p>Delivery ID:<%=order.getDeliveryID()%></p>
                                           <p>Invoice ID:<%=order.getInvoiveID()%></p>
                                           <p>Order Status: <%if (order.getOrderStatus() == 0) {out.print("Pending");} else if (order.getOrderStatus() == 1) {out.print("Completed");} else {out.print("Cancelled");};%></p>
                                           <p>Sales Rep ID: <%=order.getSalesRepID()%></p>
                                    </div></td>
                                    <td><button type="button" data-toggle="collapse" data-target="#<%=order.getOrderID()%>" >Info</button></td>
                                </tr>
                                
                        <% }%>
                    </table>
                    </div>
                </div>

                <div class="tab-pane fade" role="tabpanel" id="cancelled">
                    <h1>Upload your carport picture</h1>

                </div>                
            </div>
        </div>



        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-light-grey w3-xlarge">          
            <h3>Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</h3>

        </footer>
        <!-- Calls logout on button click -->
        
            
        <script>
            $('#logoutFunction').click(function ()
            {
                setTimeout(function () {
                    document.getElementById('logout').style.display = 'block';
                }, 800);
                var delay = 3000;
                setTimeout(function () {
                    window.location = '../logout.jsp';
                }, delay);
                return false;
            });

        </script>    
        <script>
            function demo(){
                document.getElementById('demo').style.display='block';
            }
        </script>
    </body>
</html>

