package datenbank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.Highscore;
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
		@SuppressWarnings("unused")
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
	
	public List<Highscore> highscoresabrufen() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Highscore> score = new ArrayList<Highscore>();
		String sql = "Select DISTINCT name, score, api "
					+ "from public.highscore "
					+ "INNER JOIN public.user ON public.highscore.api = public.user.key "
					+ "Order BY public.highscore.score DESC";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {	
				Highscore hs = new Highscore();
				hs.setName(rs.getString("name"));
				hs.setScore(rs.getDouble("score"));
				score.add(hs);	
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return score;
		
	}
	
	public void produktErgebnisGesamtSpeicher(Userergebnis ue) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Insert into public.ergebnis (bestellmenge, pname, kosten,userkey,periode, zeitstempel) values(?,?,?,?,?,?)";		
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			for(int i =0; i<ue.getProdukte().size(); i++) {				
				Produktergebnis pe = new Produktergebnis();
				pe = ue.getProdukte().get(i);
				ps.setInt(1, pe.getBestellmenge());
				ps.setString(2, pe.getProduktName());
				ps.setDouble(3, pe.getKosten());
				ps.setString(4, ue.getAPI_KEY());
				ps.setInt(5, pe.getPeriode());
				ps.setInt(6 , 1);
				ps.executeUpdate();
			}		
			System.out.println("Erfolg");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void saveHighScore(Highscore highscore, Userergebnis ue) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "Insert into public.highscore (score, api, fehl, kosten, zeit) values(?,?,?,?,?)";
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setDouble(1, highscore.getScore());
			ps.setString(2, ue.getAPI_KEY());
			ps.setDouble(3, highscore.getFehlmengen());
			ps.setDouble(4, highscore.getKosten());
			ps.setLong(5, highscore.getTime());			
			ps.executeUpdate();
			System.out.println("Highscore gespeichert");
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
		}
	}
		
	
	public void insStempel(String key, long stamp) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "Insert into public.stamps (key, timestamp,startstamp) values (?,?,?) ON Conflict (key) DO Update set timestamp = ?";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1,key);
			ps.setLong(2, stamp);
			ps.setLong(3, stamp);
			ps.setLong(4, stamp);			
			ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
		}
	}
	
	public long getStempel(String key) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long alterStempel = 0;
		String sql = "Select timestamp from public.stamps where key = ?";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			if(rs.next()) {
				alterStempel = rs.getLong("timestamp");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		return alterStempel;
	}
	
	public long getStartStamp(String key) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		long stamp = 0;
		String sql = "Select startstamp from public.stamps where key = ?";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			if(rs.next()) {
				stamp = rs.getLong("startStamp");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return stamp;
	}
	
	public void deleteScore(String name, double score) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
	
		
		String sql = "Delete from public.highscore USING user where score = ? and api IN (select key from public.user where name =?)";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setDouble(1, score);
			ps.setString(2, name);
			ps.executeUpdate();			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			ps.close();
			con.close();
		}
	}
	
//	public List<String> userErgebnis(String api){
//		List<String> plist =new ArrayList<>();
//		String s = "";
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		
//		String sql = "Select bestellmenge, kosten, periode, zeitstempel from public.ergebnis where userkey = ?";
//		try {
//			con = DatenbankVerbindung.getConnection();
//			ps = con.prepareStatement(sql);
//			ps.setString(1, api);
//			rs = ps.executeQuery();
//			while(rs.next()) {
//				s = Integer.toString(rs.getInt("bestellmenge")) + "," + rs.getString("pname") + "," + Double.toString(rs.getDouble("kosten")) + "," + Integer.toString(rs.getInt("periode")) + "," + Long.toString(rs.getLong("zeitstempel"));
//				plist.add(s);
//			}
//		}catch(SQLException e) {
//			e.printStackTrace();
//		}
//		return plist;
//	}
	
	
	public List<String> produktNames(){
		List<String> pName = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select name from public.produkt";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				pName.add(rs.getString("name"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return pName;
	}

	public List<String[]> bestToChart(String name, double score) throws SQLException{
		List<String[]> chart = new ArrayList<String[]>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "Select pname, bestellmenge, public.ergebnis.kosten AS eKosten, public.highscore.kosten AS hKosten, fehl, zeit\n"
				+ "from public.ergebnis\n"
				+"INNER JOIN public.highscore on public.highscore.api = public.ergebnis.userkey\n"
				+ " where userkey IN(select key from public.user where name =?)";
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()) {
				String[] s = { rs.getString("pname"), Integer.toString(rs.getInt("bestellmenge")), 
						Double.toString(rs.getDouble("eKosten")), Double.toString(rs.getDouble("hKosten")), 
						Double.toString(rs.getDouble("fehl")), Integer.toString(rs.getInt("zeit"))}; 				
				chart.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			rs.close();
			ps.close();
			con.close();
		}
		
		return chart;
	}
	
	public List<String[]> userErgebnis(String key) throws SQLException{
		List<String[]> userData = new ArrayList<>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "Select pname, bestellmenge, public.ergebnis.kosten AS pkosten, public.highscore.kosten AS hKosten, fehl, zeit\n"
				+ "from public.ergebnis \n"
				+ "INNER JOIN public.highscore ON public.highscore.api = public.ergebnis.userkey\n"
				+ "where userkey = ?";
			
		
		try {
			con = DatenbankVerbindung.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, key);
			rs = ps.executeQuery();
			while(rs.next()) {		
				String[] s = { rs.getString("pname"),Integer.toString(rs.getInt("bestellmenge")), 
						Double.toString(rs.getDouble("pkosten")), Double.toString(rs.getDouble("hKosten")), 
						Double.toString(rs.getDouble("fehl")), Integer.toString(rs.getInt("zeit"))};
				userData.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			rs.close();
			ps.close();
			con.close();
		}
		return userData;		
	}

}