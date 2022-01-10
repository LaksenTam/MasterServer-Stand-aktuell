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
<script src="js/jquery.tablesort.min.js"></script>
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<link rel = "stylesheet" href= "css/style.css">
</head>
<body>

<x:AdminHead></x:AdminHead>
		
<form action = "ProblemInstanzPost" method = "POST">
	<table>
		<tr>
			<td> Periode</td>
			<td><input type = "text" name = "periode"></td>
		</tr>
		<tr>
			<td>JSON</td>
			<td><input type = "text" name = "ergebnis"></td>
		</tr>
		<tr>
			<td><button type = "submit">Senden!</button></td>
		</tr>	
	</table>
</form>

<form action = "testCSVread" method = "GET">
<button type= "submit">testen</button>
</form>
<br>
<br>



</body>
</html>