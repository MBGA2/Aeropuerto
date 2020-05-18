package Datos;

import java.sql.Timestamp;

import Observer.Observable;

public class Flight extends Observable implements Comparable<Flight> {
	private String destination;
	private String source;
	private Timestamp departure_time;
	private Timestamp arrival_time;
	private int retarded_value;
	private Timestamp boarding_time;
	private Timestamp realDate;
	private String gate;
	private String Flight_state;
	private String plane_state;
	private String ID;
	private String company;
	private Path path;
	public static final long HOUR = 3600 * 1000; // in milli-seconds.
	public static final long MIN = 60 * 1000; // in milli-seconds.
	

	public Flight() {
		
	}

	public String checkState() {
		Timestamp dateAux2 = new Timestamp(realDate.getTime() + 100 * MIN);
		Timestamp dateAux = new Timestamp(realDate.getTime() + 5 * MIN);
		Timestamp dateAux3 = new Timestamp(arrival_time.getTime() - 15 * MIN);
		Timestamp dateAux4 = new Timestamp(arrival_time.getTime() + 15 * MIN);
		if(!this.plane_state.equalsIgnoreCase("Correcto")) {
			realDate = new Timestamp(realDate.getTime() - retarded_value * MIN);
		}
		if (this.source.equalsIgnoreCase("Madrid")) {

			if (realDate.compareTo(boarding_time) == 1 && realDate.compareTo(departure_time) == -1) {

				if (dateAux.compareTo(departure_time) == -1) {
					this.Flight_state = "Boarding";
				} else {
					this.Flight_state = "Last_Call";
				}
			} else if (realDate.compareTo(departure_time) == 1) {
				if (dateAux.compareTo(departure_time) == 1) {
					this.Flight_state = "On_Going";
				}
				else {
				this.Flight_state = "Loaded";
				}
			} else if (dateAux2.compareTo(departure_time) == 1) {
				this.Flight_state = "Go_to_gate";
			}

			else {
				this.gate = "NO_GATE";
				//this.state = FlightState.Waiting_gate;
			}
		} else {
			if (realDate.compareTo(arrival_time) == -1) {
				if (realDate.compareTo(dateAux3) == 1) {
					this.Flight_state = "Landing";
				}
				else if(realDate.compareTo(departure_time) == -1){
					this.Flight_state = "Waiting";
				}
				else {
					this.Flight_state = "On_Going";
				}
			}else if (realDate.compareTo(arrival_time) == 1) {
				if (realDate.compareTo(dateAux4) == -1) {
					this.Flight_state = "Disbounding";
				} else {
					this.Flight_state = "Storing";
				}
			}
			else {
				//this.state = FlightState.Waiting_gate;
			}
		}
		
		return this.Flight_state;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Timestamp getDeparture_time() {
		return this.departure_time;
	}

	public void setDeparture_time(Timestamp departure_time) {
		this.departure_time = departure_time;
	}

	public Timestamp getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(Timestamp arrival_time) {
		this.arrival_time = arrival_time;
	}

	public Timestamp getBoarding_time() {
		return boarding_time;
	}

	public void setBoarding_time(Timestamp boarding_time) {
		this.boarding_time = boarding_time;
	}
	
	public Timestamp getRealDate() {
		return realDate;
	}

	public void setRealDate(Timestamp realDate) {
		this.realDate = realDate;
	}

	public String getGate() {
		return gate;
	}

	public void setGate(String gate) {
		this.gate = gate;
	}

	


	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFlight_state() {
		return Flight_state;
	}

	public void setFlight_state(String flight_state) {
		Flight_state = flight_state;
	}

	public String getPlane_state() {
		return plane_state;
	}

	public void setPlane_state(String plane_state) {
		this.plane_state = plane_state;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getRetarded_value() {
		return retarded_value;
	}

	public void setRetarded_value(int retarded_value) {
		this.retarded_value = retarded_value;
	}



	@Override
	public int compareTo(Flight o) {
		if (this.getSource().equalsIgnoreCase("Madrid")) {
			if(o.getSource().equalsIgnoreCase("Madrid") && this.getDeparture_time().after(o.getDeparture_time())) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else {
			if(o.getSource().equalsIgnoreCase("Madrid")) {
				return 1;
			}
				else if (this.getArrival_time().after(o.getArrival_time())) {
					return 1;		
				}
				else {
					return -1;
			}
		}
	}

}
