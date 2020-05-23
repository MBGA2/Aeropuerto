package DAOs;

import java.sql.Timestamp;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Datos.Flight;
import Datos.map.Mapm;
import Datos.map.NumberFlights;
import Vista.MainView;

public class map_dao {
	public map_dao() {
	}

	public void fill(DefaultTableModel f, Mapm m, Timestamp timestamp) {
		for (int i = 0; i < 14; i++) {
			f.addRow(constructRow(i, m, timestamp));

		}
	}

	public Object[] constructRow(int i, Mapm m, Timestamp timestamp) {
		Object[] fila = new Object[20];
		for (int j = 0; j < 20; j++) {
			if (m.getInfoMap().containsKey(j + "," + i)) {
				if (hasPlanes(m.getInfoMap().get(j + "," + i), timestamp)) {
					String s = imageType(m.getInfoMap().get(j + "," + i), timestamp);
					fila[j] = new ImageIcon(new ImageIcon(MainView.class.getResource(s)).getImage()
							.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));

				} else {
					fila[j] = null;
				}
			} else {
				fila[j] = null;
			}
		}
		return fila;
	}

	private String imageType(NumberFlights numberFlights, Timestamp timestamp) {
		String s = null;
		int numGoing = 0;
		int numComing = 0;
		List<Flight> f = numberFlights.fl(timestamp);
		for (int i = 0; i < f.size(); i++) {
			if (f.get(i).getSource().equalsIgnoreCase("Madrid")) {
				numGoing++;
			}
			if (f.get(i).getDestination().equalsIgnoreCase("Madrid")) {
				numComing++;
			}
		}
		if (numGoing == 0) {
			if (numComing == 1) {
				s = "/Iconos/coming.png";
			} else {
				s = "/Iconos/comingMul.png";
			}
		} else if (numGoing == 1) {
			if (numComing == 0) {
				s = "/Iconos/going.png";
			} else if (numComing == 1) {
				s = "/Iconos/both.png";
			} else {
				s = "/Iconos/bothMul2.png";
			}
		} else {
			if (numComing == 0) {
				s = "/Iconos/goingMul.png";
			} else if (numComing == 1) {
				s = "/Iconos/bothMul1.png";
			} else {
				s = "/Iconos/bothMul.png";
			}
		}
		return s;
	}

	private Boolean hasPlanes(NumberFlights value, Timestamp timestamp) {
		if (value.flightsOnTime(timestamp) > 0) {
			return true;
		}
		return false;
	}

	public void showPopUp(String s) {
		JOptionPane.showConfirmDialog(null, s, "", JOptionPane.DEFAULT_OPTION);
	}
}
