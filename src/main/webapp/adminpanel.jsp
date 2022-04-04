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
<script>
$(document).ready(function(){
	  $("#pInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#tableBody tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});

</script>
</head>
<body class = "bg-secondary text-white">
	<x:AdminHead></x:AdminHead>
	
	<p>Aktuell im Bestand sind: </p>
	<p>A Produkt: 46, B Produkt: 43, C Produkt: 32
		
	
	<h4 class = "error">${fehler }</h4>

	<div class = "row m-md-5">
	<div class = "col mr-2">
	<a href ="erweitert.jsp">erweitertes Erstellen</a>
<form action ="ProblemInstanzErstellen" method = "GET">
<h4>Spielregeln</h4>
<table>
	<tr>
		<td><label>Sammelbestellung</label>
		<td><div class="input-group">
 		 <div class="input-group-prepend">
   	 		<div class="input-group-text">
     			 <input type="checkbox" aria-label="Checkbox for following text input">
    		</div>
 		 </div>
 		 
 		 <input type="text" class="form-control" aria-label="Text input with checkbox">
		</div>
		</td>
	</tr>	
	<tr>		
		<td><label>Zeitrestriktion</label></td>		
		<td><input type = "text" class = "form-control" name = "zeit" placeholder = "In Sekunden z.B. für 400ms= 0.4"></td>	
		</tr>
			
</table>
<h4>Produkterstellung</h4>
	<table>
		<tr>
		<td>Periodenanzahl:</td>		
		<td></td>	
		<td><input type = "text" name ="perioden" class = "form-control"></td>
		</tr>
		<tr>
			<td>Produkteingabe:</td>	
			<td></td>
		
			<td><label>Produkt mit starken Schwankungen</label></td>
			<td><label>Anzahl der Saisonalenprodukte</label></td>	
			<td><label>Konstantes Produkt</label>	
		</tr>	
		<tr>
			<td></td>
			<td>A-Produkt</td>
			<td><input type = "text" name = "aprodukt" class = "form-control"></td>
			<td><input type = "text" name = "saisonalA" class = "form-control"></td>
			<td><input type = "text" name = "kostantA" class = "form-control"></td>
			
		</tr>		
		<tr>
			<td></td>
			<td>B-Produkt</td>
			<td><input type = "text" name = "bprodukt" class = "form-control"></td>	
			<td><input type = "text" name = "saisonalB" class = "form-control"></td>	
			<td><input type = "text" name = "kostantB" class = "form-control"></td>
				
		</tr>		
		<tr>
			<td></td>
			<td>C-Produkt</td>
			<td><input type = "text" name = "cprodukt" class = "form-control"></td>
			<td><input type = "text" name = "saisonalC" class = "form-control"></td>
			<td><input type = "text" name = "kostantC"class = "form-control" ></td>
			
		</tr>
		<tr>
			<td><label for ="lager">Größe des Lagers</label></td>
			<td></td>
			<td><select id = "lager" name = "lager">
				<option value = "klein">Kleines Lager</option>
				<option value = "medium">Mittelgroßes Lager</option>
				<option value = "gross">Großes Lager</option>			
			</select></td>		
		</tr>
		<tr>
			<td><label for ="kapBindung">Kapitalbindung des Lagers</label></td>
			<td></td>
			<td><select id = "kapital" name = "kapital">
				<option value = "wenig">Kleines Budget</option>
				<option value = "medium">Medium Budget</option>
				<option value = "viel">Viel Budget</option>			
			</select></td>		
		</tr>							
		<tr>
		<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td><button class = "btn btn-dark" type = "submit">Senden!</button></td>
		</tr>	
	</table>
</form>
</div>
<div class = "col ml-4">

	<form action = "UpdateLager" method = "GET">
	<h4>Lagerdaten</h4>
	<table>
	<tr>
		<td><label>Volumen des Lager</label></td>
		<td><input type = "text" name = "vol" value = "${lagerVol }"></td>
	</tr>
	<tr>
		<td><label>Lager Kapitalbindung</label></td>
		<td><input type = "text" name = "kapBindung" value = "${lagerKap }"></td>
	</tr>
	<tr>
		<td><button class = "btn btn-dark" type = "submit">update</button>
	</tr>
	</table>
	</form>


</div>
</div>


<form action = "CreateCSV" method = "GET">
        <button class = "btn btn-warning" type = "submit">Als CSV speichern</button> 
</form>




<button class="btn btn-dark btn-lg btn-block collapsed" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
Produktübersicht anzeigen
</button>
				
<div class="collapse" id="collapseExample" style="">
<input class ="form-control" id="pInput" type = "text" placeholder="Filtern nach Produktnamen">
<table class = "table table-hover">
	<thead>
            <tr>                
           		<th>#</th>
                <c:forEach items="${columnNames}" var="name">                	
                    <th>${name}</th>
                </c:forEach>
            </tr>
            </thead>
            <tbody id="tableBody">
           
            <c:forEach items="${resultList}" var="result" varStatus = "index">
             
                <tr>     
                	 <th scope = "row">${index.index }</th>  
                    <c:forEach items="${result}" var="value">  
                                       	
                        <td>${value}</td>
                    </c:forEach>
                </tr>
            </c:forEach>
            </tbody>
        </table> 
       </div>
	
	

</body>
</html>