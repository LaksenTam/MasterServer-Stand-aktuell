package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Datenbankinstaller {
	
//	 protected static Connection instance;
//     
//     private static final String DB_SERVER = "localhost";
//     private static final String DB_NAME = "testdatenbankinstall2";
//     private static final String DB_USER = "postgres";
//     private static final String DB_PASSWORD = "henrik";
//     private static final String DB_DRIVER = "org.postgresql.Driver";
//     private static final String DB_URL = "jdbc:postgresql://" + DB_SERVER + "/" + DB_NAME;
//     
     public static void initDatabase(String DB_SERVER, String DB_NAME, String DB_USER, String DB_DRIVER) {
    	 try {
			createDB(DB_SERVER, DB_NAME, DB_USER, DB_DRIVER);
			System.out.println("Database created");
			createBenutzerTable();
			System.out.println("Benutzertable created");
			createProblemInstanz();
			System.out.println("Probleminstanztable created");
			createHighscore();
			System.out.println("Highscoretable created");
			createProdukt();
			System.out.println("Produktable created");			
			createErgebnis();
			System.out.println("Ergebnistable created");
			createStamps();
			System.out.println("Timestamptable created");
			createVerbrauch();
			System.out.println("Verbrauchtable created");	
			createBewaehrtProblem();			
			System.out.println("BewaehrteProbleminstanz created");
			createBewaehrtProdukt();
			System.out.println("Bewaehrte Produkte created");
			createBewaehrtVerbrauch();
			System.out.println("Bewaehrte Verbrauch created");
			createTopTen();
			System.out.println("Top10 created");
			createBewaehrtUserErgebnis();
			System.out.println("BewaehrteUserergebnisse created");
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

          
     public static void createDB(String DB_SERVER, String DB_NAME, String DB_USER, String DB_DRIVER) throws ClassNotFoundException, SQLException {
    	 Connection con = null;
    	 Statement stmt = null;
    	 String sql = "CREATE DATABASE " + DB_NAME + " OWNER " + DB_USER + ";";
    	 try {
        	 Class.forName(DB_DRIVER);
        	 con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","henrik");      	    
    		 stmt = con.createStatement();
    		 stmt.executeUpdate(sql);

    	 }catch(Exception e) {
    		 e.printStackTrace();
    		
    	 }
    	 
     }
        
     
     public static void createBenutzerTable() throws SQLException {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE IF NOT EXISTS public.\"benutzer\"\r\n"
    	 		+ "(\r\n"
    	 		+ "    id serial NOT NULL,\r\n"
    	 		+ "    name character varying NOT NULL,\r\n"
    	 		+ "    password character varying  NOT NULL,\r\n"
    	 		+ "    key character varying NOT NULL,\r\n"
    	 		+ "    zugriff integer NOT NULL,\r\n"
    	 		+ "    CONSTRAINT user_pkey PRIMARY KEY (id),\r\n"
    	 		+ "    CONSTRAINT name UNIQUE (name),\r\n"
    	 		+ "    CONSTRAINT key UNIQUE (key)\r\n"    	 	
    	 		+ ")";
    	 try {
    		 con = DatenbankVerbindung.getConnection();
    		 ps = con.prepareStatement(sql);
    		 ps.executeUpdate();
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
     
     public static void createVerbrauch() {
    	 Connection con = null;
    	 PreparedStatement ps = null;    	 
    	 String sql = "CREATE TABLE IF NOT EXISTS public.verbrauch\r\n"
    	 		+ "(\r\n"
    	 		+ "    vid serial NOT NULL,\r\n"
    	 		+ "    pname character varying NOT NULL,\r\n"
    	 		+ "    periode integer NOT NULL,\r\n"
    	 		+ "    verbrauch integer NOT NULL,\r\n"
    	 		+ "    problemkey character varying,\r\n"
    	 		+ "    verbrauchkategorie character varying COLLATE pg_catalog.\"default\",\r\n"    	 		
    	 		+ "    CONSTRAINT verbrauch_pkey PRIMARY KEY (vid),\r\n"
    	 		+ "    CONSTRAINT verbrauch FOREIGN KEY (pname)\r\n"
    	 		+ "        REFERENCES public.produkt (name) MATCH SIMPLE\r\n"
    	 		+ "        ON UPDATE NO ACTION\r\n"
    	 		+ "        ON DELETE NO ACTION,\r\n"
    	 		+ "    CONSTRAINT verbrauch_problemkey_fkey FOREIGN KEY (problemkey)\r\n"
    	 		+ "        REFERENCES public.probleminstanz (key) MATCH SIMPLE\r\n"
    	 		+ "        ON UPDATE NO ACTION\r\n"
    	 		+ "        ON DELETE CASCADE\r\n"
    	 		+ "        NOT VALID\r\n"
    	 		+ ")";
    	 
    	 try {
    		 con = DatenbankVerbindung.getConnection();
    		 ps = con.prepareStatement(sql);
    		 ps.executeUpdate();
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
     
     public static void createErgebnis() {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE IF NOT EXISTS public.ergebnis\r\n"
    	 		+ "(\r\n"
    	 		+ "    id serial NOT NULL ,\r\n"
    	 		+ "    bestellmenge integer NOT NULL,\r\n"
    	 		+ "    pname character varying NOT NULL,\r\n"
    	 		+ "    kosten double precision NOT NULL,\r\n"
    	 		+ "    userkey character varying NOT NULL,\r\n"
    	 		+ "    periode integer NOT NULL,\r\n"
    	 		+ "    uid character varying NOT NULL,\r\n"
    	 		+ "    CONSTRAINT ergebnis_pkey PRIMARY KEY (id),\r\n"
    	 		+ "    CONSTRAINT uid FOREIGN KEY (uid)\r\n"
    	 		+ "        REFERENCES public.highscore (uid) MATCH SIMPLE\r\n"
    	 		+ "        ON UPDATE NO ACTION\r\n"
    	 		+ "        ON DELETE CASCADE\r\n"
    	 		+ "        NOT VALID\r\n"
    	 		+ ")";
    	 try {
    		 con = DatenbankVerbindung.getConnection();
    		 ps = con.prepareStatement(sql);
    		 ps.executeUpdate();
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
     
     public static void createHighscore() {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE IF NOT EXISTS public.highscore\r\n"
    	 		+ "(\r\n"
    	 		+ "    id serial NOT NULL,\r\n"
    	 		+ "    score double precision NOT NULL,\r\n"
    	 		+ "    api character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
    	 		+ "    fehl double precision,\r\n"
    	 		+ "    kosten double precision,\r\n"
    	 		+ "    zeit bigint,\r\n"
    	 		+ "    schwierigkeitsgrad integer,\r\n"
    	 		+ "    uid character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
    	 		+ "    problemkey character varying COLLATE pg_catalog.\"default\",\r\n"
    	 		+ "    CONSTRAINT highscore_pkey PRIMARY KEY (id),\r\n"
    	 		+ "    CONSTRAINT uid UNIQUE (uid),\r\n"
    	 		+ "    CONSTRAINT highscore_problemkey_fkey FOREIGN KEY (problemkey)\r\n"
    	 		+ "        REFERENCES public.probleminstanz (key) MATCH SIMPLE\r\n"
    	 		+ "        ON UPDATE NO ACTION\r\n"
    	 		+ "        ON DELETE CASCADE\r\n"
    	 		+ "        NOT VALID,\r\n"
    	 		+ "	   CONSTRAINT ukey FOREIGN KEY (api)\r\n"
    	 		+ "        REFERENCES public.\"benutzer\" (key) MATCH SIMPLE\r\n"
    	 		+ "        ON UPDATE NO ACTION\r\n"
    	 		+ "        ON DELETE NO ACTION\r\n"
    	 		+ "        NOT VALID"
    	 		+ ")";
    	 try {
    		 con = DatenbankVerbindung.getConnection();
    		 ps = con.prepareStatement(sql);
    		 ps.executeUpdate();
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
     
     public static void createProblemInstanz() {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE IF NOT EXISTS public.probleminstanz\r\n"
    	 		+ "(\r\n"
    	 		+ "    id serial NOT NULL,\r\n"
    	 		+ "    panzahl integer NOT NULL,\r\n"
    	 		+ "    perioden integer NOT NULL,\r\n"
    	 		+ "    key character varying NOT NULL,\r\n"
    	 		+ "    lagervolumen integer,\r\n"
    	 		+ "    kapitalbindung double precision,\r\n"
    	 		+ "    sammelbestellung double precision,\r\n"
    	 		+ "    zeit bigint,\r\n"
    	 		+ "    name character varying,\r\n"
    	 	    + "    CONSTRAINT probleminstanz_pkey PRIMARY KEY (id),\r\n"
    	 	    + "    CONSTRAINT problemkey UNIQUE (key)\r\n "   	 		
    	 	    + ")";
    	 
    	 try {
    		 con = DatenbankVerbindung.getConnection();
    		 ps = con.prepareStatement(sql);
    		 ps.executeUpdate();
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
     
     public static void createProdukt() {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE IF NOT EXISTS public.produkt\r\n"
    	 		+ "(\r\n"
    	 		+ "    pid serial NOT NULL ,\r\n"
    	 		+ "    name character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
    	 	    + " bestellfix double precision NOT NULL,\r\n"
    	 	    + "lagerkostensatz double precision NOT NULL,\r\n"
    	 	    + "fehlmengenkosten double precision NOT NULL,\r\n"
    	 	    + "volumenprodukt double precision NOT NULL,\r\n"
    	 	    + "minbestand integer,\r\n"
    	 	    + "maxbestand integer,\r\n"
    	 	    + "einstand double precision NOT NULL,\r\n"
    	 	    + "key character varying COLLATE pg_catalog.\"default\",\r\n"
    	 	    + "pkategorie character varying COLLATE pg_catalog.\"default\","
    	 	    + "CONSTRAINT produkt_pkey PRIMARY KEY (pid),\r\n"
    	 	    + " CONSTRAINT produkt_name_key UNIQUE (name),\r\n"
    	 	    + "CONSTRAINT probleminstanz FOREIGN KEY (key) \r\n"
    	 	    + "   REFERENCES public.probleminstanz (key) MATCH SIMPLE \r\n"
    	 	    + "   ON UPDATE NO ACTION \r\n"
    	 	    + "   ON DELETE CASCADE \r\n"
    	 	    + "    NOT VALID \r\n"
    	 	    + ")";
    	 
    	 try {
    		 con = DatenbankVerbindung.getConnection();
    		 ps = con.prepareStatement(sql);
    		 ps.executeUpdate();
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
     
     public static void createStamps() {
    	Connection con = null;
    	PreparedStatement ps = null;
    	String sql = "CREATE TABLE IF NOT EXISTS public.stamps\r\n"
    			+ "(\r\n"
    			+ "    key character varying NOT NULL,\r\n"
    			+ "    \"timestamp\" numeric,\r\n"
    			+ "    startstamp numeric,\r\n"
    			+ "    CONSTRAINT stamps_pkey PRIMARY KEY (key)\r\n"
    			+ ")\r\n";
    	try {
   		 	con = DatenbankVerbindung.getConnection();
   		 	ps = con.prepareStatement(sql);
   		 	ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}

     }
     
     public static void createAdminUser(String name, String pw) throws SQLException {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 System.out.println(pw);
    	 String sql = "INSERT into public.benutzer (name, password, key, zugriff) VALUES (?,?,?,?)";
    	 
    	 try {
    		 con = DatenbankVerbindung.getConnection();
    		 ps = con.prepareStatement(sql);
    		 ps.setString(1, name);
    		 ps.setString(2, pw);
    		 ps.setString(3, "adminAccount");
    		 ps.setInt(4, 1);
    		 ps.executeUpdate();
    		 
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }finally {
    		 ps.close();
    		 con.close();
    		 
    	 }
     }
     
     public static void createBewaehrtProblem() {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE IF NOT EXISTS public.bewaehrtprobleminstanz\r\n"
    	 		+ "(\r\n"
    	 		+ "    id serial NOT NULL,\r\n"
    	 		+ "    panzahl integer NOT NULL,\r\n"
    	 		+ "    perioden integer NOT NULL,\r\n"
    	 		+ "    problemkey character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
    	 		+ "    kapitalbindung double precision NOT NULL,\r\n"
    	 		+ "    lagervolumen double precision NOT NULL,\r\n"
    	 		+ "    sammelbestellung double precision NOT NULL,\r\n"
    	 		+ "    zeit bigint NOT NULL,\r\n"
    	 		+ "    name character varying COLLATE pg_catalog.\"default\" NOT NULL,\r\n"
    	 		+ "    CONSTRAINT bewaehrtprobleminstanz_pkey PRIMARY KEY (id),\r\n"
    	 		+ "    CONSTRAINT \"Key\" UNIQUE (problemkey)\r\n"
    	 		+ ")";
    	 try {
    		 con = DatenbankVerbindung.getConnection();
    		 ps = con.prepareStatement(sql);
    		 ps.executeUpdate();
    	 }catch(SQLException e) {
    		 e.printStackTrace();
    	 }
     }
     
     public static void createBewaehrtProdukt() {
    	Connection con = null;
    	PreparedStatement ps = null;
    	String sql = "CREATE TABLE IF NOT EXISTS public.bewaehrtprodukt\r\n"
    			+ "(\r\n"
    			+ "    pid serial NOT NULL,\r\n"
    			+ "    name character varying NOT NULL,\r\n"
    			+ "    bestellfix double precision NOT NULL,\r\n"
    			+ "    lagerkostensatz double precision NOT NULL,\r\n"
    			+ "    fehlmengenkosten double precision NOT NULL,\r\n"
    			+ "    volumenprodukt double precision NOT NULL,\r\n"
    			+ "    minbestand integer NOT NULL,\r\n"
    			+ "    maxbestand integer NOT NULL,\r\n"
    			+ "    einstand double precision NOT NULL,\r\n"
    			+ "    problemkey character varying NOT NULL,\r\n"
    			+ "    pkategorie character varying COLLATE pg_catalog.\"default\",\r\n"    			
    			+ "    CONSTRAINT bewaehrtprodukt_pkey PRIMARY KEY (pid),\r\n"
    			+ "    CONSTRAINT bewaehrtprodukt_problemkey_fkey FOREIGN KEY (problemkey)\r\n"
    			+ "        REFERENCES public.bewaehrtprobleminstanz (problemkey) MATCH SIMPLE\r\n"
    			+ "        ON UPDATE NO ACTION\r\n"
    			+ "        ON DELETE NO ACTION\r\n"
    			+ ")";
    	
    	try {
   		 con = DatenbankVerbindung.getConnection();
   		 ps = con.prepareStatement(sql);
   		 ps.executeUpdate();
    	}catch(SQLException e) {
   		 e.printStackTrace();
   	 	}
     }
    
     public static void createBewaehrtUserErgebnis() {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE IF NOT EXISTS public.bewaehrtuserergebnis\r\n"
    			+ "(\r\n"
    			+ "    id serial NOT NULL,\r\n"
    			+ "    bestellmenge integer NOT NULL,\r\n"
    			+ "    pname character varying NOT NULL,\r\n"
    			+ "    kosten double precision NOT NULL,\r\n"
    			+ "    periode integer NOT NULL,\r\n"
    			+ "    uid character varying NOT NULL,\r\n"
    			+ "    CONSTRAINT bewaehrtuserergebnis_pkey PRIMARY KEY (id)\r\n"
    			+ ")";
    	 
    	 try {
       		 con = DatenbankVerbindung.getConnection();
       		 ps = con.prepareStatement(sql);
       		 ps.executeUpdate();
        	}catch(SQLException e) {
       		 e.printStackTrace();
       	 	}
    	 
     }
     
     public static void createBewaehrtVerbrauch() {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE IF NOT EXISTS public.bewaehrtverbrauch\r\n"
    	 		+ "(\r\n"
    	 		+ "    vid serial NOT NULL ,\r\n"
    	 		+ "    pname character varying NOT NULL,\r\n"
    	 		+ "    periode integer NOT NULL,\r\n"
    	 		+ "    verbrauch integer NOT NULL,\r\n"
    	 		+ "    verbrauchkategorie \"char\",\r\n"
    	 		+ "    problemkey character varying,\r\n"
    	 		+ "    CONSTRAINT bewaehrtverbrauch_pkey PRIMARY KEY (vid)\r\n"
    	 		+ ")\r\n";    	 		
    	 
    	 try {
       		 con = DatenbankVerbindung.getConnection();
       		 ps = con.prepareStatement(sql);
       		 ps.executeUpdate();
        	}catch(SQLException e) {
       		 e.printStackTrace();
       	 	}
     }
     
     public static void createTopTen() {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE IF NOT EXISTS public.toptenbewaehrt\r\n"
    	 		+ "(\r\n"
    	 		+ "    id serial NOT NULL,\r\n"
    	 		+ "    score double precision NOT NULL,\r\n"
    	 		+ "    fehl double precision NOT NULL,\r\n"
    	 		+ "    kosten double precision NOT NULL,\r\n"
    	 		+ "    zeit bigint NOT NULL,\r\n"
    	 		+ "    schwierigkeitsgrad integer NOT NULL,\r\n"
    	 		+ "    problemkey character varying,\r\n"
    	 		+ "    uid character varying,\r\n"
    	 		+ "    CONSTRAINT toptenbewaehrt_pkey PRIMARY KEY (id),\r\n"
    	 		+ "    CONSTRAINT pkey FOREIGN KEY (problemkey)\r\n"
    	 		+ "        REFERENCES public.bewaehrtprobleminstanz (problemkey) MATCH SIMPLE\r\n"
    	 		+ "        ON UPDATE NO ACTION\r\n"
    	 		+ "        ON DELETE NO ACTION\r\n"
    	 		+ ")";
    	 
    	 try {
       		 con = DatenbankVerbindung.getConnection();
       		 ps = con.prepareStatement(sql);
       		 ps.executeUpdate();
        	}catch(SQLException e) {
       		 e.printStackTrace();
       	 	}
     }

}
