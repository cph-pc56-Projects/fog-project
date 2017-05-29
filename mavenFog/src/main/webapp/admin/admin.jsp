<%@page import="exceptions.ConnectionException"%>
<%@page import="model.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    User user = null;
    User userOrder = null;
    Product product = null;
    Order order = null;
%>
<% String errorMessage = ""; %>
<%
    if (session.getAttribute("error") != null) {
        errorMessage = ConnectionException.getExceptionMessage((String) session.getAttribute("error"), session);
        session.removeAttribute("error"); %>
<!--Make modal Login to be visible if the first login attempt was failed-->
<script>
    // Get the modal
    var modal = document.getElementById('exceptionModal');
    modal.style.display = 'block';
</script>
<% } %>
<%
    if (session.getAttribute("user") != null) {
        user = (User) session.getAttribute("user");
    } else if (session.getAttribute("user") == null || user.getRole() == 0) {
        response.sendRedirect("../index.jsp");
    }
%>
<% ArrayList<Order> orders = null; %>
<% ArrayList<Delivery> deliveries = null; %>
<% ArrayList<Invoice> invoices = null; %>
<% ArrayList<User> users = null; %>
<% ArrayList<Product> products = null; %>
<% orders = (ArrayList<Order>) session.getAttribute("orders"); %>
<% deliveries = (ArrayList<Delivery>) session.getAttribute("deliveries"); %>
<% invoices = (ArrayList<Invoice>) session.getAttribute("invoices"); %>
<% users = (ArrayList<User>) session.getAttribute("users"); %>
<% products = (ArrayList<Product>) session.getAttribute("products"); %>
<html>
    <head>
        <title>Admin page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Import end here -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
        <!-- External links to documents -->
        <link rel="stylesheet" href="../css/style.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <!-- Data Tables -->
        <script src="../js/jquery.dataTables.min.js"></script>
        <script src="../js/dataTables.bootstrap.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>$(function () {
                $("#datepicker").datepicker();
                $("#datepicker").datepicker("option", "dateFormat", "yy-mm-dd");
            });</script>
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="admin.jsp">FOG Admin</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">    
                    <ul class="nav navbar-nav navbar-right">
                        <% if (user.getRole() == 2) { %>
                        <li>
                            <a href="#" id="dropdownMenu0" data-toggle="dropdown">Admin Tools&nbsp;<span class="glyphicon glyphicon-cog"></span><span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu0">
                                <li><a onclick="document.getElementById('CreateSalesRep').style.display = 'block'" href="#">Create new Sales Rep</a></li>
                                <li><a onclick="document.getElementById('DeleteSalesRep').style.display = 'block'" href="#">Delete Sales Rep</a></li>
                            </ul>
                        </li>
                        <% }%>
                        <!-- HERE WHEN LOGGED IN DIV -->
                        <li>
                            <a href="#" id="dropdownMenu1" data-toggle="dropdown"><%= user.getEmail()%>&nbsp;<span class="caret"></span></a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a id="logoutFunction" href="#">Log out</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Create NEW Sales Rep -->
        <div id="CreateSalesRep" class="modal" style="overflow-y: scroll; z-index: 4;">
            <form class="modal-content animate" action="../Register" method="post">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('CreateSalesRep').style.display = 'none'" class="close"  title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Create new Sales Rep</h1>
                </div>


                <div class="loginContainer">
                    <label><b>Email</b></label>
                    <input type="text" placeholder="salesrep@fog.dk" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" title="customer@fog.dk" class="inputFields" required>
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
                    <input type="text" placeholder="Firskovvej 20" name="adress" title="e.g. Street" required>
                    <label><b>Zip code</b></label>
                    <input type="text" placeholder="Local post code" name="zipCode" title="e.g. 2800" pattern="[0-9]{4}" class="inputFields" required>
                    <!-- current URL passed like a hidden field, so after Register, the Servlet will redirect the user back to the same page -->
                    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
                    <!--create userType = 1 means that this user will have role = admin in our DataBase--> 
                    <input type="hidden" name="userType" value="1">

                    <button type="submit" class="btn" id="RegButton">Register</button>
                    <button type="button" onclick="document.getElementById('CreateSalesRep').style.display = 'none'" class="cancelbtn">Close</button>
                </div>
            </form>
        </div>
        <!-- Create NEW Sales Rep END -->

        <!-- Delete Sales Rep -->
        <div id="DeleteSalesRep" class="modal" style="overflow-y: scroll; z-index: 4;">
            <form class="modal-content animate" action="../Register" method="post">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('DeleteSalesRep').style.display = 'none'" class="close"  title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Delete Sales Rep</h1>
                </div>
                <div class="loginContainer w3-center">
                    <label><b>Sales Rep Account ID:</b></label>
                    <input type="text" placeholder="e.g 2349493941" name="deleteAccountID" pattern="[0-9]{1,10}$" title="SalesRepID (max.10)" class="inputFields" required>
                    <!-- current URL passed like a hidden field, so after Deleting, the Servlet will redirect the user back to the same page -->
                    <input type="hidden" name="from" value="${pageContext.request.requestURI}">
                    <!-- send  userType = 3 means that this user will be deleted from our DataBase--> 
                    <input type="hidden" name="userType" value="3">

                    <button type="submit" class="btn" id="RegButton">Delete Sales Rep</button>
                    <button type="button" onclick="document.getElementById('DeleteSalesRep').style.display = 'none'" class="btn btn-danger">Close</button>
                </div>
            </form>
        </div>
        <!-- Delete Sales Rep END -->

        <!-- areYouSure modal -->
        <div id="areYouSure" class="modal" style="overflow-y: scroll; z-index: 5;">
            <form class="modal-content animate" action="../Admin" method="post">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('areYouSure').style.display = 'none'" class="close"  title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Are you Sure ?</h1>
                </div>
                <div class="loginContainer w3-center">
                    <label><b>(This will delete the order and cannot be undone!)</b></label><br>
                    <input type="hidden" name="admin" value="deleteOrder">
                    <button type="submit" class="btn btn-danger btn-lg" id="RegButton">Delete Order</button>
                    <button type="button" class="btn btn-warning btn-lg" onclick="document.getElementById('areYouSure').style.display = 'none'">Cancel</button>
                </div>
            </form>
        </div>
        <!-- areYouSure modal END -->

        <% if (session.getAttribute("popupFinalise") != null && session.getAttribute("popupFinalise").equals("yes")) {
                session.removeAttribute("popupFinalise"); %>
        <!-- Finalise -->
        <div id="Finalise" class="modal" style="overflow-y: scroll; z-index: 4;">
            <form class="modal-content animate" action="../Admin" method="post">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('Finalise').style.display = 'none'" class="close"  title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Finalise an Order</h1>
                </div>

                <script>
                    // Get the modal
                    var modal = document.getElementById('Finalise');
                    modal.style.display = 'block';
                </script>

                <div class="loginContainer">
                    <!--customer info-->
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-hover table-bordered table-responsive">
                                            <h2><span class="label label-default">Customer information:</span></h2>
                                            <thead>
                                                <tr>
                                                    <th>Account ID</th>
                                                    <th>Email</th>
                                                    <th>First Name</th>
                                                    <th>Last Name</th>
                                                    <th>Phone</th>
                                                    <th>Address</th>
                                                    <th>Zip Code</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="active">
                                                    <% userOrder = (User) session.getAttribute("userOrder");%>
                                                    <td><%= userOrder.getAccountID()%></td>
                                                    <td><%= userOrder.getEmail()%></td>
                                                    <td><%= userOrder.getfName()%></td>
                                                    <td><%= userOrder.getlName()%></td>
                                                    <td><%= userOrder.getPhone()%></td>
                                                    <td><%= userOrder.getAddress()%></td>
                                                    <td><%= userOrder.getZipCode()%></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!-- product info -->
                                        <table class="table table-hover table-bordered table-responsive">
                                            <h2><span class="label label-default">Product information:</span></h2>
                                            <thead>
                                                <tr>
                                                    <th>Product ID</th>
                                                    <th>Price</th>
                                                    <th>Inner Height</th>
                                                    <th>Width</th>
                                                    <th>Length</th>
                                                    <th>Has Shed?</th>
                                                    <th>Rooftop Type</th>
                                                    <th>Name</th>
                                                    <th>Roof Angle</th>
                                                    <th>Shed Width</th>
                                                    <th>Shed Length</th>
                                                    <th>Roof Height</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="warning">
                                                    <% product = (Product) session.getAttribute("product");%>
                                                    <td><%= product.getProductID()%></td>
                                                    <td><%= product.getPrice()%></td>
                                                    <td><%= product.getInnerHeight()%></td>
                                                    <td><%= product.getWidth()%></td>
                                                    <td><%= product.getLength()%></td>
                                                    <td><%= product.getHasShed()%></td>
                                                    <td><%= product.getRooftopType()%></td>
                                                    <td><%= product.getName()%></td>
                                                    <td><%= product.getRoofAngle()%></td>
                                                    <td><%= product.getShedWidth()%></td>
                                                    <td><%= product.getShedLength()%></td>
                                                    <td><%= product.getRooftopHeight()%></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!-- order info -->
                                        <table class="table table-hover table-bordered table-responsive">
                                            <h2><span class="label label-default">Order information:</span></h2>
                                            <thead>
                                                <tr>
                                                    <th>Order ID</th>
                                                    <th>Creation Date</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr class="info">
                                                    <% order = (Order) session.getAttribute("order");%>
                                                    <td><%= order.getOrderID()%></td>
                                                    <td><%= order.getDate()%></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <!-- 5-->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="panel panel-info">
                                    <div class="panel-heading">Pick Delivery Date:</div>
                                    <div class="panel-body">
                                        <input type="text" placeholder="Pick Date" id="datepicker" name="deliveryDate" size="30" required>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="admin" value="createInvoice">
                            <div class="col-lg-6">
                                <button type="button" onclick="document.getElementById('areYouSure').style.display = 'block'" class="btn btn-danger btn-lg"><span class="glyphicon glyphicon-remove"></span>&nbsp;Delete Order</button>
                                <button type="submit" class="btn btn-warning btn-lg pull-right"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Create Invoice</button>
                            </div>
                            <!-- form end-->
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <% }%>
        <!-- Finalise END -->

        <!--Logout modal -->
        <div id="logout" class="modal">
            <form class="modal-content animate">
                <div class="imgcontainer">
                    <h1 class="w3-container ">You are logged out!</h1>
                    <p class="w3-container ">(You will be redirected after 3 seconds...)</p>
                </div>
            </form>
        </div><!-- Logout END -->
        
        <!-- Exception modal-->
        <div id="exceptionModal" class="modal">
            <form class="modal-content animate" action="Carport">
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

        <!--PAGE CONTENT-->
        <div class="w3-card-2 w3-container">
            <h1>Your Admin Dashboard</h1>
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#pending" role="tab" id="pending-tab" data-toggle="tab"><span class="label label-default"><span class="badge"></span> Pending&nbsp;<span class="glyphicon glyphicon-step-forward"></span></span></a></li>
                <li role="presentation" class=""><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#completed" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-default">Completed Orders&nbsp;<span class="glyphicon glyphicon-th-list"></span></span></a></li>
                <li role="presentation" class=""><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#delivery" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-default"><span class="badge"><%= deliveries.size()%></span> Deliveries&nbsp;<span class="glyphicon glyphicon-send"></span></span></a></li>
                <li role="presentation" class=""><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#invoice" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-default"><span class="badge"><%= invoices.size()%></span> Invoices&nbsp;<span class="glyphicon glyphicon-list-alt"></span></span></a></li>
                <li role="presentation" class=""><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#product" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-default"><span class="badge"><%= products.size()%></span> Products&nbsp;<span class="glyphicon glyphicon-home"></span></span></a></li>
                <li role="presentation" class=""><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#user" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-default"><span class="badge"><%= users.size()%></span> Users&nbsp;<span class="glyphicon glyphicon-user"></span></span></a></li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <!--PENDING TAB-->
                <div class="tab-pane fade active in" role="tabpanel" id="pending" style="box-shadow: 10px 10px 5px #888888;">
                    <h1><span class="glyphicon glyphicon-step-forward"></span>&nbsp;Pending Orders:</h1>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <% if (orders.isEmpty()) { %>
                                        <div class="well well-lg">
                                            <p>There are no Pending Orders in the moment!</p>
                                            <p>Sit comfortably , and refresh the page per 10 seconds :)</p>
                                        </div>
                                        <% } else { %>

                                        <table class="table table-bordered table-hover table-striped mydata">
                                            <thead>
                                                <tr>
                                                    <th class="center-table">Order ID&nbsp;&nbsp;<span class="glyphicon glyphicon-sort-by-attributes"></span></th>
                                                    <th class="center-table">Product ID&nbsp;&nbsp;<span class="glyphicon glyphicon-sort-by-attributes"></span></th>
                                                    <th class="center-table">Ordered on&nbsp;&nbsp;<span class="glyphicon glyphicon-sort-by-attributes"></span></th>
                                                    <th class="center-table">Customer ID&nbsp;&nbsp;<span class="glyphicon glyphicon-sort-by-attributes"></span></th>
                                                    <th class="center-table">Finalise&nbsp;&nbsp;<span class="glyphicon glyphicon-sort-by-attributes"></span></th>
                                            </thead>
                                            <tbody>
                                                <% for (Order pending : orders) {%>
                                                <% if(pending.getOrderStatus()==0) { %>
                                                <tr class="info">
                                                        
                                                    <td><%= pending.getOrderID()%></td>
                                                    <td><%= pending.getProductID()%></td>
                                                    <td><%= pending.getDate()%></td>
                                                    <td><%= pending.getCustomerID()%></td>
                                                    <td>
                                                        <form action="../Admin" method="POST">
                                                            <input type="hidden" name="orderID" value="<%=pending.getOrderID()%>">
                                                            <input type="hidden" name="admin" value="finaliseOrder">
                                                            <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-flag"></span>&nbsp;Finalise</button>
                                                        </form>
                                                    </td>
                                                </tr>
                                                <% } } %>
                                            </tbody>
                                        </table>

                                        <% } %>
                                    </div><!-- table responsive -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--Completed Orders TAB-->
                <div class="tab-pane fade" role="tabpanel" id="completed" style="box-shadow: 10px 10px 5px #888888;">
                    <h1><span class="glyphicon glyphicon-th-list"></span>&nbsp;Completed Orders:</h1>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped mydata">
                                            <thead>
                                                <tr>
                                                    <th class="center-table">Order ID</th>
                                                    <th class="center-table">Product ID</th>
                                                    <th class="center-table">Ordered on</th>
                                                    <th class="center-table">Customer ID</th>
                                                    <th class="center-table">SalesRep ID</th>
                                                    <th class="center-table">Delivery ID</th>
                                                    <th class="center-table">Invoice ID</th>
                                                    <th class="center-table">Status</th>
                                            </thead>
                                            <tbody>
                                                <% for (Order completed : orders) {
                                                        if (completed.getOrderStatus() != 0) {%>
                                                <tr class="<% if (completed.getOrderStatus() == 1) {
                                                        out.print("success");
                                                    } else {
                                                        out.print("danger");
                                                    };%>">
                                                    <td><%= completed.getOrderID()%></td>
                                                    <td><%= completed.getProductID()%></td>
                                                    <td><%= completed.getDate()%></td>
                                                    <td><%= completed.getCustomerID()%></td>
                                                    <td><%= completed.getSalesRepID()%></td>
                                                    <td><%= completed.getDeliveryID()%></td>
                                                    <td><%= completed.getInvoiceID()%></td>
                                                    <td><% if (completed.getOrderStatus() == 1) {
                                                            out.print("Successful");
                                                        } else if (completed.getOrderStatus() == 2) {
                                                            out.print("Cancelled");
                                                        } else {
                                                            out.print("###Pending!!!");
                                                        }%></td>
                                                </tr>
                                                <% } %>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div><!-- table responsive -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--DELIVERY TAB-->
                <div class="tab-pane fade" role="tabpanel" id="delivery" style="box-shadow: 10px 10px 5px #888888;">
                    <h1><span class="glyphicon glyphicon-send"></span>&nbsp;Deliveries:</h1>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped mydata">
                                            <thead>
                                                <tr>
                                                    <th>Order ID</th>
                                                    <th>Delivery ID</th>
                                                    <th>Delivery Status</th>
                                                    <th>Delivery Price</th>
                                                    <th>Date for Delivery</th>
                                                    <th>More info</th>
                                                    <th>Change Date</th>
                                            </thead>
                                            <tbody>
                                                <% for (Delivery delivery : deliveries) {%>
                                                <tr class="<%if (delivery.getDeliveryStatus() == 0) {
                                                        out.print("info");
                                                    } else if (delivery.getDeliveryStatus() == 1) {
                                                        out.print("success");
                                                    } else {
                                                        out.print("danger");
                                                    };%>">
                                                    <td><%= delivery.getOrderID()%></td>
                                                    <td><%= delivery.getDeliveryID()%></td>
                                                    <td><% if (delivery.getDeliveryStatus() == 1) {
                                                            out.print("Successful");
                                                        } else if (delivery.getDeliveryStatus() == 2) {
                                                            out.print("Cancelled");
                                                        } else {
                                                            out.print("Pending");
                                                        }%></td>
                                                    <td><%= delivery.getPrice()%></td>
                                                    <td><%= delivery.getDeliveryDate()%></td>
                                                    <td><% if (delivery.getMoreInfo() == null) {
                                                            out.print("N/A");
                                                        } else {
                                                            out.print(delivery.getMoreInfo());
                                                        }%></td>
                                                    <td><% if (delivery.getDeliveryStatus() == 0) { %>
                                                        <button type="button" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span>&nbsp;Complete</button>
                                                        <button type="button" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>&nbsp;Cancel</button>
                                                        <% } else { %>
                                                        <button type="button" class="btn btn-info disabled"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Cannot Change</button>
                                                        <% } %>
                                                    </td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!--INVOICE TAB-->
                <div class="tab-pane fade" role="tabpanel" id="invoice" style="box-shadow: 10px 10px 5px #888888;">
                    <h1><span class="glyphicon glyphicon-list-alt"></span>&nbsp;Invoices:</h1>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped mydata">
                                            <thead>
                                                <tr>
                                                    <th>Invoice ID</th>
                                                    <th>Order ID</th>
                                                    <th>Product ID</th>
                                                    <th>Created on</th>
                                                    <th>Customer ID</th>
                                                    <th>SalesRep ID</th>
                                                    <th>Total Price</th>
                                            </thead>
                                            <tbody>
                                                <% for (Invoice invoice : invoices) {%>
                                                <tr>
                                                    <td><%= invoice.getInvoiceID()%></td>
                                                    <td><%= invoice.getOrderID()%></td>
                                                    <td><%= invoice.getProductID()%></td>
                                                    <td><%= invoice.getCreationDate()%></td>
                                                    <td><%= invoice.getCustomerID()%></td>
                                                    <td><%= invoice.getSalesRepID()%></td>
                                                    <td><%= invoice.getTotalPrice()%></td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- PRODUCTS TAB-->
                <div class="tab-pane fade" role="tabpanel" id="product" style="box-shadow: 10px 10px 5px #888888;">
                    <h1><span class="glyphicon glyphicon-home"></span>&nbsp;Products:</h1>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped mydata">
                                            <thead>
                                                <tr>
                                                    <th>Product ID</th>
                                                    <th>Product name</th>
                                                    <th>Price</th>
                                                    <th>Roof Type</th>
                                                    <th>Inner Height</th>
                                                    <th>Rooftop Height</th>
                                                    <th>Rooftop Angle</th>
                                                    <th>Overall Width</th>
                                                    <th>Overall Length</th>
                                                    <th>Has Shed Room ?</th>
                                                    <th>Shed Width</th>
                                                    <th>Shed Length</th>
                                            </thead>
                                            <tbody>
                                                <% for (Product forProduct : products) {%>
                                                <tr>
                                                    <td><%= forProduct.getProductID()%></td>
                                                    <td><%= forProduct.getName()%></td>
                                                    <td><%= forProduct.getPrice()%></td>
                                                    <td><%= forProduct.getRooftopType()%></td>
                                                    <td><%= forProduct.getInnerHeight()%></td>
                                                    <td><%= forProduct.getRooftopHeight()%></td>
                                                    <td><%= forProduct.getRoofAngle()%></td>
                                                    <td><%= forProduct.getWidth()%></td>
                                                    <td><%= forProduct.getLength()%></td>
                                                    <td><% if (forProduct.getHasShed() == 1) {
                                                            out.print("yes");
                                                        } else {
                                                            out.print("no");
                                                        }%></td>
                                                    <td><%= forProduct.getShedWidth()%></td>
                                                    <td><%= forProduct.getShedLength()%></td>
                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- USERS TAB-->
                <div class="tab-pane fade" role="tabpanel" id="user" style="box-shadow: 10px 10px 5px #888888;">
                    <h1><span class="glyphicon glyphicon-user"></span>&nbsp;Users:</h1>
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-body">
                                    <div class="table-responsive">
                                        <table class="table table-bordered table-hover table-striped mydata">
                                            <thead>
                                                <tr>
                                                    <th>User ID</th>
                                                    <th>First name</th>
                                                    <th>Last name</th>
                                                    <th>Phone</th>
                                                    <th>Address</th>
                                                    <th>Zip Code</th>
                                                    <th>Email</th>
                                                    <th>Status</th>
                                            </thead>
                                            <tbody>
                                                <% for (User userCell : users) {%>
                                                <tr class="<%if (userCell.getUserStatus() == 0) {
                                                        out.print("danger");
                                                    };%>">
                                                    <td><%= userCell.getAccountID()%></td>
                                                    <td><%= userCell.getfName()%></td>
                                                    <td><%= userCell.getlName()%></td>
                                                    <td><%= userCell.getPhone()%></td>
                                                    <td><%= userCell.getAddress()%></td>
                                                    <td><%= userCell.getZipCode()%></td>
                                                    <td><%= userCell.getEmail()%></td>
                                                    <td><%= userCell.getUserStatus() %>
                                                    </td>

                                                </tr>
                                                <% }%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!--id="myTabContent" END-->
        </div>
        <!--PAGE CONTENT END-->

        <div class="w3-container w3-padding-16 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2>Legend & Tips how to use the admin part of our IT System</h2>
                <p>Something else that is permanently here, no matter which tab is opened</p>
                <ul style="list-style-type:none">
                    <li>Order status:  <span class="label label-primary">0 - Pending</span> , <span class="label label-success">1 - Success</span> , <span class="label label-danger">2 - Cancelled</span></li>
                    <li>Delivery status:  <span class="label label-primary">0 - Pending</span> , <span class="label label-success">1 - Success</span> , <span class="label label-danger">2 - Cancelled</span></li>
                </ul>
            </div>
        </div>

        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-white w3-xlarge">          
            <h3>Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</h3>
        </footer>

        <!-- Close Create new ,Delete Sales Rep, Finalise, areYouSurel  modals -->
        <script>
            // Get the modal
            var Createmodal = document.getElementById('CreateSalesRep');
            var Deletemodal = document.getElementById('DeleteSalesRep');
            var areYouSurel = document.getElementById('areYouSure');

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function (event) {
                if (event.target == Createmodal || event.target == Deletemodal || event.target == Finalise || event.target == areYouSurel) {
                    Createmodal.style.display = "none";
                    Deletemodal.style.display = "none";
                    Finalise.style.display = "none";
                    areYouSurel.style.display = "none";
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
                    window.location = '../logout.jsp';
                }, delay);
                return false;
            });

        </script>
        <script>
            $('.mydata').dataTable();
        </script>
    </body>
</html>

