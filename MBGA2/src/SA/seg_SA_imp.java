package SA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import DAOs.seg_dao_camlist;
import Datos.NTYPE;
import Datos.NotifyData;
import Factory.DCamFactory;
import Factory.DCamListFactory;
import Factory.DaoFactory;
import Factory.DaoFactoryImp;
import Main.Aeropuerto;
import Transfers.TCam;
import Transfers.TListCam;
import Transfers.Transfer;

public class seg_SA_imp implements seg_SA {

	private Aeropuerto data;
	private DaoFactoryImp f = DaoFactoryImp.getInstance();

	public seg_SA_imp(Aeropuerto aero) {
		data = aero;
	}

	public void init(String nombreArch) {
		f.addConcreteFactory(new DCamFactory());
		f.addConcreteFactory(new DCamListFactory());

		loadData(nombreArch);
	}

	public void notifyRefresh() {

		data.notifyAllO(new NotifyData(NTYPE.CAM_REFRESH, getListDAO().getCamList()));
	}

	private seg_dao_camlist getListDAO() {
		f = DaoFactoryImp.getInstance();

		DaoFactory concreteF = f.parse((Transfer) new TListCam());

		seg_dao_camlist d = (seg_dao_camlist) concreteF.getDAO(null);

		d.setList(data.getCamaras());
		return d;
	}

	@Override
	public boolean addCamToRepairs(TCam tC) {

		TCam result = getListDAO().addToMant(tC);
		data.notifyAllO(new NotifyData(NTYPE.CAM_UPDATE, result)); // only id is stable

		return result != null;

	}

	public TCam searchCamByID(TCam tC) {

		return getListDAO().searchTCamById(tC);
	}

	public boolean loadData(String nombreArch) {
		File archivo = new File(nombreArch);
		try {
			BufferedReader BR = new BufferedReader(new FileReader(archivo));
			String palabra;
			DCamListFactory concreteFactory = (DCamListFactory) f.parse(new TListCam());
			seg_dao_camlist cDAO = (seg_dao_camlist) concreteFactory.getDAO(new TListCam());
			cDAO.setList(data.getCamaras());
			TListCam tList = new TListCam();
			while ((palabra = BR.readLine()) != null) {
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

	@Override
	public void visFlow(TCam tC) {

	}

	@Override
	public void actvAlarm(TCam tC) {

	}

	@Override
	public void actvLockdown(TCam tC) {

	}

}
