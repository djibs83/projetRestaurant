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

            <div align="center">
                <h2>ENTER</h2>
                <form class="form-inline" method="POST" accept-charset='UTF-8' action="FrontControleur">
                    <div class="form-group">
                        <input type="hidden" name="section" value="interface-gestion"/>
                        <input type="text" class="form-control" placeholder="Saisir code"  name="code"/>
                    </div>
                    <button type="submit" class="btn btn-warning">Valider</button>
                </form>
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

