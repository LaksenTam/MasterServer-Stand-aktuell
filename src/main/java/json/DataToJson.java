package json;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import data.Lager;
import data.Produkt;

public class DataToJson {

	public String dataToJson(List<Produkt> produktListe) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(produktListe);
		return json;
	}
	public String produktToJson(Produkt p) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(p);
		return json;
	}

	public String lagerToJson(Lager lager) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(lager);
		return json;
	}

}
