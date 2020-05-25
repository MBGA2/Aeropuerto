package Controladores;

import java.sql.Timestamp;

import javax.swing.table.DefaultTableModel;
import Main.Aeropuerto;
import Observer.Observer;
import SA.atm_SA;
import Datos.Flight;
import Datos.InfoCity;
import Datos.NTYPE;
import Datos.NotifyData;
import Datos.Path;
import Datos.Tuple;

public class atm_controller {
	private Aeropuerto airport;
	private atm_SA sa;
	private int sliderDelay;
	private int sliderCrash;
	public atm_controller(Aeropuerto airport) {
		this.airport = airport;
	}

	public void addModelObserver(Observer o) {
		airport.addObserver(o);
		for (int i = 0; i < this.airport.getFligths().size(); i++) {
			airport.getFligths().get(i).addObserver(o);
		}
	}

	private int sarPath(Flight f, Flight sar) {
		int x = -1, y = -1, mx = -1, my = -1, t = 0, ti = 60 * 60 * 1000, d = 0;
		Tuple<Integer, Integer, Timestamp> aux = this.airport.getMap().currentPos(f, this.airport.getTime());
		for (InfoCity city : this.airport.getPath().getCities()) {
			if (city.getName().equalsIgnoreCase("Madrid")) {
				x = city.getPosX();
				mx = x;
				y = city.getPosY();
				my = y;
				break;
			}
		}
		d = (Math.abs(x - aux.x) + Math.abs(y - aux.y)) * 2;
		while ((x < aux.x || x > aux.x) || (y < aux.y || y > aux.y)) {
			if (x < aux.x) {
				this.airport.getMap().addFlightInMap(sar, x, y,
						new Timestamp(this.airport.getTime().getTime() + (t * ti)),
						new Timestamp(this.airport.getTime().getTime() + ((t + 1) * ti)));
				x++;
				t++;
			}
			if (x > aux.x) {
				this.airport.getMap().addFlightInMap(sar, x, y,
						new Timestamp(this.airport.getTime().getTime() + (t * ti)),
						new Timestamp(this.airport.getTime().getTime() + ((t + 1) * ti)));
				x--;
				t++;
			}
			if (y < aux.y) {
				this.airport.getMap().addFlightInMap(sar, x, y,
						new Timestamp(this.airport.getTime().getTime() + (t * ti)),
						new Timestamp(this.airport.getTime().getTime() + ((t + 1) * ti)));
				y++;
				t++;
			}
			if (y > aux.y) {
				this.airport.getMap().addFlightInMap(sar, x, y,
						new Timestamp(this.airport.getTime().getTime() + (t * ti)),
						new Timestamp(this.airport.getTime().getTime() + ((t + 1) * ti)));
				y--;
				t++;
			}
		}
		while ((x < mx || x > mx) || (y < my || y > my)) {
			if (x < mx) {
				this.airport.getMap().addFlightInMap(sar, x, y,
						new Timestamp(this.airport.getTime().getTime() + (t * ti)),
						new Timestamp(this.airport.getTime().getTime() + ((t + 1) * ti)));
				x++;
				t++;
			}
			if (x > mx) {
				this.airport.getMap().addFlightInMap(sar, x, y,
						new Timestamp(this.airport.getTime().getTime() + (t * ti)),
						new Timestamp(this.airport.getTime().getTime() + ((t + 1) * ti)));
				x--;
				t++;
			}
			if (y < my) {
				this.airport.getMap().addFlightInMap(sar, x, y,
						new Timestamp(this.airport.getTime().getTime() + (t * ti)),
						new Timestamp(this.airport.getTime().getTime() + ((t + 1) * ti)));
				y++;
				t++;
			}
			if (y > my) {
				this.airport.getMap().addFlightInMap(sar, x, y,
						new Timestamp(this.airport.getTime().getTime() + (t * ti)),
						new Timestamp(this.airport.getTime().getTime() + ((t + 1) * ti)));
				y--;
				t++;
			}
		}
		this.airport.getMap().addFlightInMap(sar, x, y, new Timestamp(this.airport.getTime().getTime() + (t * ti)),
				new Timestamp(this.airport.getTime().getTime() + ((t + 1) * ti)));
		return d;
	}

	private void warnSAR(Flight f) {
		
		for (Flight sar : this.airport.getSar()) {
			if (!sar.getFlight_state().equalsIgnoreCase("On_Going")) {
				sar.setFlight_state("On_Going");
				sar.setDeparture_time(this.airport.getTime());
				sar.setDestination("Avion Estrellado");
				sar.setPlane_state("Correcto");
				sar.setPath(new Path("Avion Estrellado", f.getID(), sarPath(f, sar)));
				sar.setArrival_time(
						new Timestamp(sar.getDeparture_time().getTime() + sar.getPath().getDuration() * 60000));
				break;
			}
		}
	}

	public void randomCrash(int r) {
		if (10 - r <= 0 && !this.airport.getFligths().isEmpty()) {
			int ran = (int) (Math.random() * this.airport.getFligths().size()), i = 0;
			while (i < 50 && !this.airport.getFligths().get(ran).getFlight_state().equalsIgnoreCase("On_Going")
					&& !this.airport.getFligths().get(ran).getPlane_state().equalsIgnoreCase("Crashed")) {
				ran = (int) (Math.random() * this.airport.getFligths().size());
				i++;
			}
			if (i < 50)
				crashPlane(this.airport.getFligths().get(ran).getID());
		}
	}

	public void randomDelay(int r) {
		if (10 - r <= 0 && !this.airport.getFligths().isEmpty()) {
			int ran = (int) (Math.random() * this.airport.getFligths().size()), i = 0;
			while (i < 50 && !this.airport.getFligths().get(ran).getFlight_state().equalsIgnoreCase("On_Going")
					&& !this.airport.getFligths().get(ran).getPlane_state().equalsIgnoreCase("Crashed")) {
				ran = (int) (Math.random() * this.airport.getFligths().size());
				i++;
			}
			if (i < 50)
				delayPlane(this.airport.getFligths().get(ran).getID());
		}
	}

	public void searchPlane(String text) {
		this.sa.infoPlane(this.airport.getTime(), this.airport.getFligths(), text);
	}

	public void searchPath(String text) {
		this.sa.infoPath(this.airport.getMap(), this.airport.getTime(), this.airport.getFligths(), text);
	}

	public void crashPlane(String text) {
		if (this.sa.planeNotCrashed(text, this.airport.getFligths())) {
			Flight f = this.sa.planeCrashed(text, this.airport.getFligths());
			this.airport.getMap().freezeFlight(f, this.airport.getTime());
			this.warnSAR(f);
			this.airport.notifyAllO(new NotifyData(NTYPE.TOR_CRASH, f));
		}
	}

	public void delayPlane(String text) {
		Flight f = this.sa.planeDelayed(text, this.airport.getFligths());
		this.airport.getMap().delayFlight(f, this.airport.getTime(), this.sa.getDelay());
		this.airport.notifyAllO(new NotifyData(NTYPE.TOR_DELAY, f, this.sa.getDelay()));
	}

	public void addModels(DefaultTableModel tableModel, DefaultTableModel tableSar) {
		this.sa.setOnGoing(tableModel);
		this.sa.setSar(tableSar);
	}

	public void addAll() {
		this.sa.addFlights(this.airport.getFligths());
		this.sa.fillSar(this.airport.getSar());
	}

	public void setSA(atm_SA generaSAinf) {
		this.sa = generaSAinf;
	}

	public int getSliderDelay() {
		return sliderDelay;
	}

	public void setSliderDelay(int sliderDelay) {
		this.sliderDelay = sliderDelay;
	}

	public int getSliderCrash() {
		return sliderCrash;
	}

	public void setSliderCrash(int sliderCrash) {
		this.sliderCrash = sliderCrash;
	}
}
