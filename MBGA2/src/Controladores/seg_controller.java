package Controladores;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import DAOs.seg_dao_camlist;
import Factory.DCamFactory;
import Factory.DCamListFactory;
import Factory.DaoFactory;
import Factory.DaoFactoryImp;
import Main.Aeropuerto;
import Observer.Observer;
import Transfer.seg.TCam;
import Transfer.seg.TListCam;
import Transfer.seg.Transfer;
import Utils.NTYPE;
import Utils.NotifyData;


public class seg_controller {

	public static final String nombreArch = "src/Datos/seg/listaCamaras.txt";//rev
	private Aeropuerto data;
	private DaoFactoryImp f = DaoFactoryImp.getInstance();;
	
	public seg_controller(Aeropuerto aero) {
		data = aero;
			//JFrame seg_view=new seg_view();
		//init();
	}
	
	public void init()
	{
		f.addConcreteFactory(new DCamFactory());
		f.addConcreteFactory(new DCamListFactory());
		
		loadData();
		//f.init();
		//f.parse(t)
	}

	//cargar datos
	public boolean loadData() {
		File archivo= new File (nombreArch);
		try {
			BufferedReader BR = new BufferedReader(new FileReader(archivo));
			String palabra;
			DCamListFactory concreteFactory = (DCamListFactory) f.parse(new TListCam());
			//crear un TLista y un TCam
			//obtener factoria concreta
			//obtener el dao
			//archivo > Tcam	loop
			//Tcam > TLista		endloop
			//solicitar al dao que inserte la TLista en data(almacen)
			
			seg_dao_camlist cDAO = (seg_dao_camlist) concreteFactory.getDAO(new TListCam());
			cDAO.setList(data.getCamaras());
			
			TListCam tList = new TListCam();
			//cDAO.insertNewList(tList);
			while((palabra = BR.readLine()) != null)
			{
				TCam c = new TCam(palabra);
				tList.insert(c);
			}
			cDAO.saveData(tList);
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

	public TCam searchCameraById(TCam t) {
		
		return getListDAO().searchTCamById(t);
		
	}
	
	public seg_dao_camlist getListDAO()
	{
		f = DaoFactoryImp.getInstance();
		
		DaoFactory concreteF = f.parse( (Transfer) new TListCam());
		
		seg_dao_camlist d = (seg_dao_camlist) concreteF.getDAO(null);
		
		d.setList(data.getCamaras());
		return d;
	}

	public boolean addToMant(TCam tc) {
		
		TCam result = getListDAO().addToMant(tc);
		data.notifyAllO(new NotifyData(NTYPE.CAM_UPDATE, result));	//only id is stable
		return result!=null;
	}
	
	public void notifyRefresh() {
		
		data.notifyAllO(new NotifyData(NTYPE.CAM_REFRESH, getListDAO().getCamList()));
	}
	
}
