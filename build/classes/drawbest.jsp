<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script type="text/javascript"	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

 <script type="text/javascript">
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawKosten);
 
 function drawKosten(){
 	var data = google.visualization.arrayToDataTable([
 		['Name' , 'Kapitalbindung'], 		
 		${drawKosten}    		
 	]);
 	
 	var options = {
 			title: 'Kapitalbindung pro Produkt',
 			subtitle:'eigene Heuristik'
 	};
 	
 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_Kosten'));
 	chart.draw(data, options); 		
 	
	}
 
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawBestand);
 
 function drawBestand(){
	 var data = google.visualization.arrayToDataTable([
	 		['Name' , 'Bestand'], 		
	 		${drawBestand}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Bestand',
	 			subtitle:'eigene Heuristik'
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_Bestand'));
	 	chart.draw(data, options); 		 	
		}  
 
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawVolumen);
 
 function drawVolumen(){
	 var data = google.visualization.arrayToDataTable([
	 		['Name' , 'Volumen'], 		
	 		${drawVol}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Volumen',
	 			subtitle:'eigene Heuristik'
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_Volumen'));
	 	chart.draw(data, options); 		
 }
 </script>

</head>
<body>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
       <div id="draw_Kosten" style="width: 900px; height: 500px;"></div>
       <div id="draw_Bestand" style="width: 900px; height: 500px;"></div>
       <div id="draw_Volumen" style="width: 900px; height: 500px;"></div>

</body>
</html>