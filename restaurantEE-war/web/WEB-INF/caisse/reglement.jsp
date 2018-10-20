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
            <h1> Règlement </h1>
            <p>Prix total à règler : ${prix}</p>
            <FORM action='FrontControleur?section=affichage-reglement' method='POST' accept-charset='UTF-8'>
                <c:forEach items="${typereg}" var ="typereg">
                <INPUT type= "radio" name="choice" value="${typereg.value.typeReglement}"> ${typereg.value.typeReglement}
                </c:forEach>
                <input type ='submit' value='Envoyer'/>
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
