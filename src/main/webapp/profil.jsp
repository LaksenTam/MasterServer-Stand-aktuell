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
 <script type="text/javascript">
    // Load the Visualization API and the corechart package.
 	google.charts.load('current', {packages: ['corechart', 'bar']});
	google.charts.setOnLoadCallback(drawBasic);
	
	function drawproduktList(){
		var data = google.visualization.arrayToDataTable([
			['Bestellmenge', 'Periode' ]
		])
	}
</head>
<body>
<x:AdminHead></x:AdminHead>
<div class= "container">

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

</div>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
       <div id="chart_div" style="width: 900px; height: 500px;"></div>


</body>
</html>