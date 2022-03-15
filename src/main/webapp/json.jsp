<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<script type="text/javascript">
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
	
</script>
</head>
<body class = "bg-secondary text-white">
<x:AdminHead></x:AdminHead>
<nav class="nav nav-pills flex-column flex-sm-row">
  <a class="flex-sm-fill text-sm-center nav-link" href="how-to.jsp">Übersicht</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="datenklassen.jsp">Datenklassen</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="httpRequest.jsp">Http-Request</a>
  <a class="flex-sm-fill text-sm-center nav-link active bg-dark" href="json.jsp">JSON</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="silvermeal.jsp">Beispielheuristik</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="ergebniseinreichen.jsp">Ergebnis einreichen</a>
</nav>


<h5>Serialisieren der Ergebnisse mit Gson <img src = "img/sort-down-solid.svg" width ="20" class = "show pb-3" onClick="toggle('ergebnis')"></h5>

<div id="ergebnis" style="display:none">
<pre class = "text-white">
<code>
		public String dataToJson(UserErgebnis ue) {		
			Gson gson = new Gson();
			String ergebnis = gson.toJson(ue);
			return ergebnis;		
		}
	</code>	
</pre>
</div>

<h5>Deserialisieren mit JSON mit der Produktklasse <img src = "img/sort-down-solid.svg" width ="20" class = "show pb-3" onClick="toggle('deser')"></h5>


<div id = "deser" style = "display:none">
<pre class = "text-white">
	<code>
		public void jsonToData(String response) {		
			Gson gson = new Gson();
			Produkt[] p = gson.fromJson(response, Produkt[].class);				
		}
	</code>
	</pre>
	</div>
	<h5>Deserialisieren der Lagerklasse <img src = "img/sort-down-solid.svg" width ="20" class = "show pb-3" onClick="toggle('lager')"></h5>

	<div id = "lager" style = "display:none">
	<pre class = "text-white">
	<code>
		public void jsonToLager(String response){
		Gson gson = new Gson();
		Lager lager = gson.fromJson(response, Lager.class);
		}
		
	</code>
	</pre>
	</div>
	<h5>JSON-Format <img src = "img/sort-down-solid.svg" width ="20" class = "show pb-3" onClick="toggle('jsonbsp')"></h5>
	<div id ="jsonbsp" style = "display:none">
	<pre class = "text-white"><code>
		{
  			"API_KEY": "", 			
  			"produkte": [
    			{
    				"bestellmenge": ,
     				"produktName": "",
      				"kosten": 734,
      				"ergebnisPeriode": 0
    			},
    			{
      				"bestellmenge": ,
      				"produktName": "",
      				"kosten": 800,
      				"ergebnisPeriode": 
    			},
   				{
      				"bestellmenge": ,
      				"produktName": "",
      				"kosten": ,
      				"ergebnisPeriode": 
    			},
    			{
      				"bestellmenge": ,
      				"produktName": "",
      				"kosten": ,
      				"ergebnisPeriode": 
    			},
   				{
      				"bestellmenge": ,
      				"produktName": "",
      				"kosten": ,
      				"ergebnisPeriode": 
    			}
  			]
		}
	</code></pre>
	</div>
</body>
</html>