<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Survey</title>
    </head>
    <body>

        <!-- NAVBAR Suite START-->
        <jsp:include page="navbarSuite.jsp" />  
        <!-- NAVBAR Suite END-->

        <!-- content starts 50px from the top, because the navbar is 50px -->
        <div style="margin-top: 50px;"></div>

        <div class="w3-container w3-padding-64 w3-content">
            <div class="w3-card-2 w3-center w3-container w3-margin">
                <h1>Customer survey</h1>
                <form class="w3-container">
                    <ol>
                        <li><label>"In one sentence describe yourself"</label>
                            <input class="w3-input w3-hover-green" type="text"></li>

                        <li><label>Where exactly did you first hear about Johannes FOG</label>
                            <input class="w3-input w3-hover-green" type="text"></li>

                        <li><label>"How would you describe us to a friend (Please write
                                the exact words you would use.)"</label>
                            <input class="w3-input w3-hover-green" type="text"></li>

                        <li><label>"How likely are you to recommend us to a friend?"</label>
                            <input class="w3-input w3-hover-green" type="text"></li>

                        <li><label>"What's the one big thing we're missing?"</label>
                            <input class="w3-input w3-hover-green" type="text"></li>

                        <li><label>"Please list the top 3 things that nearly stopped you from using us"</label>
                            <input class="w3-input w3-hover-green" type="text"></li>

                        <li><label>"Please list the top 3 things that persuaded you to use us"</label>
                            <input class="w3-input w3-hover-green" type="text"></li>
                    </ol>
                </form>
                <button type="submit">Submit answers&nbsp;<span class="glyphicon glyphicon-chevron-right"></span></button>
            </div>
        </div>

        <!-- FOOTER Suite-->
        <jsp:include page="footer.jsp" />

    </body>
</html>
