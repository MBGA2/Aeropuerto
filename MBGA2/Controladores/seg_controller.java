package Controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import DAOs.seg_camlist_dao;
import Main.Aeropuerto;
import Observer.Observer;
import Transfer.seg.TCam;
import Transfer.seg.TListCam;
import Utils.NTYPE;
import Utils.NotifyData;

public class seg_controller {

	public static final String nombreArch = "Datos/Seg/listaCamaras.txt";//rev
	private Aeropuerto data;
	
	public seg_controller(Aeropuerto aero) {
		data = aero;
			//JFrame seg_view=new seg_view();
			
	}

	//cargar datos
	public boolean loadData() {
		File archivo= new File (nombreArch);
		try {
			BufferedReader BR = new BufferedReader(new FileReader(archivo));
			String palabra;
			seg_camlist_dao cDAO = new seg_camlist_dao(data.getCamaras());
			TListCam tList = new TListCam();
			cDAO.insertNewList(tList);
			while((palabra = BR.readLine()) != null)
			{
				TCam c = new TCam(palabra);
				tList.insert(c);
			}
			data.notifyAllO(new NotifyData(NTYPE.CAM_INSERT, tList));
		
		BR.close();		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public void addModelObserver(Observer o)
	{
		data.addObserver(o);
	}
}
