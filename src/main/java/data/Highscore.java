package data;

import java.io.Serializable;

public class Highscore implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2363548343240336576L;
	private double score;
	private String name;
	
	
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
	
	

}
