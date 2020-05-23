package Datos.seg;

public enum camState {

	on, off;

	public String toString() {
		switch (this) {
		case on:
			return "Encendido";
		case off:
			return "Apagado";
		}
		return "";

	}

}
