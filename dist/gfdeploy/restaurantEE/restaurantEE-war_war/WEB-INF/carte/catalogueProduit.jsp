<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
            

            <ul class="nav nav-tabs">
                <c:forEach items="${catProduits}" var="cp">
                    <c:url var="urlCategorie" value="FrontControleur?section=affichage-catalogue-produit&categorieId=${cp.id}" />
                    <li><a href="${urlCategorie}">${cp.libelle}</a></li>
                </c:forEach>
            </ul>

            <div style="margin-top: 60px" >
                <div class='panel col-lg-8'>
                    <div class="panel-group">
                        <div class="panel panel-default">
                            <div class="panel panel-body">

                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th></th>
                                                    <th>Nom</th>
                                                    <th>Prix</th>
                                                    <th>Statut</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${lproduits}" var="p">
                                                    <tr>
                                                        <td><img src="images/produits/${p.image}" class="img-thumbnail" alt="image" width="254" height="254"></td>
                                                        <td><em>${p.nom}</em></td>
                                                        <td><fmt:formatNumber value="${p.prixHT}" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> €</td>
                                                        <td>${p.statut.libelle}</td>
                                                        <td>
                                                            <c:url var="urlAddProduit" value="FrontControleur?section=operation-panier&origine=catalogue&type=carte&action=add&categorieId=${catProduit.id}&id=${p.id}" />
                                                            <a href="${urlAddProduit}">Ajout Panier</a>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>    

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
                                            <c:url var="url07" value="FrontControleur?section=menu-Client" />
                                            <div><a class="btn btn-warning" style="color: white" href="${url07}">RETOUR</a></div>
                                        </div>
                                    </div>

                                    <div class="col-lg-6">
                                        <div id="commander" class="form-group" style="text-align: left;">
                                            <c:url var="url05" value="FrontControleur?section=affichage-panier" />
                                            <div><a class="btn btn-warning" style="color: white" href="${url05}">SUIVANT</a></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-lg-12">
                    <div id="quitter" class="form-group" style="text-align: center;">
                        <c:url var="url06" value="FrontControleur?section=accueil-Client" />
                        <div><a class="btn btn-warning" style="color: white" href="${url06}">QUITTER</a></div>
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
        <script src="js/script.js" type="text/javascript"></script>
    </body>
</html>
