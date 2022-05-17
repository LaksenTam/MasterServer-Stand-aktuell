<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib uri="WEB-INF/custom.tld" prefix="x"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="js/jquery-3.3.1.slim-min.js" ></script>
<script src="js/popper.min.js" ></script>
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<script src="js/jquery.tablesort.min.js"></script>
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">

<link rel = "stylesheet" href= "css/style.css">
<script src="https://kit.fontawesome.com/5cfe696ca3.js"></script>
<title>Insert title here</title>
<script>
$(document).ready(function(){
	  $("#pInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#tableBody tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
	
	



</script>
</head>
<body class = "bg-secondary text-white">
	<x:AdminHead></x:AdminHead>
	
	<form action ="ProblemInstanzErstellen" method = "POST">
<div class="container-grid">
  <div class="spielregeln">
    <div class="spielregelnueber"><h3>Spielregeln</h3></div>
    <div class="sammelblabel"><label for="" class = "label">Sammelbestellungskosten</label>
    <label for="" class = "form-label">Zeitrestriktion</label></div>
    <div class="sammelbin"><input type = "text" class = "form-control" id = "spielregeln" name = "sammelB" placeholder = "Kosten für Sammelbestellungen">
    <input type = "text" class = "form-control" id = "spielregeln" name = "zeit" placeholder = "maximale Zeit für einen Durchlauf"></div>
   
  </div>
  <div class="lagerdata">
    <div class="lagerueber"><h3>Lagergenerieren</h3></div>
    <div class="lagergen">
      <div class="lagergenueber"></div>
      <div class="lagervollabel"><label for="" class = "form-label">Lagervolumen</label></div>
      <div class="lagerkaplabel"><label for="" class = "form-label">Lagerkapital</label></div>
      <div class="lagervolin"><input type = "text" class = "form-control" id = "" name = "lagervol" placeholder = "Maximales Lagervolumen"></div>
      <div class="lagerkapin"><input type = "text" class = "form-control" id = "" name = "kapital" placeholder = "Maximale Kapitalbindung"></div>
    </div>
  
   </div>
   <div class="produkterstellung">
   
    <div class="periodenlabel"><h3>Produktgenerierung</h3><label for="" class = "form-label">Anzahl der Perioden</label></div>
    <div class="periodeninput"><input type = "text" class = "form-control" id = "" name = "perioden" placeholder = "Periodenanzahl"></div>
    <div class="verbrauchgen"><h4>Verbrauchsgenerierung</h4></div>
    <div class="verbrauchlabel">
      <div class="a"><label for="" class = "form-label">A-Produkt</label></div>
      <div class="b"><label for="" class = "form-label">B-Produkt</label></div>
      <div class="c"><label for="" class = "form-label">C-Produkt</label></div>
    </div>
    <div class="verbrauchsin">
      <div class="b-in">
        <div class="b-konst"><input type = "text" class = "form-control" id = "spielregeln" name = "kostantB" placeholder = "Konstanter Verbrauch (X)"></div>
        <div class="b-sai"><input type = "text" class = "form-control" id = "spielregeln" name = "saisonalB" placeholder = "Saisonaler Verbrauch (Y)"></div>
        <div class="b-schwank"><input type = "text" class = "form-control" id = "spielregeln" name = "bprodukt" placeholder = "Schwankender-Verbrauch(Z)"></div>
      </div>
      <div class="c-in">
        <div class="c-konst"><input type = "text" class = "form-control" id = "spielregeln" name = "kostantC" placeholder = "Konstanter Verbrauch (X)"></div>
        <div class="c-sai"><input type = "text" class = "form-control" id = "spielregeln" name = "saisonalC" placeholder = "Saisonaler Verbrauch (Y)"></div>
        <div class="c-schwank"><input type = "text" class = "form-control" id = "spielregeln" name = "cprodukt" placeholder = "Schwankender-Verbrauch(Z)"></div>
      </div>
      <div class="a-in">
        <div class="a-konst"><input type = "text" class = "form-control" id = "spielregeln" name = "kostantA" placeholder = "Konstanter Verbrauch (X)"></div>
        <div class="a-sai"><input type = "text" class = "form-control" id = "spielregeln" name = "saisonalA" placeholder = "Saisonaler Verbrauch (Y)"></div>
        <div class="a-schwank"><input type = "text" class = "form-control" id = "spielregeln" name = "aprodukt" placeholder = "Schwankender-Verbrauch(Z)"></div>
      </div>      
    </div>    
  </div>
  <div class="buttons"><a href = "erweitert.jsp" class = "btn btn-dark">Erweitertes Erstellen</a> <button class = "btn btn-dark" type = "Submit">generieren</button></div>
</div>
</form>

 
  


<form action = "CreateCSV" method = "GET">
        <button class = "btn btn-warning" type = "submit">Als CSV speichern</button> 
</form>




<button class="btn btn-dark btn-lg btn-block collapsed" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
Produktübersicht anzeigen
</button>
				
<div class="collapse" id="collapseExample" style="">
<input class ="form-control" id="pInput" type = "text" placeholder="Filtern nach Produktnamen">
<table class = "table table-hover">
	<thead>
            <tr>                
           		<th>#</th>
                <c:forEach items="${columnNames}" var="name">                	
                    <th>${name}</th>
                </c:forEach>
            </tr>
            </thead>
            <tbody id="tableBody">
           
            <c:forEach items="${resultList}" var="result" varStatus = "index">
             
                <tr>     
                	 <th scope = "row">${index.index }</th>  
                    <c:forEach items="${result}" var="value">  
                                       	
                        <td>${value}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table> 
       </div>
	
	

</body>
</html>