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
<title></title>
</head>
<body class = "bg-secondary text-white">
<x:AdminHead></x:AdminHead>
<p>${updated }</p>
<div class="container-problem">
  <div class="anpassen">
  	<x:ProblemInstanzBearbeiten></x:ProblemInstanzBearbeiten>
  </div>
  <div class="save-load">
    <div class="save">
    
    </div>
    <div class="load">
    	<x:showProblem/>    
    </div>
    
  </div>
</div>

			
</body>
</html>