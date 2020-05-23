package Vista.Subsistemas;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Controladores.seg_controller;
import Observer.Observer;
import Transfer.seg.TListCam;
import Utils.NotifyData;

public class seg_view_genCam extends javax.swing.JFrame implements Observer {

	private static final long serialVersionUID = 1L;
	private List<String> pathCamList = new ArrayList<String>();
	private seg_controller sAux;
	private static seg_view_genCam instanceF = null;

	public seg_view_genCam(seg_controller s) {
		sAux = s;
		initComponents();
		pathCamList.add("/Iconos/Apagada.gif");
	}

	public synchronized static seg_view_genCam getInstance(seg_controller sAux) {
		if (instanceF == null)
			instanceF = new seg_view_genCam(sAux);
		instanceF.repaint();
		return instanceF;
	}

	private void initComponents() {
		getContentPane().setPreferredSize(new Dimension(800, 500));
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new java.awt.GridLayout(2, 3));
		pack();
	}

	@Override
	public void update(NotifyData data) {

		switch (data.getN()) {
		case CAM_REFRESH:
			TListCam listCams = (TListCam) data.getData();
			this.getContentPane().removeAll();
			for (int i = 0; i <= listCams.getSize() - 1; i++) {
				seg_CheckCam panAux = new seg_CheckCam(listCams.getTCam(i), sAux);
				panAux.panCreate(null, null);
				this.add(panAux);
			}
			break;
		case ALRM_CLOSE:
			JOptionPane.showMessageDialog(null, "Protocolo de emergencia en curso, por favor evacuen la zona. ",
					"Cierre de sistemas", JOptionPane.QUESTION_MESSAGE);
			this.dispose();
			break;
		default:
			break;
		}
		repaint();
	}
}
