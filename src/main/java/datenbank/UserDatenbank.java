package datenbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data.Produktergebnis;
import data.User;
import data.Userergebnis;

public class UserDatenbank {
	
	
	public boolean ergebnisSpeichern(Userergebnis ue, long zeitstempel) throws SQLException {
		boolean status = false;
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "insert into public.ergebnis (bestellmenge, pname, kosten, userkey, periode, zeitstempel) values (?,?,?,?,?,?)";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			for(int i = 0;i<ue.getProdukte().size(); i++ ) {	
				Produktergebnis pe = new Produktergebnis();
				pe = ue.getProdukte().get(i);
				ps.setInt(1, pe.getBestellmenge());
				ps.setString(2, pe.getProduktName());
				ps.setDouble(3, pe.getKosten());
				ps.setString(4,ue.getAPI_KEY());
				ps.setInt(5, ue.getPeriode());
				ps.setLong(6, zeitstempel);
				System.out.println("pe: " + pe.getBestellmenge() + "Name: " + pe.getProduktName() + "api: " +ue.getAPI_KEY() + "Kosten: " + pe.getKosten() + " per: "+ ue.getPeriode() );
				ps.executeUpdate();				
			}
			status = true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
		}
		return status;
	}
	
	public boolean registrierungUser(String name, String password, String api_key, int zugriff) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT into public.user (name, password, key, zugriff) values(?,?,?,?)";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			ps.setString(3, api_key);
			ps.setInt(4, zugriff);
			ps.executeUpdate();
			return true;
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			ps.close();
			con.close();
		}		
	}
	
	public User getUser(String name, String password, User user) throws SQLException {
		boolean status = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select * from public.user where name = ? AND password = ?;";
		try {
			System.out.println(name);
			System.out.println(password);
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(status = rs.next()) {
				user.setName(name);
				user.setApi_key(rs.getString("key"));
			}
			
			System.out.println(status);
		}catch(SQLException e) {
			e.printStackTrace();			
		}finally {
			ps.close();
			rs.close();
			con.close();
		}
		return user;		
	
	}
	
	public boolean login (String name, String password) throws SQLException {
		boolean status = false;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select * from public.user where name = ? AND password = ?;";
		
		try {
			System.out.println(name);
			System.out.println(password);
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			status = rs.next();
			System.out.println(status);
		}catch(SQLException e) {
			e.printStackTrace();			
		}finally {
			ps.close();
			rs.close();
			con.close();
		}
		return status;		
	}
	
	public long zeitStempel(int periode, String userkey) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select zeitstempel from public.ergebnis where periode = ? and userkey = ?";
		long stempel = 0;
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, periode);
			ps.setString(2, userkey);
			rs = ps.executeQuery();
			if(rs.next()) {
				stempel = rs.getLong("zeitstempel");
			}
			
			System.out.println("DB Zeitstempel: " + stempel);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return stempel;
	}
	
	public ResultSet bestenListe() {
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement ps = null;
	
		
		String sql = "Select * from public.ergebnis";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}

}