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

            <h1>Connexion Interface</h1>
            <div class="principal container container-shadow">
                <div class="container-fluid">                
                    <div class='row'>
                        <div class="panel-body">
                            <form action="FrontControleur" method="POST" accept-charset="UTF-8"> 
                                <div class="form-group">
                                    <input type="hidden" name="origine" value="passer-commande" />
                                    <input type="hidden" name="section" value="connection" />
                                </div>
                                <div class="form-group">
                                    <input type="email" class="form-control" placeholder="Identifiant" name="login" value="${login}" required/>
                                </div>
                                <div class="form-group">                                                
                                    <button type="submit" class="glyphicon glyphicon-log-in btn btn-warning" name="valide" value="connection"> Login</button>
                                </div>
                                <div class="form-group">
                                    <label><input type="checkbox" name="memo" value="save"> Se souvenir de moi</label>
                                </div>
                            </form>

                        </div>
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
