package utility;

import java.util.List;

public class DrawChart {
	
	public String drawKosten(List<String[]> kostenList) {
		String kosten ="";
		for(int i = 0; i<kostenList.size();i++) {			
				kosten += "['" + kostenList.get(i)[0] + "'," + kostenList.get(i)[2] + "],";					
		}
		
		return kosten;
	}
	
	public String drawBestandsVerlauf(List<String[]> bestandList) {
		String bestand = "";
		for(int i = 0; i<bestandList.size();i++) {
			bestand += "['" + bestandList.get(i)[0] + "'," + bestandList.get(i)[1] + "],";			
		}
		return bestand;
	}

	public String drawProdukteChart(List<String> produktNamen) {
		String proNamen = "";
		for(int i = 0;i<produktNamen.size();i++) {
			proNamen += "'" + produktNamen.get(i) + "',";
		}
		return proNamen;
	}

	public String drawVolumenVerlauf(List<String[]> volumen) {
		String vol = "";
		for(int i = 0; i<volumen.size();i++) {
			vol += "['" + volumen.get(i)[0] + "'," + volumen.get(i)[1] + "],";			
		}
		return vol;
	}

	public String drawScores(List<String[]> userData) {
		String score ="";
		for(int i = 0; i<userData.size();i++) {
			score = "['Highscore Übersicht'," + userData.get(i)[3] + "," + userData.get(i)[4] + "," + userData.get(i)[5] + "],"; 
		}
		return score;
	}
	
	public String drawHighScoreLeader(List<String[]> leader) {
		String leaderScore = "";
		for(int i = 0; i<leader.size();i++) {
			leaderScore = "['Highscore Übersicht'," + leader.get(i)[2] + "," + leader.get(i)[1] + "," + leader.get(i)[3] + "],"; 
		}
		
		return leaderScore;
	}

	public String drawGesamtBestandVerlauf(List<String[]> bestandsverlauf) {
		String verlauf = "['0', 0]";		
		for(int i = 0; i<bestandsverlauf.size();i++) {
			verlauf += ",['" + bestandsverlauf.get(i)[0] + "'," + bestandsverlauf.get(i)[1] + "]";
		}
		verlauf +=",['" + (bestandsverlauf.size() +1) + "',0]";
		System.out.println(verlauf);
		return verlauf;
	}

}
