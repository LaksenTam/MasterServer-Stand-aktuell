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
<script src="https://kit.fontawesome.com/5cfe696ca3.js"></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript"	src="https://www.gstatic.com/charts/loader.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel = "stylesheet" href= "css/style.css">
 <script type="text/javascript">
 
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawVolumen);
 
 function drawVolumen(){
	 var data = google.visualization.arrayToDataTable([
	 		${produktNamen}	
	 		${stackedVolumen}    		
	 	]);
	 		 
	 		 	var options = {
	 		 			title: 'Volumenverlauf pro Periode',
	 		 			subtitle:'eigene Heuristik',
	 		 			backgroundColor: '#6c757d',     		
 			     	    is3D: true,
 			     	    isStacked:true,
 			     	 
 					 hAxis: {
 						    textStyle: {
 						        color: '#ffffff'
 						    }
 						},
 						vAxis: {
 							 baselineColor: '#ffffff',
 						    textStyle: {
 						        color: '#ffffff'
 						    }
 						},
 						legend: {
 						    textStyle: {
 						        color: '#ffffff'
 						    }
 						},
 						titleTextStyle: {
 						    color: '#ffffff'
 						},	
	 		 				
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_Volumen'));
	 	chart.draw(data, options); 		
 }
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawHighscore);
 
 function drawHighscore(){
	 var data = google.visualization.arrayToDataTable([
	 		['Daten', 'variable Kosten' , 'Bestellfix Kosten', 'Fehlmengenkosten'], 		
	 		${drawHighscore}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Highscore Daten',
	 			subtitle:'eigene Heuristik',
 				backgroundColor: '#6c757d',     		
	 			     	    is3D: true,
	 			     	  
	 					 hAxis: {
	 						    textStyle: {
	 						        color: '#ffffff'
	 						    }
	 						},
	 						vAxis: {
	 							 baselineColor: '#ffffff',
	 						    textStyle: {
	 						        color: '#ffffff'
	 						    }
	 						},
	 						legend: {
	 						    textStyle: {
	 						        color: '#ffffff'
	 						    }
	 						},
	 						titleTextStyle: {
	 						    color: '#ffffff'
	 						},	
					
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_Highscore'));
	 	chart.draw(data, options); 		
 }
 
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawHighscoreKosten);
 
 function drawHighscoreKosten(){
 	var data = google.visualization.arrayToDataTable([
 		${produktNamen}	
 		${drawHighKosten}    		
 	]);
 	
 	var options = {
 			title: 'Kapitalbindung pro Produkt',
 			subtitle:'eigene Heuristik',
 			backgroundColor: '#6c757d',     		
	     	    is3D: true,
	     	   isStacked:true,
			 hAxis: {
				    textStyle: {
				        color: '#ffffff'
				    }
				},
				vAxis: {
					 baselineColor: '#ffffff',
				    textStyle: {
				        color: '#ffffff'
				    }
				},
				legend: {
				    textStyle: {
				        color: '#ffffff'
				    }
				},
				titleTextStyle: {
				    color: '#ffffff'
				},	
 				
 	};
 	
 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_HighscoreKosten'));
 	chart.draw(data, options); 		
 	
	}
 
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawHighscoreBestand);
 
 function drawHighscoreBestand(){
	 var data = google.visualization.arrayToDataTable([
	 		${produktNamen} 		
	 		${drawHighBestand}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Bestand',
	 			subtitle:'eigene Heuristik',
	 			backgroundColor: '#6c757d',     		
		     	    is3D: true,
		     	   isStacked:true,
				 hAxis: {
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					vAxis: {
						 baselineColor: '#ffffff',
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					legend: {
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					titleTextStyle: {
					    color: '#ffffff'
					},	
					
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_HighscoreBestand'));
	 	chart.draw(data, options); 		 	
		}  
 
 //Volumen des Spitzenreiters
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawHighscoreVolumen);
 
 function drawHighscoreVolumen(){
	 var data = google.visualization.arrayToDataTable([
	 		${produktNamen} 		
	 		${drawHighVol}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Volumen',
	 			subtitle:'eigene Heuristik',
	 			backgroundColor: '#6c757d',     		
		     	    is3D: true,
		     	    isStacked:true,		     	  
				 hAxis: {
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					vAxis: {
						 baselineColor: '#ffffff',
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					legend: {
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					titleTextStyle: {
					    color: '#ffffff'
					},	
	 									
	 	};
	 	
	 	var chart = new google.visualization.ColumnChart(document.getElementById('draw_HighscoreVolumen'));
	 	chart.draw(data, options); 		
 }
 
 //Highscoredaten des Spitzenreiters
 google.charts.load('current', {packages:['corechart', 'bar']});
 google.charts.setOnLoadCallback(drawLeaderScore);
 
 function drawLeaderScore(){
	 var data = google.visualization.arrayToDataTable([
	 		['Daten', 'variable Kosten' , 'Bestellfix Kosten', 'Fehlmengenkosten'], 		
	 		${drawHighscoreScore}    		
	 	]);
	 	
	 	var options = {
	 			title: 'Highscore Daten',
	 			subtitle:'eigene Heuristik',
	 				backgroundColor: '#6c757d',     		
		     	    is3D: true,
				 hAxis: {
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					vAxis: {
						 baselineColor: '#ffffff',
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					legend: {
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					titleTextStyle: {
					    color: '#ffffff'
					},
					
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
	     		backgroundColor: '#6c757d',     		
	     	    is3D: true,
	     	  	colors: ['yellow'],
	     	   lineWidth: 3,
			 hAxis: {
				    textStyle: {
				        color: '#ffffff'
				    }
				},
				vAxis: {
					 baselineColor: '#ffffff',
				    textStyle: {
				        color: '#ffffff'
				    }
				},
				legend: {
				    textStyle: {
				        color: '#ffffff'
				    }
				},
				titleTextStyle: {
				    color: '#ffffff'
				},
				
     		};

     var chart = new google.visualization.LineChart(document.getElementById('userBestandsVerlauf'));
     chart.draw(data, options);

 }
 google.charts.load('current', {
	  callback: drawChart,
	  packages: ['corechart']
	});

 //StackedKostenKapbindung Links
	function drawChart() {
	  var data = google.visualization.arrayToDataTable([
		 ${produktNamen}
	   ${drawStackedKosten}
	  ]);


	 	var options = {
	 			title: 'Kosten pro Periode ',
	 			subtitle:'eigene Heuristik',
	 			backgroundColor: '#6c757d',     		
	     	    is3D: true,
	     	    isStacked:true,
	     	 
			 hAxis: {
				    textStyle: {
				        color: '#ffffff'
				    }
				},
				vAxis: {
					 baselineColor: '#ffffff',
				    textStyle: {
				        color: '#ffffff'
				    }
				},
				legend: {
				    textStyle: {
				        color: '#ffffff'
				    }
				},
				titleTextStyle: {
				    color: '#ffffff'
				},	
	 				
};

	  var chartDiv = document.getElementById('chart_div');
	  var chart = new google.visualization.ColumnChart(chartDiv);
	  chart.draw(data, options);
	}
	
 //stackedBestandkapitalbindung links
	google.charts.load('current', {
		  callback: drawStackedBestand,
		  packages: ['corechart']
		});

		function drawStackedBestand() {
		  var data = google.visualization.arrayToDataTable([
			 ${produktNamen}
		   ${stackedBestand}
		  ]);


		 	var options = {
		 			title: 'Bestand pro Periode',
		 			subtitle:'eigene Heuristik',
		 			backgroundColor: '#6c757d',     		
		     	    is3D: true,
		     	    isStacked:true,
		     	 
				 hAxis: {
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					vAxis: {
						 baselineColor: '#ffffff',
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					legend: {
					    textStyle: {
					        color: '#ffffff'
					    }
					},
					titleTextStyle: {
					    color: '#ffffff'
					},	
		 				
	};

		  var chartDiv = document.getElementById('stackedBestand');
		  var chart = new google.visualization.ColumnChart(stackedBestand);
		  chart.draw(data, options);
		}
		
		function toggle(id) {
			  var x = document.getElementById(id);
			  if (x.style.display !== "none") 
		      {  
		          x.style.display = "none";  
		      }  
		      else
		      {  
		          x.style.display = "block";  
		      }  
		 }  
		
		function removeGoogleErrors() {
			  var id_root = "google-visualization-errors-all-";			    
			    for(let i = 0; i<5;i++){
			    	document.getElementById(id_root + i).style.display = 'none';
			    	console.log(document.getElementById(id_root + i).style.display = 'none');
			    }			   
			}		
		
			
 </script>


</head>
<body class = "bg-secondary text-white"> 
<x:AdminHead></x:AdminHead>
<x:Tipp></x:Tipp>
<div class = "container mb-5">
	<table>
		<tr>
			<td>Name: </td>
			<td>${name }</td>
		</tr>
		<tr>
			<td>Auth-Key: </td>
			<td>${key }</td>
		</tr>
	</table>  
</div>

<div class = "centerdrop">
	<x:ProfilDropDown/> 
</div>



<h6>${fehler }</h6>
	<div class = "row">	
		<div class = "col">
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
		<h6 class = "pl-5">Highscore 1 Punkte</h6>
		<div class = "container">
		<div class = "row pl-5">
		<div class  = "col">
		<h6 >Tabelle Score 1 <img src = "img/sort-down-solid.svg" width ="20" class = "show pb-3" onClick="toggle('h1')"></h6>	
		<div  id ="h1" style = display:none>
			<table class = "table table-hover"> 
 			<thead class = "bg-dark"> 
				<tr> 					
					<c:forEach items="${columnNames }" var = "name">
						<th scope = "row">${name }</th>
					</c:forEach>			
 				</tr> 
			</thead> 
			<tbody class = "tablebody"> 
				<c:forEach items = "${ resultList}" var = "produkt" >
					<tr> 
						
						<c:forEach items = "${produkt}" var = "value">
							<td>${value }</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>		 
 		</table>
 		</div>	
 		</div> 
 		</div>
 		</div>
			<div id="draw_Highscore" style = "margin-top:28px; width: 900px; height: 500px;"></div>       		
      		 <div id="draw_Volumen" style="width: 900px; height: 500px;"></div>      		 
      		 <div id = "chart_div" style = "width: 900px; height: 500px;"></div>
      		  <div id = "stackedBestand" style = "width: 900px; height: 500px;"></div>
      		  <div id="userBestandsVerlauf" style="width: 900px; height: 500px;"></div>
		</div>
		
		<div class = "col">	
		<h6 class = "pl-5">Highscore 2 Punkte</h6>
		<div class = "container">
		<div class = "row pl-5">
		<div class  = "col ">
		<h6>Tabelle Score 2 <img src = "img/sort-down-solid.svg" width ="20" class = "show pb-3" onClick="toggle('h2')"></h6>	
		<div id ="h2" style = display:none>
		<table class = "table table-hover"> 
 			<thead class = "bg-dark"> 
				<tr> 					
					<c:forEach items="${columnNames }" var = "name">
						<th scope = "row">${name }</th>
					</c:forEach>			
 				</tr> 
			</thead> 
			<tbody class = "tablebody"> 
				<c:forEach items = "${ resultList2}" var = "produkt2" >
					<tr> 
						
						<c:forEach items = "${produkt2}" var = "value">
							<td class = "center">${value }</td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>		 
 		</table>	 
 	</div>
 	</div>
 	</div>
 	</div>
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>			
			<div id="draw_HighscoreLeader" style="width: 900px; height: 500px;"></div>		
       		<div id="draw_HighscoreKosten" style="width: 900px; height: 500px;"></div>
       		<div id="draw_HighscoreBestand" style="width: 900px; height: 500px;"></div>
       		<div id="draw_HighscoreVolumen" style="width: 900px; height: 500px;"></div>
		</div>
	</div>



		
       


</body>
</html>