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
<link rel = "stylesheet" type = "text/css" href= "css/style.css">
<meta charset="ISO-8859-1">
<title>Bestenliste</title>

<script>
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

<x:Tipp/>


<x:Bestenliste></x:Bestenliste>
       
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