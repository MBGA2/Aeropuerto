package Datos;

public class InfoCity {
	private String name;
	private int posX;
	private int posY;
	public InfoCity(String name, int posX, int posY) {
		super();
		this.name = name;
		this.posX = posX;
		this.posY = posY;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
}
