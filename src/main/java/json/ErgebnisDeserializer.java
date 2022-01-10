package json;



import com.google.gson.Gson;

import data.Produktergebnis;
import data.Userergebnis;

public class ErgebnisDeserializer {
	
	//Userergebnis in Produktergebnis getauscht
	public Userergebnis deserializeJsonInput(String jsonErgebnis, Userergebnis ue) {		
		return ue = new Gson().fromJson(jsonErgebnis, Produktergebnis.class);		
	}

}