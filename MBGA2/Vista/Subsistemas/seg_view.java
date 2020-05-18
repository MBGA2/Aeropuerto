package Vista.Subsistemas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
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
import Observer.Observer;
import Transfer.seg.TCam;
import Transfer.seg.TListCam;
import Utils.NotifyData;

import java.awt.Dimension;


public class seg_view extends JPanel implements Observer{
	
	private static final long serialVersionUID = 1L;

	private JPanel panel;
	//private JPanel panelPop;
	
	private JButton listCams;
	private JButton searchCamID;
	private JButton addToMant;
	private JButton genView;//substitutes checkCam
	
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
	 
	
	private seg_controller seg_controller;	//Concrete Controller reference


	// ******************************************************************************************
	/**
	 * Builder
	 * @param seg_controller
	 */
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
		//listCam lCam = new listCam();
		//Deprecated url method
		
		@SuppressWarnings("unused")
		URL nurl = null;	
		try {
		nurl = new URL("https://cdn.discordapp.com/attachments/674384844893192193/703020214257713152/c6781f1dedc1bc451d678e57bcadf40c0fc283766012e0d86041dd166cdefa76_1.mp4");
		} catch (MalformedURLException e) {
		e.printStackTrace();
		}
		Authenticator au = new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication ("user", "pass".toCharArray());
		}
		};
		Authenticator.setDefault(au);
	
		
	
		
		
		
		
		
		
		
		
		
		listCams.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent evento) {
				System.out.println("Botoncete pulsadete");
				//seg_controller.loadData();
			}
		}
				);
		
		
		addToMant.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent evento) {
					System.out.println("Botoncete pulsadete");
					TCam tc = searchCamActionPerformed(evento);
					if(tc != null && tc.isOnRepairs() == false && seg_controller.addToMant(tc))
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
		
		/////////
		
		genView.addActionListener(new ActionListener(){
			
			public void actionPerformed (ActionEvent evento) {
				
				 java.awt.EventQueue.invokeLater(new Runnable() {
			            public void run() {
			                 g = new seg_view_genCam();
			               
			                g.setVisible(true);
			                seg_controller.addModelObserver(g);
			                seg_controller.notifyRefresh();
			              //  g.update(new NotifyData(NTYPE.CAM_UPDATE));
			            }
			        });
				 
				panelPop.setBackground(Color.red); 
				  
				panelPop.add(l); 
				
				 JPanel p1 = new JPanel(); 
				 
				p1.add(bVis);
				
				
			}	
			
			
		}
		);
		
		//////////
		
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
		//table.
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
	//	panelAux.add(this.listCams);
		panelAux.add(this.searchCamID);
		panelAux.add(this.addToMant);
		panelAux.add(this.genView);
		
		this.panel.add(panelAux ,BorderLayout.SOUTH);
		this.panel.add(this.table, BorderLayout.NORTH);
		this.table.setPreferredSize(new Dimension(150,250));
		this.panel.setPreferredSize(new Dimension(1200,300));
		this.panel.setVisible(true);
	}
	
	// *******************************************************************************************
	
	
	public JPanel getMainPanel() {
		return this.panel;
	}
	/**
	 * Custom ActionPerformed for cameras search by id
	 * @param evento
	 * @return
	 */
	public TCam searchCamActionPerformed(ActionEvent evento)
	{
		//TCam t = new TCam();
		String s = JOptionPane.showInputDialog(null, "Instroduzca Id", "Busqueda de camara", JOptionPane.QUESTION_MESSAGE);
		TCam t = new TCam(s, null, false);
		t = seg_controller.searchCameraById(t);
		//if(t != null)JOptionPane.showMessageDialog(null, t.toString(), "Camara encontrada:", JOptionPane.INFORMATION_MESSAGE);
		if(t == null) JOptionPane.showMessageDialog(null, "Error, camara no encontrada", "Busqueda de camara", JOptionPane.ERROR_MESSAGE);
		return t;
	}
	
	
	
	
	
	
	// ********************************************************************************************
	
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
		
	}
}

