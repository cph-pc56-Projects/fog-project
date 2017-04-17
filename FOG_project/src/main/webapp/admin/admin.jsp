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
                    <a class="navbar-brand" href="#">FOG</a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">                        
                        <li><a href="#About">Create Order&nbsp;<span class="glyphicon glyphicon-plus"></span></a></li>

                    </ul>      
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#" onclick="document.getElementById('adminTools').style.display = 'block'">Admin Tools&nbsp;<span class="glyphicon glyphicon-cog"></span></a></li>
                        <li> <a href="../index.html">Log Out</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <!--Login form -->
        <div id="adminTools" class="modal">
            <form class="modal-content animate">
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
        </div>

        <div class="w3-card-2 w3-container">
            <h1>View orders by status</h1>
            <ul class="nav nav-tabs">
                <li role="presentation" class="active"><a href="#pending" id="pending-tab" role="tab" data-toggle="tab"><span class="label label-primary"><span class="badge">3</span> Pending&nbsp;<span class="glyphicon glyphicon-step-forward"></span></span></a></li>
                <li role="presentation" class=""><a href="#completed" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-success"><span class="badge">7</span> Completed&nbsp;<span class="glyphicon glyphicon-ok"></span></span></a></li>
                <li role="presentation" class=""><a href="#cancelled" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-danger"><span class="badge">1</span> Cancelled&nbsp;<span class="glyphicon glyphicon-remove"></span></span></a></li>
                <li role="presentation" class=""><a href="#dashboard" role="tab" id="profile-tab" data-toggle="tab"><span class="label label-warning">Dashboard&nbsp;<span class="glyphicon glyphicon-list-alt"></span></span></a></li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade active in" role="tabpanel" id="pending">
                    <h1><span class="glyphicon glyphicon-step-forward"></span>&nbsp;Pending here:</h1>
                    <table class="table table-bordered">
                        <th>Number</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>More info</th>

                        <tr>
                            <td>Order #1</td>
                            <td>Carport CUH02</td>
                            <td>Carport whit flat roof type, which can hold up to 2 compact vehicles.</td>
                            <td><button type="button" class="btn btn-info">Info</button></td>
                        </tr>
                        <tr>
                            <td>Order #2</td>
                            <td>Carport CRH15</td>
                            <td>Carport with "erected" roof for 1 car with max length 3.15 meters</td>
                            <td><button type="button" class="btn btn-info">Info</button></td>
                        </tr>
                        <tr>
                            <td>Order #3</td>
                            <td>Carport HXYF4</td>
                            <td>Carport for 2 cars</td>
                            <td><button type="button" class="btn btn-info">Info</button></td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" role="tabpanel" id="completed">
                    <h1><span class="glyphicon glyphicon-ok"></span>&nbsp;Completed here:</h1>
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
                    <h1><span class="glyphicon glyphicon-remove"></span>&nbsp;Cancelled here:</h1>
                    <table class="table table-bordered">
                        <th>Number</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>More info</th>

                        <tr class="danger">
                            <td>Order #0287</td>
                            <td>Carport CUH02</td>
                            <td>Carport whit flat roof type, which can hold up to 2 compact vehicles.</td>
                            <td><button type="button" class="btn btn-info">Info</button></td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane fade" role="tabpanel" id="dashboard">
                    <h1><span class="glyphicon glyphicon-list-alt"></span>&nbsp;Dashboard here:</h1>
                </div>
            </div>
        </div>

        <div class="w3-container w3-padding-16 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h2>Something else that is permanently here, no matter which tab is opened</h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam velit risus, feugiat a euismod at, lobortis vel mi. Nam tellus nibh, convallis id lacinia at, cursus et nisi. </p>
            </div>
        </div>

        <footer class="w3-container w3-padding-64 w3-center w3-opacity w3-light-grey w3-xlarge">          
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
    </body>
</html>

