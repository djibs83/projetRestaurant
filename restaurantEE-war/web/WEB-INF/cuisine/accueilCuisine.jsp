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

            <hr>
            <div class='row text-left' style="margin-left: 100px">      
                <h1>INTERFACE CUISINE</h1>
            </div>
            <hr>

            <div class='row text-top' style="margin-top: 30px">

            </div>
            <div class='row text-left' style="margin-left: 30px">
                <h2>Commandes</h2>
            </div>

            <table class="table">
                <thead>
                    <tr>
                        <th>nom</th>
                        <th>image</th> 
                        <th>statut</th>
                        <th>description</th>
                        <th>tempsPreparation</th>
                        <th>cuisson</th>
                        <th>dateCommande</th>
                        <th>Action</th>
             
                       

                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${ligneCommandes}" var="l">
                        <tr>
                            <td>${l.produit.nom}</td>
                            <td>${l.produit.image}</td>
                            <td>${l.produit.statut}</td>
                            <td>${l.produit.description}</td>
                            <td>${l.produit.tempsPreparation}</td>
                            <td>${l.produit.cuisson}</td>
                            <td>${l.commande.dateCommande}</td>
                            <td>
                                <div class="form-group" style="text-align: center">
                                    <c:url var="pers" value="FrontControleur?section=accueil-cuisine&id=${l.id}&action=pers" />
                                    <div><a class="btn btn-warning" style="color: white" href="${pers}">START</a></font></div>
                                </div>
                                <div class="form-group" style="text-align: center">
                                    <c:url var="pers" value="FrontControleur?section=accueil-cuisine&id=${l.id}&action=pers" />
                                    <div><a class="btn btn-warning" style="color: white" href="${pers}">END</a></font></div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div> 


        <div>
            <div style="height: 50px; background-color: #81bc00; margin-top: 0px">                
            </div>
            <c:url var="urlFooter" value="FrontControleur?section=includes&include=footer" />
            <c:import url="${urlFooter}" />
        </div>
    </body>
</html>
