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


            <h1>Formule</h1>
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
                    <c:forEach items="${menus}" var="m">
                        <tr>
                            <td><img src="images/produits/${m.image}" alt="image" width="124" height="174"></td>
                            <td><em>${m.libelle}</em></td>
                            <td><fmt:formatNumber value="${m.prixHT}" minFractionDigits="2" maxFractionDigits="2" minIntegerDigits="1" /> €</td>
                            <td>${m.statut.libelle}</td>
                            <td>
                                <c:url var="urlMenu" value="FrontControleur?section=affichage-catalogue-menu&menuId=${m.id}" /> 
                                <a href="${urlMenu}">Choisir</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="col-lg-12">
                <div id="retour" class="form-group" style="text-align: center;">
                    <c:url var="urlRetour" value="FrontControleur?section=menu-Client" />
                    <div><a class="btn btn-warning" style="color: white" href="${urlRetour}">RETOUR</a></div>
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
