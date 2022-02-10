package manager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data.*;
import datenbank.*;
import json.DataToJson;
import json.ErgebnisDeserializer;
import utility.CheckTime;

public class DatenManager {
	
	Datenbank db = new Datenbank();
	DataToJson json = new DataToJson();	
	CheckTime ct = new CheckTime();
	UserDatenbank ud = new UserDatenbank();
	ErgebnisDeserializer ed = new ErgebnisDeserializer(); 
	
	public boolean produktSpeichern(List<Produkt> produkte, String identifier) throws SQLException {			
		return db.problemInstanzAnlegen(produkte, identifier);
	}
	
	public ResultSet problemInstanzAbrufen() {
		return db.problemInstanzAnzeigen();
	}
	
	public Userergebnis ergebnis(String jsonErgebnis, Userergebnis ue) {
		return ed.deserializeJsonInput(jsonErgebnis, ue);		
	}
	
	public List<Produkt> produktListePeriode(List<Produkt> produktListe, int periode) throws NullPointerException, SQLException {
		return db.getProblemInstanz(produktListe, periode);
	}
	
	public boolean userErgebnisSpeichern(Userergebnis ue, int grad) throws SQLException {
		return ud.ergebnisSpeichern(ue, grad);
	}
	
	public boolean ueberpruefeZeit(long currentTime, int periode, String api) throws SQLException {
		return ct.ueberpruefeZeit(currentTime, periode, api);
	}
	
	public String dataToJson(List<Produkt> produkte) {
		return json.dataToJson(produkte);
	}
	
	
	
	public boolean problemInstanzDatenSpeichern(int anzProdukte, int perioden, String key, Lager lager) {
		return db.problemInstanzDatenSpeichern(anzProdukte, perioden, key, lager);
	}
	
	public int getPeriodenAnzahl() {
		return db.getPeriodenAnzahl();
	}
	
	public void berechneHighscore() {
		
	}
	
	public List<Produkt> getStartProblem(List<Produkt> start) throws SQLException{
		return db.getStartProblem(start);
		
	}
	
	public void updateProblemInstanz(List<Produkt> produkte) {
		db.updateProblem(produkte);
	}

}
