package utility;

import java.sql.SQLException;

import data.Spielregeln;
import datenbank.UserDatenbank;

public class CheckTime {
	
	/**
	 * Klasse ueberpruft die Zeitspanne, die ein Algogorithmus gebraucht um zu Antworten
	 * @param currentTime
	 * 					Die aktuelle Zeit in Millisekunden
	 * @param periode
	 * 			gibt die aktuelle Periode an
	 * @param api
	 * 			Benutzerstring, um die Ergebnisse zu erhalten
	 * @return
	 * @throws SQLException
	 */
	public boolean ueberpruefeZeit(long currentTime, int periode, String api) throws SQLException {
		UserDatenbank ud = new UserDatenbank();
		boolean status = false;
		Spielregeln spiel = new Spielregeln();
		
		long ergebnis;
		periode = periode -1;
		long altesErgebnis = ud.zeitStempel(periode, api);
		
		ergebnis = currentTime - altesErgebnis;		
		if(ergebnis/1000<spiel.getZeit()) {
			status = true;
		}		
		
		return status;
	}
	
	public boolean testeStempel(long neuStempel, String key) throws SQLException {
		boolean status = false;
		UserDatenbank ud = new UserDatenbank();
		Spielregeln spiel = new Spielregeln();
		long altStempel = ud.getStempel(key);		
		double ergebnis = neuStempel - altStempel;		
		
		
		if(ergebnis/1000<spiel.getZeit()) {
			status = true;
		}
		return status;
	}
	
	public long berechneZeit(String key) {
		UserDatenbank db = new UserDatenbank();	
		System.out.println(key);
		long startStamp = db.getStartStamp(key);
	
		long stamp = System.currentTimeMillis() - startStamp;
		stamp = stamp /1000;
		
		return stamp;
	}

}