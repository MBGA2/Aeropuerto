package Datos;

public class Plane {
	
	private String id_plane;
	private PlaneState state;
	private String company;
	
	public Plane(String company, String planeID) {
		this.company=company;
		this.id_plane = planeID;
		this.state = PlaneState.Waiting;
	}
	public String getId_plane() {
		return id_plane;
	}
	public void setId_plane(String id_plane) {
		this.id_plane = id_plane;
	}
	public PlaneState getState() {
		return state;
	}
	public void setState(PlaneState state) {
		this.state = state;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	
	
}
