<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:url var="urlhead" value="FrontControleur?section=includes&include=header" />
    <c:import url="${urlhead}" />
    <body>        
        <c:url var="urlMenu" value="FrontControleur?section=includes&include=nav-main" />
        <c:import url="${urlMenu}" />

        <%-- HAUT DU CONTAINER --%>
        <div class="container" style="min-height: 600px; padding-top :0px">


            <h3><center><em>INTERFACE SERVEUR</em></center></h3> 
            <div class='row text-left' style="margin-left: 100px">


            </div>
            <hr>

            <div class='row text-top' style="margin-top: 50px">
                <%-- sauts de lignes --%>
            </div>


            <div class='row text-left' style="margin-left: 50px">

                <h4>${employe.prenom} ${employe.nom}</h4>

            </div>
            <hr>
            <div class='row text-top' style="margin-top: 50px">
                <%-- sauts de lignes --%>
            </div>
            <div class='row text-left' style="margin-left: 50px">
                <label> Nombre de place pour cette table: ${table.nbPlace}  numéro de la table: ${table.id}</label>
                <div>
                    <input type="hidden" value="message reçu" id="messageinput"/>
                    <button type="button" onclick="send();" >Send</button>
                </div>
                <div id="messages"></div>

            </div>
            <div class='row text-top' style="margin-top: 50px">
                <%-- sauts de lignes --%>
            </div>
            <div class="col-sm-6">
                <table class="table">
                    <thead>
                        <tr>
                            <th><center>Date</center></th>
                    <th><center>Commande</center></th>
                    <th><center>Status</center></th>
                    </tr>
                    </thead>
                    <tbody>                             
                        <c:forEach items="${commandes}" var="c">
                            <c:url var="urlx" value="FrontControleur?section=serveur-table&serveurId=${employe.id}&tableId=${t.id}"/>                       
                                   
                                    <tr>

                            <tr>

                                <td><center>${c.dateCommande}</center></td>
                        <td><center>${c.id}</center></td>

                        <td><center>${c.statut.libelle}</center></td>
                        <td><center><div class="btn btn-success" data-toggle="modal" data-target="#myModal">+</div></center></td>



                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%-- BAS DU CONTAINER --%>


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