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
            <hr>
            <div class='row text-left' style="margin-left: 100px">

                <h3><center><em>INTERFACE SERVEUR</em></center></h3> 
            </div>
            <hr>

            <div class='row text-top' style="margin-top: 50px">
                <%-- sauts de lignes --%>
            </div>


            <div class='row text-left' style="margin-left: 50px">

                <h4>Bonjour ${employe.prenom} ${employe.nom}</h4>
                <div class="btn btn-success">Mes tables <span class="badge">${nbTable}</span></div>
            </div>
            <hr>
            <div class='row text-top' style="margin-top: 50px">
                <%-- sauts de lignes --%>
            </div>
            <div class='row text-left' style="margin-left: 50px">
                <div class="col-sm-8">
                    <table class="table">
                        <thead>
                            <tr>
                                <th><center>N°Table</center></th>
                        <th><center>place par table</center></th>
                        </tr>
                        </thead>
                        <tbody>                             
                            <c:forEach items="${tables}" var="t">
                                <c:url var="urlpageServeur" value="FrontControleur?section=serveur-table&serveurId=${employe.id}&tableId=${t.id}"/>
                                <c:url var="urlAddTable" value="FrontControleur?section=accueil-serveur&action=add&tableId=${t.id}"/>
                                <tr>

                                    <td><center>${t.id}</center></td>
                            <td><center>${t.nbPlace}</center></td>
                                <%--<td><center>${t.nbPlace}</center></td>
                                <%-- <td><center>${t.statut}</center></td> --%>
                            <td><center><div class="btn btn-success" ><a style="color: white" href="${urlAddTable}">[+] Table</font></div></center></td>
                            <td><center><div class="btn btn-warning" ><a style="color: white" href="${urlpageServeur}" >Voir commandes de la table</a></font></div></center></td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <%--  <div class='row text-center' style="margin-left: 100px">
                 <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">Liste Tables</button>

            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">Serveurs présents</button>
        </div> --%>
        </div>
        <%-- BAS DU CONTAINER --%>



        <div>
            <div style="height: 50px; background-color: #81bc00; margin-top: 0px">                
            </div>
            <c:url var="urlFooter" value="FrontControleur?section=includes&include=footer" />
            <c:import url="${urlFooter}" />
        </div>
    </body>
</html>