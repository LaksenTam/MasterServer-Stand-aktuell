package data;



public class Produktergebnis extends Userergebnis {
	
	private int bestellmenge;
	private String produktName;
	private double kosten;


	
	public Produktergebnis() {
	
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

	@Override
	public String toString() {
		return "Produktergebnis [bestellmenge=" + bestellmenge + ", produktName=" + produktName + ", kosten=" + kosten
				+ "]";
	}

	
	
	

}