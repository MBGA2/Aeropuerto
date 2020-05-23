package Datos;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneratePath {
	private ArrayList<InfoCity> cities;
	private HashMap<String, ArrayList<String>> direct;
	static int CASMAX = 10;

	public GeneratePath() {
		this.cities = new ArrayList<InfoCity>();
		this.direct = new HashMap<String, ArrayList<String>>();
		this.cityConstruct();
		this.calculateDirect();
	}

	public void cityConstruct() {
		cities.add(new InfoCity("Amsterdam", 5, 8));
		cities.add(new InfoCity("Andorra La Vieja", 4, 10));
		cities.add(new InfoCity("Ankara", 13, 11));
		cities.add(new InfoCity("Atenas", 10, 12));
		cities.add(new InfoCity("Barcelona", 4, 10));
		cities.add(new InfoCity("Belgrado", 9, 11));
		cities.add(new InfoCity("Berlin", 7, 8));
		cities.add(new InfoCity("Berna", 6, 10));
		cities.add(new InfoCity("Bratislava", 8, 9));
		cities.add(new InfoCity("Bruselas", 5, 8));
		cities.add(new InfoCity("Bucarest", 11, 10));
		cities.add(new InfoCity("Budapest", 10, 8));
		cities.add(new InfoCity("Chisinau", 12, 9));
		cities.add(new InfoCity("Ciudad del Vaticano", 7, 11));
		cities.add(new InfoCity("Copenhague", 7, 7));
		cities.add(new InfoCity("Dublin", 3, 7));
		cities.add(new InfoCity("Erevan", 17, 10));
		cities.add(new InfoCity("Estocolmo", 9, 5));
		cities.add(new InfoCity("Helsinki", 10, 5));
		cities.add(new InfoCity("Kiev", 12, 8));
		cities.add(new InfoCity("Lisboa", 0, 10));
		cities.add(new InfoCity("Liubliana", 8, 10));
		cities.add(new InfoCity("Londres", 4, 8));
		cities.add(new InfoCity("Luxemburgo", 5, 9));
		cities.add(new InfoCity("Madrid", 2, 10));
		cities.add(new InfoCity("Minsk", 11, 7));
		cities.add(new InfoCity("Monaco", 6, 8));
		cities.add(new InfoCity("Moscu", 13, 5));
		cities.add(new InfoCity("Oslo", 7, 5));
		cities.add(new InfoCity("Paris", 4, 9));
		cities.add(new InfoCity("Podgorica", 9, 11));
		cities.add(new InfoCity("Praga", 8, 9));
		cities.add(new InfoCity("Reikiavik", 2, 1));
		cities.add(new InfoCity("Riga", 10, 6));
		cities.add(new InfoCity("Roma", 7, 11));
		cities.add(new InfoCity("San Marino", 7, 10));
		cities.add(new InfoCity("Sarajevo", 9, 11));
		cities.add(new InfoCity("Skopje", 10, 11));
		cities.add(new InfoCity("Sofia", 10, 11));
		cities.add(new InfoCity("Tbilisi", 16, 10));
		cities.add(new InfoCity("Tirana", 9, 11));
		cities.add(new InfoCity("Vaduz", 6, 10));
		cities.add(new InfoCity("Varsovia", 9, 8));
		cities.add(new InfoCity("Viena", 8, 9));
		cities.add(new InfoCity("Vilna", 10, 7));
		cities.add(new InfoCity("Zagreb", 8, 10));
	}

	public void calculateDirect() {
		for (InfoCity city : this.cities) {
			ArrayList<String> aux = new ArrayList<String>();
			for (InfoCity cityAux : this.cities)
				if (!city.getName().equalsIgnoreCase(cityAux.getName()) && (Math
						.abs(city.getPosX() - cityAux.getPosX() + city.getPosY() - cityAux.getPosY()) <= CASMAX))
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
