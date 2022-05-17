package utility;

import java.util.ArrayList;
import java.util.List;

import data.Produkt;

public class NumFormat {
	
	public static List<String[]> formatNum(List<String[]> num) {
		for(int i = 0; i<num.size(); i++) {
			Double numParsed = Double.parseDouble(num.get(i)[2]);
			String numString = String.format("%.2f", numParsed);
			num.get(i)[2] = numString; 
		}
		return num;
	}
	
	public static List<String> seperatekv(List<Produkt> pDaten, List<String[]> userData) {
		List<String> costData = new ArrayList<String>();
		double kv =0;
		double kf = 0;
		for(int i = 0; i<userData.size();i++) {	
			for(int j = 0; j<pDaten.size();j++) {
				if(userData.get(i)[0].equals(pDaten.get(j).getName())) {
					kv += Double.parseDouble(userData.get(i)[1]) * pDaten.get(j).getEinstand();						
					break;					
				}
			}
		}	
		
		kf= Double.parseDouble(userData.get(userData.size()-1)[3]) - kv;		
		costData.add(Double.toString(kv));
		costData.add(Double.toString(kf));
		costData.add(userData.get(userData.size()-1)[4]);		
		return costData;
	}
	
	public static List<String> seperatekvHigh(List<Produkt> pDaten, List<String[]> highScoreData, 
			List<String[]> bestScoreErgebnis){
		List<String> highScoreCostData = new ArrayList<String>();
		double kv =0;
		double kf = 0;
		for(int i = 0; i<bestScoreErgebnis.size();i++) {	
			for(int j = 0; j<pDaten.size();j++) {
				if(bestScoreErgebnis.get(i)[0].equals(pDaten.get(j).getName())) {
					kv += Double.parseDouble(bestScoreErgebnis.get(i)[1]) * pDaten.get(j).getEinstand();						
					break;					
				}
			}
		}
		
		kf = Double.parseDouble(highScoreData.get(0)[2]) - kv;	
		highScoreCostData.add(Double.toString(kv));
		highScoreCostData.add(Double.toString(kf));
		highScoreCostData.add(highScoreData.get(0)[1]);
		
		
		return highScoreCostData;
	}
	

}
