package utility;

import java.sql.SQLException;

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
		
		long ergebnis;
		periode = periode -1;
		long altesErgebnis = ud.zeitStempel(periode, api);
		
		ergebnis = currentTime - altesErgebnis;		
		System.out.println("Ergebnis: " + ergebnis);
		if(ergebnis/1000<10) {
			status = true;
		}		
		
		return status;
	}

}