package Datos.seg;

public class cam {
	private String id_cam;
	public void setId_cam(String id_cam) {
		this.id_cam = id_cam;
	}
	
	private camState camStat;
	private boolean onRepairs;
	
	public camState getCamStat() {
		return camStat;
	}

	public void setCamStat(camState camStat) {
		this.camStat = camStat;
	}

	public boolean isOnRepairs() {
		return onRepairs;
	}

	public void setOnRepairs(boolean onRepairs) {
		this.onRepairs = onRepairs;
	}

	public cam(String id_cam, camState camStat, boolean r) {
		//super();
		this.id_cam = id_cam;
		this.camStat = camStat;
		this.onRepairs = r;
	}
	
	public cam() {
	}

	public String getId_cam() {
		return id_cam;
	}
	public camState getState() {
		return camStat;
	}
	public void setState(camState camStat) {
		this.camStat = camStat;
	}
	/*
	public String toString()
	{
		
	}*/
	
	public String toString() {	
		return id_cam + " " + camStat + " " + onRepairs;
	}

	public boolean equals(cam c) {
		return this.id_cam.equals(c.id_cam) && this.onRepairs == c.onRepairs && this.camStat == c.camStat;
	}
}
