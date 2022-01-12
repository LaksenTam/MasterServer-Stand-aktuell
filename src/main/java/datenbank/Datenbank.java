package datenbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import data.Lager;
import data.Produkt;



public class Datenbank {	
	
	
	public boolean problemInstanzAnlegen(List <Produkt> produktListe, String key) throws SQLException {		
		//Initialisierung der Statements und Verbindung
		Connection con = null;
		PreparedStatement ps1 = null;
		boolean status = false;
		
		//SQL Strings
		String sql = "insert into public.produkt (name, bestellfix,lagerkostensatz,fehlmengenkosten,volumenprodukt, "
				+ " minbestand, maxbestand, einstand, key) VALUES (?,?,?,?,?,?,?,?,?)";
		
		//F�ge die Werte der Datenbank hinzu
		try {	
			loeschTabelle();	
			System.out.println("Tabellen geloescht");
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
				ps1.executeUpdate();
				verbrauchProProduktAnlegen(produktListe.get(j).getName(),produktListe.get(j).getVerbraeuche());					
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
	
	public boolean verbrauchProProduktAnlegen(String name, List<Integer> verbrauch) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		boolean status = false;
		String sql = "insert into public.verbrauch(pname, periode, verbrauch) VALUES (?,?,?)";
		
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			for(int i = 0; i< verbrauch.size();i++) {
				ps.setString(1, name);
				ps.setInt(2, i + 1);
				ps.setInt(3, verbrauch.get(i));
				ps.executeUpdate();
			}			
			status = true;
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Fehler im Verbrauch sichern");
		}		
		return status;
	}
	
	public static boolean loeschTabelle() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		boolean status = false;
		
		String sql = "Truncate public.produkt restart identity cascade";
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
			System.out.println("Datenbankliste: " + Arrays.asList(produktListe));
			
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
	
	public ResultSet problemInstanzAnzeigen() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT name, bestellfix,einstand, "
				+ "fehlmengenkosten, lagerkostensatz, minbestand, maxbestand, volumenprodukt, verbrauch"
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
	
	public boolean problemInstanzDatenSpeichern(int anzProdukte, int perioden, String key, Lager lager) {
		boolean status = false;
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Insert into public.probleminstanz(panzahl, perioden, key,lagervolumen, kapitalbindung ) VALUES(?,?,?,?,?)";
		
		try {
			problemInstanzLoeschen();
			con =  DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, anzProdukte);
			ps.setInt(2, perioden);
			ps.setString(3, key);	
			ps.setFloat(4, lager.getLagerVol());
			ps.setDouble(5, lager.getKbindung());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}
	
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
			perioden = rs.getInt("perioden");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return perioden;
	}
	
	
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
	
	public Lager lagerAbrufen() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select panzahl, perioden, lagervolumen, kapitalbindung from public.probleminstanz";
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
	
	
	}
	