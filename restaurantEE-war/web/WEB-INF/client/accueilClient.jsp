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

            <div class='row text-center' style="margin-top: 190px">

                <h1>Bienvenue !</h1>

                <div>
                    <input type="hidden" value="table ${table.id} à Besoin aide" id="messageinput"/>
                </div>
                <div id="messages"></div>

                <div>
                    <button type="button" onclick="send();" >Send</button>
                </div>

                <div class="btn btn-warning" data-toggle="modal" data-target="#myModal">Nouvelle commande</div>
                <!-- Modal -->
                <div class="modal fade text-left" id="myModal" role="dialog">
                    <div class="modal-dialog">

                        <!-- Modal contenu-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Saisissez un Nom ou Pseudo</h4>
                                <p>Facultatif *</p>
                            </div>
                            <div class="modal-body">                                   
                                <form action="FrontControleur" method="POST" accept-charset='UTF-8'>

                                    <div class="form-group">
                                        <input type="hidden" name="section" value="menu-Client"/>
                                        <label for="nom">Nom : *</label>
                                        <input class="form-control" name="nom"/>
                                        <br>
                                        <label for="prenom">Prenom : *</label>
                                        <input class="form-control" name="prenom"/>
                                        <p><i>Recevez nos promotions en exclusivité !</i></p>
                                        <label for="email">Email : *</label>
                                        <input class="form-control" type="email" name="email"/>
                                        <label for="telephone">Téléphone : *</label>
                                        <input class="form-control" name="telephone"/>
                                    </div>
                                    <div class="form-group" style="text-align: center">
                                        <button type="submit" name="valider" class="btn btn-warning" >Valider</button>
                                    </div>
                                </form>                                                  
                            </div>                                              
                        </div>
                    </div>
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
                location.reload();
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
                messages.innerHTML += "<br/>" + text;
            }

        </script>

    </body>
</html>
