<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<link rel = "stylesheet" href = "bootstrap-4.2.1-dist/css/bootstrap.min.css">
<script src="bootstrap-4.2.1-dist/js/bootstrap.min.js">></script>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action ="verbrauchProProdukt" method = "Get">
	<label>Produktname:</label>
	<input type = "text" name = "produktName">
	<label>Periode: </label>
	<input type = "text" name = "periode">
	<button type = "submit">senden</button>

</form>

<form action ="ProduktListenAnfordern" method = "Get">
<button type = "submit">senden</button>
</form>

</body>
</html>