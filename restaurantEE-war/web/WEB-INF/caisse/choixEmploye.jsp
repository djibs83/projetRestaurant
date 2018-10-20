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
            <label for="sel1">Identification employe:</label>
            <c:url var="affichage" value="FrontControleur?section=affichage-commande-table" />
            <FORM method = "POST" action = "${affichage}" name="form1">
            <select class="form-control" id="sel1" name="employe">
                <c:forEach items="${liste}" var="emp"> <option value="${emp.id}">${emp}</option> </c:forEach>
            </select> 
            <input type="submit" value ="Valider">
        </FORM>
    </div>  
    <div>
        <div style="height: 50px; background-color: #81bc00; margin-top: 0px">                
        </div>
        <c:url var="urlFooter" value="FrontControleur?section=includes&include=footer" />
        <c:import url="${urlFooter}" />
    </div>
</body>
</html>
