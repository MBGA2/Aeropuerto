package DAOs;

import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Datos.Flight;

public class atm_dao {


	static int TCASILLA = 30;

	public atm_dao() {
	}

	public void planeCrash(Flight f) {
		if (!f.getPlane_state().equalsIgnoreCase("Crashed"))
			f.setPlane_state("Crashed");
	}

	public void planeDelay(int d, Flight f) {
		if (!f.getPlane_state().equalsIgnoreCase("Crashed") && !f.getPlane_state().equalsIgnoreCase("Delayed")) {
			f.setPlane_state("Delayed");
			f.setRetarded_value(d);
		} else if (!f.getPlane_state().equalsIgnoreCase("Crashed"))
			f.setRetarded_value(d + f.getRetarded_value());
	}


	public void tableFill(DefaultTableModel table, List<Flight> fligths) {
		for (int i = 0; i < fligths.size(); i++) {
			if (fligths.get(i).getFlight_state().equalsIgnoreCase("On_Going")) {
				Object[] fila = constructRow(fligths.get(i));
				table.addRow(fila);
			}
		}
	}

	@SuppressWarnings("deprecation")
	private Object[] constructRow(Flight f) {
		Object[] fila = new Object[9];
		fila[0] = f.getID();
		fila[1] = f.getDestination();
		fila[2] = f.getDeparture_time().toGMTString();
		fila[3] = f.getArrival_time().toGMTString();

		fila[4] = f.getSource();
		fila[5] = f.getCompany();
		fila[6] = f.getPlane_state();
		if (f.getPlane_state().equalsIgnoreCase("Crashed")) {
			fila[7] = "Accidente";
			fila[8] = "";
		} else if (f.getPlane_state().equalsIgnoreCase("Damaged") || (f.getPlane_state().equalsIgnoreCase("Delayed"))) {
			fila[7] = "Retrasado";
			fila[8] = Integer.toString(f.getRetarded_value()) + " minutos";
		} else {
			fila[7] = "";
			fila[8] = "";
		}
		return fila;
	}

	public void tableSarFill(DefaultTableModel sarTable, List<Flight> sar) {
		for (int i = 0; i < sar.size(); i++) {
			Object[] fila = constructRowSar(sar.get(i));
			sarTable.addRow(fila);
		}
	}

	private Object[] constructRowSar(Flight f) {
		String aux;
		Object[] fila = new Object[9];
		fila[0] = f.getID();
		fila[1] = f.getCompany();
		if (!f.getFlight_state().equalsIgnoreCase("On_Going"))
			fila[2] = "A la espera";
		else if (f.getFlight_state().equalsIgnoreCase("On_Going")) {
			fila[2] = "En vuelo";
		}
		if (f.getFlight_state().equalsIgnoreCase("On_Going")) {
			fila[3] = f.getPath().getStopover();
			aux = "Sin retraso";
		} else {
			fila[3] = "-";
			aux = "En Tierra";
		}
		if (f.getPlane_state().equalsIgnoreCase("Crashed")) {
			fila[4] = "Accidente";
			fila[5] = aux;
		} else if (f.getPlane_state().equalsIgnoreCase("Damaged") || (f.getPlane_state().equalsIgnoreCase("Delayed"))) {
			fila[4] = "Retrasado";
			fila[5] = Integer.toString(f.getRetarded_value()) + " minutos";
		} else {
			fila[4] = "-";
			fila[5] = aux;
		}
		return fila;
	}

	public void showPopUp(String s) {
		JOptionPane.showConfirmDialog(null, s, "", JOptionPane.DEFAULT_OPTION);
	}

}
