package Utils.atm;

public class Path {
	private String destination;
	private String stopover;
	private int duration;
	public Path(String destination, String stopover, int duration) {
		super();
		this.stopover = stopover;
		this.duration = duration;
		this.destination = destination;
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
	
}
