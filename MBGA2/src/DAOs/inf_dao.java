package DAOs;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Datos.Flight;

public class inf_dao {
	public static final long MIN = 60 * 1000; // in milli-seconds.

	public inf_dao() {
	}

	@SuppressWarnings("deprecation")
	private String parseDate(Timestamp date) {
		if (date == null) {
			return "";
		}
		String h = Integer.toString(date.getHours());
		String m = Integer.toString(date.getMinutes());
		if (date.getHours() < 10) {
			h = "0" + h;
		}
		if (date.getMinutes() < 10) {
			m = "0" + m;
		}

		return h + ":" + m;
	}

	private Object[] constructRow(Flight f) {
		Object[] fila = new Object[9];
		fila[0] = f.getDestination();
		fila[1] = parseDate(f.getDeparture_time());
		fila[2] = parseDate(f.getArrival_time());
		fila[3] = f.getGate();
		fila[4] = f.getID();
		fila[5] = parseDate(f.getBoarding_time());
		fila[6] = f.getCompany();
		fila[7] = f.getFlight_state();
		if (f.getPlane_state().equalsIgnoreCase("Delayed")) {
			fila[8] = "estimated "
					+ parseDate(new Timestamp(f.getDeparture_time().getTime() + f.getRetarded_value() * MIN));
		} else if (f.getPlane_state().equalsIgnoreCase("Crashed")) {
			fila[8] = "cancelled";
		} else {
			fila[8] = "";
		}
		return fila;
	}

	private Object[] constructArrRow(Flight f) {
		Object[] fila = new Object[9];
		fila[0] = f.getSource();
		fila[1] = parseDate(f.getArrival_time());
		fila[2] = f.getID();
		fila[3] = f.getCompany();
		fila[4] = f.getFlight_state();
		if (f.getPlane_state().equalsIgnoreCase("Delayed")) {
			fila[5] = "estimated "
					+ parseDate(new Timestamp(f.getArrival_time().getTime() + f.getRetarded_value() * MIN));
		} else if (f.getPlane_state().equalsIgnoreCase("Crashed")) {
			fila[5] = "cancelled";
		} else {
			fila[5] = "";
		}
		return fila;
	}

	public void buscarVuelosTableModel(DefaultTableModel model, DefaultTableModel model2, List<Flight> l) {
		Collections.sort(l);
		;
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).getSource().equalsIgnoreCase("Madrid")) {
				if (!l.get(i).getFlight_state().equalsIgnoreCase("On_Going")) {
					Object[] fila = constructRow(l.get(i));
					model.addRow(fila);
				}
			} else {
				Object[] fila = constructArrRow(l.get(i));
				model2.addRow(fila);
			}
		}

	}

	public void StringVuelosTableModel(DefaultTableModel model, Object token, List<Flight> l) {

		for (int i = 0; i < l.size(); i++) {
			Boolean flag = false;
			Object[] fila = constructRow(l.get(i));
			if (l.get(i).getSource().equalsIgnoreCase("Madrid")) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					if (fila[j].toString().toLowerCase().contains(token.toString().toLowerCase())) {
						flag = true;
					}
				}
				if (flag) {
					model.addRow(fila);
				}
			}
		}
	}

	public void StringArrVuelosTableModel(DefaultTableModel model, Object token, List<Flight> l) {
		for (int i = 0; i < l.size(); i++) {
			Boolean flag = false;
			Object[] fila = constructArrRow(l.get(i));
			if (l.get(i).getDestination().equalsIgnoreCase("Madrid")) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					if (fila[j].toString().toLowerCase().contains(token.toString().toLowerCase())) {
						flag = true;
					}
				}
				if (flag) {
					model.addRow(fila);
				}
			}
		}
	}

}
