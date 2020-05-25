package Datos;

import Observer.Observable;

public class Form extends Observable implements Comparable<Form> {
	
	private String id_form;
	//quitar id
	//private String id_passenger;
	private String id_plane;
	
	private String id_destination;
	
	private int weight;
	private String description;
	private double fee;
	//quitar reason
	//private String reason;
	private String passName;
	private String contact;
	
	private String state;
	
	public Form() {
	}

	public Form(String id_form, String id_plane, String id_citydeparture, int weight, String description, double fee, String state, String passName, String contact) {
		this.id_form = id_form;
		this.id_plane = id_plane;
		//this.id_passenger = id_passenger;
		this.id_destination = id_citydeparture;
		this.weight = weight;
		this.description = description;
		this.fee = fee;
		this.state = state;
		this.contact = contact;
		this.passName = passName;
		//this.reason = reason;
	}


public String getidForm() {
	return id_form;
}

public void setidForm(String id_form) {
	this.id_form = id_form;
}

public String getIdPlane() {
	return id_plane;
}
public void setIdplane(String id_plane) {
	this.id_plane = id_plane;
}

public String getiddestination() {
	return id_destination;
}

public void setidDestination(String id_destination) {
	this.id_destination = id_destination;
}

public int getWeight() {
	return weight;
}

public void setWeight(int weight) {
	this.weight = weight;
}
public String getDescription() {
	return description;
}

public void setDescription(String description) {
	this.description = description;
}
public double getfee() {
	return fee;
}

public void setfee(double fee) {
	this.fee = fee;
}
public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}

@Override
public int compareTo(Form o) {
    // TODO Auto-generated method stub
    if (this.getState().equalsIgnoreCase("Pendiente") && (o.getState().equalsIgnoreCase("Aceptado") || o.getState().equalsIgnoreCase("Denegado"))) {
        return 1;
    }
    else
        return -1;
    }



public String getContact() {
	return contact;
}

public String getNamePassenger() {
	return passName;
}


public void setContact(String contact) {
	this.contact =  contact;
}

public void setNamePassenger(String passName) {
	this.passName = passName;
}

}


