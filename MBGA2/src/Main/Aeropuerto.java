package Main;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import Datos.Flight;
import Datos.Form;
import Datos.GeneratePath;
import Datos.GeneratePlaneInfo;
import Datos.genForm;
import Datos.map.Mapm;
import Datos.seg.listCam;
import Observer.Observable;
import Vista.MainView;

public class Aeropuerto extends Observable {
	protected String nombre;
	protected MainView vista;
	private List<Flight> fligths;
	private List<Flight> sar;
	private GeneratePath path;
	private Timestamp time;
	private listCam camaras;
	private GeneratePlaneInfo gen;
	private Mapm map;
	private genForm genFor;
	private List<Form> forms;
	public Aeropuerto(String nombre) {
		this.nombre = nombre;
		this.setFligths(new ArrayList<Flight>());
		this.camaras = new listCam();
		this.path = new GeneratePath();
		this.time = new Timestamp(System.currentTimeMillis());
		this.gen = new GeneratePlaneInfo();
		this.map = new Mapm();
		this.setSar(this.setSAR());
		this.setForms(new ArrayList<Form>());
	}

	private ArrayList<Flight> setSAR() {
		ArrayList<Flight> aux = new ArrayList<Flight>();
		aux.add(new Flight("Madrid", "SAR", "ISSAR1-ES", "Waiting", this.getTime()));
		aux.add(new Flight("Madrid", "SAR", "ISSAR2-ES", "Waiting", this.getTime()));
		aux.add(new Flight("Madrid", "SAR", "FRSAR1-ES", "Waiting", this.getTime()));
		aux.add(new Flight("Madrid", "SAR", "GES5SS-ES", "Waiting", this.getTime()));
		aux.add(new Flight("Madrid", "SAR", "RSN77K-ES", "Waiting", this.getTime()));
		return aux;
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

	public void setMap() {
		this.map = new Mapm();
	}

	public GeneratePath getPath() {
		return path;
	}

	public void setPath(GeneratePath path) {
		this.path = path;
	}

	public List<Flight> getSar() {
		return sar;
	}

	public void setSar(List<Flight> sar) {
		this.sar = sar;
	}
	public genForm genForms() {
        return genFor;
    }
    public void setGenForm(genForm genForm) {
        this.genFor = genForm;
    }
    public List<Form> getForms() {
        return forms;
    }
    private void setForms(List<Form> forms) {
        this.forms = forms;
    }
}
