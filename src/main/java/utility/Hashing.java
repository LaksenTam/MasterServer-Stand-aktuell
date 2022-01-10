package utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
	
	// Methode um Passwort zu verschlüsseln
			public String erstelleHash(String password) {
				
				final String saltWert = "EMA";
				password = password + saltWert;

				StringBuffer buffer = new StringBuffer();

				try {
					MessageDigest md = MessageDigest.getInstance("SHA-256");
					byte[] hash = md.digest(password.getBytes());

					for (int i = 0; i < hash.length; i++) {
						buffer.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3));
					}

					password = buffer.toString();

				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
					password = null;
				}

				return password;
			}

}
