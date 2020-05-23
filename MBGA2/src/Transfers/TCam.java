package Transfers;

import Datos.seg.camState;

public class TCam implements Transfer {

	public TCam(String id_cam, camState camStat, boolean r) {
		this.id_cam = id_cam;
		this.camStat = camStat;
		this.onRepairs = r;
	}

	public TCam(String s) {
		String[] unArray = s.split(" ");
		id_cam = unArray[0];
		Integer estaCosa;
		estaCosa = Integer.valueOf(unArray[1]);
		switch (estaCosa) {
		case 0:
			this.camStat = camState.on;
			break;
		case 1:
			this.camStat = camState.off;
			break;

		}
		this.onRepairs = (unArray[2].equals("1")) ? true : false;

	}

	private String id_cam;
	private camState camStat;
	private boolean onRepairs;

	public boolean isOnRepairs() {
		return onRepairs;
	}

	public void setOnRepairs(boolean onRepairs) {
		this.onRepairs = onRepairs;
	}

	public String getId_cam() {
		return id_cam;
	}

	public void setId_cam(String id_cam) {
		this.id_cam = id_cam;
	}

	public camState getCamStat() {
		return camStat;
	}

	public void setCamStat(camState camStat) {
		this.camStat = camStat;
	}

	@Override
	public String toString() {
		return id_cam + " " + camStat + " " + onRepairs;
	}

	public String cloneToString() {
		return id_cam + " " + (camStat == camState.on ? 0 : 1) + " " + (onRepairs == true ? 1 : 0);
	}

}
