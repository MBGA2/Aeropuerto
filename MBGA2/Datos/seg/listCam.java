package Datos.seg;

import java.util.ArrayList;
import java.util.List;

public class listCam {
	
private List <cam> listCam;

public List<cam> getListCam() {
	return listCam;
}
public void setListCam(List<cam> listCam) {
	this.listCam = listCam;
}
public listCam() {
	listCam=new ArrayList<cam>();
	
}
public void insert(cam c) {
	
	for(cam cN : listCam)
		if(cN.getId_cam().equals(c.getId_cam()))
			cN = c;
	listCam.add(c);
}

public String get(int index) {
	
	
	return listCam.get(index).toString();
}

public cam searchCamById(String s)
{
	for(cam c: listCam)
		if(c.getId_cam().equals(s))
			return c;
	return null;
}

public cam searchCam(cam cN)
{
	for(cam c: listCam)
		if(c.equals(cN))
			return c;
	return null;
}

}
