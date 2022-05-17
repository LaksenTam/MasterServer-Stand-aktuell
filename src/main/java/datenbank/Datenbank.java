package datenbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Lager;
import data.Produkt;

/**
 * Klasse für Datenbankoperationen 
 * @author Lukas
 *
 */

public class Datenbank {	
	
	/**
	 * Methode um Datenbank zurückzusetzen
	 * @throws SQLException
	 */
	public void resetProblem() throws SQLException {
		loeschTabelle();	
		resetBestenListe();
		deleteStamps();
		System.out.println("Tabellen geloescht");
	}
	
	/**
	 * Erstellt eine Probleminstanz
	 * @param produktListe = Liste mit den generierten Produktinformationen
	 * @param key = Identifier für die PRobleminstanz 
	 * @return
	 * @throws SQLException
	 */
	public boolean problemInstanzAnlegen(List <Produkt> produktListe, String key) throws SQLException {		
		//Initialisierung der Statements und Verbindung
		Connection con = null;
		PreparedStatement ps1 = null;
		boolean status = false;
		
		//SQL Strings
		String sql = "insert into public.produkt (name, bestellfix,lagerkostensatz,fehlmengenkosten,volumenprodukt, "
				+ " minbestand, maxbestand, einstand, key,pkategorie) VALUES (?,?,?,?,?,?,?,?,?,?)";
		
		//Füge die Werte der Datenbank hinzu
		try {			
			con = DatenbankVerbindung.getConnection();									
			ps1 = con.prepareStatement(sql);
			for(int j = 0; j<produktListe.size();j++) {				
				ps1.setString(1, produktListe.get(j).getName());					
				ps1.setDouble(2, produktListe.get(j).getBestellfix());
				ps1.setDouble(3, produktListe.get(j).getLagerkostensatz());
				ps1.setDouble(4, produktListe.get(j).getFehlmengenkosten());
				ps1.setDouble(5, produktListe.get(j).getvProdukt());
				ps1.setInt(6, produktListe.get(j).getMinBestand());
				ps1.setInt(7, produktListe.get(j).getMaxBestand());
				ps1.setDouble(8, produktListe.get(j).getEinstand());
				ps1.setString(9, key);
				ps1.setString(10, produktListe.get(j).getpKat());
				ps1.executeUpdate();
				verbrauchProProduktAnlegen(produktListe.get(j).getName(),produktListe.get(j).getVerbraeuche(), produktListe.get(j).getvKat(),key);					
			}				
			status = true;			
		}catch(SQLException e) {
			e.printStackTrace();
			status = false;
		//Schliesse Ressourcen 
		}finally {	
				ps1.close();
				con.close();
		}
		return status;
	}
	
	/**
	 * setzt die Zeitstempel in der Datenbank zurück
	 */
	private void deleteStamps() {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Truncate public.stamps";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Speichert die Verbraueche der Produkte in der Datenbank
	 * @param name = name des Produktes
	 * @param verbrauch = Hoehe des Verbrauches
	 * @param verbrauchKategorie = Kategorie im Sinne von XYZ
	 * @param key = Probleminstanz key
	 * @return
	 * @throws SQLException
	 */
	public boolean verbrauchProProduktAnlegen(String name, List<Integer> verbrauch, String verbrauchKategorie, String key) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		boolean status = false;
		String sql = "insert into public.verbrauch(pname, periode, verbrauch,verbrauchkategorie,problemkey) VALUES (?,?,?,?,?)";		
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			for(int i = 0; i< verbrauch.size();i++) {
				ps.setString(1, name);
				ps.setInt(2, i + 1);
				ps.setInt(3, verbrauch.get(i));				
				ps.setString(4, verbrauchKategorie);
				ps.setString(5, key);
				ps.executeUpdate();
			}			
			status = true;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Fehler im Verbrauch sichern");
		}		
		return status;
	}
	
	/**
	 * loescht die Probleminstanz
	 * @return
	 * @throws SQLException
	 */
	public static boolean loeschTabelle() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean status = false;
		
		String sql = "Truncate public.probleminstanz restart identity cascade";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			status = true;
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {		
			
		}
		return status;
	}
	

	/**
	 * Ruft die Probleminstanz mit den Verbraeuchen in einer spezifischen Periode ab und speichert sie in einer Liste
	 * @param produktListe
	 * @param periode
	 * @return
	 * @throws SQLException
	 * @throws NullPointerException
	 */
	public List<Produkt> getProblemInstanz(List<Produkt> produktListe, int periode) throws SQLException, NullPointerException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM public.produkt RIGHT JOIN public.verbrauch "
				+ "ON public.produkt.name = public.verbrauch.pname WHERE periode = ?";		
		try {			
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, periode);
			rs = ps.executeQuery();
			while(rs.next()) {
				Produkt p = new Produkt();
				p.setName(rs.getString("name"));
				p.setBestellfix(rs.getDouble("bestellfix"));
				p.setLagerkostensatz(rs.getDouble("lagerkostensatz"));
				p.setFehlmengenkosten(rs.getDouble("fehlmengenkosten"));
				p.setvProdukt(rs.getDouble("volumenprodukt"));
				p.setMinBestand(rs.getInt("minbestand"));
				p.setMaxBestand(rs.getInt("maxbestand"));
				p.setEinstand(rs.getDouble("einstand"));
				p.setVerbrauch(rs.getInt("verbrauch"));
				produktListe.add(p);				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}catch(NullPointerException npe) {
			npe.printStackTrace();
		}finally {
			ps.close();
			rs.close();
			con.close();
		}
		return produktListe;
	}
	
	/**
	 * Hier schauen ob die Funktion so umgeschrieben werden kann, dass eine Liste zurückgegeben wird
	 * @return
	 */
	public ResultSet problemInstanzAnzeigen() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT name, bestellfix,einstand, "
				+ "fehlmengenkosten, lagerkostensatz, minbestand, maxbestand, volumenprodukt, verbrauch, pkategorie, verbrauchkategorie"
				+ " FROM public.produkt RIGHT JOIN public.verbrauch "
				+ "ON public.produkt.name = public.verbrauch.pname";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	/**
	 * Speichert die Parameter der Probleminstanz
	 * @param anzProdukte = Anzahl der Produkte der PRobleminstanz
	 * @param perioden = ANzahl der Perioden
	 * @param key = Identifier der Probleminstanz
	 * @param lager =  Lagerdatenklasse, die die Daten enthält
	 * @return
	 */
	public boolean problemInstanzDatenSpeichern(int anzProdukte, int perioden, String key, Lager lager) {
		boolean status = false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Insert into public.probleminstanz(panzahl, perioden, key,lagervolumen, kapitalbindung, sammelbestellung, zeit ) VALUES(?,?,?,?,?,?,?)";
		
		try {
			problemInstanzLoeschen();
			con =  DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, anzProdukte);
			ps.setInt(2, perioden);
			ps.setString(3, key);	
			ps.setFloat(4, lager.getLagerVol());
			ps.setDouble(5, lager.getKbindung());
			ps.setDouble(6, lager.getSammelKosten());
			ps.setLong(7, lager.getZeit());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	

	/**
	 * Liefert die Anzahl der Perioden zurück
	 * @return
	 */
	public int getPeriodenAnzahl() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int perioden = 0;
		String sql = "Select perioden from public.probleminstanz";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				perioden = rs.getInt("perioden");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return perioden;
	}
	
	/**
	 * Methode um die Produktliste abzurufen 
	 * @param produktListe
	 * @return
	 */
	public List<Produkt> getStartProblem(List<Produkt> produktListe) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select name, bestellfix, lagerkostensatz, fehlmengenkosten, volumenprodukt, minbestand, maxbestand, einstand from public.produkt";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Produkt p = new Produkt();
				p.setName(rs.getString("name"));
				p.setBestellfix(rs.getDouble("bestellfix"));
				p.setLagerkostensatz(rs.getDouble("lagerkostensatz"));
				p.setFehlmengenkosten(rs.getDouble("fehlmengenkosten"));
				p.setvProdukt(rs.getDouble("volumenprodukt"));
				p.setMinBestand(rs.getInt("minbestand"));
				p.setMaxBestand(rs.getInt("maxbestand"));
				p.setEinstand(rs.getDouble("einstand"));
				produktListe.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return produktListe;
	}
	
	/**
	 * Methode um Informationen eines bestimmten Produktes in einer spezifischen Periode zu erhalten
	 * @param periode
	 * @param pName
	 * @return
	 */
	public Produkt getProduktInfos(int periode, String pName) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT name, bestellfix, lagerkostensatz, fehlmengenkosten, volumenprodukt, minbestand, maxbestand, einstand,periode, verbrauch"
				+ " FROM public.produkt RIGHT JOIN public.verbrauch "
				+ "ON public.produkt.name = public.verbrauch.pname Where periode = ? AND name = ?";
		Produkt p = new Produkt();
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, periode);
			ps.setString(2, pName);
			rs = ps.executeQuery();
			while(rs.next()) {
				p.setName(rs.getString("name"));
				p.setBestellfix(rs.getDouble("bestellfix"));
				p.setLagerkostensatz(rs.getDouble("lagerkostensatz"));
				p.setFehlmengenkosten(rs.getDouble("fehlmengenkosten"));
				p.setvProdukt(rs.getDouble("volumenprodukt"));
				p.setMinBestand(rs.getInt("minbestand"));
				p.setMaxBestand(rs.getInt("maxbestand"));
				p.setEinstand(rs.getDouble("einstand"));
				p.setVerbrauch(rs.getInt("verbrauch"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Fehler");
		}
		return p;		
	}
	
	/**
	 * Methode um die Informationen für die Schnittstelle /GetProdukte zur Verfügung zu stellen
	 * @return
	 * @throws SQLException
	 */
	public List<Produkt> produktListeAbrufen() throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Produkt> produkte = new ArrayList<>();
		
		String sql = "SELECT name, bestellfix, lagerkostensatz, fehlmengenkosten, volumenprodukt, minbestand, maxbestand, einstand,periode, verbrauch"
				+ " FROM public.produkt RIGHT JOIN public.verbrauch "
				+ "ON public.produkt.name = public.verbrauch.pname Where periode = 1";
		try {			
		
		con = DatenbankVerbindung.getConnection();
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		while(rs.next()) {
			Produkt p = new Produkt();
			p.setName(rs.getString("name"));
			p.setBestellfix(rs.getDouble("bestellfix"));
			p.setLagerkostensatz(rs.getDouble("lagerkostensatz"));
			p.setFehlmengenkosten(rs.getDouble("fehlmengenkosten"));
			p.setvProdukt(rs.getDouble("volumenprodukt"));
			p.setMinBestand(rs.getInt("minbestand"));
			p.setMaxBestand(rs.getInt("maxbestand"));
			p.setEinstand(rs.getDouble("einstand"));
			p.setVerbrauch(rs.getInt("verbrauch"));
			produkte.add(p);
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return produkte;
		}
	
	/**
	 * Methode um Informationen für /GetProdukte zur Verfügung zu stellen
	 * @return
	 */
	public Lager lagerAbrufen() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select panzahl, perioden, lagervolumen, kapitalbindung, sammelbestellung from public.probleminstanz";
		Lager lager = new Lager();
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				lager.setAnz(rs.getInt("panzahl"));
				lager.setKbindung(rs.getFloat("kapitalbindung"));
				lager.setLagerVol(rs.getFloat("lagervolumen"));
				lager.setPer(rs.getInt("perioden"));
			}			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return lager;
		
	}
	
	/**
	 * Aktualisiert die Werte der Probleminstanz
	 * @param produkte
	 */
	public void updateProblem(List<Produkt> produkte) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Update public.produkt set bestellfix =?, lagerkostensatz=?, fehlmengenkosten=?, minbestand =?, maxbestand=?, einstand=? where name =?";
		
		try {
			con=DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			for(int i=0; i<produkte.size(); i++) {				
				Produkt p = produkte.get(i);
				ps.setString(7, p.getName());
				ps.setDouble(1, p.getBestellfix());
				ps.setDouble(2, p.getLagerkostensatz());
				ps.setDouble(3, p.getFehlmengenkosten());
				ps.setInt(4, p.getMinBestand());
				ps.setInt(5, p.getMaxBestand());
				ps.setDouble(6, p.getEinstand());	
				ps.executeUpdate();
			}
		}catch(SQLException e) {
		e.printStackTrace();
		}
	}
	
	/**
	 * Methode, die eine Liste der Produkte mit dessen VErbrauch liefert
	 * @return
	 * @throws SQLException
	 */
	public List<Produkt> getVerbrauchsListe() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Produkt> produkte = new ArrayList<>();
		String sql = "SELECT name, bestellfix,einstand, "
				+ "fehlmengenkosten, lagerkostensatz, minbestand, maxbestand, volumenprodukt, verbrauch"
				+ " FROM public.produkt RIGHT JOIN public.verbrauch "
				+ "ON public.produkt.name = public.verbrauch.pname";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Produkt p = new Produkt();
				p.setName(rs.getString("name"));
				p.setBestellfix(rs.getDouble("bestellfix"));
				p.setLagerkostensatz(rs.getDouble("lagerkostensatz"));
				p.setFehlmengenkosten(rs.getDouble("fehlmengenkosten"));
				p.setvProdukt(rs.getDouble("volumenprodukt"));
				p.setMinBestand(rs.getInt("minbestand"));
				p.setMaxBestand(rs.getInt("maxbestand"));
				p.setEinstand(rs.getDouble("einstand"));
				p.setVerbrauch(rs.getInt("verbrauch"));
				produkte.add(p);
			}
			return produkte;
		
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			rs.close();
			ps.close();
			con.close();
		}
	}
	
	/**
	 * Setzt die Bestenliste zurück
	 */
	public void resetBestenListe() {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "Truncate public.highscore restart identity cascade";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Erstellt eine Liste aller User der Anwendung
	 * @return
	 */
	public List<String[]> getUser() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String[]> list = new ArrayList<String[]>();
		
		String sql = "Select name,score,fehl,kosten,zeit, zugriff From public.benutzer "
				+ " LEFT JOIN public.highscore ON public.benutzer.key = public.highscore.api";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);	
			rs = ps.executeQuery();
			while(rs.next()) {
				String[] s = {rs.getString("name"), Double.toString(rs.getDouble("score")), 
						Double.toString(rs.getDouble("fehl")), Double.toString(rs.getDouble("kosten")), 
						Integer.toString(rs.getInt("zeit")), Integer.toString(rs.getInt("zugriff"))};	
				list.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * Loescht einen User 
	 * @param name
	 * @throws SQLException
	 */
	public void deleteUser(String name) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "DELETE from public.benutzer where name = ?";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
		}
	}

	/**
	 * Erstellt einen neuen Admin 
	 * @param name
	 * @throws SQLException
	 */
	public void AdminErstellen(String name) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Update public.benutzer Set zugriff = 1 where name =?";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
		}
		
	}
	
	/**
	 * Setzt den Zugriff eines Admins auf den eines Users
	 * @param name
	 * @throws SQLException
	 */
	public void AdminDemoten(String name) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Update public.benutzer Set zugriff = 0 where name =?";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
		}
	}
	
	/**
	 * erzeugt eine Liste mit allen Produktnamen
	 * @return
	 */
	public List<String> proList() {
		List<String> pListe = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps= null;
		ResultSet rs = null;
		
		String sql = "Select name from public.produkt";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				pListe.add(rs.getString("name"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pListe;
	}
	
	/**
	 * aktualisiert die Lagerdaten der kapitalbindung und das lagervolumen
	 * @param lager
	 * @throws SQLException
	 */
	public void updateLager(Lager lager) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE probleminstanz set kapitalbindung = ?, lagervolumen = ? ";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setFloat(1, lager.getKbindung());
			ps.setFloat(2, lager.getLagerVol());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
		}
	}
	
	/**
	 * Prüfe ob gelöscht werden kann
	 */
	public void resetErgebnisse() {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Truncate public.ergebnis restart identity cascade";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Entfernt eine Probleminstanz
	 */
	public void problemInstanzLoeschen() {
		Connection con  = null;
		PreparedStatement ps = null;
		String sql = "Delete from public.probleminstanz";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Setzt einen Namen für eine Probleminstanz
	 * @param name
	 */
	public void insertName(String name) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE probleminstanz "
				+ "SET name = ?";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Speichert eine Probleminstanz, sodass sie nicht mehr geloescht werden kann
	 * @throws SQLException
	 */
	public void problemInstanzSpeichern() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO bewaehrtprobleminstanz (panzahl, perioden, problemkey, kapitalbindung, lagervolumen, sammelbestellung, zeit, name) "
				+ "SELECT panzahl, perioden, key, kapitalbindung, lagervolumen, sammelbestellung, zeit, name "
				+ "FROM probleminstanz";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
			produktebewaehrtSpeichern();
			topUserSpeichern();
			verbrauchBewaehrtSpeichern();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
		}
	}
	
	/**
	 * Speichert die Produkte einer gespeicherten Probleminstanz
	 */
	public void produktebewaehrtSpeichern() {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO bewaehrtprodukt (name, bestellfix, lagerkostensatz, fehlmengenkosten, volumenprodukt, minbestand, maxbestand, einstand, problemkey, pkategorie) "
				+ "SELECT name, bestellfix, lagerkostensatz, fehlmengenkosten, volumenprodukt, minbestand, maxbestand, einstand, key, pkategorie "
				+ "FROM produkt";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Speichert die Top10 User einer gespeicherten Probleminstanz
	 */
	public void topUserSpeichern() {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO toptenbewaehrt (score, fehl, kosten, zeit, schwierigkeitsgrad, uid, problemkey) "
				+ "SELECT score, fehl, kosten, zeit, schwierigkeitsgrad, uid, problemkey "
				+ "FROM highscore "
				+ "ORDER BY score DESC "
				+ "LIMIT 10";				
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);			
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Speichert die Ergebnisse der Top10 User
	 * @param userid
	 */
	public void topUserErgebnis(List<String> userid) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql ="INSERT INTO bewaehrtuserergebnis (bestellmenge, pname, kosten, periode, uid) "
				+ "SELECT bestellmenge, pname, kosten, periode, uid "
				+ "from ergebnis "
				+ "where uid = ?";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			for(int i = 0;i<userid.size();i++ ) {
				ps.setString(1, userid.get(i));
				ps.executeUpdate();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Speichert die Verbräuche der gespeicherten Produkte
	 */
	public void verbrauchBewaehrtSpeichern() {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO bewaehrtverbrauch (pname, periode, verbrauch, verbrauchkategorie, problemkey) "
		+ "SELECT pname, periode, verbrauch, verbrauchkategorie, problemkey "
		+ "FROM verbrauch ";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Ruft die UID ab
	 * @return
	 * @throws SQLException
	 */
	public List<String> getUIDsave() throws SQLException {
		List<String> uid = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select uid "
				+ "from toptenbewaehrt "
				+ "where problemkey IN (Select key from probleminstanz)";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				uid.add(rs.getString("uid"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			rs.close();
			ps.close();
			con.close();
		}
		return uid;
	}
	
	/**
	 * Ruft eine Liste mit allen gespeicherten Probleminstanzen ab
	 * @return
	 */
	public List<String> showSavedProbleminstanz() {
		List<String> problem = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT name FROM bewaehrtprobleminstanz";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				problem.add(rs.getString("name"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return problem;
	}
	
	/**
	 * Lädt eine gespeicherte Probleminstanz und macht sie aktiv
	 * @param pkey
	 */
	public void loadProbleminstanz(String pkey) {
		
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO probleminstanz (panzahl, perioden, key, kapitalbindung, lagervolumen, sammelbestellung, zeit, name) "
				+ "SELECT panzahl, perioden, problemkey, kapitalbindung, lagervolumen, sammelbestellung, zeit, name "
				+ "FROM bewaehrtprobleminstanz "
				+ "WHERE problemkey = ?";
		
		try {
			loeschTabelle();		
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, pkey);
			ps.execute();
			
			loadProdukte(pkey);
			System.out.println("Produkte geladen");
			loadTop10(pkey);
			System.out.println("Top10 geladen");
			loadVerbrauch(pkey);
			System.out.println("Produktverbäuche geladen");
			loadTopTenErgebnisse(pkey);
			System.out.println("TopTen Ergebnisse geladen");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lädt die Produkte der Probleminstanz
	 * @param pkey
	 */
	public void loadProdukte(String pkey) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO produkt (name, bestellfix, lagerkostensatz, fehlmengenkosten, volumenprodukt, minbestand, maxbestand, einstand, key, pkategorie) "
				+ "SELECT name, bestellfix, lagerkostensatz, fehlmengenkosten, volumenprodukt, minbestand, maxbestand, einstand, problemkey, pkategorie "
				+ "FROM bewaehrtprodukt "
				+ "WHERE problemkey = ?";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, pkey);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lädt die Top10 User der Probleminstanz
	 * @param pkey
	 */
	public void loadTop10(String pkey) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO highscore (score, fehl, kosten, zeit, schwierigkeitsgrad, uid, problemkey) "
				+ "SELECT score, fehl, kosten, zeit, schwierigkeitsgrad, uid, problemkey "
				+ "FROM toptenbewaehrt "
				+ "WHERE problemkey = ?";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, pkey);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * Lädt den Verbrauch der Produkte der Probleminstanz
	 * @param pkey
	 */
	public void loadVerbrauch(String pkey) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO verbrauch (pname, periode, verbrauch, verbrauchkategorie, problemkey) "
				+ "SELECT pname, periode, verbrauch, verbrauchkategorie, problemkey "
				+ "FROM bewaehrtverbrauch "
				+ "WHERE problemkey = ?";
		try {			
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, pkey);
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ruft die ID der Probleminstanz mit Hilfe des Namens ab
	 * @param name
	 * @return
	 */
	public String getProblemInstanzByName(String name) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String pkey = "";
		
		String sql = "SELECT problemkey "
				+ "from public.bewaehrtprobleminstanz "
				+ "where name = ?";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				pkey = rs.getString("problemkey");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pkey;
	}
	
	/**
	 * Lädt die Ergebnisse der TOp10
	 * @param pkey
	 */
	public void loadTopTenErgebnisse(String pkey) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql ="INSERT INTO ergebnis (bestellmenge, pname, kosten, periode, uid) "
				+ "SELECT bestellmenge, pname, kosten, periode, uid "
				+ "from bewaehrtuserergebnis "
				+ "where uid = (SELECT uid FROM toptenbewaehrt where problemkey = ?)";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, pkey);
			ps.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
	