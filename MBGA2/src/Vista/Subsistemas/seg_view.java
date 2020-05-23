package Vista.Subsistemas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.table.DefaultTableModel;

import Controladores.seg_controller;
import Datos.NotifyData;
import Observer.Observer;
import Transfers.TCam;
import Transfers.TListCam;

import java.awt.Dimension;


public class seg_view extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JButton listCams;
	private JButton searchCamID;
	private JButton addToMant;
	private JButton genView;
	JTextField text = new JTextField(10);
	JLabel l = new JLabel("Date");
	JPanel panelPop = new JPanel(new BorderLayout(1100, 1500));
	PopupFactory pf = new PopupFactory(); 
	Popup p;
	JButton bVis = new JButton("Visualizar camara");
	private JTable dataTable;
	private DefaultTableModel tableModel;
	private JScrollPane table;	
	private seg_view_genCam g; 
	private seg_controller seg_controller;	


	public seg_view(seg_controller seg_controller) {
		this.seg_controller = seg_controller;
		seg_controller.addModelObserver(this);
		this.panel = new JPanel(new BorderLayout(1100, 1500));
		panel.setLayout(new BorderLayout());
		this.searchCamID = new JButton("Buscar camara por ID");
		this.listCams = new JButton("Listar/Actualizar camaras");
		this.addToMant = new JButton("Agregar una camara a mantenimiento");
		this.genView = new JButton("Vista general de camaras");
		this.setVisible(false);
		listCams.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evento) {
				System.out.println("Botoncete pulsadete");
			}
		}
				);
		addToMant.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent evento) {
					System.out.println("Botoncete pulsadete");
					TCam tc = searchCamActionPerformed(evento);
					if(tc != null && tc.isOnRepairs() == false && seg_controller.addCamToRepairs(tc))
						JOptionPane.showMessageDialog(null, "La camara perteneciente al sector: " + tc.getId_cam() + " ha sido seleccionada para reparaciones con exito." , "Camara marcada para mantemiento:", JOptionPane.INFORMATION_MESSAGE);
					else if (tc != null && tc.isOnRepairs() == true)
						JOptionPane.showMessageDialog(null, "Esta camara ya esta pendiente de reparaciones y no necesita volver a marcarse.", "Error al marcar camara: ", JOptionPane.ERROR_MESSAGE, null);
					else
						JOptionPane.showMessageDialog(null, "Error al marcar camara", "Operacion cancelada ", JOptionPane.ERROR_MESSAGE, null);
				}
			}
					);
		
		searchCamID.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evento) {
				TCam t = searchCamActionPerformed(evento);
				if(t != null)
					if(t != null)JOptionPane.showMessageDialog(null, "Sector: " + t.getId_cam() + ". Estado de la camara: "+ t.getCamStat() + ". En reparaciones: " + t.isOnRepairs() , "Camara encontrada:", JOptionPane.INFORMATION_MESSAGE);
			}
		}
				);
		genView.addActionListener(new ActionListener(){
			public void actionPerformed (ActionEvent evento) {
				 java.awt.EventQueue.invokeLater(new Runnable() {
			            public void run() {
			                g = seg_view_genCam.getInstance(seg_controller);//Singleton estaba llamando a la constructora en vez de a getinstance que es el que necesita el parametro
			                g.setVisible(true);
			                seg_controller.addModelObserver(g);
			            	 seg_controller.notifyRefresh();
			            }
			        });
				panelPop.setBackground(Color.red); 
				panelPop.add(l); 
				 JPanel p1 = new JPanel(); 
				p1.add(bVis);
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
		JPanel panelAux=new JPanel();
		panelAux.add(this.searchCamID);
		panelAux.add(this.addToMant);
		panelAux.add(this.genView);
		this.panel.add(panelAux ,BorderLayout.SOUTH);
		this.panel.add(this.table, BorderLayout.NORTH);
		this.table.setPreferredSize(new Dimension(150,250));
		this.panel.setPreferredSize(new Dimension(1200,300));
		this.panel.setVisible(true);
		repaint();
	}
	public JPanel getMainPanel() {
		return this.panel;
	}
	public TCam searchCamActionPerformed(ActionEvent evento)
	{
		String s = JOptionPane.showInputDialog(null, "Instroduzca Id", "Busqueda de camara", JOptionPane.QUESTION_MESSAGE);
		TCam t = new TCam(s, null, false);
		t = seg_controller.searchCameraById(t);
		if(t == null) JOptionPane.showMessageDialog(null, "Error, camara no encontrada", "Busqueda de camara", JOptionPane.ERROR_MESSAGE);
		return t;
	}
	@SuppressWarnings("unchecked")
	public void update(NotifyData n) {
		switch(n.getN())
		{
		case CAM_INSERT:
			int quantity = 0;
			tableModel.setRowCount(0);
			for(String c : ((TListCam) (n.getData())).toStringTable())
			{
				c = quantity + " " +c;
				String[] s = c.split(" ");
				tableModel.addRow(s);
				quantity++;
			}
			tableModel.fireTableDataChanged();
			break;
		case CAM_DELETE:
			break;
		case CAM_UPDATE:
			TCam t = ((TCam)(n.getData()));
			for(@SuppressWarnings("rawtypes") Vector s:tableModel.getDataVector())
				if(s.elementAt(1).equals(t.getId_cam()))
				{
					s.setElementAt(t.getCamStat(), 2);
					s.setElementAt(t.isOnRepairs(), 3);
					tableModel.fireTableDataChanged();
					break;
				}
				seg_controller.notifyRefresh();	
			break;
		default:
			break;
		}
		repaint();
	}
}

