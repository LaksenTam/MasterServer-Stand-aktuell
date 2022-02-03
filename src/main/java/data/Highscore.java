package data;

import java.io.Serializable;

public class Highscore implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2363548343240336576L;
	private double score;
	private String name;
	private double fehlmengen;
	private double kosten;
	private long time;
	
	
	public Highscore() {
		
	}
	
	
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public double getFehlmengen() {
		return fehlmengen;
	}

	public void setFehlmengen(double fehlmengen) {
		this.fehlmengen = fehlmengen;
	}

	public double getKosten() {
		return kosten;
	}

	public void setKosten(double kosten) {
		this.kosten = kosten;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
