<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <c:url var="urlhead" value="FrontControleur?section=includes&include=header" />
    <c:import url="${urlhead}" />
    <body>        
        <c:url var="urlMenu" value="FrontControleur?section=includes&include=nav-main" />
        <c:import url="${urlMenu}" />
        <%-- <c:url var="url11" value="FrontControleur?section=page-serveur&id=${e.id}"/> --%>

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
                <div class="col-sm-8">
                    <table class="table">
                        <thead>
                            <tr>
                                <th><center>CIVILITE</center></th>
                        <th><center>NOM</center></th>
                        <th><center>PRENOM</center></th>
                            <%-- <th><center>date de naissance</center></th>
                             <th><center>date d'embauche</center></th> --%>
                        </tr>

                        </thead>
                        <tbody>
                            <c:forEach items="${employes}" var="e">
                                <c:url var="urlpageServeur" value="FrontControleur?section=accueil-serveur&serveurId=${e.id}"/>
                                <tr>
                                    <td><center>${e.civilite}</center></td>
                            <td><center>${e.nom}</center></td>
                            <td><center>${e.prenom}</center></td>
                                <%--  <td><center>${e.dateNaissance}</center></td>
                                 <td><center>${e.dateEmbauche}</center></td> --%>
                            <td> <div class="btn btn-danger" ><a style="color: white" href="${urlpageServeur}" >Me</a></font></div></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class='row text-left' style="margin-left: 50px">
                <%--            
               <div class="col-sm-4">
                           <table class="table table-bordered">
                               <thead>
                                   <tr>
                                       <th><center>Nombre de place par table</center></th>
                                       <th><center>Statut de la table</center></th>
                                   </tr>

                    </thead>
                    <tbody>
                        <c:forEach items="${tables}" var="t">
                        <tr>
                            <td><center>${t.nbPlace}</center></td>
                            <td><center>${t.statut}</center></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
        </div> 
            </div> --%>

                <%--
                           <div class='row text-center' style="margin-left: 100px">
                               <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">Liste Tables</button>

                <button type="button" class="btn btn-default" data-toggle="modal" data-target="#myModal">Serveurs présents</button> --%>
            </div>
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