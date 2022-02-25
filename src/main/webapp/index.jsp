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

<link rel = "stylesheet" href= "css/style.css">
<script src="https://kit.fontawesome.com/5cfe696ca3.js"></script>
</head>
<body>

<x:AdminHead></x:AdminHead>

<h2>How-To</h2>
<ol>
	<li>Erstelle einen Account oder nutze einen bereits vorhandenen Account</li>
	<li>Programmier deine eigene Bestellheuristik</li>
	<li> Rufe die Daten vom Server mit Hilfe der zur Verfügung gestellten Schnittstellen mit Hilfe eines Request ab.</li>
	<li> Sende deine Ergebnisse an den Server</li>
	<li>Vergleiche dich mit den anderen in der Bestenliste</li>	
</ol>

Eine detaillierte Anleitung mit Code-Beispielen ist  <a href= "how-to.jsp" >hier</a> zu finden.

<footer class = "page footer font-small blue pt-4">
	<div class = "container-fluid text-center text-md-left">
		<a href = "impressum.jsp">Impressum</a>
	</div>

</footer>
</body>
</html>