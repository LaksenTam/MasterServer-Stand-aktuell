package data;

import java.util.ArrayList;
import java.util.List;

public class Produkt {
	
	private String name;
	private double einstand;
	private double bestellfix;
	private double lagerkostensatz;
	private double fehlmengenkosten;
	private double vProdukt;
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
	public double getEinstand() {
		return einstand;
	}

	/**
	 * @param einstand the einstand to set
	 */
	public void setEinstand(double einstand) {
		this.einstand = einstand;
	}

	/**
	 * @return the bestellfix
	 */
	public double getBestellfix() {
		return bestellfix;
	}

	/**
	 * @param bestellfix the bestellfix to set
	 */
	public void setBestellfix(double bestellfix) {
		this.bestellfix = bestellfix;
	}

	/**
	 * @return the lagerkostensatz
	 */
	public double getLagerkostensatz() {
		return lagerkostensatz;
	}

	/**
	 * @param lagerkostensatz the lagerkostensatz to set
	 */
	public void setLagerkostensatz(double lagerkostensatz) {
		this.lagerkostensatz = lagerkostensatz;
	}

	/**
	 * @return the fehlmengenkosten
	 */
	public double getFehlmengenkosten() {
		return fehlmengenkosten;
	}

	/**
	 * @param fehlmengenkosten the fehlmengenkosten to set
	 */
	public void setFehlmengenkosten(double fehlmengenkosten) {
		this.fehlmengenkosten = fehlmengenkosten;
	}

	/**
	 * @return the vProdukt
	 */
	public double getvProdukt() {
		return vProdukt;
	}

	/**
	 * @param vProdukt the vProdukt to set
	 */
	public void setvProdukt(double vProdukt) {
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

	@Override
	public String toString() {
		return "Produkt [name=" + name + ", einstand=" + einstand + ", bestellfix=" + bestellfix + ", lagerkostensatz="
				+ lagerkostensatz + ", fehlmengenkosten=" + fehlmengenkosten + ", vProdukt=" + vProdukt
				+ ", minBestand=" + minBestand + ", maxBestand=" + maxBestand + ", maxLager=" + maxLager
				+ ", verbrauch=" + verbrauch + ", verbraeuche=" + verbraeuche + "]";
	}
	
	
	
}