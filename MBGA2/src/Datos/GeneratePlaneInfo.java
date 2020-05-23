package Datos;

import java.util.ArrayList;
import java.util.List;

public class GeneratePlaneInfo {
	private List<String> capitals;
	private List<String> companies;
	private List<String> gates;
	private List<Path> paths;

	public GeneratePlaneInfo() {
		this.capitals = new ArrayList<String>();
		this.companies = new ArrayList<String>();
		this.gates = new ArrayList<String>();
		this.paths = new ArrayList<Path>();
		fillDestiny();
		fillCompanies();
		fillGates();

	}

	public void fillDestiny() {
		this.capitals.add("Tirana");
		this.capitals.add("Berlin");
		this.capitals.add("Andorra La Vieja");
		this.capitals.add("Erevan");
		this.capitals.add("Viena");
		this.capitals.add("Bruselas");
		this.capitals.add("Minsk");
		this.capitals.add("Sarajevo");
		this.capitals.add("Sofia");
		this.capitals.add("Ciudad del Vaticano");
		this.capitals.add("Zagreb");
		this.capitals.add("Copenhague");
		this.capitals.add("Bratislava");
		this.capitals.add("Liubliana");
		this.capitals.add("Barcelona");
		this.capitals.add("Helsinki");
		this.capitals.add("Paris");
		this.capitals.add("Tbilisi");
		this.capitals.add("Atenas");
		this.capitals.add("Budapest");
		this.capitals.add("Dublin");
		this.capitals.add("Reikiavik");
		this.capitals.add("Roma");
		this.capitals.add("Riga");
		this.capitals.add("Vaduz");
		this.capitals.add("Vilna");
		this.capitals.add("Luxemburgo");
		this.capitals.add("Skopje");
		this.capitals.add("Chisinau");
		this.capitals.add("Monaco");
		this.capitals.add("Podgorica");
		this.capitals.add("Oslo");
		this.capitals.add("Amsterdam");
		this.capitals.add("Varsovia");
		this.capitals.add("Lisboa");
		this.capitals.add("Londres");
		this.capitals.add("Praga");
		this.capitals.add("Bucarest");
		this.capitals.add("Moscu");
		this.capitals.add("San Marino");
		this.capitals.add("Belgrado");
		this.capitals.add("Estocolmo");
		this.capitals.add("Berna");
		this.capitals.add("Ankara");
		this.capitals.add("Kiev");
	}

	public void fillCompanies() {
		this.companies.add("Ryanair");
		this.companies.add("Lufthansa");
		this.companies.add("Iberia");
		this.companies.add("Air France");
		this.companies.add("easyJet");
		this.companies.add("Turkish Airlines");
		this.companies.add("Aeroflot");
		this.companies.add("Norwegian");
		this.companies.add("Alitalia");
		this.companies.add("Finnair");
	}

	public void fillGates() {
		this.gates.add("A1");
		this.gates.add("A2");
		this.gates.add("A3");
		this.gates.add("A4");
		this.gates.add("A5");
		this.gates.add("B1");
		this.gates.add("B2");
		this.gates.add("B3");
		this.gates.add("B4");
		this.gates.add("B5");
		this.gates.add("C1");
		this.gates.add("C2");
		this.gates.add("C3");
		this.gates.add("C4");
		this.gates.add("C5");
		this.gates.add("D1");
		this.gates.add("D2");
		this.gates.add("D3");
		this.gates.add("D4");
		this.gates.add("D5");

	}

	public String randomID() {
		String pass;
		char[] elementos = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
				'I', 'J', 'K', 'L', 'M', 'N', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] conjunto = new char[8];
		for (int i = 0; i < 8; i++) {
			int el = (int) (Math.random() * 37);
			conjunto[i] = (char) elementos[el];
		}
		pass = new String(conjunto);
		return pass;
	}

	public List<Path> getPaths() {
		return paths;
	}

	public void setPaths(List<Path> paths) {
		this.paths = paths;
	}

	public List<String> getCapitals() {
		return capitals;
	}

	public void setCapitals(List<String> capitals) {
		this.capitals = capitals;
	}

	public List<String> getCompanies() {
		return companies;
	}

	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}

	public List<String> getGates() {
		return gates;
	}

	public void setGates(List<String> gates) {
		this.gates = gates;
	}
}
