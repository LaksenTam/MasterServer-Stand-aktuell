/**
 * 
 */
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