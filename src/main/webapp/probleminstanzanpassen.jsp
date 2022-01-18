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
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<link rel = "stylesheet" href= "css/style.css">
<title>Insert title here</title>
</head>
<body>
<x:AdminHead></x:AdminHead>
<p>${updated }</p>
<x:ProblemInstanzBearbeiten></x:ProblemInstanzBearbeiten>


			
</body>
</html>