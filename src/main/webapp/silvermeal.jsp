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
  <a class="flex-sm-fill text-sm-center nav-link active" href="silvermeal.jsp">Beispielheuristik</a>
  <a class="flex-sm-fill text-sm-center nav-link" href="ergebniseinreichen.jsp">Ergebnis einreichen</a>
  </nav>
 <h5>Beispielheuristik (Silver Meal)</h5>
<pre>
<code>
	public class SilverMealTestKlasse {
	
	public static void starteSilverMeal(Lager lager, List<Produkt> produkte) throws IOException {
		List<Ergebnis> ergebnisse = new ArrayList<>();
		UserErgebnis ue = new UserErgebnis();
		System.out.println("Lager: " + lager.toString());
		
		for(int i = 0; i < produkte.size();i++) {
			Produkt p = produkte.get(i);		
			System.out.println(p.toString());
			List<Ergebnis> tmp = warenBestellung(p, lager);			
			ergebnisse.addAll(tmp);
			System.out.println("---------------------------------------------------------------");
		}
		System.out.println(ergebnisse.toString());
		ue.setProdukte(ergebnisse);
		ue.setAPI_KEY("eigener API KEY");
		ErgebnisSenden.sendErgebnis(ue);		
	}
	</code>
	</pre>
	<pre><code>
	/**
	 * Berechne die durchschnittlichen Kosten einer Bestellung und füge solange weitere Bestellungen hinzu bis 
	 * die durchschnittlichen Kosten ansteigen
	 * @param p
	 * @param lager
	 * @return
	 * @throws IOException
	 */
	
	public static List<Ergebnis> warenBestellung(Produkt p, Lager lager) throws IOException{
		List<Ergebnis> ergebnisse= new ArrayList<>();
		
		for(int i = 0;i < lager.getPer();i++) {
			int bestellper = i+1;
			int verbrauch = HtttpAnfrage.httpNaechstePer(p.getName(), i+1);
			if(i!= lager.getPer()-1) {	
				double lagerKosten = 0.00;
				int zaehler = 0;
				double kosten = p.getBestellfix();
				double tmpKosten = p.getBestellfix();			
				for(int k = (i+1); k< lager.getPer();k++) {
					zaehler++;
					int v2 = HtttpAnfrage.httpNaechstePer(p.getName(), k+1);
					lagerKosten += v2 * p.getLagerkostensatz()*zaehler;
					kosten = p.getBestellfix() + lagerKosten;
				
					/**
					 * Falls die Kosten des Zwischenspeichers > als die Kosten für eine Bestellung der nächsten Periode sind
					 */
					if(tmpKosten>(kosten/(zaehler+1))){
						i++;
						verbrauch +=v2;
						tmpKosten = kosten/(zaehler+1);
						/**
						 * Falls die letzte Periode erreicht wird, 
						 * dann füge den letzten Wert hinzu und brich ab
						 */
						if(k==lager.getPer()-1) {
							Ergebnis e = new Ergebnis();
							e.setBestellmenge(verbrauch);
							e.setErgebnisPeriode(bestellper);
							e.setKosten(tmpKosten);
							e.setProduktName(p.getName());
							ergebnisse.add(e);
							List<Ergebnis> zeros = addZeros(bestellper,p,zaehler);	
							ergebnisse.addAll(zeros);
							break;
						}
					}else {
						Ergebnis e = new Ergebnis();
						e.setBestellmenge(verbrauch);
						e.setErgebnisPeriode(bestellper);
						e.setKosten(tmpKosten);
						e.setProduktName(p.getName());
						ergebnisse.add(e);
						List<Ergebnis> zeros = addZeros(bestellper, p, zaehler);
						ergebnisse.addAll(zeros);
						break;
					}				
				}
			}else {
				Ergebnis e = new Ergebnis();
				e.setBestellmenge(verbrauch);
				e.setErgebnisPeriode(bestellper);
				e.setProduktName(p.getName());
				e.setKosten(p.getBestellfix());
				ergebnisse.add(e);
				break;
			}
		}		
		return ergebnisse;		
	}
	</code>
	</pre>
	<pre>
	<code>
	/**
	*Füge 0 Werte hinzu für bestellfreie Perioden des Produktes	
	*/
	public static List<Ergebnis> addZeros(int start, Produkt p, int zaehler) {
		List<Ergebnis> zeros = new ArrayList<Ergebnis>();
		for(int i = 1;i< zaehler; i++) {
			Ergebnis e = new Ergebnis();
			e.setBestellmenge(0);		
			e.setKosten(0);
			e.setProduktName(p.getName());
			e.setErgebnisPeriode(start+i);
			zeros.add(e);			
		}	
		return zeros;
	}
}
</code>
</pre>

</body>
</html>