<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
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
</head>
<body class = "bg-secondary text-white">
<x:AdminHead></x:AdminHead>
<nav class="nav nav-pills flex-column flex-sm-row">
  <a class="flex-sm-fill text-sm-center nav-link active bg-dark" href="how-to.jsp">Übersicht</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="datenklassen.jsp">Datenklassen</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="httpRequest.jsp">Http-Request</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="json.jsp">JSON</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="silvermeal.jsp">Beispielheuristik</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="ergebniseinreichen.jsp">Ergebnis einreichen</a>
</nav>

<h4>Die Schnittstellen</h4>
<p> Dieser Server stellt verschiedene <a>Schnittstellen</a> zur Verfügung, über die die Daten abgefragt und gesendet werden können. 
<p> Die abgefragten Daten werden im JSON-Format ausgegeben. Zur Verarbeitung der Daten empfiehlt sich eine JSON-Library zu verwenden, wie z.B. <a href ="https://github.com/google/gson">Gson</a> oder <a href ="https://github.com/FasterXML/jackson">Jackson</a>.
<p> In den nachfolgenden Beispielen wurde die Library <a href ="https://github.com/google/gson">Gson</a> verwendet.
<p> Zur Interaktion mit dem Server muss der zur Verfügung gestellt API-Key verwendet werden. Der API-Key ist im Profil hinterlegt. 
<ol>
	<li>/GetProdukte stellt die Produktinformationen zur Verfügung
	<li>/GetLager gibt die Attribute des Lagers aus
	<li>/ProduktListenAnfordern stellt euch alle notwendigen Daten (Produkte + Lager) zur Verfügung um mit der Berechnung zu beginnen</li>
	<li>/Schwierigkeitsgrad1 gibt ein angefragtes Produkt in einer Periode mit dem dazugehörigen Verbrauch aus</li>
	<li>/Schwierigkeitsgrad2 gibt eine Liste mit Produkten in einer angefragten Periode mit den dazugehörigen Verbräuchen zurück</li>
	<li>/Schwierigkeitsgrad3 gibt eine Liste mit Produkten in einer angefragten Periode mit den dazugehörigen Verbräuchen zurück. Zusätzlich wird die Performanz des Algorithmus gemessen.</li>
	<li>/Ergebnis über diese Schnittstelle kann das Ergebnis für Schwierigkeitsgrad1 eingereicht werden in den anderen wird erkannt wann die Berechnungen abgeschlossen sind. 
</ol>


	
</body>
</html>