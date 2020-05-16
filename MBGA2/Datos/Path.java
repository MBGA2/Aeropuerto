package Datos;

public class Path {
	private String destination;
	private String stopover;
	private int duration;
	private int posX;
	private int posY;
	public Path(String destination, String stopover, int duration) {
		super();
		this.stopover = stopover;
		this.duration = duration;
		this.destination = destination;
	}
	public Path(String destination, String stopover, int duration, int posX, int posY) {
		super();
		this.stopover = stopover;
		this.duration = duration;
		this.destination = destination;
		this.setPosX(posX);
		this.setPosY(posY);
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getStopover() {
		return stopover;
	}
	public void setStopover(String stopover) {
		this.stopover = stopover;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
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
