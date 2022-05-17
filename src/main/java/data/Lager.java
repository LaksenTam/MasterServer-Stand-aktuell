package data;

public class Lager {
	
	private int anz;
	private int per;
	private float kbindung;	
	private float lagerVol;
	private transient long zeit;
	private boolean sammelbestellung;
	private double sammelKosten;
	
		
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
	
	public float getLagerVol() {
		return lagerVol;
	}


	
	public void setLagerVol(float lagerVol) {
		this.lagerVol = lagerVol;
	}
	
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