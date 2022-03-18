<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="WEB-INF/custom.tld" prefix="x"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery-3.3.1.slim-min.js" ></script>
<script src="js/popper.min.js" ></script>
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<script src="js/jquery.tablesort.min.js"></script>
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">
<link rel = "stylesheet" type = "text/css" href= "css/style.css">

</head>
<body class = "bg-secondary text-white">

<x:AdminHead></x:AdminHead>
<div class = "wrapper">
<div class = "container-fluid intro">
<div>
	${meldung }
</div>

<div >
<h2 class = "text-center">Intro</h2>
<p>Sie sind der Betreiber eines Technik-Geschäfts. Ihr oberstes Ziel ist die Gewinnmaximierung. Daher wollen Sie möglichst günstig Wareneinkaufen. Ihre typischen Waren umfassen sowohl sehr teure Produkte, als auch günstige Produkte.
	Sie kaufen Ihre Waren bei verschiedenen Großhändlern ein. Jeder Händler verlangt für jede Warenlieferung Lieferkosten, sowie einen Einkaufspreis für jedes Produkt.</p>
	Ihr Lager hat jedoch nur begrenztes Fassungsvermögen und eine feste maximale Kapitalbindung. Basierend auf früheren Erfahrungen, haben Sie bereits für die verschiedenen Produkte minimale und maximale Bestände festgelegt.
<p>Optimieren Sie das Lager mit Hilfe einer Bestellheuristik. Dabei ist ihre oberste Prämisse, dass Sie die Kosten der Bestellungen möglichst gering halten. 
Unter Beachtung der Nebenbedingungen:
<ul>
	<li>Die maximale Kapitalbindung darf nicht überschritten werden
	<li>Das Lagervolumen darf nicht überschritten werden
	<li>Die minimalen Produktbestände müssen eingehalten werden
	<li>Die maximalen Produktbestände dürfen nicht überschritten werden
</ul>
<h2 class = "text-center">Kurz-Anleitung</h2>
<ol>
	<li>1. Erstelle einen Account oder nutze einen bereits vorhandenen Account</li>
	<li>2. Programmier deine eigene Bestellheuristik</li>
	<li>3.  Rufe die Daten vom Server mit Hilfe der zur Verfügung gestellten Schnittstellen mit Hilfe eines Request ab.</li>
	<li>4.  Sende deine Ergebnisse an den Server</li>
	<li>5. Vergleiche dich mit den anderen in der Bestenliste</li>	
</ol>

Eine detaillierte Anleitung mit Code-Beispielen ist  <a href= "how-to.jsp" >hier</a> zu finden.

</div>

</div>
</div>
<x:FooterTag></x:FooterTag>
</body>
</html>