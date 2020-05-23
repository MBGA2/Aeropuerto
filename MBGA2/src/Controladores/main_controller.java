package Controladores;

import java.sql.*;
import java.util.Random;
import javax.swing.JOptionPane;
import BD.conexionBD;
import Datos.*;
import Main.Aeropuerto;
import Observer.Observer;
import Utils.NotifyData;

public class main_controller implements Observer {

	private Aeropuerto aero;
	private adu_controller adu;
	private atm_controller atm;
	private inf_controller inf;
	private tor_controller tor;
	private seg_controller seg;
	private map_controller map;
	private Timestamp calendar;
	private Timestamp first;
	private Random rand;
	private conexionBD c;
	private GeneratePlaneInfo info;
	private Boolean connected;
	public static final long HOUR = 3600 * 1000;
	public static final long MIN = 60 * 1000;

	public main_controller(Aeropuerto aero) throws ClassNotFoundException {
		this.first = aero.getTime();
		info = aero.getGen();
		rand = new Random();
		c = new conexionBD();
		if (c.conectar() == null) {
			connected = false;
			JOptionPane.showConfirmDialog(null, "No estas conectado a la BD, los resultados no se guardaran", "",
					JOptionPane.DEFAULT_OPTION);
		} else {
			connected = true;
		}
		this.aero = aero;
		this.seg = new seg_controller(this.aero);
		this.atm = new atm_controller(this.aero);
		this.adu = new adu_controller(this.aero);
		this.inf = new inf_controller(this.aero);
		this.tor = new tor_controller(this.aero);
		this.map = new map_controller(this.aero);

	}

	public void init() throws ClassNotFoundException, SQLException {
		seg.init();
		if (connected) {
			readFlightFromDatabase();
		}
	}

	public void generateFlight(int n) throws ClassNotFoundException, SQLException {
		this.calendar = this.aero.getTime();

		int i = 0;
		while (i < n) {
			newFligthDepartures();
			newFligthArrivals();

			calendar = new Timestamp(calendar.getTime() + rand.nextInt(20) * MIN); //
			i++;
		}
	}

	private void readFlightFromDatabase() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM vuelos order by departure_time";
		PreparedStatement ps = c.conectar().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Flight f = new Flight();
			f.setArrival_time(rs.getTimestamp("arrival_time"));
			f.setBoarding_time(rs.getTimestamp("boarding_time"));
			f.setSource(rs.getString("source"));
			f.setDeparture_time(rs.getTimestamp("departure_time"));
			f.setDestination(rs.getString("destination"));
			f.setGate(rs.getString("gate"));
			f.setFlight_state(rs.getString("flight_state"));
			f.setPlane_state(rs.getString("plane_state"));
			f.setID(rs.getString("id_p"));
			f.setCompany(rs.getString("company"));
			f.setRealDate(this.first);
			if (f.getGate() == null) {
				f.setGate("No_gate");
			}
			Path p = calculatePath(f.getSource(), f.getDestination(), f.getDeparture_time());
			f.setPath(p);
			this.aero.getFligths().add(f);
			this.map.fillMap(f);
		}
		ps.close();
		ps = null;
		c.desconectar();

	}

	public void newFligthDepartures() throws ClassNotFoundException, SQLException {
		String destination = info.getCapitals().get(rand.nextInt(info.getCapitals().size()));
		Timestamp departure_time = calendar;
		Timestamp boarding_time = new Timestamp(departure_time.getTime() - (rand.nextInt(15) + 15) * MIN);
		Path p = calculatePath("Madrid", destination, departure_time);
		Timestamp arrival_time = new Timestamp(departure_time.getTime() + p.getDuration() * MIN);
		String company = info.getCompanies().get(rand.nextInt(info.getCompanies().size()));
		// calendar = new Timestamp(calendar.getTime() + rand.nextInt(5) * MIN);
		if (connected) {
			String sql = "insert into vuelos(destination,source,departure_time,arrival_time,company,boarding_time,id_p,flight_state,plane_state) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = c.conectar().prepareStatement(sql);
			ps.setString(1, destination);
			ps.setString(2, "Madrid");
			ps.setTimestamp(3, departure_time);
			ps.setTimestamp(4, arrival_time);
			ps.setString(5, company);
			ps.setTimestamp(6, boarding_time);
			ps.setString(7, info.randomID());
			ps.setString(8, "Esperando");
			ps.setString(9, "Correcto");
			ps.execute();
			ps.close();
			c.desconectar();
		}
		Flight f = new Flight();
		f.setArrival_time(arrival_time);
		f.setBoarding_time(boarding_time);
		f.setSource("Madrid");
		f.setDeparture_time(departure_time);
		f.setDestination(destination);
		f.setID(info.randomID());
		f.setCompany(company);
		f.setRealDate(this.first);
		f.setPath(p);
		f.setGate("No_gate");
		f.setFlight_state("Esperando");
		f.setPlane_state("Correcto");
		this.aero.getFligths().add(f);
		this.map.fillMap(f);
	}

	public void newFligthArrivals() throws ClassNotFoundException, SQLException {
		String source = info.getCapitals().get(rand.nextInt(info.getCapitals().size()));
		Timestamp departure_time = calendar;
		Timestamp boarding_time = new Timestamp(departure_time.getTime() - (rand.nextInt(15) + 15) * MIN);
		Path p = calculatePath(source, "Madrid", departure_time);
		Timestamp arrival_time = new Timestamp(departure_time.getTime() + p.getDuration() * MIN);
		String company = info.getCompanies().get(rand.nextInt(info.getCompanies().size()));
		if (connected) {
			String sql = "insert into vuelos(destination,source,departure_time,arrival_time,company,boarding_time,id_p,flight_state,plane_state) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = c.conectar().prepareStatement(sql);
			ps.setString(1, "Madrid");
			ps.setString(2, source);
			ps.setTimestamp(3, departure_time);
			ps.setTimestamp(4, arrival_time);
			ps.setString(5, company);
			ps.setTimestamp(6, boarding_time);
			ps.setString(7, info.randomID());
			ps.setString(8, "Esperando");
			ps.setString(9, "Correcto");

			ps.execute();
			ps.close();
			c.desconectar();
		}
		Flight f = new Flight();
		f.setArrival_time(arrival_time);
		f.setBoarding_time(boarding_time);
		f.setSource(source);
		f.setDeparture_time(departure_time);
		f.setDestination("Madrid");
		f.setID(info.randomID());
		f.setCompany(company);
		f.setRealDate(this.first);
		f.setPath(p);
		f.setGate("No_gate");
		f.setFlight_state("Esperando");
		f.setPlane_state("Correcto");
		this.aero.getFligths().add(f);
		this.map.fillMap(f);
	}

	public void add1Flight(Boolean going) throws ClassNotFoundException, SQLException {

		String destination = info.getCapitals().get(rand.nextInt(info.getCapitals().size()));
		String source = "Madrid";
		Timestamp departure_time = new Timestamp(this.aero.getTime().getTime() + (rand.nextInt(20)+60) * MIN);
		Timestamp boarding_time = new Timestamp(departure_time.getTime() - (rand.nextInt(15) + 15) * MIN);
		Path p = calculatePath("Madrid", destination, departure_time);
		Timestamp arrival_time = new Timestamp(departure_time.getTime() + p.getDuration() * MIN);
		if (!going) {
			destination = "Madrid";
			source = info.getCapitals().get(rand.nextInt(info.getCapitals().size()));
			departure_time = new Timestamp(this.aero.getTime().getTime() + (rand.nextInt(20)+60) * MIN);
			boarding_time = new Timestamp(departure_time.getTime() - (rand.nextInt(15) + 15) * MIN);
			p = calculatePath(source, destination, departure_time);
			arrival_time = new Timestamp(departure_time.getTime() + p.getDuration() * MIN);
		}
		String company = info.getCompanies().get(rand.nextInt(info.getCompanies().size()));
		String ID = info.randomID();
		if (connected) {
			String sql = "insert into vuelos(destination,source,departure_time,arrival_time,company,boarding_time,id_p,flight_state,plane_state) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = c.conectar().prepareStatement(sql);
			ps.setString(1, destination);
			ps.setString(2, source);
			ps.setTimestamp(3, departure_time);
			ps.setTimestamp(4, arrival_time);
			ps.setString(5, company);
			ps.setTimestamp(6, boarding_time);
			ps.setString(7, ID);
			ps.setString(8, "Esperando");
			ps.setString(9, "Correcto");
			ps.execute();
			ps.close();
			c.desconectar();
		}
		Flight f = new Flight();
		f.setArrival_time(arrival_time);
		f.setBoarding_time(boarding_time);
		f.setSource(source);
		f.setDeparture_time(departure_time);
		f.setDestination(destination);
		f.setGate("NO_GATE");
		f.setFlight_state("Esperando");
		f.setPlane_state("Correcto");
		f.setID(ID);
		f.setCompany(company);
		f.setPath(p);
		f.setRealDate(this.aero.getTime());
		this.aero.getFligths().add(f);
		this.map.fillMap(f);
	}

	public void check() throws ClassNotFoundException, SQLException {
		checkCrashing();
		for (int i = 0; i < this.aero.getFligths().size(); i++) {
			this.aero.getFligths().get(i).setRealDate(aero.getTime());
			String fAux = this.aero.getFligths().get(i).getFlight_state();
			String newF = this.aero.getFligths().get(i).checkState();
			if (!newF.equalsIgnoreCase(fAux)) {
				changeFlightState(newF, this.aero.getFligths().get(i).getID());
			}

			if (this.aero.getFligths().get(i).getSource().equalsIgnoreCase("Madrid")) {
				if (this.aero.getFligths().get(i).getArrival_time().before(this.aero.getTime())) {
					removeFromDateBase(this.aero.getFligths().get(i).getID());
					add1Flight(true);
					this.aero.getFligths().remove(i);
				}
			}
			if (this.aero.getFligths().get(i).getFlight_state().equalsIgnoreCase("Storing")) {
				Timestamp t = new Timestamp(this.aero.getFligths().get(i).getArrival_time().getTime() + 15 * MIN);
				if (t.before(this.aero.getTime())) {
					removeFromDateBase(this.aero.getFligths().get(i).getID());
					add1Flight(false);
					this.aero.getFligths().remove(i);
					
				}
			}
		}
	}

	public void checkCrashing() throws ClassNotFoundException, SQLException {
		String id = null;
		id = this.map.rescued();
		if (id != null) {
			for (int i = 0; i < this.aero.getFligths().size(); i++) {
				if (id.equalsIgnoreCase(this.aero.getFligths().get(i).getID())) {
					removeFromDateBase(id);
					this.aero.getFligths().remove(i);
				}
			}
		}
	}

	private void changeFlightState(String newF, String id) throws ClassNotFoundException, SQLException {
		if (connected) {
			String sql = "UPDATE Vuelos\r\n" + "SET Flight_state = '" + newF + "'\r\n" + "where id_p like '" + id + "'";
			PreparedStatement ps = c.conectar().prepareStatement(sql);
			ps.execute();
			ps.close();
			c.desconectar();
		}
	}

	private void removeFromDateBase(String id) throws ClassNotFoundException, SQLException {
		if (connected) {
			String sql = "delete from vuelos where id_p like '" + id + "'";
			PreparedStatement ps = c.conectar().prepareStatement(sql);
			ps.execute();
			ps.close();
			c.desconectar();
		}
	}

	public void deleteAll() throws ClassNotFoundException, SQLException {
		if (connected) {
			String sql = "delete from vuelos";
			PreparedStatement ps = c.conectar().prepareStatement(sql);
			ps.execute();
			ps.close();
			c.desconectar();
		}
		this.aero.getFligths().clear();
		this.aero.setMap();
	}

	private Path calculatePath(String source, String dest, Timestamp ini) {

		String stop = "none";
		int n = 0;

		InfoCity destInfo = null;

		for (InfoCity cityDest : this.aero.getPath().getCities())
			if (cityDest.getName().equalsIgnoreCase(dest))
				destInfo = cityDest;
		for (InfoCity citySource : this.aero.getPath().getCities())
			if (source.equalsIgnoreCase(citySource.getName())) {
				if (this.aero.getPath().getDirect().get(source).contains(dest)) {
					stop = "Direct";
					n = Math.abs(citySource.getPosX() - destInfo.getPosX())
							+ Math.abs(citySource.getPosY() - destInfo.getPosY());
				} else if (!dest.equalsIgnoreCase("Reikiavik") || !dest.equalsIgnoreCase("Oslo")) {
					int dist = 0;
					for (InfoCity cityStop : this.aero.getPath().getCities()) {
						if (this.aero.getPath().getDirect().get(source).contains(cityStop.getName())
								&& (Math.abs(cityStop.getPosX())) > dist) {
							dist = cityStop.getPosX();
							stop = cityStop.getName();
							n = Math.abs(citySource.getPosX() - cityStop.getPosX())
									+ Math.abs((citySource.getPosY() - cityStop.getPosY()))
									+ Math.abs(cityStop.getPosX() - destInfo.getPosX())
									+ Math.abs((cityStop.getPosY() - destInfo.getPosY()));
						}
					}
				} else {
					int dist = 0;
					for (InfoCity cityStop : this.aero.getPath().getCities()) {
						if (this.aero.getPath().getDirect().get(source).contains(cityStop.getName())
								&& (Math.abs(cityStop.getPosY())) > dist) {
							dist = cityStop.getPosY();
							stop = cityStop.getName();
							n = Math.abs(citySource.getPosX() - cityStop.getPosX())
									+ Math.abs((citySource.getPosY() - cityStop.getPosY()))
									+ Math.abs(cityStop.getPosX() - destInfo.getPosX())
									+ Math.abs((cityStop.getPosY() - destInfo.getPosY()));
						}
					}
				}
			}
		return new Path(dest, stop, 30 * n);
	}

	@Override
	public void update(NotifyData n) {
		switch (n.getN()) {

		case REFRESH:
			try {
				check();
				this.inf.addAll();
				this.atm.addAll();
				this.map.refresh();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	public static void quit() {
		System.exit(0);
	}

	public Aeropuerto getAeropuerto() {
		return aero;
	}

	public adu_controller getAdu() {
		return adu;
	}

	public void setAdu(adu_controller adu) {
		this.adu = adu;
	}

	public atm_controller getAtm() {
		return atm;
	}

	public void setAtm(atm_controller atm) {
		this.atm = atm;
	}

	public inf_controller getInf() {
		return inf;
	}

	public void setInf(inf_controller inf) {
		this.inf = inf;
	}

	public tor_controller getTor() {
		return tor;
	}

	public void setTor(tor_controller tor) {
		this.tor = tor;
	}

	public seg_controller getSeg() {
		return seg;
	}

	public void setSeg(seg_controller seg) {
		this.seg = seg;
	}

	public void addModelObserver(Observer o) {
		aero.addObserver(o);
	}

	public map_controller getMap() {
		return this.map;
	}

}
