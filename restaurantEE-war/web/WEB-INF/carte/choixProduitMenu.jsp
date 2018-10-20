<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html>
<html>
    <c:url var="urlhead" value="FrontControleur?section=includes&include=header" />
    <c:import url="${urlhead}" />
    <body>
        <nav>
            <c:url var="urlMenu" value="FrontControleur?section=includes&include=nav-main" />
            <c:import url="${urlMenu}" />
        </nav>
        <div class="container" style="min-height: 600px; padding-top :0px">

            <div style="margin-top: 60px">
                <div class='panel col-lg-8'>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel panel-body">

                                <c:forEach items="${catProduits}" var="cat">
                                    <h1>${cat.libelle}</h1>

                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${menu.produits}" var="pro">
                                                <tr>
                                                    <c:if test="${pro.sousCategorie.categorieProduit.libelle == cat.libelle}">
                                                        <td><img src="images/produits/${pro.image}" class="img-thumbnail" alt="image" width="104" height="104"></td>
                                                        <td><h4><em></br>${pro.nom}</em></h4></td>
                                                        <td><c:if test="${fn:contains(choixProduits,pro)}">
                                                                </br><img src="images/selectionner.png" alt="image" width="23" height="19">
                                                            </c:if></td>
                                                        <td style="text-align: right">
                                                            <c:url var="urlProduit" value="FrontControleur?section=operation-panier-menu&menu=${menu.id}&cat=${cat.libelle}&action=add&id=${pro.id}" /> 
                                                            </br><a href="${urlProduit}">aj. panier</a>
                                                        </td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:forEach>

                                <br>
                                <div class='panel col-lg-12'>
                                    <div class='panel col-sm-6'>
                                        <p>
                                            <c:url var="urlAnnuler" value="FrontControleur?section=operation-panier-menu&action=annuler" /> 
                                            <a href="${urlAnnuler}">ANNULER</a>
                                        </p>
                                    </div>
                                    <div class='panel col-sm-6'>
                                        <c:if test="${choixProduits.size() ge fn:length(catProduits)}">
                                            <p style="text-align: right">
                                                <c:url var="urlValider" value="FrontControleur?section=operation-panier&type=menu&menuId=${menu.id}&action=add" /> 
                                                <a href="${urlValider}">VALIDER</a>
                                            </p>
                                        </c:if>
                                    </div>
                                </div>

                                <c:if test="${menu == null}">
                                    <p>Pas de Menu selectionné !</p>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </div>

                <div class='panel col-lg-4'>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel panel-body">
                                <h1>Ma Commande</h1>
                                <c:if test="${not empty produitsPanier or not empty menusPanier}">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <th>Nom</th>
                                                <th>Prix</th>
                                                <th>Description</th>
                                                <th>Statut</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${produitsPanier}" var="lp" varStatus="loop">
                                                <tr>
                                                    <td>${lp.produit.nom}</td>
                                                    <td>
                                                        <fmt:formatNumber value="${lp.produit.prixHT}" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> €
                                                    </td>
                                                    <td>${lp.produit.description}</td>
                                                    <td>${lp.produit.statut.libelle}</td>
                                                    <td>
                                                        <c:url var="urlsubProduit" value="FrontControleur?section=operation-panier&origine=detail-panier&action=subProduit&index=${loop.index}" />                                                        
                                                        <a href="${urlsubProduit}"> x </a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:forEach items="${menusPanier}" var="lm" varStatus="loop">
                                                <tr>
                                                    <td>${lm.menu.libelle}</td>
                                                    <td>
                                                        <fmt:formatNumber value="${lm.menu.prixHT}" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> €
                                                    </td>
                                                    <td></td>
                                                    <td>${lm.menu.statut.libelle}</td>
                                                    <td>
                                                        <c:url var="urlsubMenu" value="FrontControleur?section=operation-panier&origine=detail-panier&action=subMenu&index=${loop.index}" />                                                        
                                                        <a href="${urlsubMenu}"> x </a>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="5">
                                                        <c:forEach items="${lm.choix}" var="ch">
                                                            <p>${ch.key} : ${ch.value.produit.nom}</p>
                                                        </c:forEach>  
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tr>
                                                <td colspan="5">Total HT</td>
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

                                <hr />
                                <div class="col-sm-12">
                                    <div class="col-lg-6">
                                        <div id="retour" class="form-group" style="text-align: center;">
                                            <c:url var="urlRetour" value="FrontControleur?section=menu-Client" />
                                            <div><a class="btn btn-warning" style="color: white" href="${urlRetour}">RETOUR</a></div>
                                        </div>
                                    </div>

                                    <div class="col-lg-6">
                                        <div id="commander" class="form-group" style="text-align: left;">
                                            <c:url var="urlSuivant" value="FrontControleur?section=affichage-panier" />
                                            <div><a class="btn btn-warning" style="color: white" href="${urlSuivant}">SUIVANT</a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-lg-12">
                    <div id="quitter" class="form-group" style="text-align: center;">
                        <c:url var="urlQuitter" value="FrontControleur?section=accueil-Client" />
                        <div><a class="btn btn-warning" style="color: white" href="${urlQuitter}">QUITTER</a></div>
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
    </body>
</html>
