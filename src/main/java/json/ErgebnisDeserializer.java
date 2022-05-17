package json;



import com.google.gson.Gson;


import data.Userergebnis;
import data.Zwischenergebnis;

public class ErgebnisDeserializer {
	
	//Userergebnis in Produktergebnis getauscht
	public Userergebnis deserializeJsonInput(String jsonErgebnis, Userergebnis ue) {		
		return ue = new Gson().fromJson(jsonErgebnis, Userergebnis.class);		
	}
	
	//Zwischenergebnis in Produktergebnis getauscht
		public Zwischenergebnis deserializeJsonZwischenergebnis(String jsonErgebnis, Zwischenergebnis ue) {		
			return ue = new Gson().fromJson(jsonErgebnis, Zwischenergebnis.class);		
		}

}