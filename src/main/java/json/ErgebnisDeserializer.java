package json;



import com.google.gson.Gson;

import data.Produktergebnis;
import data.Userergebnis;

public class ErgebnisDeserializer {
	
	//Userergebnis in Produktergebnis getauscht
	public Userergebnis deserializeJsonInput(String jsonErgebnis, Userergebnis ue) {		
		return ue = new Gson().fromJson(jsonErgebnis, Userergebnis.class);		
	}
	
	public Produktergebnis jsonToProduktergebnis(String ergebnis, Produktergebnis pe) {
		return pe = new Gson().fromJson(ergebnis, Produktergebnis.class);		
	}
	
	

}