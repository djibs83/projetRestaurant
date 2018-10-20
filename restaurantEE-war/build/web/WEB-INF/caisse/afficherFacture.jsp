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
        
            
            <h1>Articles</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>Commande ID</th>
                        <th>Date</th>
                        <th>ID Table</th>
                        <th>Produit</th>
                        <th>Prix</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${facture.ligneCommandes}" var="c">
                        <tr>
                            <td>${c.commande.id}</td>
                            <td>${c.commande.dateCommande}</td>
                            <td>${c.commande.tabla.id}</td>
                            <td>${c.produit}</td>
                            <td>${c.prixHT}</td>
                           
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            
            <c:url var="regl" value="FrontControleur?section=affichage-reglement" />
            <a href="${regl}">Valider la facture</a>
        </div>
        <div>
            <div style="height: 50px; background-color: #81bc00; margin-top: 0px">                
            </div>
            <c:url var="urlFooter" value="FrontControleur?section=includes&include=footer" />
            <c:import url="${urlFooter}" />
        </div>
    </body>
</html>