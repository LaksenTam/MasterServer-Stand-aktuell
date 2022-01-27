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
<body>
	<x:AdminHead></x:AdminHead>
	
	<p>Aktuell im Bestand sind: </p>
	<p>A Produkt: 46, B Produkt: 43, C Produkt: 32
		
	
	<h4 class = "error">${fehler }</h4>
	
<form action ="ProblemInstanzErstellen" method = "GET">
	<table>
		<tr>
		<td>Periodenanzahl:</td>		
		<td></td>	
		<td><input type = "text" name ="perioden"></td>
		</tr>
		<tr>
			<td>Produkteingabe:</td>	
			<td></td>
			<td></td>
			<td><label>Anzahl der Saisonalenprodukte</label></td>		
		</tr>	
		<tr>
			<td></td>
			<td>A-Produkt</td>
			<td><input type = "text" name = "aprodukt"></td>
			<td><input type = "text" name = "saisonalA"></td>
			
		</tr>		
		<tr>
			<td></td>
			<td>B-Produkt</td>
			<td><input type = "text" name = "bprodukt"></td>	
			<td><input type = "text" name = "saisonalB"></td>		
		</tr>		
		<tr>
			<td></td>
			<td>C-Produkt</td>
			<td><input type = "text" name = "cprodukt"></td>
			<td><input type = "text" name = "saisonalC"></td>
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
			<td><button type = "submit">Senden!</button></td>
		</tr>	
	</table>
</form>

<form action = "CreateCSV" method = "GET">
        <button type = "submit">Als CSV speichern</button> 
</form>

	<p>Produkttabelle</p>
<button class="btn btn-primary btn-lg btn-block collapsed" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
Anzeigen
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