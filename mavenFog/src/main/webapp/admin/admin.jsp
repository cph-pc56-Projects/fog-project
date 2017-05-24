<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand" href="admin.jsp">FOG</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">                        
                        <li><a href="#About">Create Order&nbsp;<span class="glyphicon glyphicon-plus"></span></a></li>

                    </ul>      
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#" onclick="document.getElementById('adminTools').style.display = 'block'">Admin Tools&nbsp;<span class="glyphicon glyphicon-cog"></span></a></li>
                        <li><a id="logoutFunction" href="#">Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--Login form -->
        <div id="adminTools" class="modal">
            <form class="modal-content animate" style="margin: 140px auto">
                <div class="imgcontainer">
                    <span onclick="document.getElementById('adminTools').style.display = 'none'" class="close" title="Close Modal">&times;</span>
                    <h1 class="w3-container ">Admin Tools&nbsp;<span class="glyphicon glyphicon-cog"></span></h1>
                </div>
                <div class="container-fluid">
                    <div class="w3-card-2 w3-center w3-container w3-margin">
                        <div class="row">
                            <div class="col-md-6 col-lg-6">
                                <a href="#" class="thumbnail">
                                    <img src="../media/pictures/createNewUser.png">
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-6">
                                <a href="#" class="thumbnail">
                                    <img src="../media/pictures/removeUser.png">
                                </a>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 col-lg-6">
                                <a href="#" class="thumbnail">
                                    <img src="../media/pictures/editUser.png">
                                </a>
                            </div>
                            <div class="col-md-6 col-lg-6">
                                <a href="#" class="thumbnail">
                                    <img src="../media/pictures/smth.png">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div> <!-- Login END here -->
        
        <!--Logout modal -->
        <div id="logout" class="modal">
            <form class="modal-content animate">
            <div class="imgcontainer">
                    
                    <h1 class="w3-container ">You are logged out!</h1>
                    <p class="w3-container ">(You will be redirected after 3 seconds...)</p>
                </div>
            </form>
        </div><!-- Logout END -->

        <div class="w3-card-2 w3-container">
            <h1>View orders by status</h1>
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#pending" role="tab" id="pending-tab" data-toggle="tab"><span class="label label-default"><span class="badge">3</span> Pending&nbsp;<span class="glyphicon glyphicon-step-forward"></span></span></a></li>
                <li role="presentation" class=""><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#completed" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-default"><span class="badge">7</span> All Orders&nbsp;<span class="glyphicon glyphicon-th-list"></span></span></a></li>
                <li role="presentation" class=""><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#delivery" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-default"><span class="badge">1</span> Delivery&nbsp;<span class="glyphicon glyphicon-send"></span></span></a></li>
                <li role="presentation" class=""><a class="btn btn-default btn-lg active" style="box-shadow: 10px 10px 5px #888888;" href="#invoice" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-default"><span class="badge">4</span> Invoice&nbsp;<span class="glyphicon glyphicon-list-alt"></span></span></a></li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active in" role="tabpanel" id="pending" style="box-shadow: 10px 10px 5px #888888;">
                    <h1><span class="glyphicon glyphicon-step-forward"></span>&nbsp;Pending Orders:</h1>
                    <div class="table-responsive">
                    <table class="table table-bordered">
                        <th>Order ID</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Ordered on</th>
                        <th>Customer email</th>
                        <th>Customer ID</th>
                        <th>Finalise</th>

                        <tr class="info">
                            <td>#0001</td>
                            <td>Carport CUH02</td>
                            <td>3.495,- dkk</td>
                            <td>2017-05-18</td>
                            <td>andrian@fog.dk</td>
                            <td>0245</td>
                            <td><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-flag"></span>&nbsp;Finalise</button></td>
                        </tr>
                        <tr class="info">
                            <td>#0001</td>
                            <td>Carport CUR02HR</td>
                            <td>12.295,- dkk</td>
                            <td>2017-05-19</td>
                            <td>pesho@fog.dk</td>
                            <td>0012</td>
                            <td><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-flag"></span>&nbsp;Finalise</button></td>
                        </tr>
                        <tr class="info">
                            <td>#0001</td>
                            <td>Carport CUR02</td>
                            <td>8.492,- dkk</td>
                            <td>2017-05-20</td>
                            <td>sexypink69@fog.dk</td>
                            <td>0312</td>
                            <td><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-flag"></span>&nbsp;Finalise</button></td>
                        </tr>
                    </table>
                    </div>
                </div>
                <div class="tab-pane fade" role="tabpanel" id="completed" style="box-shadow: 10px 10px 5px #888888;">
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select Completed</button>
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select by Customer</button>
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select by Sales Rep</button>
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select by Product</button>
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select by Order ID</button>
                    <h1><span class="glyphicon glyphicon-th-list"></span>&nbsp;All Orders:</h1>
                    <div class="table-responsive">
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
                </div>
                <div class="tab-pane fade" role="tabpanel" id="delivery" style="box-shadow: 10px 10px 5px #888888;">
                    
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select Pending</button>
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select by Date</button>
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select by Order ID</button>
                    <h1><span class="glyphicon glyphicon-send"></span>&nbsp;Delivery:</h1>
                    <div class="table-responsive">
                    <table class="table table-bordered">
                        <th>Order ID</th>
                        <th>Delivery ID</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Ordered on</th>
                        <th>Ordered for</th>
                        <th>Customer email</th>
                        <th>Customer ID</th>
                        <th>Sales Rep email</th>
                        <th>Change Date</th>

                        <tr class="info">
                            <td>#0001</td>
                            <td>#0001</td>
                            <td>Carport CUH02</td>
                            <td>3.495,- dkk</td>
                            <td>2017-05-18</td>
                            <td>Pending</td>
                            <td>andrian@fog.dk</td>
                            <td>0245</td>
                            <td>admin1@fog.dk</td>
                            <td><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Change</button></td>
                        </tr>
                        <tr class="success">
                            <td>#0002</td>
                            <td>#0002</td>
                            <td>Carport CUH02</td>
                            <td>3.495,- dkk</td>
                            <td>2017-05-18</td>
                            <td>2017-05-23<br>Sent</td>
                            <td>peter@fog.dk</td>
                            <td>0345</td>
                            <td>admin2@fog.dk</td>
                            <td><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Change</button></td>
                        </tr>
                        <tr class="danger">
                            <td>#0003</td>
                            <td>#0003</td>
                            <td>Carport CUR01RH</td>
                            <td>7.495,- dkk</td>
                            <td>2017-04-19</td>
                            <td>2017-05-23 <br>Canceled</td>
                            <td>peter@fog.dk</td>
                            <td>0345</td>
                            <td>admin2@fog.dk</td>
                            <td><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Change</button></td>
                        </tr>
                        <tr class="info">
                            <td>#0004</td>
                            <td>#0004</td>
                            <td>Carport CUR02H</td>
                            <td>12.495,- dkk</td>
                            <td>2017-05-18</td>
                            <td>Pending</td>
                            <td>callub21emo@fog.dk</td>
                            <td>0045</td>
                            <td>admin1@fog.dk</td>
                            <td><button type="button" class="btn btn-info"><span class="glyphicon glyphicon-pencil"></span>&nbsp;Change</button></td>
                        </tr>
                    </table>
                    </div>
                </div>
                <div class="tab-pane fade" role="tabpanel" id="invoice" style="box-shadow: 10px 10px 5px #888888;">
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select by Order ID</button>
                    <button type="button" class="btn btn-default" style="box-shadow: 10px 10px 5px #888888;">Select by Customer</button>
                    <h1><span class="glyphicon glyphicon-list-alt"></span>&nbsp;Invoice:</h1>
                </div>
            </div>
        </div>

        <div class="w3-container w3-padding-16 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2>Legend & Tips how to use the admin part of our IT System</h2>
                <p>Something else that is permanently here, no matter which tab is opened</p>
                <ul style="list-style-type:none">
                    <li>Order status:  <span class="label label-primary">0 - Pending</span> , <span class="label label-success">1 - Success</span> , <span class="label label-danger">2 - Cancelled</span></li>
                </ul>
            </div>
        </div>

        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-white w3-xlarge">          
            <h3>Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439</h3>
        </footer>

        <script>
                        // Get the modal
                        var modal = document.getElementById('adminTools');

                        // When the user clicks anywhere outside of the modal, close it
                        window.onclick = function (event) {
                            if (event.target === modal) {
                                modal.style.display = "none";
                            }
                        }
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
    </body>
</html>

