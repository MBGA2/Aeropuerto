
package DAOs;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import BD.conexionBD;
import Datos.Flight;
import Datos.NTYPE;
import Datos.NotifyData;
import Main.Aeropuerto;
import Observer.Observer;

public class tor_dao implements Observer {

	private conexionBD c;
	private Aeropuerto aero;
	public static final long MIN = 60 * 1000;
	private String[] gates = { "A1", "A2", "A3", "A4", "A5", "B1", "B2", "B3", "B4", "B5", "C1", "C2", "C3", "C4", "C5",
			"D1", "D2", "D3", "D4", "D5", "E1", "E2", "E3", "E4", "E5" };

	public tor_dao(Aeropuerto aero) {
		this.aero = aero;
		this.aero.addObserver(this);

	}

	@SuppressWarnings("deprecation")
	public String parseDate(Date date) {
		String h = Integer.toString(date.getHours());
		String m = Integer.toString(date.getMinutes());
		if (date.getHours() < 10) {
			h = "0" + date.getHours();
		}
		if (date.getMinutes() < 10) {
			m = "0" + date.getMinutes();
		}
		return h + ":" + m;
	}

	public void addGates() {
		c = new conexionBD();
		Boolean connected = true;
		try {
			if (c.conectar() == null) {
				connected = false;
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		for (Flight vuelo : this.aero.getFligths()) {

			String puerta = gates[(int) (Math.random() * (gates.length))];
			vuelo.setGate(puerta);
			try {
				if (connected) {
					String sql = "UPDATE vuelos\r\n" + "SET gate = '" + puerta + "'\r\n" + "where id_p like '"
							+ vuelo.getID() + "'";

					PreparedStatement ps;
					ps = c.conectar().prepareStatement(sql);
					ps.execute();
					ps.close();

				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public void update(NotifyData data) {

		Flight flightdelay = new Flight();
		int delayMinutes;

		if (data.getN() == NTYPE.TOR_DELAY) {
			flightdelay = (Flight) data.getData();
			delayMinutes = (int) data.getData2();

			for (Flight todelay : this.aero.getFligths()) {
				if (todelay.getDeparture_time().after(flightdelay.getDeparture_time())
						&& (flightdelay.getGate().equalsIgnoreCase(todelay.getGate()))) {
					try {

						todelay.setPlane_state("Delayed");
						Timestamp newBoardingTime = new Timestamp(todelay.getBoarding_time().getTime() + delayMinutes);
						todelay.setBoarding_time(newBoardingTime);
						Timestamp newDEparturTime = new Timestamp(todelay.getDeparture_time().getTime() + delayMinutes);
						todelay.setDeparture_time(newDEparturTime);
						Timestamp newArrivalTime = new Timestamp(todelay.getArrival_time().getTime() + delayMinutes);
						todelay.setArrival_time(new Timestamp(todelay.getArrival_time().getTime() + delayMinutes));

						c = new conexionBD();
						String sql = "UPDATE vuelos\r\n" + "SET boarding_time = '" + newBoardingTime + "'\r\n"
								+ "SET departure_time = '" + newDEparturTime + "'\r\n" + "SET arrival_time = '"
								+ newArrivalTime + "'\r\n" + "where id_p like '" + todelay.getID() + "'";

						PreparedStatement ps = c.conectar().prepareStatement(sql);
						ps.execute();
						ps.close();
						c.desconectar();
					} catch (ClassNotFoundException | SQLException e) {
						System.out.println("Error de conexion en la BBDD");

					}
				}
			}

		} else if (data.getN() == NTYPE.TOR_CRASH) {
			flightdelay = (Flight) data.getData();
			flightdelay.setPlane_state("Crashed");
		}

	}

	public void showdelayFlights(DefaultTableModel tableModel) {

		tableModel.setRowCount(1);

		Object[] fila = new Object[8];
		for (int i = 0; i < this.aero.getFligths().size(); i++) {

			if (this.aero.getFligths().get(i).getPlane_state().equalsIgnoreCase("Delayed")
					|| this.aero.getFligths().get(i).getPlane_state().equalsIgnoreCase("cancelled")|| this.aero.getFligths().get(i).getPlane_state().equalsIgnoreCase("Crashed")) {
				// "#", "Destino", "Hora Salida", "Hora Llegada", "Puerta", "ID", "Compania",
				// "Estado"

				fila[0] = i;
				fila[1] = this.aero.getFligths().get(i).getDestination();
				fila[2] = parseDate(this.aero.getFligths().get(i).getDeparture_time());
				fila[3] = parseDate(this.aero.getFligths().get(i).getArrival_time());
				fila[4] = this.aero.getFligths().get(i).getGate();
				fila[5] = this.aero.getFligths().get(i).getID();
				fila[6] = this.aero.getFligths().get(i).getCompany();
				fila[7] = this.aero.getFligths().get(i).getPlane_state();

				tableModel.addRow(fila);
			}

		}

	}

}