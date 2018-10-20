<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:url var="urlhead" value="FrontControleur?section=includes&include=header" />
    <c:import url="${urlhead}" />
    <body>        
        <c:url var="urlMenu" value="FrontControleur?section=nav-main" />
        <c:import url="${urlMenu}" />

        <%-- HAUT DU CONTAINER --%>
        <div class="container" style="min-height: 600px; padding-top :0px">
            <hr>
            <div class='row text-left' style="margin-left: 100px">

                <h3><center><em>INTERFACE SERVEUR</em></center></h3>
                <h3><center><em>PERSO</em></center></h3>
            </div>
            <hr>

            <div class='row text-top' style="margin-top: 50px">
                <%-- sauts de lignes --%>
            </div>


            <div class='row text-left' style="margin-left: 50px">

                <h4>Bonjour ${employe.prenom} ${employe.nom}</h4>
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
                                <th><center>Nombre de place par table</center></th>
                        <th><center></center></th>
                        </tr>

                        </thead>
                        <tbody>
                            <c:url var="urlpageServeur" value="FrontControleur?section=page-serveur&id=${e.id}"/> 
                            <c:forEach items="${tables}" var="t">
                              
                                
                                <tr>
                                    <td><center>${t.nbPlace}</center></td>
                           <%-- <td><center>${t.statut}</center></td> --%>
    <td><center><div class="btn btn-success" ><a style="color: white" href="${urlpageServeur}" >Voir les commandes</a></font></div></center></td>
    <td><center><div class="btn btn-danger" ><a style="color: white" href="${urlAddTable}">+ Table</a></font></div></center></td>
    <%-- 
    <td>
                                                            <c:url var="urlAddProduit" value="FrontControleur?section=operation-panier&origine=catalogue&type=carte&action=add&id=${p.id}&categorieId=${categorie.id}" />
                                       
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



    </div>
    <div>
        <div style="height: 50px; background-color: #81bc00; margin-top: 0px">                
        </div>
      <c:url var="urlFooter" value="FrontControleur?section=includes&include=footer" />
        <c:import url="${urlFooter}" />
    </div>
</body>
</html>