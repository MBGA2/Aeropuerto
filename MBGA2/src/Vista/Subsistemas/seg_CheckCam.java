package Vista.Subsistemas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

import javax.swing.*;

import Controladores.seg_controller;
import Datos.seg.camState;
import Transfer.seg.TCam;

public class seg_CheckCam extends JPanel {

	private JLabel labele;
	private String cID;
	private TCam tC;
	private seg_controller sAux2;
	seg_view_framecamsingle fCam;
	boolean incRep;
	private static final long serialVersionUID = 1L;

	public seg_CheckCam(String id_cam) {
		cID = id_cam;
	}

	public seg_CheckCam(TCam tC, seg_controller sAux) {
		this.tC = tC;
		sAux2 = sAux;
	}

	public seg_CheckCam cloneP() {
		seg_CheckCam aux;
		if (this.cID == null)
			aux = new seg_CheckCam(new TCam(this.tC.cloneToString()), sAux2);
		else
			aux = new seg_CheckCam(this.cID);
		aux.labele = new JLabel();
		aux.labele.setIcon(new ImageIcon(seg_Resources.mountCamPath(tC == null ? cID : tC.getId_cam())));
		aux.labele.setPreferredSize(this.labele.getPreferredSize());
		aux.add(aux.labele);
		return aux;
	}

	public JLabel panCreate(Object objecte, Color c) {
		return panCreate(objecte, this.tC, c);
	}

	public JLabel panCreate(Object objecte, TCam tC, Color c) {
		String dirImg = setImgPath(tC);
		this.setLayout(new BorderLayout());
		labele = new JLabel();
		labele.setIcon(new javax.swing.ImageIcon(dirImg));
		compIn(labele);
		this.add(labele, BorderLayout.CENTER);
		if (c != null) {
			labele.setBackground(c);
			this.setBackground(c);
			labele.setVisible(false);
		}
		return labele;
	}

	private String setImgPath(TCam tC) {
		String path = new String();
		if (tC.isOnRepairs() == true)
			path = seg_Resources.mountCamPath("Apagada");
		else
			path = seg_Resources.mountCamPath(tC.getId_cam());
		if (tC.getCamStat() == camState.off) {
			path = seg_Resources.mountCamPath("cOff");
		}
		return path;
	}

	private void addButtons() {
		JButton bAl = new JButton("Activar alarma");
		JButton bCi = new JButton("Activar cierres");
		JPanel pAux = new JPanel();
		this.labele.setEnabled(false);
		pAux.setSize(800, 800);
		pAux.setVisible(true);
		pAux.setLayout(new BoxLayout(pAux, BoxLayout.Y_AXIS));
		pAux.add(bAl);
		pAux.add(bCi);
		this.add(pAux, BorderLayout.EAST);
		bAl.setVisible(true);
		bCi.setVisible(true);
		this.setSize(410, 330);// gif dim = 283x254
		this.labele.setSize(320, 330);
		bAl.setSize(90, 10);
		bCi.setSize(90, 10);
		bAl.setLocation(320, 10);
		bCi.setLocation(320, 0);
		incRep = false;
		bAl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Incidencia reportada en el sector:  " + tC.getId_cam(), cID,
						JOptionPane.WARNING_MESSAGE);
				bAl.setVisible(true);
				incRep = true;
			}
		});
		bCi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionAlrmPerformed(bAl, bCi, e);
			}
		});
	}

	private void actionAlrmPerformed(JButton bAl, JButton bCi, ActionEvent e) {
		if (incRep == true) {
			JOptionPane.showMessageDialog(null,
					"Alarma general en curso, iniciando cierres y avisando a las autoridades: ", cID,
					JOptionPane.WARNING_MESSAGE);
			bAl.setVisible(true);
			sAux2.actAlrm();
			Component c = ((Component) e.getSource()).getParent().getParent().getParent().getParent().getParent()
					.getParent();// Llegar a JFrame 6 ancestros para cerrar el framecamsingle
			((JFrame) c).dispose();
		} else
			JOptionPane.showMessageDialog(null,
					"No ha habido ninguna incidencia localizada en el sector " + tC.getId_cam(), cID,
					JOptionPane.ERROR_MESSAGE);
	}

	private void compIn(JLabel l) {
		this.add(l);
		l.setEnabled(true);
		l.addMouseListener(new MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				System.out.print("clackS");
				seg_CheckCam pAux;
				pAux = cloneP();
				java.awt.EventQueue.invokeLater(new Runnable() {
					public void run() {
						fCam = new seg_view_framecamsingle("Vista de IDcam: " + (tC == null ? cID : tC.getId_cam()));
						fCam.setSize(410, 450);
						fCam.setResizable(false);
						fCam.add(pAux);
						pAux.addButtons();
						fCam.setVisible(true);
					}
				});
			}
		});
	}
}