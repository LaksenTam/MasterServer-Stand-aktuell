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
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawHighscore);
 
 function drawHighscore(){
	 var data = google.visualization.arrayToDataTable([
	 		['Daten', 'Kosten' , 'Fehlmengen', 'Zeit'], 		
	 		${drawHighscore}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Highscore Daten',
	 			subtitle:'eigene Heuristik'
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_Highscore'));
	 	chart.draw(data, options); 		
 }
 
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawHighscoreKosten);
 
 function drawHighscoreKosten(){
 	var data = google.visualization.arrayToDataTable([
 		['Name' , 'Kapitalbindung'], 		
 		${drawHighKosten}    		
 	]);
 	
 	var options = {
 			title: 'Kapitalbindung pro Produkt',
 			subtitle:'eigene Heuristik'
 	};
 	
 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_HighscoreKosten'));
 	chart.draw(data, options); 		
 	
	}
 
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawHighscoreBestand);
 
 function drawHighscoreBestand(){
	 var data = google.visualization.arrayToDataTable([
	 		['Name' , 'Bestand'], 		
	 		${drawHighBestand}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Bestand',
	 			subtitle:'eigene Heuristik'
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_HighscoreBestand'));
	 	chart.draw(data, options); 		 	
		}  
 
 //Volumen des Spitzenreiters
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawHighscoreVolumen);
 
 function drawHighscoreVolumen(){
	 var data = google.visualization.arrayToDataTable([
	 		['Name' , 'Volumen'], 		
	 		${drawHighVol}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Volumen',
	 			subtitle:'eigene Heuristik'
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_HighscoreVolumen'));
	 	chart.draw(data, options); 		
 }
 
 //Highscoredaten des Spitzenreiters
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawLeaderScore);
 
 function drawLeaderScore(){
	 var data = google.visualization.arrayToDataTable([
	 		['Daten', 'Kosten' , 'Fehlmengen', 'Zeit'], 		
	 		${drawHighscoreScore}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Highscore Daten',
	 			subtitle:'eigene Heuristik'
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_HighscoreLeader'));
	 	chart.draw(data, options); 		
 }
 
 google.charts.load('current', {'packages':['line']});
 google.charts.setOnLoadCallback(drawBestandsVerlauf);
 
 function drawBestandsVerlauf(){
	 var data = google.visualization.arrayToDataTable([
	 	['Periode', 'Bestand'],
	 	${userBestandsVerlauf}
	 	]);
	 
	 var options = {
     		title: 'Bestandsverlauf ',
     		subtitle:'Test',
     		
     		};

     var chart = new google.visualization.LineChart(document.getElementById('userBestandsVerlauf'));
     chart.draw(data, options);

 }
 
 </script>


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
<h5>${fehler }</h5>
	<div class = "row">
	
		<div class = "col">
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<h3></h3>
			<div id="draw_Highscore" style = "margin-top:28px; width: 900px; height: 500px;"></div>
       		<div id="draw_Kosten" style="width: 900px; height: 500px;"></div>
       		<div id="draw_Bestand" style="width: 900px; height: 500px;"></div>
      		 <div id="draw_Volumen" style="width: 900px; height: 500px;"></div>
      		 <div id="userBestandsVerlauf" style="width: 900px; height: 500px;"></div>
		</div>
		
		<div class = "col">	
		<h6>Daten von ${ highscoreName }</h6>	
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
			<div id="draw_HighscoreLeader" style="width: 900px; height: 500px;"></div>
       		<div id="draw_HighscoreKosten" style="width: 900px; height: 500px;"></div>
       		<div id="draw_HighscoreBestand" style="width: 900px; height: 500px;"></div>
       		<div id="draw_HighscoreVolumen" style="width: 900px; height: 500px;"></div>
		</div>
	</div>



		
       


</body>
</html>