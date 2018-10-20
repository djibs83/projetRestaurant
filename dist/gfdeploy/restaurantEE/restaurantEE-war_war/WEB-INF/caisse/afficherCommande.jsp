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
                    <c:forEach items="${commandes}" var="c">
                        <tr>
                            <td>${c.commande.id}</td>
                            <td>${c.commande.dateCommande}</td>
                            <td>${c.commande.tabla.id}</td>
                            <td>${c.produit}</td>
                            <td>${c.prixHT}</td>
                            <c:url var="add" value="FrontControleur?section=operation-facture&action=add&origine=afficherCommande&ref=${c.id}" />
                            <td> <a href="${add}" > Ajouter facture </a> </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:url var="addAll" value="FrontControleur?section=operation-facture&action=addAll&table=${table}" />
                <a href ="${addAll}" >Ajouter la table</a>
             <h1>Factures</h1>
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
                    <c:forEach items="${facture}" var="f">
                        <tr>
                            <td>${f.commande.id}</td>
                            <td>${f.commande.dateCommande}</td>
                            <td>${f.commande.tabla.id}</td>
                            <td>${f.produit}</td>
                            <td>${f.prixHT}</td>
                            <c:url var="supp" value="FrontControleur?section=operation-facture&action=supp&origine=afficherCommande&ref=${f.id}" />
                            <td> <a href="${supp}" > X </a> </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <c:url var="gen" value="FrontControleur?section=operation-facture&action=gen" />
            <a href="${gen}">Générer la facture</a>
        </div>
        <div>
            <div style="height: 50px; background-color: #81bc00; margin-top: 0px">                
            </div>
            <c:url var="urlFooter" value="FrontControleur?section=includes&include=footer" />
            <c:import url="${urlFooter}" />
        </div>
    </body>
</html>
