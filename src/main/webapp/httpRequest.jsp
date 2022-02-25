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
<script src="https://kit.fontawesome.com/5cfe696ca3.js" crossorigin="anonymous">
</script><title>Insert title here</title>
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
  <a class="flex-sm-fill text-sm-center nav-link active" href="httpRequest.jsp">Http-Request</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="json.jsp">JSON</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="silvermeal.jsp">Beispielheuristik</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="ergebniseinreichen.jsp">Ergebnis einreichen</a>
</nav>
<div class = "container">
<h5>Senden eines POST-Request <img src = "img/sort-down-solid.svg" width ="20" class = "pb-3" onClick="toggle('post')"></h5>
<div id="post" style="display:none">
<pre>
	<code>
	
	public static void produktListe() throws IOException{		
		jsonConvertion jc = new jsonConvertion();		
		URL url = new URL("Link einfügen für die Abfrage");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())){
			wr.writeBytes("key= "der eigene API Key"");			
			wr.flush();
		}		
		int status = con.getResponseCode();
		System.out.println("StatusCode: " + status);
		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);					    
		}				
		String res = content.toString();
		//Weiterverarbeitung der Daten		
		in.close();
		con.disconnect();		
	}
	
	</code>
</pre>
</div>

<h5>Senden eines GET-Request
<img src = "img/sort-down-solid.svg" width ="20" class = "pb-3" onClick="toggle('get')"></h5>
<div id="get" style="display:none">
<pre>
<code>
	public static int httpNaechstePer(String name, int periode) throws IOException{
		int verbrauch = 0;
		URL url = new URL("link einfügen");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		String v = String.valueOf(periode);
		
		Map<String, String> parameters = new HashMap<>();
		parameters.put("periode", v );
		parameters.put("produktName", name);
		parameters.put("key", "eigener API-Key");
		con.setDoOutput(true);
		DataOutputStream out = new DataOutputStream(con.getOutputStream());
		out.writeBytes(getParamsString(parameters));
		out.flush();
		out.close();
		int status = con.getResponseCode();
		if(status != 200) {
			System.out.println("UPS");
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		
		
		String response = content.toString();		
		
		verbrauch = jsonConvertion.convertVerbrauchtoProdukt(response);
		
		in.close();
		con.disconnect();	
		return verbrauch;
	}
</code>

<code>
	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException{
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
		    result.append("=");
		    result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
		    result.append("&");
		    }

		String resultString = result.toString();
		return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	}
	
</code>
</pre>
</div>
</div>
</body>
</html>