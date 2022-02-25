<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
           <%@ taglib uri="WEB-INF/custom.tld" prefix="x"%>
     
<!DOCTYPE html>
<html>
<head>
<script src="js/jquery-3.3.1.slim-min.js" ></script>
<script src="js/popper.min.js" ></script>
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<script src="js/jquery.tablesort.min.js"></script>
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">

<link rel = "stylesheet" href= "css/style.css">
<script src="https://kit.fontawesome.com/5cfe696ca3.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<x:AdminHead></x:AdminHead>
<form action = "ErweiterteErstellung" method = "GET">
<div class = "border border-dark rounded p-1 m-2">
<div class = "border border-dark rounded m-3 p-1">
	<div class = "form-group row p-1">
		<label for="input" class = "col-sm-1 col-form-label">Spieleinstellungen</label>
		<div class = "col">
			<label for="perioden" class = "col col-form-label">Anzahl der Perioden</label>
			<input type = "text" class = "form-control" id = "perioden" name = "perioden" placeholder = "Anzahl der Perioden">
		</div>
		
		<div class = "col">
			<label for= "lager" class = "col-sm1 col-form-label">Lager Erstellung</label>
			<input type = "text" class = "form-control" id = "lager" name = "kapBindung" placeholder = "Maximale Kapitalbindung"> 
			<input type = "text" class = "form-control" id = "lager" name = "lagerVol" placeholder = "Maximales Lagervolumen"> 
	</div>
	<div class = "col">
			<label for= "spielregeln" class = "col-sm1 col-form-label">Spielregeln</label>
			<input type = "text" class = "form-control" id = "spielregeln" name = "zeit" placeholder = "maximale Zeit für einen Durchlauf"> 
			<input type = "text" class = "form-control" id = "spielregeln" name = "sammelB" placeholder = "Sammelbestellungskosten"> 
	</div>
	
	</div>
	</div>
	<div class = "border border-dark rounded m-3 p-1">
	<div class = "form-group row p-1">
		<label for="input" class = "col-sm-1 col-form-label">Verbrauch</label>
		<div class = "col">
			<h5>A-Produkte</h5>
			<input type = "text" class = "form-control" id = "input" name = "aprodukt" placeholder = "schwankender Verbrauch">
			<input type = "text" class = "form-control" id = "input" name = "saisonalA" placeholder = "saisonaler Verbrauch">			
			<input type = "text" class = "form-control" id = "input" name = "konstantA" placeholder = "konstanter Verbrauch">			
		</div>
		<div class = "col">
			<h5>B-Produkte</h5>
			<input type = "text" class = "form-control" id = "input" name = "bprodukt" placeholder = "schwankender Verbrauch B-Produkt">
			<input type = "text" class = "form-control" id = "input" name = "saisonalB" placeholder = "saisonaler Verbrauch">			
			<input type = "text" class = "form-control" id = "input" name = "konstantB" placeholder = "konstanter Verbrauch">
		</div>
		<div class = "col">
			<h5>C-Produkte</h5>
			<input type = "text" class = "form-control" id = "input" name = "cprodukt" placeholder = "schwankender Verbrauch">
			<input type = "text" class = "form-control" id = "input" name = "saisonalC" placeholder = "saisonaler Verbrauch">			
			<input type = "text" class = "form-control" id = "input" name = "konstantC" placeholder = "konstanter Verbrauch">
		</div>
	</div>
		<div class = "form-group row">
		<label for="lagerkosten" class = "col-sm-1 col-form-label">Lagerkostensatz</label>
		<div class = "col">
			<input type = "text" class = "form-control" id = "lagerkosten" name = "lagerKostenA" placeholder = "Lagerkostensatz in %">
		</div>
		<div class = "col">
			<input type = "text" class = "form-control" id = "lagerkosten" name = "lagerKostenB" placeholder = "Lagerkostensatz in %">
		</div>
		<div class = "col">
			<input type = "text" class = "form-control" id = "lagerkosten" name = "lagerKostenC" placeholder = "Lagerkostensatz in %">
		</div>
	</div>
	<div class = "form-group row">
		<label for="einstandskosten" class = "col-sm-1 col-form-label">Einstandskosten</label>
		<div class = "col">
			<input type = "text" class = "form-control" id = "einstandkosten" name = "einstandMinA" placeholder = "Min Einstandspreis">
			<input type = "text" class = "form-control" id = "einstandkosten" name = "einstandMaxA" placeholder = "Max Einstandspreis">
		</div>
		<div class = "col">
			<input type = "text" class = "form-control" id = "einstandkosten" name = "einstandMinB" placeholder = "Min Einstandspreis">
			<input type = "text" class = "form-control" id = "einstandkosten" name = "einstandMaxB" placeholder = "Max Einstandspreis">
		</div>
		<div class = "col">
			<input type = "text" class = "form-control" id = "einstandkosten" name = "einstandMinC" placeholder = "Min Einstandspreis">
			<input type = "text" class = "form-control" id = "einstandkosten" name = "einstandMaxC" placeholder = "Max Einstandspreis">
		</div>
	</div>
	<div class = "form-group row">
		<label for="bestellfix" class = "col-sm-1 col-form-label">Bestellfixe Kosten</label>
		<div class = "col">
			<input type = "text" class = "form-control" id = "bestellfix" name = "bestellFixMinA" placeholder = "Min bestellfixe Kosten">
			<input type = "text" class = "form-control" id = "bestellfix" name = "bestellFixMaxA" placeholder = "Max bestellfixe Kosten">
		</div>
		<div class = "col">
			<input type = "text" class = "form-control" id = "bestellfix" name = "bestellFixMinB" placeholder = "Min bestellfixe Kosten">
			<input type = "text" class = "form-control" id = "bestellfix" name = "bestellFixMaxB" placeholder = "Max bestellfixe Kosten">
		</div>
		<div class = "col">
			<input type = "text" class = "form-control" id = "bestellfix" name = "bestellFixMinC" placeholder = "Min bestellfixe Kosten">
			<input type = "text" class = "form-control" id = "bestellfix" name = "bestellFixMaxC" placeholder = "Max bestellfixe Kosten">
		</div>
	</div>
	<div class = "form-group row">
		<label for="fehlkosten" class = "col-sm-1 col-form-label">Fehlkosten</label>
		<div class = "col">
			<input type = "text" class = "form-control" id = "fehlkosten" name = "fehlkostenMinA" placeholder = "Min Fehlkosten">
			<input type = "text" class = "form-control" id = "fehlkosten" name = "fehlkostenMaxA" placeholder = "Max Fehlkosten">
		</div>
		<div class = "col">
			<input type = "text" class = "form-control" id = "fehlkosten" name = "fehlkostenMinB" placeholder = "Min Fehlkosten">
			<input type = "text" class = "form-control" id = "fehlkosten" name = "fehlkostenMaxB" placeholder = "Max Fehlkosten">
		</div>
		<div class = "col">
			<input type = "text" class = "form-control" id = "fehlkosten" name = "fehlkostenMinC" placeholder = "Min Fehlkosten">
			<input type = "text" class = "form-control" id = "fehlkosten" name = "fehlkostenMaxC" placeholder = "Max Fehlkosten">
		</div>
	</div>
	</div>
		<button class = "btn btn-info" type = "submit">Senden!</button>
	</div>
</form>

</body>
</html>