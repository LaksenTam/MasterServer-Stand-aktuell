package data;

public class ErweiterteProdukte {
	private int einstandminRange;
	private int einstandmaxRange;
	private double lagersatz;
	private int bestellMinRange;
	private int bestellMaxRange;
	private int fehlkostenMinRange;
	private int fehlkostenMaxRange;
	
	public ErweiterteProdukte() {
		
	}
	
	public double getEinstandminRange() {
		return einstandminRange;
	}
	public void setEinstandminRange(int einstandminRange) {
		this.einstandminRange = einstandminRange;
	}
	public int getEinstandmaxRange() {
		return einstandmaxRange;
	}
	public void setEinstandmaxRange(int einstandmaxRange) {
		this.einstandmaxRange = einstandmaxRange;
	}
	public double getLagersatz() {
		return lagersatz;
	}
	public void setLagersatz(double lagersatz) {
		this.lagersatz = lagersatz/100;
	}
	public int getBestellMinRange() {
		return bestellMinRange;
	}
	public void setBestellMinRange(int bestellMinRange) {
		this.bestellMinRange = bestellMinRange;
	}
	public int getBestellMaxRange() {
		return bestellMaxRange;
	}
	public void setBestellMaxRange(int bestellMaxRange) {
		this.bestellMaxRange = bestellMaxRange;
	}

	public int getFehlkostenMinRange() {
		return fehlkostenMinRange;
	}

	public void setFehlkostenMinRange(int fehlkostenMinRange) {
		this.fehlkostenMinRange = fehlkostenMinRange;
	}

	public int getFehlkostenMaxRange() {
		return fehlkostenMaxRange;
	}

	public void setFehlkostenMaxRange(int fehlkostenMaxRange) {
		this.fehlkostenMaxRange = fehlkostenMaxRange;
	}

	@Override
	public String toString() {
		return "ErweiterteProdukte [einstandminRange=" + einstandminRange + ", einstandmaxRange=" + einstandmaxRange
				+ ", lagersatz=" + lagersatz + ", bestellMinRange=" + bestellMinRange + ", bestellMaxRange="
				+ bestellMaxRange + ", fehlkostenMinRange=" + fehlkostenMinRange + ", fehlkostenMaxRange="
				+ fehlkostenMaxRange + "]";
	}
	
	
	

}
