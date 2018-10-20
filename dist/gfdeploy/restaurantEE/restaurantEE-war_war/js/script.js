function getXhr() {
    var xhr = null;

    if (window.XMLHttpRequest) { // Firefox et autres
        xhr = new XMLHttpRequest();
    } else if (window.ActiveXObject) { // Internet Explorer
        try {
            xhr = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            xhr = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    else { // XMLHttpRequest non supporté par le navigateur
        alert("Votre navigateur ne supporte pas les objets XMLHTTPRequest...");
        xhr = false;
    }
    return xhr;
}

function actualiserMenu(url) {
    var xhr = getXhr();
    if (!xhr) {
        return;
    }   
    url += "&demande=ajax-menu";
    
  xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var reponse = xhr.responseText;
            //alert(reponse);
            var div = document.getElementById("nav-main");
            div.innerHTML = reponse;
        }
    };
    //alert("Produit ajouter dans votre panier");
    xhr.open("POST",url,true);
    xhr.send(null);
}


function actualiserPanier(url) {
    var xhr = getXhr();
    if (!xhr) {
        return;
    }   
    url += "&demande=ajax-menu";
    
  xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var reponse = xhr.responseText;
            //alert(reponse);
            var div = document.getElementById("actu-panier");
            div.innerHTML = reponse;
        }
    };
    //alert("Produit ajouter dans votre panier");
    xhr.open("POST",url,true);
    xhr.send(null);
}

