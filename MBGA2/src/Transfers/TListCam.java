package Transfers;

import java.util.ArrayList;
import java.util.List;

public class TListCam implements Transfer {

	private List<TCam> listCam = new ArrayList<TCam>();

	public TListCam() {
	}

	public List<TCam> getListCam() {
		return listCam;
	}

	public void setListCam(List<TCam> listCam) {
		this.listCam = listCam;
	}

	public void insert(TCam c) {
		listCam.add(c);
	}

	public TCam getTCam(int i) {
		return listCam.get(i);
	}

	public int getSize() {
		return listCam != null ? listCam.size() : -1;
	}

	public List<String> toStringTable() {
		List<String> s = new ArrayList<String>();
		for (TCam c : this.listCam)
			s.add(c.toString());
		return s;
	}

}
