package data;

public class Zwischenergebnis extends Userergebnis {
	
	public int zwischenPeriode;
	public String produktNameAnfrage;
	
	public Zwischenergebnis() {
		
	}
	
	public int getZwischenPeriode() {
		return zwischenPeriode;
	}
	public void setZwischenPeriode(int zwischenPeriode) {
		this.zwischenPeriode = zwischenPeriode;
	}
	public String getProduktNameAnfrage() {
		return produktNameAnfrage;
	}
	public void setProduktNameAnfrage(String produktNameAnfrage) {
		this.produktNameAnfrage = produktNameAnfrage;
	}
	
}
