package Transfer.seg;

import java.util.ArrayList;
import java.util.List;

public class TListCam {
	
	private List <TCam> listCam;
	
	public TListCam()
	{
		listCam = new ArrayList<TCam>();
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
	
	public TCam getTCam(int i)
	{
		return listCam.get(i);
	}
	
	public int getSize()
	{
		return listCam.size();
	}
	
	public List<String> toStringTable()
	{
		List<String> s = new ArrayList<String>();
		for(TCam c : this.listCam)
			s.add(c.toString());
		return s;
	}

}
