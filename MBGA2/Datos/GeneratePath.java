package Datos;

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
		cities.add(new InfoCity("Amsterdam", 5, 7));
		cities.add(new InfoCity("Andorra La Vieja", 4, 11));
		cities.add(new InfoCity("Ankara", 14, 11));
		cities.add(new InfoCity("Atenas", 12, 12));
		cities.add(new InfoCity("Barcelona", 4, 11));
		cities.add(new InfoCity("Belgrado", 10, 10));
		cities.add(new InfoCity("Berlin", 9, 6));
		cities.add(new InfoCity("Berna", 6, 9));
		cities.add(new InfoCity("Bratislava", 10, 8));
		cities.add(new InfoCity("Bruselas", 5, 8));
		cities.add(new InfoCity("Bucarest", 12, 10));
		cities.add(new InfoCity("Budapest", 10, 8));
		cities.add(new InfoCity("Chisinau", 13, 8));
		cities.add(new InfoCity("Ciudad del Vaticano", 8, 10));
		cities.add(new InfoCity("Copenhague", 9, 5));		
		cities.add(new InfoCity("Dublin", 3, 6));
		cities.add(new InfoCity("Erevan", 18, 10));
		cities.add(new InfoCity("Estocolmo", 10, 3));
		cities.add(new InfoCity("Helsinki", 11, 3));
		cities.add(new InfoCity("Kiev", 13, 7));
		cities.add(new InfoCity("Lisboa", 0, 11));
		cities.add(new InfoCity("Liubliana", 9, 9));
		cities.add(new InfoCity("Londres", 4, 7));
		cities.add(new InfoCity("Luxemburgo", 6, 8));
		cities.add(new InfoCity("Madrid", 2, 11));
		cities.add(new InfoCity("Minsk", 12, 6));
		cities.add(new InfoCity("Monaco", 6, 8));
		cities.add(new InfoCity("Moscu", 14, 5));
		cities.add(new InfoCity("Oslo", 8, 3));
		cities.add(new InfoCity("Paris", 5, 8));
		cities.add(new InfoCity("Podgorica", 10, 10));
		cities.add(new InfoCity("Praga", 9, 7));
		cities.add(new InfoCity("Reikiavik", 2, 1));
		cities.add(new InfoCity("Riga", 11, 4));
		cities.add(new InfoCity("Roma", 8, 10));
		cities.add(new InfoCity("San Marino", 7, 8));
		cities.add(new InfoCity("Sarajevo", 10, 10));
		cities.add(new InfoCity("Skopje", 11, 11));
		cities.add(new InfoCity("Sofia", 11, 10));
		cities.add(new InfoCity("Tbilisi", 18, 9));
		cities.add(new InfoCity("Tirana", 10, 11));
		cities.add(new InfoCity("Vaduz", 6, 9));
		cities.add(new InfoCity("Varsovia", 11, 6));
		cities.add(new InfoCity("Viena", 9, 8));
		cities.add(new InfoCity("Vilna", 12, 5));
		cities.add(new InfoCity("Zagreb", 9, 9));
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
