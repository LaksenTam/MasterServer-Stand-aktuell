package datenbank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Datenbankinstaller {
	
	 protected static Connection instance;
     
     private static final String DB_SERVER = "localhost";
     private static final String DB_NAME = "testdatenbankinstall2";
     private static final String DB_USER = "postgres";
     private static final String DB_PASSWORD = "henrik";
     private static final String DB_DRIVER = "org.postgresql.Driver";
     private static final String DB_URL = "jdbc:postgresql://" + DB_SERVER + "/" + DB_NAME;
     
     public static void initDatabase() {
    	 try {
			createDB();
			System.out.println("Database created");
			createUserTable();
			System.out.println("Usertable created");
			createProdukt();
			System.out.println("Produktable created");
			createProblemInstanz();
			System.out.println("Probleminstanztable created");
			createHighscore();
			System.out.println("Highscoretable created");
			createErgebnis();
			System.out.println("Ergebnistable created");
			createStamps();
			System.out.println("Timestamptable created");
			createVerbrauch();
			System.out.println("Verbrauchtable created");			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }

          
     public static void createDB() throws ClassNotFoundException, SQLException {
    	 Connection con = null;
    	 Statement stmt = null;
    	 String sql = "CREATE DATABASE " + DB_NAME+ " OWNER " + DB_USER + ";";
    	 try {
        	 Class.forName(DB_DRIVER);
        	 con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","henrik");      	    
    		 stmt = con.createStatement();
    		 stmt.executeUpdate(sql);

    	 }catch(Exception e) {
    		 e.printStackTrace();
    		
    	 }
    	 
     }
     
     /**
      * Initializes a connection to the data base defined by DB_URL.
      * 
      * @return java.sql.Connection or null if there is an error.
      */
     private static Connection init() {
         try {
             Class.forName(DB_DRIVER);
             instance = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             return instance;
         } catch (ClassNotFoundException | SQLException e) {
             System.out.println("PostgresDb: Something went wrong: ");
             initDatabase();
             //e.printStackTrace();
         }

         return null;
     }

     /**
      * Returns a Connection or null if connecting to data base fails for some
      * reason.
      * 
      * @return java.sql.Connection
      */
     public static Connection getConnection() {
         try {
             return (instance == null || instance.isClosed()) ? init() : instance;
         } catch (SQLException e) {
             e.printStackTrace();
             return init();
         }
     }

     public static void closeConnection() {
         try {
             instance.close();
         } catch (SQLException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     }
     
     public static void createUserTable() throws SQLException {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "CREATE TABLE users("
    			 +"id serial NOT NULL,"
    			 +"name character varying NOT NULL,"
    			 +"password character varying NOT NULL,"
    			 +"key character varying NOT NULL,"
    			 +"zugriff integer NOT NULL,"
    			  +"CONSTRAINT user_pkey PRIMARY KEY (id),"
    			  +"CONSTRAINT name UNIQUE (name)"
    			  +");";
    	 try {
    		 con = getConnection();
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
    	 		+ "    CONSTRAINT verbrauch_pkey PRIMARY KEY (vid),\r\n"
    	 		+ "    CONSTRAINT verbrauch FOREIGN KEY (pname)\r\n"
    	 		+ "        REFERENCES public.produkt (name) MATCH SIMPLE\r\n"
    	 		+ "        ON UPDATE NO ACTION\r\n"
    	 		+ "        ON DELETE NO ACTION\r\n"
    	 		+ ")";
    	 
    	 try {
    		 con = getConnection();
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
    		 con = getConnection();
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
    	 		+ "    id serial NOT NULL ,\r\n"
    	 		+ "    score double precision NOT NULL,\r\n"
    	 		+ "    api character varying NOT NULL,\r\n"
    	 		+ "    fehl double precision,\r\n"
    	 		+ "    kosten double precision,\r\n"
    	 		+ "    zeit bigint,\r\n"
    	 		+ "    schwierigkeitsgrad integer,\r\n"
    	 		+ "    uid character varying NOT NULL,\r\n"
    	 		+ "    CONSTRAINT highscore_pkey PRIMARY KEY (id),\r\n"
    	 		+ "    CONSTRAINT uid UNIQUE (uid)\r\n"
    	 		+ ")";
    	 
    	 try {
    		 con = getConnection();
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
    	 		+ "    CONSTRAINT probleminstanz_pkey PRIMARY KEY (id)\r\n"
    	 		+ ")";
    	 
    	 try {
    		 con = getConnection();
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
    	 		+ "    name character varying NOT NULL,\r\n"
    	 		+ "    bestellfix double precision NOT NULL,\r\n"
    	 		+ "    lagerkostensatz double precision NOT NULL,\r\n"
    	 		+ "    fehlmengenkosten double precision NOT NULL,\r\n"
    	 		+ "    volumenprodukt double precision NOT NULL,\r\n"
    	 		+ "    minbestand integer,\r\n"
    	 		+ "    maxbestand integer,\r\n"
    	 		+ "    einstand double precision NOT NULL,\r\n"
    	 		+ "    key character varying ,\r\n"
    	 		+ "    CONSTRAINT produkt_pkey PRIMARY KEY (pid),\r\n"
    	 		+ "    CONSTRAINT produkt_name_key UNIQUE (name)\r\n"
    	 		+ ")";
    	 
    	 try {
    		 con = getConnection();
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
   		 	con = getConnection();
   		 	ps = con.prepareStatement(sql);
   		 	ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}

     }
     
     public static void createAdminUser(String name, String pw) throws SQLException {
    	 Connection con = null;
    	 PreparedStatement ps = null;
    	 String sql = "INSERT into public.users (name, password, key, zugriff) VALUES (?,?,?,?)";
    	 
    	 try {
    		 con = getConnection();
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
    

}
