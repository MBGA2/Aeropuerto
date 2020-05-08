
package DAOs;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.table.DefaultTableModel;

import Datos.Flight;
import Main.Aeropuerto;
import Main.conexion;
import Observer.Observer;
import Utils.NTYPE;
import Utils.NotifyData;


public class tor_dao implements Observer {
	
	private conexion c;
	private Aeropuerto aero;
	public static final long MIN = 60 * 1000;

	public tor_dao(Aeropuerto aero) {
		this.aero = aero;
	}

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
	
	

	public void searchFlights(DefaultTableModel model, String filled) {
		/*model.setRowCount(1);
		for (int i = 0; i < this.aero.getFligths().size(); i++) {

			Object[] fila = new Object[9];
			fila[0] = i;
			fila[1] = this.aero.getFligths().get(i).getDestination();
			fila[2] = parseDate(this.aero.getFligths().get(i).getDeparture_time());
			fila[3] = parseDate(this.aero.getFligths().get(i).getArrival_time());
			fila[4] = this.aero.getFligths().get(i).getGate();
			fila[5] = this.aero.getFligths().get(i).getNumVuelo();
			fila[6] = parseDate(this.aero.getFligths().get(i).getBoarding_time());
			fila[7] = this.aero.getFligths().get(i).getPlane().getCompany();
			fila[8] = this.aero.getFligths().get(i).getState().toString();

			if (fila[5].toString().toLowerCase().contains(filled.toLowerCase())) {
				

				model.addRow(fila);
			}
		}*/
	}
	
	public void assignMatricula(DefaultTableModel model, String filled, String matricula) {
	/*	int i = 0;
		boolean ok = false;
		model.setRowCount(1);
	
		Object[] fila = new Object[10];
		while( i < this.aero.getFligths().size() && !ok) {

			if (this.aero.getFligths().get(i).getNumVuelo().toString().toLowerCase().contains(filled.toLowerCase())) {
				
				this.aero.getFligths().get(i).getPlane().setId_plane(matricula);
				
				fila[0] = i;
				fila[1] = this.aero.getFligths().get(i).getDestination();
				fila[2] = parseDate(this.aero.getFligths().get(i).getDeparture_time());
				fila[3] = parseDate(this.aero.getFligths().get(i).getArrival_time());
				fila[4] = this.aero.getFligths().get(i).getGate();
				fila[5] = this.aero.getFligths().get(i).getNumVuelo();
				fila[6] = parseDate(this.aero.getFligths().get(i).getBoarding_time());
				fila[7] = this.aero.getFligths().get(i).getPlane().getCompany();
				fila[8] = this.aero.getFligths().get(i).getPlane().getId_plane();
				fila[9] = this.aero.getFligths().get(i).getState().toString();
														
				model.addRow(fila);
				ok = true;	
			}	
			i++;
		}*/
		
	}

	@Override
	public void update(NotifyData data)  {
		
		Flight fligthdelay  = new Flight();
		
		List<Flight> fligthstoDelay = new ArrayList<Flight>();
		int  delayMinutes ;
		
		if(data.getN() == NTYPE.TOR_DELAY) {
			fligthdelay  = (Flight) data.getData();
			delayMinutes = (int) data.getData2();	
			
			
			for (Flight vuelo : this.aero.getFligths()) {
				if( vuelo.getDeparture_time().after(fligthdelay.getDeparture_time()) &&  (fligthdelay.getGate() == vuelo.getGate() ) )		{
					fligthstoDelay.add(vuelo);
				}
			}
			
			for (Flight todelay :  fligthstoDelay ) {
				try {
					
						Timestamp newBoardingTime = new Timestamp (todelay.getBoarding_time().getTime() + delayMinutes);
						todelay.setBoarding_time(newBoardingTime);
						Timestamp newDEparturTime = new Timestamp (todelay.getDeparture_time().getTime() + delayMinutes);
						todelay.setDeparture_time(newDEparturTime);
						Timestamp newArrivalTime = new Timestamp (todelay.getArrival_time().getTime() + delayMinutes);
						todelay.setArrival_time(new Timestamp (todelay.getArrival_time().getTime() + delayMinutes));
						
						c = new conexion();
						String sql = "UPDATE Vuelos\r\n" + 
								"SET boarding_time = '"+ newBoardingTime +"'\r\n" + 
								"SET departure_time = '"+ newDEparturTime +"'\r\n" + 
								"SET arrival_time = '"+ newArrivalTime +"'\r\n" + 
								"where id like '" + todelay.getID() + "'";
						
						PreparedStatement ps = c.conectar().prepareStatement(sql);
						ps.execute();
						ps.close();
						c.desconectar();
						
					
				} catch (ClassNotFoundException | SQLException e) {
					System.out.println("Error de conexion en la BBDD");
				}
			}
		
		}
		
		
		
		
		
	}


}


