package data;

import java.util.List;

public class Userergebnis extends User {
	
		
	private String API_KEY;
	private List<Produktergebnis> produkte;
	
	public Userergebnis() {
		
	}
	
	public List<Produktergebnis> getProdukte() {
		return produkte;
	}
	public void setProdukte(List<Produktergebnis> produkte) {
		this.produkte = produkte;
	}
	public String getAPI_KEY() {
		return API_KEY;
	}
	public void setAPI_KEY(String aPI_KEY) {
		API_KEY = aPI_KEY;
	}


	@Override
	public String toString() {
		return "Userergebnis [ API_KEY=" + API_KEY + ", produkte=" + produkte + "]";
	}



}