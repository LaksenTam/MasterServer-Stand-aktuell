<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
  <a class="flex-sm-fill text-sm-center nav-link active" href="datenklassen.jsp">Datenklassen</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="httpRequest.jsp">Http-Request</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="json.jsp">JSON</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="silvermeal.jsp">Beispielheuristik</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="ergebniseinreichen.jsp">Ergebnis einreichen</a>
</nav>
<h5>Datenklassen</h5>

<h6>Userergebnis <img src = "img/sort-down-solid.svg" width ="20" class = "pb-3" onClick="toggle('user')"></h6>

<div id="user" style="display:none">
<pre>
<code>
public class UserErgebnis {
		
	private String API_KEY;
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

	public void setAPI_KEY(String aPI_KEY) {
		API_KEY = aPI_KEY;
	}
</code></pre>
</div>
<h6>Ergebnisklasse <img src = "img/sort-down-solid.svg" width ="20" class = "pb-3" onClick="toggle('ergebnis')"></h6>

<div id="ergebnis" style="display:none">
<pre><code>
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
</div>
<h6>Lagerklasse <img src = "img/sort-down-solid.svg" width ="20" class = "pb-3" onClick="toggle('lager')"></h6>

<div id="lager" style="display:none">
<pre><code>
public class Lager {
	private int anz;
	private int per;
	private float kbindung;
	private float sammelbkosten;
	private float lagerVol;
	
	public Lager() {
		
	}
	
	public int getAnz() {
		return anz;
	}
	public void setAnz(int anz) {
		this.anz = anz;
	}
	public int getPer() {
		return per;
	}
	public void setPer(int per) {
		this.per = per;
	}
	public float getKbindung() {
		return kbindung;
	}
	public void setKbindung(float kbindung) {
		this.kbindung = kbindung;
	}
	public float getSammelbkosten() {
		return sammelbkosten;
	}
	public void setSammelbkosten(float sammelbkosten) {
		this.sammelbkosten = sammelbkosten;
	}
	public float getLagerVol() {
		return lagerVol;
	}
	public void setLagerVol(float lagerVol) {
		this.lagerVol = lagerVol;
	}

</code></pre>
</div>
<h6>Produktklasse <img src = "img/sort-down-solid.svg" width ="20" class = "pb-3" onClick="toggle('produkt')"></h6>

<div id="produkt" style="display:none">
<pre><code>
public class Produkt {
	
	private String name;
	private float einstand;
	private float bestellfix;
	private float lagerkostensatz;
	private float fehlmengenkosten;
	private float vProdukt;
	private int minBestand;
	private int maxBestand;
	private int maxLager;
	private int verbrauch;
	private List<Integer> verbraeuche = new ArrayList<Integer>();

	public Produkt() {
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the einstand
	 */
	public float getEinstand() {
		return einstand;
	}

	/**
	 * @param einstand the einstand to set
	 */
	public void setEinstand(float einstand) {
		this.einstand = einstand;
	}

	/**
	 * @return the bestellfix
	 */
	public float getBestellfix() {
		return bestellfix;
	}

	/**
	 * @param bestellfix the bestellfix to set
	 */
	public void setBestellfix(float bestellfix) {
		this.bestellfix = bestellfix;
	}

	/**
	 * @return the lagerkostensatz
	 */
	public float getLagerkostensatz() {
		return lagerkostensatz;
	}

	/**
	 * @param lagerkostensatz the lagerkostensatz to set
	 */
	public void setLagerkostensatz(float lagerkostensatz) {
		this.lagerkostensatz = lagerkostensatz;
	}

	/**
	 * @return the fehlmengenkosten
	 */
	public float getFehlmengenkosten() {
		return fehlmengenkosten;
	}

	/**
	 * @param fehlmengenkosten the fehlmengenkosten to set
	 */
	public void setFehlmengenkosten(float fehlmengenkosten) {
		this.fehlmengenkosten = fehlmengenkosten;
	}

	/**
	 * @return the vProdukt
	 */
	public float getvProdukt() {
		return vProdukt;
	}

	/**
	 * @param vProdukt the vProdukt to set
	 */
	public void setvProdukt(float vProdukt) {
		this.vProdukt = vProdukt;
	}

	/**
	 * @return the minBestand
	 */
	public int getMinBestand() {
		return minBestand;
	}

	/**
	 * @param minBestand the minBestand to set
	 */
	public void setMinBestand(int minBestand) {
		this.minBestand = minBestand;
	}

	/**
	 * @return the maxBestand
	 */
	public int getMaxBestand() {
		return maxBestand;
	}

	/**
	 * @param maxBestand the maxBestand to set
	 */
	public void setMaxBestand(int maxBestand) {
		this.maxBestand = maxBestand;
	}

	/**
	 * @return the maxLager
	 */
	public int getMaxLager() {
		return maxLager;
	}

	/**
	 * @param maxLager the maxLager to set
	 */
	public void setMaxLager(int maxLager) {
		this.maxLager = maxLager;
	}

	/**
	 * @return the verbrauch
	 */
	public int getVerbrauch() {
		return verbrauch;
	}

	/**
	 * @param verbrauch the verbrauch to set
	 */
	public void setVerbrauch(int verbrauch) {
		this.verbrauch = verbrauch;
	}

	/**
	 * @return the verbraeuche
	 */
	public List<Integer> getVerbraeuche() {
		return verbraeuche;
	}

	/**
	 * @param verbraeuche the verbraeuche to set
	 */
	public void setVerbraeuche(List<Integer> verbraeuche) {
		this.verbraeuche = verbraeuche;
	}
</code></pre>
</div>
</body>
</html>