<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="url01" value="FrontControleur?section=interface-gestion" />
<c:url var="url02" value="FrontControleur?section=jeuDeTeste" />
<c:url var="url03" value="FrontControleur?section=affichage-catalogue-produit" /> 
<c:url var="url04" value="FrontControleur?section=affichage-catalogue-menu" /> 
<c:url var="urlPanier" value="FrontControleur?section=affichage-panier" />
<c:url var="url06" value="FrontControleur?section=accueil-Client" />
<c:url var="url07" value="FrontControleur?section=menu-Client" />
<c:url var="url08" value="FrontControleur?section=accueil-cuisine" />
<c:url var="url09" value="FrontControleur?section=affichage-commande-table" />
<c:url var="url10" value="FrontControleur?section=accueil-serveur" />
<c:url var="urlQuitter" value="FrontControleur?section=interface-gestion&exit=exit" />

<nav class="navbar navbar-inverse" style="border: 0px">
    <div class="container-fluid" style="background-color: black">
        <div class="navbar-header">
            
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>                        
            </button>
            <p class="navbar-brand">JUAN'MATALDJI</p>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">


            <ul class="nav navbar-nav">
                <li><a href="${url02}">Creer donnees</a></li>
                <li><a href="${url01}">home</a></li>
                <li><a href="${url03}">catalogue produit</a></li>
                <li><a href="${url04}">catalogue menu</a></li>
                <li><a href="${url06}">Accueil Client</a></li>
                <li><a href="${url08}">accueil Cuisine</a></li>
                <li><a href="${url09}">Caisse</a></li>
                <li><a href="${url10}">Accueil Serveur</a></li>

                <li><a href="${urlPanier}">panier (${infoPanier}) </a></li>
                <li><a href="${urlQuitter}">Exit</a></li>
            </ul>
        </div>
    </div>
    <div style="height: 40px; background-color: #81bc00">

    </div>
</nav>

