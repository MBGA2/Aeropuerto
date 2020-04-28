package DAOs;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import Datos.Flight;
import Main.Aeropuerto;

public class inf_dao {
	private Aeropuerto aero;
	public static final long MIN = 60 * 1000; // in milli-seconds.

	public inf_dao(Aeropuerto aero) {
		this.aero = aero;
	}

	@SuppressWarnings("deprecation")
	public String parseDate(Timestamp date) {
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




	public void refreshDate(DefaultTableModel model, DefaultTableModel model2, Boolean flag)
			throws ClassNotFoundException, SQLException {
		// aux.add(Calendar.MINUTE, 1);

		if (flag) {
			model.setRowCount(0);
			model2.setRowCount(0);
			buscarVuelosTableModel(model, model2, this.aero.getFligths());
			model.fireTableDataChanged();
		}
	}


	public Object[] constructRow(Flight f) {
		Object[] fila = new Object[9];
		fila[0] = f.getDestination();
		fila[1] = parseDate(f.getDeparture_time());
		fila[2] = parseDate(f.getArrival_time());
		fila[3] = f.getGate();
		fila[4] = f.getID();
		fila[5] = parseDate(f.getBoarding_time());
		fila[6] = f.getCompany();
		fila[7] = f.getFlight_state();
		return fila;
	}

	public Object[] constructArrRow(Flight f) {
		Object[] fila = new Object[9];
		fila[0] = f.getSource();
		fila[1] = parseDate(f.getArrival_time());
		fila[2] = f.getID();
		fila[3] = f.getCompany();
		fila[4] = f.getFlight_state();
		return fila;
	}

	public void buscarVuelosTableModel(DefaultTableModel model, DefaultTableModel model2, List<Flight> l) {

		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).getSource().equalsIgnoreCase("Madrid")) {
				if (!this.aero.getFligths().get(i).getFlight_state().equalsIgnoreCase("On_Going")) {
					Object[] fila = constructRow(l.get(i));
					model.addRow(fila);
				}
			}
				 else {
				Object[] fila = constructArrRow(l.get(i));
				model2.addRow(fila);
			}
		}

	}

	public void StringVuelosTableModel(DefaultTableModel model, Object token, List<Flight> l) {
		for (int i = 0; i < l.size(); i++) {
			Boolean flag = false;
			Object[] fila = constructRow(l.get(i));
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

	public void StringArrVuelosTableModel(DefaultTableModel model, Object token, List<Flight> l) {
		for (int i = 0; i < l.size(); i++) {
			Boolean flag = false;
			Object[] fila = constructArrRow(l.get(i));
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
