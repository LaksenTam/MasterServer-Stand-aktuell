<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib uri="WEB-INF/custom.tld" prefix="x"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/jquery-3.3.1.slim-min.js" ></script>
<script src="js/popper.min.js" ></script>
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<link rel = "stylesheet" href= "css/style.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<x:AdminHead></x:AdminHead>
<div class= "container">
<form action = "Profil" method = "POST">
<table>
	<tr>
		<td>Name: </td>
		<td>${name }</td>
	</tr>
	<tr>
		<td>API-Key: </td>
		<td>${key }</td>
	</tr>
</table>
</form>
</div>

<div class = "container">
	<table>
		<thead>
			<tr>
			<td>Ergebnis Nr.</td>
			<td>andere Daten</td>
			</tr>
		</thead>
	
	
	</table>


</div>

</body>
</html>