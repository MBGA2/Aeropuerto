package DAOs;

import java.sql.Timestamp;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Datos.map.Mapm;
import Datos.map.NumberFlights;
import Vista.MainView;

public class map_dao {
	public map_dao() {
	}
	public void fill(DefaultTableModel f, Mapm m, Timestamp timestamp) {
		for (int i = 0; i < 14; i++) {
			f.addRow(constructRow(i,m,timestamp));

		}
	}
	public Object[] constructRow(int i, Mapm m, Timestamp timestamp) {
		Object[] fila = new Object[20];
		for (int j = 0; j < 20; j++) {
			if(m.getInfoMap().containsKey(j+","+i)) {
				if(hasPlanes(m.getInfoMap().get(j+","+i),timestamp)) {
					String s;
					if(m.getInfoMap().get(j+","+i).flightsOnTime(timestamp)>1) {
						s = "/Iconos/goingMul.png";
					}
					else {
						s = "/Iconos/going.png";
					}
					fila[j] = new ImageIcon( new ImageIcon(MainView.class.getResource(s)).getImage().getScaledInstance( 50, 50,  java.awt.Image.SCALE_SMOOTH ));	

	            }
				else {
					fila[j] = null;
				}
			}
			else {
				fila[j] = null;
			}
		}
		return fila;
	}
	private Boolean hasPlanes(NumberFlights value, Timestamp timestamp) {
		if (value.flightsOnTime(timestamp) > 0) {
		return true;
		}
		return false;
	}
	public void showPopUp(String s) {
		JOptionPane.showConfirmDialog(null,
                s, "", JOptionPane.DEFAULT_OPTION);
	}
}
