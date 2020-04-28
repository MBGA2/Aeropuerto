package Vista.Auxiliares;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controladores.NotifyData;
import Controladores.seg_controller;
import Datos.seg.listCam;
import Observer.Observer;
import Transfer.seg.TListCam;


import java.awt.Dimension;

public class seg_view extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	
	private JButton listCams;
	private JButton searchCamID;
	private JButton checkCam;
	private JButton addToMant;

	private JTable dataTable;
	private DefaultTableModel tableModel;
	private JScrollPane table;	


public seg_view(seg_controller seg_controller) {
	seg_controller.addModelObserver(this);
	//seg_controller.loadData();
	
	this.panel = new JPanel(new BorderLayout(1100, 1500));
	panel.setLayout(new BorderLayout());
	this.searchCamID = new JButton("Buscar camara por ID");

	this.listCams = new JButton("Listar/Actualizar camaras");
	
	this.addToMant = new JButton("Agregar una camara a mantenimiento");

	this.checkCam = new JButton("Comprobar el estado de una camara");

	this.setVisible(false);
	@SuppressWarnings("unused")
	listCam lCam = new listCam();
	
	
	//falta accion de listar camaras
	listCams.addActionListener(new ActionListener() {
		public void actionPerformed (ActionEvent evento) {
			System.out.println("Botoncete pulsadete");
		}
	}
			);
	this.tableModel = new DefaultTableModel() {
		private static final long serialVersionUID = 1L;
		String[] camInfo = {"#","ID","Estado","En reparaciones"};
		
		@Override 
		public int getColumnCount() { 
			return camInfo.length; 
		}
		
		@Override 
        public String getColumnName(int index) { 
            return camInfo[index]; 
        } 
	};
	this.dataTable = new JTable(this.tableModel);
	this.table = new JScrollPane(this.dataTable);
	this.add(this.panel);
	
	
//	this.listCams.setBounds(200, 200, 150, 40);// x,y,ancho,alto
//	this.searchCamID.setBounds(200, 250, 150, 40);// x,y,ancho,alto
//	this.addToMant.setBounds(200,300,150,40);
//	this.checkCam.setBounds(200,350,150,40);
	
//	this.table.setBounds(180, 400, 150, 200);

	JPanel panelAux=new JPanel();
	
	//this.panel.add(this.listCams);
	//this.panel.add(this.searchCamID);
	//this.panel.add(this.addToMant);
	//this.panel.add(this.checkCam);
	panelAux.add(this.listCams);
	panelAux.add(this.searchCamID);
	panelAux.add(this.addToMant);
	panelAux.add(this.checkCam);
	
	this.panel.add(panelAux ,BorderLayout.SOUTH);
	this.panel.add(this.table, BorderLayout.NORTH);
	this.table.setPreferredSize(new Dimension(150,250));
	this.panel.setPreferredSize(new Dimension(1200,300));
	this.panel.setVisible(true);
}

public JPanel getMainPanel() {
	return this.panel;
}

public void update(NotifyData n) {
	
	switch(n.getN())
	{
	case CAM_INSERT:
		int quantity = 0;
		for(String c : ((TListCam) (n.getData())).toStringTable())
		{
			c = quantity + " " +c;
			String[] s = c.split(" ");
			tableModel.addRow(s);
		}
		tableModel.fireTableDataChanged();
		
		break;
	case CAM_DELETE:
		break;
	case CAM_UPDATE:
		break;
	case VUEL_ERROR1:
		break;
	case VUEL_INSERT:
		break;
	default:
		break;
	}
	
}
}

