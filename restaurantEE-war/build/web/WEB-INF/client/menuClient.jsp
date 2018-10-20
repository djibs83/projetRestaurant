<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:url var="urlhead" value="FrontControleur?section=includes&include=header" />
    <c:import url="${urlhead}" />
    <body>        
        <c:url var="urlMenu" value="FrontControleur?section=includes&include=nav-main" />
        <c:import url="${urlMenu}" />
        <div class="container" style="min-height: 600px; padding-top :0px">


            <div style="text-align: center">
                <h2>Bonjour !</h2>
                <hr />
                <h1>Qu'est ce qui vous tente ?</h1>
            </div>

            <div style="margin-top: 60px">
                <div class='panel col-lg-6'>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel panel-body">
                                <div class="col-sm-12" style="text-align: center; min-height: 300px">
                                    <img src="images/salad.png" alt="menu" style="width: 30%; margin: 0 auto">
                                    <br>
                                    <h3>Menus de la semaine !</h3>
                                    <p>Choisissez une de nos formules</p>
                                    <div class="form-group" style="text-align: center">
                                        <c:url var="urlMenu" value="FrontControleur?section=affichage-catalogue-menu" />
                                        <div><a class="btn btn-warning" style="color: white" href="${urlMenu}">CHOISIR</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class='panel col-lg-6'>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel panel-body">
                                <div class='col-sm-12' style="text-align: center; min-height: 300px">
                                    <img src="images/menu.png" alt="carte" style="width: 20%; margin: 0 auto">
                                    <br>
                                    <h3>A la carte !</h3>
                                    <p>Composez vos repas au grés de vos envies</p>
                                    <div class="form-group" style="text-align: center">
                                        <c:url var="urlProduit" value="FrontControleur?section=affichage-catalogue-produit" />
                                        <div><a class="btn btn-warning" style="color: white" href="${urlProduit}">CHOISIR</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div>
                    <input type="hidden" value="table ${table.id} à Besoin aide" id="messageinput"/>
                </div>
                <div id="messages"></div>
                <div>
                    <button type="button" onclick="send();" >Send</button>
                </div>
            </div>



        </div>

        <div>
            <div style="height: 50px; background-color: #81bc00; margin-top: 0px">                
            </div>
            <c:url var="urlFooter" value="FrontControleur?section=includes&include=footer" />
            <c:import url="${urlFooter}" />
        </div>

        <script type="text/javascript">

            var webSocket;
            var messages = document.getElementById("messages");
            // Create a new instance of the websocket
            webSocket = new WebSocket("ws://10.75.240.133:8080/restaurantEE-war/echo");

            /**
             * Binds functions to the listeners for the websocket.
             */
            webSocket.onopen = function (event) {
                // For reasons I can't determine, onopen gets called twice
                // and the first time event.data is undefined.
                // Leave a comment if you know the answer.
                if (event.data === undefined)
                    return;
                writeResponse(event.data);
            };


            webSocket.onmessage = function (event) {
                /*location.reload();*/
                writeResponse(event.data);
            };


            webSocket.onclose = function (event) {
                writeResponse("Connection closed");
            };


            /**
             * Sends the value of the text input to the server
             */
            function send() {
                var text = document.getElementById("messageinput").value;
                webSocket.send(text);
            }

            function closeSocket() {
                webSocket.close();
            }

            function writeResponse(text) {
                messages.innerHTML = "<br/>" + text;
            }

        </script>
    </body>
</html>
