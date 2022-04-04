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
</head>
<body class = "bg-secondary text-white">
<x:AdminHead></x:AdminHead>


<div class = "content-wrap">
<div class = "separieren">
<h6>Probleminstanzspeichern</h6>
<form action = "problemInstanzSpeichern" method = "POST">
<div class = "form-group row p-1">
<div class = "col">
	<label for= "name" class = "form-label">Name</label>
	</div>
	<div class = "col">
	<input type = "text" class = "form-control" id = "name" name = "name" placeholder = "Name">	
	</div>
	</div>	
	<button class = "btn btn-secondary" type = "submit">Speichern</button>	
</form>
</div>
<div class = separieren>
<h6>Probleminstanz laden</h6>
<x:showProblem/>
</div>
</div>
</body>
</html>