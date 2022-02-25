package data;

public class Spielregeln {
	
	private long zeit;
	private boolean sammelbestellung;
	private double sammelKosten;
	
	public long getZeit() {
		return zeit/1000;
	}
	public void setZeit(long zeit) {
		this.zeit = zeit*1000;
	}
	public boolean isSammelbestellung() {
		return sammelbestellung;
	}
	public void setSammelbestellung(boolean sammelbestellung) {
		this.sammelbestellung = sammelbestellung;
	}
	public double getSammelKosten() {
		return sammelKosten;
	}
	public void setSammelKosten(double sammelKosten) {
		this.sammelKosten = sammelKosten;
	}
	
	
	
}
