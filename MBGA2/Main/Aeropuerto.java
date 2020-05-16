package Main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Datos.Flight;
import Datos.GeneratePath;
import Datos.GeneratePlaneInfo;
import Datos.map.Mapm;
import Datos.seg.listCam;
import Observer.Observable;
import Vista.MainView;

public class Aeropuerto extends Observable{
	protected String nombre;
	protected MainView vista;
	private List<Flight> fligths;
	private GeneratePath path;
	private Timestamp time;
	private listCam camaras;
	private GeneratePlaneInfo gen;
	private Mapm map;
	public static final long HOUR = 3600 * 1000;
	public static final long MIN = 60 * 1000;

	public Aeropuerto(String nombre) {
		this.nombre = nombre;
		this.setFligths(new ArrayList<Flight>());
		this.camaras = new listCam();
		this.path = new GeneratePath();
		this.time = new Timestamp(System.currentTimeMillis());
		this.gen = new GeneratePlaneInfo();
		this.map = new Mapm();
	}
	public Timestamp getLastDep() {
		Timestamp t = null;
		for (int i = 0; i < this.fligths.size();i++) {
			if(this.fligths.get(i).getSource().equalsIgnoreCase("Madrid")) {
				t = this.fligths.get(i).getDeparture_time();
			}
		}
		return t;
	}
public Timestamp getLastArr() {
	Timestamp t = null;
	for (int i = 0; i < this.fligths.size();i++) {
		if(this.fligths.get(i).getDestination().equalsIgnoreCase("Madrid")) {
			t = this.fligths.get(i).getArrival_time();
		}
	}
	return t;
	}
	public GeneratePlaneInfo getGen() {
		return gen;
	}

	public void setGen(GeneratePlaneInfo gen) {
		this.gen = gen;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}



	public void setVista(MainView vista) {
		this.vista = vista;
	}

	public List<Flight> getFligths() {
		return fligths;
	}

	public MainView getVista() {
		return vista;
	}

	public void setFligths(List<Flight> fligths) {
		this.fligths = fligths;
	}
	public listCam getCamaras() {
		return camaras;
	}
	public Mapm getMap() {
		return map;
	}
	public GeneratePath getPath() {
		return path;
	}
	public void setPath(GeneratePath path) {
		this.path = path;
	}
}
