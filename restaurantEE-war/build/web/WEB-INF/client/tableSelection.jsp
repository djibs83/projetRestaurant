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
        
            
            <h1>Selection la table pour cette tablette</h1>
            <table class="table">
                <thead>
                    <tr>
                        <th>numero</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${tables}" var="t">
                        <tr>
                            <td>${t.numero}</td>                            
                            <td>
                                <c:url var="urltable" value="FrontControleur?section=accueil-client&tableId=${t.id}" /> 
                                <a href="${urltable}">Choisir</a>
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
