package Utils.atm;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneratePath {
	private ArrayList<InfoCity> cities;
	private HashMap<String,ArrayList<String>> direct;
	static int CASMAX = 10;
	
	public GeneratePath() {
		this.cities = new ArrayList<InfoCity>();
		this.direct = new HashMap<String, ArrayList<String>>();
		this.cityConstruct();
		this.calculateDirect();
	}
	
	public void cityConstruct() {
		cities.add(new InfoCity("Amsterdam", 5, 6));
		cities.add(new InfoCity("Andorra La Vieja", 4, 9));
		cities.add(new InfoCity("Ankara", 11, 9));
		cities.add(new InfoCity("Atenas", 9, 10));
		cities.add(new InfoCity("Barcelona", 5, 9));
		cities.add(new InfoCity("Baku", 15, 9));
		cities.add(new InfoCity("Belgrado", 9, 8));
		cities.add(new InfoCity("Berlin", 7, 6));
		cities.add(new InfoCity("Berna", 6, 7));
		cities.add(new InfoCity("Bratislava", 8, 7));
		cities.add(new InfoCity("Bruselas", 5, 6));
		cities.add(new InfoCity("Bucarest", 10, 8));
		cities.add(new InfoCity("Budapest", 8, 7));
		cities.add(new InfoCity("Chisinau", 10, 7));
		cities.add(new InfoCity("Ciudad del Vaticano", 7, 9));
		cities.add(new InfoCity("Copenhague", 7, 5));		
		cities.add(new InfoCity("Dublin", 3, 6));
		cities.add(new InfoCity("Erevan", 14, 9));
		cities.add(new InfoCity("Estocolmo", 8, 4));
		cities.add(new InfoCity("Helsinki", 9, 4));
		cities.add(new InfoCity("Kiev", 11, 7));
		cities.add(new InfoCity("La Valeta", 7, 10));
		cities.add(new InfoCity("Lisboa", 2, 10));
		cities.add(new InfoCity("Liubliana", 7, 8));
		cities.add(new InfoCity("Londres", 4, 6));
		cities.add(new InfoCity("Luxemburgo", 6, 7));
		cities.add(new InfoCity("Madrid", 3, 9));
		cities.add(new InfoCity("Minsk", 10, 6));
		cities.add(new InfoCity("Monaco", 6, 8));
		cities.add(new InfoCity("Moscu", 12, 5));
		cities.add(new InfoCity("Nicosia", 12, 11));
		cities.add(new InfoCity("Nus-Sultan", 18, 7));
		cities.add(new InfoCity("Oslo", 6, 4));
		cities.add(new InfoCity("Paris", 5, 7));
		cities.add(new InfoCity("Podgorica", 8, 9));
		cities.add(new InfoCity("Praga", 7, 7));
		cities.add(new InfoCity("Reikiavik", 1, 3));
		cities.add(new InfoCity("Riga", 9, 5));
		cities.add(new InfoCity("Roma", 7, 9));
		cities.add(new InfoCity("San Marino", 7, 8));
		cities.add(new InfoCity("Sarajevo", 8, 8));
		cities.add(new InfoCity("Skopje", 9, 9));
		cities.add(new InfoCity("Sofia", 9, 8));
		cities.add(new InfoCity("Tbilisi", 14, 9));
		cities.add(new InfoCity("Tirana", 8, 9));
		cities.add(new InfoCity("Vaduz", 6, 7));
		cities.add(new InfoCity("Varsovia", 9, 6));
		cities.add(new InfoCity("Viena", 8, 7));
		cities.add(new InfoCity("Vilna", 9, 5));
		cities.add(new InfoCity("Zagreb", 8, 8));
	}
	
	public void calculateDirect() {
		for (InfoCity city : this.cities) {
			ArrayList<String> aux = new ArrayList<String>();
			for(InfoCity cityAux : this.cities) 
				if (!city.getName().equalsIgnoreCase(cityAux.getName()) && 
						(Math.abs(city.getPosX() - cityAux.getPosX() + city.getPosY() - cityAux.getPosY()) <= CASMAX)) 
					aux.add(cityAux.getName());
			if (aux.isEmpty())
				aux.add("None");
			direct.put(city.getName(), aux);
		}
	}

	public ArrayList<InfoCity> getCities() {
		return cities;
	}

	public HashMap<String, ArrayList<String>> getDirect() {
		return direct;
	}
	
	
}
