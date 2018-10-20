<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <c:url var="urlhead" value="FrontControleur?section=includes&include=header" />
    <c:import url="${urlhead}" />
    <body>        
        <c:url var="urlMenu" value="FrontControleur?section=includes&include=nav-main" />
        <c:import url="${urlMenu}" />
        <div class="container" style="min-height: 600px; padding-top :0px">


            <h1>panier</h1>
            <c:if test="${empty produits}">
                <p>Votre panier est vide</p>
            </c:if>
            <c:if test="${not empty produits}">
                <table class="table">
                    <thead>
                        <tr><th>key</th>
                            <th>nom</th>
                            <th>prix</th>
                            <th>description</th>
                            <th>statut</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${produits}" var="lp" varStatus="loop">
                            <c:if test="${lp.isMenu == false}">
                                <tr>
                                    <td>${loop.index}</td>
                                    <td>${lp.produit.nom}</td>
                                    <td>
                                        <fmt:formatNumber value="${lp.produit.prixHT}" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> €
                                    </td>
                                    <td>${lp.produit.description}</td>
                                    <td>${lp.produit.statut.libelle}</td>
                                    <td>
                                        <c:url var="urlsubProduit" value="FrontControleur?section=operation-panier&origine=detail-panier&action=subProduit&index=${loop.index}" />                                                        
                                        <a href="${urlsubProduit}"> Supprimer </a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>

                        <c:forEach items="${produits}" var="lp" varStatus="loop">
                            <c:if test="${lp.isMenu == true}">
                                <tr>
                                    <td>${loop.index}</td>
                                    <td>${lp.menu.libelle}</td>
                                    <td>
                                        <fmt:formatNumber value="${lp.menu.prixHT}" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> €
                                    </td>
                                    <td></td>
                                    <td>${lp.menu.statut.libelle}</td>
                                    <td>
                                        <c:url var="urlsubProduit" value="FrontControleur?section=operation-panier&origine=detail-panier&action=subProduit&index=${loop.index}" />                                                        
                                        <a href="${urlsubProduit}"> Supprimer </a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>



                        <tr>
                            <td colspan="5">total HT</td>
                            <td><fmt:formatNumber value="${totalHT}" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> €
                            </td>
                        </tr>
                        <tr>
                            <td colspan="5">
                                <c:url var="urlreset" value="FrontControleur?section=operation-panier&origine=detail-panier&action=vider" />
                                <a href="${urlreset}"> Vider le panier </a>
                            </td>
                        </tr>
                    </tbody>
                </table>        
            </c:if>

            <div id="retour" class="form-group" style="text-align: center;">
                <c:url var="urlRetour" value="FrontControleur?section=menu-Client" />
                <div><a class="btn btn-warning" style="color: white" href="${urlRetour}">RETOUR</a></div>
            </div>
            <div id="commander" class="form-group" style="text-align: center;">
                <c:url var="urlCommander" value="FrontControleur?section=operation-panier&action=commander" />
                <div><a class="btn btn-warning" style="color: white" href="${urlCommander}">COMMANDER</a></div>
            </div>


        </div>
        <div>
            <div style="height: 50px; background-color: #81bc00; margin-top: 0px">                
            </div>
            <c:url var="urlFooter" value="FrontControleur?section=includes&include=footer" />
            <c:import url="${urlFooter}" />
        </div>
    </body>
</html>
