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

<!--  <link rel = "stylesheet" href= "css/style.css">-->
<link rel = "stylesheet" href= "css/management.css">
<script src="https://kit.fontawesome.com/5cfe696ca3.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body class = "bg-secondary text-white">
<x:AdminHead></x:AdminHead>
<div class="container-grid">
<form action = "UpdateLager" method = "GET">
  <div class="lagerDaten">     
    <div class="ueber"><h6>Lagerdaten aktuell</h6></div>
    <div class="label"><label for="" class = "form-label">Lagervolumen</label></div>
    <div class="inputvol"><input type = "text" class = "form-control" id = "" name = "vol" value = "${lagerVol }"></div>
    <div class="labelkap"><label for="" class = "form-label">Lagerkapital</label></div>
    <div class="inputkap"><input type = "text" class = "form-control" id = "" name = "kapBindung" value = "${lagerKap }"></div>
    <div class="buttonLager"><button class = "btn btn-dark" type = "submit">aktualisieren</button></div>    
  </div>
  </form>
   <form action = "problemInstanzSpeichern" method = "POST">
 	 <div class="probleminstanzSpeichern">  
    <div class="ueberSpeichern"><h6>Probleminstanzspeichern</h6></div>
    <div class="inputsafe"><div class="input-group-prepend"><input type = "text" class = "form-control" id = "name" name = "name" placeholder = "Name"></div></div>
    <div class="button"><button class = "btn btn-dark" type = "submit">Speichern</button></div>
  </div>
  </form>
  <div class="problemLaden">
    <div class="ueberLaden"><h6>Probleminstanz laden</h6></div>
    <div class="tag"><x:showProblem/></div>
  </div>
  <div class="anpassen">
  	<h4>Probleminstanz anpassen</h4>
  	<x:ProblemInstanzBearbeiten></x:ProblemInstanzBearbeiten>
  </div>
</div>
</body>
</html>