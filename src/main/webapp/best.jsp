<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <%@ taglib uri="WEB-INF/custom.tld" prefix="x"%>
<!DOCTYPE html>
<html>
<head>

<script src="js/jquery-3.3.1.slim-min.js" ></script>
<script src="js/popper.min.js" ></script>
<script src="js/jquery.tablesort.min.js"></script>

<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js"></script>
<link rel = "stylesheet" href= "css/style.css">
<meta charset="ISO-8859-1">
<title>Bestenliste</title>

</head>
<body>

<x:AdminHead></x:AdminHead>

<div class="list-group w-25 mb-4">
  <div class="list-group-item list-group-item-action list-group-item-info i-btn">
  <img src = "img/info.svg">
  <span>Klicken Sie auf die Spalte, um die Tabelle zu sortieren  </span>  
  </div>
</div>
<div class = "container">
<input class="form-control " id="myInput" type="text" placeholder="Search..">

<table id = "tablesorter" class = "table table-hover tablesorter">
	<thead class = "bg-danger text-white">
            <tr>
                <c:forEach items="${columnNames}" var="name">                	
                    <th>${name}</th>
                </c:forEach>
            </tr>
            </thead>
            <tbody id = "myTable">           
          	 	<c:forEach items="${resultList}" var="result">             
                	<tr>                     	
                    	<c:forEach items="${result}" var="value">                                        	
                        	<td>${value}</td>
                    	</c:forEach>
                	</tr>
            	</c:forEach>
            </tbody>
        </table> 
</div>
       
<script>
$(function() {
	$('table').tablesorter();
});

$(document).ready(function(){
	  $("#myInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#myTable tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
</script>

</body>
</html>