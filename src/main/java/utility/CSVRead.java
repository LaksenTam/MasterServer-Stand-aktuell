package utility;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CSVRead {
	


	public List<String> lese(String produktNamen, List<String> namenListe) {
			
	
		String[] elements = produktNamen.split(", ");
		
		List<String> liste = Arrays.asList(elements);
		
		namenListe = new ArrayList<String>(liste);
		
		
		//System.out.println(namenListe.toString());
		
		return namenListe;
	
	}

}
