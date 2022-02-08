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
</head>
<body>


<table class = "table table-hover">
	<thead>
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Score</th>			
		</tr>
	</thead>
	<tbody id = "tablebody">
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




</body>
</html>