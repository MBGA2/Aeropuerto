
///

package Controladores;

import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import Datos.Form;
import Datos.genForm;
import Main.Aeropuerto;
//import Observer.Observer;
import SA.adu_SA;
import java.util.Random;

public class adu_controller {
	private Aeropuerto aero;
	private adu_SA SA;
	private Random rand;
	private genForm inForm;
	
	public adu_controller(Aeropuerto aero) {
		this.aero = aero;
		this.SA = new adu_SA();
		this.inForm = new genForm();
		this.rand = new Random();
		}
	public Aeropuerto getAero() {
		return aero;
	}

	public void setAero(Aeropuerto aero) {
		this.aero = aero;
	}


	public void addTables(DefaultTableModel model) {
		this.SA.setmodelForms(model);
	}

	public void addAll() {
		this.SA.addForms(this.aero.getForms());
	}

	
public void generateForm(int n) throws ClassNotFoundException, SQLException {
		

		int i = 0;
		while (i < n) {
			newForm();

			i++;
		}
	}

	private void newForm() throws ClassNotFoundException, SQLException {
		
		String passName = inForm.getNombre().get(rand.nextInt(inForm.getNombre().size()));
		String id_destination = inForm.getDestiny().get(rand.nextInt(inForm.getDestiny().size()));
		String description = inForm.getDescripcion().get(rand.nextInt(inForm.getDescripcion().size()));
		String contact = inForm.getContacto().get(rand.nextInt(inForm.getContacto().size()));
		
		
		int weight = inForm.randomN();
		double fee = weight * 2.3;
		Form f  = new Form();
		f.setidForm(inForm.randomID());
		
		f.setNamePassenger(passName);
		f.setIdplane(inForm.randomID());
		
		f.setidDestination(id_destination);
		f.setWeight(weight);
		
		f.setDescription(description);
		f.setfee(fee);
		f.setContact(contact);
		f.setState("Pendiente");
		
		this.aero.getForms().add(f);
		this.addAll();
	}
	public void changeStateAce(String text) {
		// TODO Auto-generated method stub
	Form bueno = null;
		for(Form aux: this.aero.getForms()) {
			if (aux.getidForm().equalsIgnoreCase(text)&& aux.getState().equalsIgnoreCase("Pendiente")) {
				
				bueno = aux;
				
				
				break;
			}
		}
		bueno.setState("Aceptado");
	}
	public void changeStateDen(String text) {
		// TODO Auto-generated method stub
	Form bueno = null;
		for(Form aux: this.aero.getForms()) {
			if (aux.getidForm().equalsIgnoreCase(text)&& aux.getState().equalsIgnoreCase("Pendiente")) {
				
				bueno = aux;
				
				
				break;
			}
		}
		bueno.setState("Denegado");
	}
	public void cout(String text) {
		this.SA.infoAdu(this.aero.getForms(), text);
		
	}
	}