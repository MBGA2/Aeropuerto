package DAOs;

import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


import Datos.Form;

public class adu_dao {
	
	
	
	public adu_dao() {
	}
	public Object[] constructRow(Form f) {
		Object[] fila = new Object[9];
		
		fila[0] = f.getidForm();
		fila[1] = f.getIdPlane();
		fila[2] =f.getNamePassenger();
		fila[3] = f.getContact();
		fila[4] = f.getiddestination();
		fila[5] = f.getWeight();
		fila[6] = f.getDescription();
		fila[7] = f.getfee();
		fila[8] = f.getState();
		
		
		return fila;
	}
	
	public void tableFFill(DefaultTableModel table, List<Form> form) {
		for (int i = 0; i < form.size(); i++) {
				Object[] fila = constructRow(form.get(i));
				table.addRow(fila);
			
		}
	}
	public void buscarFormTableModel(DefaultTableModel model, List<Form> l) {
		Collections.sort(l);
		for (int i = 0; i < l.size(); i++) {
			if (l.get(i).getState().equalsIgnoreCase("Aceptado")||
					l.get(i).getState().equalsIgnoreCase("Ignorado")|| 
					l.get(i).getState().equalsIgnoreCase("Denegado")) {
				
					Object[] fila = constructRow(l.get(i));
					model.addRow(fila);
				
				}
			}

		}
	public void showPopUp(String s) {

		JOptionPane.showConfirmDialog(null, s, "", JOptionPane.DEFAULT_OPTION);
	}
	
	
	}

