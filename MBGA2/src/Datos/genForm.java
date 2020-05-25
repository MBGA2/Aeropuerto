package Datos;

import java.util.ArrayList;
import java.util.List;

public class genForm {
	private List<String> state;
	private List<String> destino;

	private List<String> nombre;
	private List<String> contacto;
	
	private List<String> descripcion;
	
	

	public genForm() {
		this.destino = new ArrayList<String>();
		this.contacto = new ArrayList<String>();
		this.nombre = new ArrayList<String>();
		this.descripcion = new ArrayList<String>();
		this.state = new ArrayList<String>();
		
		
		fillDestiny();
		fillNombre();
		fillContact();
		fillDescripcion();
		fillState();

	}

	public void fillDestiny() {
		this.destino.add("Tirana");
		this.destino.add("Berlin");
		this.destino.add("Andorra La Vieja");
		this.destino.add("Erevan");
		this.destino.add("Viena");
		this.destino.add("Bruselas");
		this.destino.add("Minsk");
		this.destino.add("Sarajevo");
		this.destino.add("Sofia");
		this.destino.add("Ciudad del Vaticano");
		this.destino.add("Zagreb");
		this.destino.add("Copenhague");
		this.destino.add("Bratislava");
		this.destino.add("Liubliana");
		this.destino.add("Barcelona");
		this.destino.add("Helsinki");
		this.destino.add("Paris");
		this.destino.add("Tbilisi");
		this.destino.add("Atenas");
		this.destino.add("Budapest");
		this.destino.add("Dublin");
		this.destino.add("Reikiavik");
		this.destino.add("Roma");
		this.destino.add("Riga");
		this.destino.add("Vaduz");
		this.destino.add("Vilna");
		this.destino.add("Luxemburgo");
		this.destino.add("Skopje");
		this.destino.add("Chisinau");
		this.destino.add("Monaco");
		this.destino.add("Podgorica");
		this.destino.add("Oslo");
		this.destino.add("Amsterdam");
		this.destino.add("Varsovia");
		this.destino.add("Lisboa");
		this.destino.add("Londres");
		this.destino.add("Praga");
		this.destino.add("Bucarest");
		this.destino.add("Moscu");
		this.destino.add("San Marino");
		this.destino.add("Belgrado");
		this.destino.add("Estocolmo");
		this.destino.add("Berna");
		this.destino.add("Ankara");
		this.destino.add("Kiev");
	}

	public void fillNombre() {
		this.nombre.add("Juan Gomez");
		this.nombre.add("Pepe Luis");
		this.nombre.add("Josefa Garcia");
		this.nombre.add("Maria Rot");
		this.nombre.add("Karlos Kas");
		this.nombre.add("Lois Asol");
		this.nombre.add("Barbara Jaiz");
		this.nombre.add("Paula Oram");
		this.nombre.add("Manuel Crei");
		this.nombre.add("Quasi Modo");
	}

	public void fillContact() {
		this.contacto.add("aoskd@gmail.com");
		this.contacto.add("correo@yahoo.es");
		this.contacto.add("ejaddemi-3984@yopmail.com");
		this.contacto.add("milawexe-3077@yopmail.com");
		this.contacto.add("qamivuddyfo-0457@yopmail.com");
		this.contacto.add("Bdofabehitt-6647@yopmail.com");
		this.contacto.add("Boo@hotmail.com");
		this.contacto.add("Bee3@hotmail.com");
		this.contacto.add("Qrach@hotmail.com");
		this.contacto.add("m3n1@hotmail.com");
		this.contacto.add("Cccc21@gmail.com");
		this.contacto.add("3456789@gmail.com");
		this.contacto.add("sisi@yahoo.es");
		this.contacto.add("email@ucm.es");
	
	}
	
	public void fillDescripcion() {


		this.descripcion.add("Ropa");
		this.descripcion.add("Juguetes");
		this.descripcion.add("Material escolar");
		this.descripcion.add("Armas");
		this.descripcion.add("Instrumento musical");
		this.descripcion.add("Equipamiento deportivo");
		this.descripcion.add("Comida");
		this.descripcion.add("Animal vivo");

	
	}
	
	public void fillState() {
		this.state.add("Aceptado");
		this.state.add("Denegado");
		this.state.add("Pendiente");
	
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

	public int randomN() {
		int n=0;
		for (int i = 0; i <2; i++) {
			n = (int) (Math.random() * 10);
			
		}
		return n;
	}
	public List<String> getDestiny() {
		return destino;
	}

	public void setDestiny(List<String> destino) {
		this.destino = destino;
	}
	public List<String> getNombre() {
		return nombre;
	}

	public void setNombre(List<String> nombre) {
		this.nombre = nombre;
	}
	public List<String> getContacto() {
		return contacto;
	}

	public void setContacto(List<String> contacto) {
		this.contacto = contacto;
	}
	public List<String> getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(List<String> descripcion) {
		this.descripcion = descripcion;
	}
	public List<String> getState() {
		return state;
	}

	public void setState(List<String> state) {
		this.state = state;
	}
	
}