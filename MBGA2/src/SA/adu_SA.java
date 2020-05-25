package SA;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import DAOs.adu_dao;
import Datos.Form;

public class adu_SA {
	private adu_dao dao;
	private DefaultTableModel modelForms;
	
	public adu_SA() {
		this.dao = new adu_dao();
	}
public void setmodelForms(DefaultTableModel modelForms) {
		this.modelForms = modelForms;
	}
	public void addForms(List<Form> list) {
		
		this.modelForms.setRowCount(0);
		this.dao.tableFFill(this.modelForms, list);
		this.modelForms.fireTableDataChanged();
		
	}
	
	public void searchForms(String search, List<Form> list) {
		modelForms.setRowCount(0);
		dao.buscarFormTableModel(modelForms, list);
		modelForms.fireTableDataChanged();
	
	}

	

	public DefaultTableModel getmodelForms() {
		return modelForms;
	}

	
	public void infoAdu( List<Form> form, String f) {
		String s = "";
		Form faux = null;
		for (Form aux : form) 
			if (aux.getidForm().equalsIgnoreCase(f)) {
				faux = aux;
				break;
			}
		s = "Formulario numero : " + faux.getidForm() + " del pasajero:  " + faux.getNamePassenger() + ".\n"; 
		
		if (faux.getState().equalsIgnoreCase("Pendiente")) {
			s = s + "Estado de la peticion: Pendiente de revision.\n";
			s = s + "Se le notificara al siguiente correo" + faux.getContact() + "\n";
			s = s + "Datos de la peticion:\n";
			s = s + "\n";
			s = s + "Destino del paquete: " + faux.getiddestination() + " en el vuelo: " + faux.getIdPlane() + ".\n";
			s = s + "Descripcion del paquete: " + faux.getDescription() + ".\n";
			s = s + " Peso : " + faux.getWeight() + "Kg.\n";
			s = s + "El impuesto de envio son: " + faux.getfee() + "€\n";
			
			
		}
		else if (faux.getState().equalsIgnoreCase("Aceptado")) {
			
			s = s + "Estado de la peticion: Aceptada.\n";
			s = s + "Se le notificara al siguiente correo" + faux.getContact() + "\n";
			s = s + "Datos de la peticion:\n";
			s = s + "\n";
			s = s + "Destino del paquete: " + faux.getiddestination() + " en el vuelo: " + faux.getIdPlane() + ".\n";
			s = s + "Descripcion del paquete: " + faux.getDescription() + ".\n";
			s = s + " Peso : " + faux.getWeight() + "Kg.\n";
			s = s + "El impuesto de envio son: " + faux.getfee() + "€\n";
			
		}else if(faux.getState().equalsIgnoreCase("Denegado")){
			s = s + "Estado de la peticion: Denegada. \n";
			s = s + "Se le notificara al siguiente correo" + faux.getContact() + "\n";
			s = s + "Datos de la peticion:\n";
			s = s + "\n";
			s = s + "Destino del paquete: " + faux.getiddestination() + " en el vuelo: " + faux.getIdPlane() + ".\n";
			s = s + "Descripcion del paquete: " + faux.getDescription() + ".\n";
			s = s + " Peso : " + faux.getWeight() + "Kg.\n";
			s = s + "El impuesto de envio son: " + faux.getfee() + "€\n";
		}
		this.dao.showPopUp(s);
	}
	


}
