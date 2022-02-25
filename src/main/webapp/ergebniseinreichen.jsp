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
<body>
<x:AdminHead></x:AdminHead>
<nav class="nav nav-pills flex-column flex-sm-row">
  <a class="flex-sm-fill text-sm-center nav-link" href="how-to.jsp">Übersicht</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="datenklassen.jsp">Datenklassen</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="httpRequest.jsp">Http-Request</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="json.jsp">JSON</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="silvermeal.jsp">Beispielheuristik</a>
  <a class="flex-sm-fill text-sm-center nav-link active" href="ergebniseinreichen.jsp">Ergebnis einreichen</a>
</nav>
<h4>Ergebnis einreichen</h4>

<p> Das Einsenden der Ergebnisse erfolgt über die oben genannte /Ergebnis Schnittstelle. Zur Interaktion mit dieser Schnittstelle muss das Ergebnis im JSON-Format gesendet werden. Hierzu wird ebenfalls empfohlen eine der genannten Librarys zu verwenden.
<p>  Syntax des Ergebnis:
	<ul>
		<li>API-Key</li>		
		<li>Produkte:</li>
			<ul>
				<li>Bestellmenge</li>
				<li>Produktname</li>
				<li>Kosten</li>
				<li>Periode</li>
			</ul>
	</ul>
	
	<p> Die nachfolgende Klasse nimmt das zuvor erzeugte Ergebnis der Heuristik convertiert es ins JSON-Format 
	und sendet es an die Schnittstelle als POST-Request.<img src = "img/sort-down-solid.svg" width ="20" class = "pb-3" onClick="toggle('ergebnis')">
	</p>
	<br>
	
	<div id="ergebnis" style="display:none">	
	<pre>
	<code>
	public class ErgebnisSenden {	
	public static void sendErgebnis(UserErgebnis ue) throws MalformedURLException, IOException {		
		jsonConvertion jc = new jsonConvertion();		
		String json = jc.dataToJson(ue);		
		
		String url = "ErgebnisSendenLink einfügen";
		HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
		
		httpClient.setRequestMethod("POST");
		httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		httpClient.setDoOutput(true);		
		try (DataOutputStream dos = new DataOutputStream(httpClient.getOutputStream())){
			 dos.writeBytes("json=" +json);
			 dos.flush();
		}
		int responseCode = httpClient.getResponseCode();
		System.out.println("Sende Request an: " + url);
		System.out.println("Http: " + responseCode);
		BufferedReader in = new BufferedReader( new InputStreamReader(httpClient.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);					    
		}		
	}

}
	
	</code>
	</pre>
</div>
</body>
</html>