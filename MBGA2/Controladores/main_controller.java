package Controladores;

import java.sql.*;
import java.util.Random;

import Datos.Flight;
import Main.Aeropuerto;
import Main.conexion;
import Observer.Observer;
import Utils.GeneratePlaneInfo;
import Utils.NotifyData;

public class main_controller implements Observer{

	private Aeropuerto aero;
	private adu_controller adu;
	private atm_controller atm;
	private inf_controller inf;
	private tor_controller tor;
	private seg_controller seg;
	private Timestamp calendar;
	private Timestamp first;
	private Random rand;
	private conexion c;
	private GeneratePlaneInfo info;
	public static final long HOUR = 3600 * 1000;
	public static final long MIN = 60 * 1000;

	public main_controller(Aeropuerto aero) {
		this.first = aero.getTime();
		info = aero.getGen();
		rand = new Random();
		c = new conexion();
		this.aero = aero;
		this.seg = new seg_controller(this.aero);
		this.atm = new atm_controller(this.aero);
		this.adu = new adu_controller(this.aero);
		this.inf = new inf_controller(this.aero);
		this.tor = new tor_controller(this.aero);

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

	public void init() throws ClassNotFoundException, SQLException {
		readFlight();
		seg.loadData();

	}

	
	public void generateFlight(int n) throws ClassNotFoundException, SQLException {
		this.calendar = this.aero.getTime();
		
		int i = 0;	
		while (i < n) {
			newFligth();
			newFligthArr();

			calendar = new Timestamp(calendar.getTime() + rand.nextInt(20) * MIN); //
			//calendar = new Timestamp(calendar.getTime() + rand.nextInt(20000));
			i++;
		}	
		readFlight();
	}

	private void readFlight() throws ClassNotFoundException, SQLException {
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
			if (f.getFlight_state() == null) {
				f.setFlight_state("Esperando");
			}
			if (f.getPlane_state() == null) {
				f.setPlane_state("Correcto");
			}
			this.aero.getFligths().add(f);
		}
		ps.close();
		ps = null;
		c.desconectar();

	}

	public void newFligth() throws ClassNotFoundException, SQLException {
		String destination = info.getCapitals().get(rand.nextInt(info.getCapitals().size()));
		Timestamp departure_time = calendar;
		Timestamp boarding_time = new Timestamp(departure_time.getTime() - (rand.nextInt(15) + 15) * MIN);
		Timestamp arrival_time = new Timestamp(calendar.getTime() + path(destination));
		String company = info.getCompanies().get(rand.nextInt(info.getCompanies().size()));
		//calendar = new Timestamp(calendar.getTime() + rand.nextInt(5) * MIN);
		String sql = "insert into vuelos(destination,source,departure_time,arrival_time,company,boarding_time,id_p) values(?,?,?,?,?,?,?)";
		PreparedStatement ps = c.conectar().prepareStatement(sql);
		ps.setString(1, destination);
		ps.setString(2, "Madrid");
		ps.setTimestamp(3, departure_time);
		ps.setTimestamp(4, arrival_time);
		ps.setString(5, company);
		ps.setTimestamp(6, boarding_time);
		ps.setString(7, info.randomID());
		ps.execute();
		ps.close();
		c.desconectar();

	}

	public void newFligthArr() throws ClassNotFoundException, SQLException {
		String source = info.getCapitals().get(rand.nextInt(info.getCapitals().size()));
		Timestamp departure_time = new Timestamp(calendar.getTime() - path(source));
		Timestamp boarding_time = new Timestamp(departure_time.getTime() - (rand.nextInt(15) + 15) * MIN);
		Timestamp arrival_time = calendar;
		String company = info.getCompanies().get(rand.nextInt(info.getCompanies().size()));
		String sql = "insert into vuelos(destination,source,departure_time,arrival_time,company,boarding_time,id_p) values(?,?,?,?,?,?,?)";
		PreparedStatement ps = c.conectar().prepareStatement(sql);
		ps.setString(1, "Madrid");
		ps.setString(2, source);
		ps.setTimestamp(3, departure_time);
		ps.setTimestamp(4, arrival_time);
		ps.setString(5, company);
		ps.setTimestamp(6, boarding_time);
		ps.setString(7, info.randomID());
		ps.execute();
		ps.close();
		c.desconectar();
	}

	public void add1Flight(Boolean going) throws ClassNotFoundException, SQLException {

		String destination = info.getCapitals().get(rand.nextInt(info.getCapitals().size()));
		String source = "Madrid";
		Timestamp departure_time = new Timestamp(this.aero.getLastDep().getTime()+ rand.nextInt(20) * MIN);
		Timestamp boarding_time = new Timestamp(departure_time.getTime() - (rand.nextInt(15) + 15) * MIN);
		Timestamp arrival_time = new Timestamp(departure_time.getTime() + path(destination));
		if (!going) {
			destination = "Madrid";
			source = info.getCapitals().get(rand.nextInt(info.getCapitals().size()));
			arrival_time = new Timestamp(this.aero.getLastArr().getTime()+ rand.nextInt(20) * MIN);
			departure_time = new Timestamp(arrival_time.getTime() - path(source));
			boarding_time = new Timestamp(departure_time.getTime() - (rand.nextInt(15) + 15) * MIN);
		}
		String company = info.getCompanies().get(rand.nextInt(info.getCompanies().size()));
		String ID =  info.randomID();
		String sql = "insert into vuelos(destination,source,departure_time,arrival_time,company,boarding_time,id_p) values(?,?,?,?,?,?,?)";
		PreparedStatement ps = c.conectar().prepareStatement(sql);
		ps.setString(1, destination);
		ps.setString(2, source);
		ps.setTimestamp(3, departure_time);
		ps.setTimestamp(4, arrival_time);
		ps.setString(5, company);
		ps.setTimestamp(6, boarding_time);
		ps.setString(7, ID);
		ps.execute();
		ps.close();
		c.desconectar();
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
		f.setRealDate(this.first);
		this.aero.getFligths().add(f);
	}
	
	public int path(String dest) {
		for (int i = 0; i < this.info.getPaths().size(); i++) {
			if (this.info.getPaths().get(i).getDestination().equalsIgnoreCase(dest)) {
				return this.info.getPaths().get(i).getDuration();
			}
		}
		return 0;
	}

	public void check() throws ClassNotFoundException, SQLException {
		for (int i = 0; i < this.aero.getFligths().size(); i++) {
			this.aero.getFligths().get(i).setRealDate(aero.getTime());
			String fAux = this.aero.getFligths().get(i).getFlight_state();
			String newF = this.aero.getFligths().get(i).checkState();
			if(!newF.equalsIgnoreCase(fAux)) {
				changeFlightState(newF,this.aero.getFligths().get(i).getID());
			}
			
			if(this.aero.getFligths().get(i).getSource().equalsIgnoreCase("Madrid")) {
				if(this.aero.getFligths().get(i).getArrival_time().before(this.aero.getTime())){
					removeFromDateBase(this.aero.getFligths().get(i).getID());
					this.aero.getFligths().remove(i);
					add1Flight(true);
				}
			}
			if (this.aero.getFligths().get(i).getFlight_state().equalsIgnoreCase("Storing")) {
				Timestamp t = new Timestamp(this.aero.getFligths().get(i).getArrival_time().getTime() + 15*MIN);
				if (t.after(this.aero.getTime())) {
					removeFromDateBase(this.aero.getFligths().get(i).getID());
					this.aero.getFligths().remove(i);
					add1Flight(false);
				}
			}
		}
	}
	public void changeFlightState(String newF,String id) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Vuelos\r\n" + 
				"SET Flight_state = '"+newF+"'\r\n" + 
				"where id_p like '" + id + "'";
		PreparedStatement ps = c.conectar().prepareStatement(sql);
		ps.execute();
		ps.close();
		c.desconectar();
		
	}

	public void removeFromDateBase(String id) throws ClassNotFoundException, SQLException {
		String sql = "delete from vuelos where id_p like '" + id + "'";
		PreparedStatement ps = c.conectar().prepareStatement(sql);
		ps.execute();
		ps.close();
		c.desconectar();
		
	}

	public void deleteAll() throws ClassNotFoundException, SQLException {
		String sql = "delete from vuelos";
		PreparedStatement ps = c.conectar().prepareStatement(sql);
		ps.execute();
		ps.close();
		c.desconectar();
		this.aero.getFligths().clear();
	}

	@Override
	public void update(NotifyData n) {
		switch (n.getN()) {
		
		case REFRESH:
			try {
				check();
				this.inf.addAll();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	

}
