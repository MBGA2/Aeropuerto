package Datos.seg;

import java.util.ArrayList;
import java.util.List;

public class listCam {
	
private List <cam> listCam;

public listCam() {
	listCam=new ArrayList<cam>();
	
}
public void insert(cam c) {
	listCam.add(c);
}

public String get(int index) {
	
	
	return listCam.get(index).toString();
}
}
