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
</head>
<body>
<x:AdminHead></x:AdminHead>
<h4>Http-Request(Post-Anfrage)</h4>
<p>Bei dieser Seite ist es egal, ob ihr eine Post oder Get Anfrage stellt, es werden beide Arten unterstützt und verarbeitet.
<pre>
<code>
	//z.B. URL url = new URL("http://www.google.com/");
	URL url = new URL("Hier die Webseite einfügen");
	HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setDoOutput(true);
		try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())){
			//z.B. wr.writeBytes("q=1111");				
			wr.writeBytes("Hier entsprechende Attribute eingeben");			
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
		//Schließen der Attribute
		in.close();
		con.disconnect();
</code>
</pre>

<h4>Die Schnittstellen</h4>
<p> Dieser Server stellt verschiedene <a>Schnittstellen</a> zur Verfügung, über die die Daten abgefragt und gesendet werden können. 
<p> Die abgefragten Daten werden im JSON-Format ausgegeben. Zur Verarbeitung der Daten empfiehlt sich eine JSON-Library zu verwenden, wie z.B. <a href ="https://github.com/google/gson">Gson</a> oder <a href ="https://github.com/FasterXML/jackson">Jackson</a>.
<p> In den nachfolgenden Beispielen wurde die Library <a href ="https://github.com/google/gson">Gson</a> verwendet.
<p> Zur Interaktion mit dem Server muss der zur Verfügung gestellt API-Key verwendet werden. Der API-Key ist im Profil hinterlegt. 
<ol>
	<li>/GetProdukte stellt die Produktinformationen zur Verfügung
	<li>/GetLager gibt die Attribute des Lagers aus
	<li>/ProduktListenAnfordern stellt euch alle notwendigen Daten (Produkte + Lager) zur Verfügung um mit der Berechnung zu beginnen</li>
	<li>/Schwierigkeitsgrad1 gibt ein angefragtes Produkt in einer Periode mit dem dazugehörigen Verbrauch aus</li>
	<li>/Schwierigkeitsgrad2 gibt eine Liste mit Produkten in einer angefragten Periode mit den dazugehörigen Verbräuchen zurück</li>
	<li>/Schwierigkeitsgrad3 gibt eine Liste mit Produkten in einer angefragten Periode mit den dazugehörigen Verbräuchen zurück. Zusätzlich wird die Performanz des Algorithmus gemessen.</li>
	<li>/Ergebnis über diese Schnittstelle kann das Ergebnis eingereicht werden
</ol>

<h4>Ergebnis einreichen</h4>
<p> Das Einsenden der Ergebnisse erfolgt über die oben genannte /Ergebnis Schnittstelle. Zur Interaktion mit dieser Schnittstelle muss das Ergebnis im JSON-Format gesendet werden. Hierzu wird ebenfalls empfohlen eine der genannten Librarys zu verwenden.
<p>  Syntax des Ergebnis:
	<ul>
		<li>API-Key</li>
		<li>Periode</li>
		<li>Produkte:</li>
			<ul>
				<li>Bestellmenge</li>
				<li>Produktname</li>
				<li>Kosten</li>
			</ul>
	</ul>
	<h5>Beispieldatenklassen</h5>
	<pre>
	<code>
	</code>
	import java.util.List;

	public class UserErgebnis {
		
		private String API_KEY;
		private int periode;
		private List<Ergebnis> produkte;
	
		public UserErgebnis() {		
		}	
	
		public List<Ergebnis> getProdukte() {
			return produkte;
		}
		public void setProdukte(List<Ergebnis> produkte) {
			this.produkte = produkte;
		}
		public String getAPI_KEY() {
			return API_KEY;		
		}
	
		public int getPeriode() {
			return periode;
		}

		public void setPeriode(int periode) {
			this.periode = periode;
		} 
		public void setAPI_KEY(String aPI_KEY) {
			API_KEY = aPI_KEY;
		}	
	}
	
		<code>
	public class Ergebnis extends UserErgebnis{
	
		private int bestellmenge;
		private String produktName;
		private double kosten;
		private int ergebnisPeriode;
	
		public Ergebnis() {
	
		}

		public int getBestellmenge() {
			return bestellmenge;
		}

		public void setBestellmenge(int bestellmenge) {
			this.bestellmenge = bestellmenge;
		}

		public String getProduktName() {
			return produktName;
		}

		public void setProduktName(String produktName) {
			this.produktName = produktName;
		}

		public double getKosten() {
			return kosten;
		}

		public void setKosten(double kosten) {
			this.kosten = kosten;
		}
	
		public int getErgebnisPeriode() {
			return ergebnisPeriode;
		}

		public void setErgebnisPeriode(int ergebnisPeriode) {
			this.ergebnisPeriode = ergebnisPeriode;
		}	
	}
				
		</code>		
	</pre>
<h5>Beispielklasse zum Senden des Ergebnisses</h5>
<pre>
	<code>
	public class ErgebnisSenden {	
		public static void sendErgebnis(UserErgebnis ue) throws MalformedURLException, IOException {		
			jsonConvertion jc = new jsonConvertion();		
			String json = jc.dataToJson(ue);
			System.out.println(json);
		
			String url = "http://lukas-hoffmeister.de/MasterServer/ErgebnisTestServ";
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
	</code>
</pre>
<h5>Beispiel zum Serialisieren und Deserialisieren mit Gson</h5>
<pre>
<code>
		public String dataToJson(UserErgebnis ue) {		
			Gson gson = new Gson();
			String ergebnis = gson.toJson(ue);
			return ergebnis;		
		}
	</code>
	<code>
		public void jsonToData(String response) {		
			Gson gson = new Gson();
			Produkt[] p = gson.fromJson(response, Produkt[].class);				
		}
	</code>
</pre>

<h5>Beispielhafte Lagerheuristik(Silver Meal)</h5>
<p> folgt...
</body>
</html>