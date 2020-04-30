package Utils.atm;

public class Path {
	private String destination;
	private String stopover;
	private int duration;
	private int actualPosX;
	private int actualPosY;
	
	public Path(String destination, String stopover, int duration) {
		super();
		this.destination = destination;
		this.stopover = stopover;
		this.duration = duration;
	}

	public Path(String destination, String stopover, int duration, int actualPosX, int actualPosY) {
		super();
		this.destination = destination;
		this.stopover = stopover;
		this.duration = duration;
		this.actualPosX = actualPosX;
		this.actualPosY = actualPosY;
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

	public int getActualPosX() {
		return actualPosX;
	}

	public void setActualPosX(int actualPosX) {
		this.actualPosX = actualPosX;
	}

	public int getActualPosY() {
		return actualPosY;
	}

	public void setActualPosY(int actualPosY) {
		this.actualPosY = actualPosY;
	}
	
}
