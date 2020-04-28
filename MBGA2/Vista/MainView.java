package Vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

import Controladores.NotifyData;
import Controladores.main_controller;
import Observer.Observer;
import Utils.atm.SimpleDigitalClock;
import Vista.Auxiliares.atm_view;
import Vista.Auxiliares.inf_view;
import Vista.Auxiliares.seg_view;
import Vista.Auxiliares.tor_view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainView extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	
	// PANEL PRINCIPAL
	public JPanel mainPanel;
	private JPanel mainPanel_1;
	private JPanel subPanel;
	// CENTER PANEL
	private JPanel centerPanel;
	private JPanel ATMpanel;
	private inf_view inf_view;
	private JPanel INFOpanel;
	private JPanel TORpanel;
	private JPanel SEGpanel;
	
	SimpleDigitalClock clock1; 
	private JButton btnInfo;
	private JButton btnAduanas;
	private JButton btnSeguridad;
	private JButton btnAtm;
	private JButton btnControl;
	
	// BOTTOM STATUS BAR
	private JPanel statusBar;
	private JLabel statusInfo;
	
	// CONTROLLERS
	private main_controller ctrl;

	private atm_view atm_view;

	


	
	/**
	 * Constructora de MainView, da formato a la interfaz de la aplicacion.
	 * @param ctrl Controlador generico del programa
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public MainView(main_controller ctrl)  {
		super("MBGA");
		this.ctrl = ctrl;
		this.initGUI();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		this.setUndecorated(true);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.inf_view = new inf_view(this.ctrl.getInf());
		this.INFOpanel = inf_view.getMainInfo();
		this.atm_view = new atm_view(this.ctrl.getAtm());
		this.ATMpanel = atm_view.getMainPanel();
		this.SEGpanel = new seg_view(this.ctrl.getSeg()).getMainPanel();
		this.TORpanel = new tor_view(this.ctrl.getTor()).getMainPanel();
		//this.clock1 = new SimpleDigitalClock(); 

	}

	/**
	 * Inicializa las principales partes de la interfaz.
	 */
	private void initGUI() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(
			new WindowListener() {
				@Override
				public void windowActivated(WindowEvent arg0) {
					
				}
	
				@Override
				public void windowClosed(WindowEvent arg0) {
					
				}
	
				@Override
				public void windowClosing(WindowEvent arg0) {
					quit();
					
				}
	
				@Override
				public void windowDeactivated(WindowEvent arg0) {
					
				}
	
				@Override
				public void windowDeiconified(WindowEvent arg0) {
					
				}
	
				@Override
				public void windowIconified(WindowEvent arg0) {
					
				}
	
				@Override
				public void windowOpened(WindowEvent arg0) {
					
				}
			}
		);
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.setContentPane(mainPanel);
		this.createTopPanel();
		this.createStatusBar();
		
	}
	private void visualizar() {
		this.setVisible(true);
	}
	private void createTopPanel() {
	
		mainPanel_1 = new JPanel();
		mainPanel_1.setLayout(new BorderLayout());
		
		subPanel = new JPanel();
		clock1 = new SimpleDigitalClock(this.ctrl);

	
		btnInfo = new JButton("");
		btnInfo.setIcon(new ImageIcon( new ImageIcon(MainView.class.getResource("/Iconos/infoLogo.png")).getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH )));
		btnInfo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Subsistema
				//test();
				centerPanel.removeAll();
				ctrl.getInf().addAll();
				centerPanel.add(INFOpanel);
				centerPanel.repaint();
				visualizar();
			
			}
			
		});
		
		
		btnAduanas = new JButton("");
		btnAduanas.setIcon(new ImageIcon( new ImageIcon(MainView.class.getResource("/Iconos/aduanasLogo.png")).getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH )));
		btnAduanas.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnAduanas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Subsistema
			}
			
		});
		
		
		btnSeguridad = new JButton("");
		btnSeguridad.setIcon(new ImageIcon( new ImageIcon(MainView.class.getResource("/Iconos/seguridadLogo.png")).getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH )));
		btnSeguridad.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSeguridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Subsistema
				centerPanel.removeAll();
				centerPanel.add(SEGpanel);
				centerPanel.repaint();
				visualizar();
			}
			
		});
		btnAtm = new JButton("");
		btnAtm.setIcon(new ImageIcon( new ImageIcon(MainView.class.getResource("/Iconos/atmLogo.png")).getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH )));
		
		btnAtm.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnAtm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				centerPanel.removeAll();
				centerPanel.add(ATMpanel);
				centerPanel.repaint();
				visualizar();
			}
			
		});
		
		btnControl = new JButton("");
		btnControl.setIcon(new ImageIcon( new ImageIcon(MainView.class.getResource("/Iconos/controlLogo.png")).getImage().getScaledInstance( 200, 200,  java.awt.Image.SCALE_SMOOTH )));
		btnControl.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				centerPanel.removeAll();
				centerPanel.add(TORpanel);
				centerPanel.repaint();
				visualizar();
			}
			
		});
		
	    subPanel.add(btnInfo);
	    subPanel.add(btnAduanas);
	    subPanel.add(btnSeguridad);
	    subPanel.add(btnAtm);
		subPanel.add(btnControl);
		subPanel.add(clock1);
		mainPanel_1.add(subPanel,BorderLayout.CENTER);
		this.centerPanel = new JPanel();
		JLabel picLabel = new JLabel(new ImageIcon( new ImageIcon(MainView.class.getResource("/Iconos/MBGA.png")).getImage().getScaledInstance( 400, 400,  java.awt.Image.SCALE_SMOOTH )));
		this.centerPanel.add(picLabel);
		//test();
		mainPanel.add(this.centerPanel);
		mainPanel.add(mainPanel_1, BorderLayout.NORTH);
	}

	
	private void createStatusBar() {
		this.statusBar = new JPanel();
		this.statusInfo = new JLabel("Make Barajas Great Again, tu aeropuerto de confianza");
		statusInfo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 50));
		this.statusBar.add(statusInfo);
		JButton btnSalir = new JButton("SALIR");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quit();
			}
		});
		
		btnSalir.setFont(new Font("Arial Unicode MS", Font.PLAIN, 50));
		statusBar.add(btnSalir);
		mainPanel.add(statusBar, BorderLayout.SOUTH);
	}

	public void quit() {
		int option = JOptionPane.showOptionDialog(null,"¿Seguro que quieres salir?", "Salir",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (option == 0) {
			main_controller.quit();
		}
	}
	public main_controller getMain_controller(){
		return this.ctrl;
	}

	@Override
	public void update(NotifyData n) {
		switch (n.getN()) {
		
		case REFRESH:
			try {
				this.ctrl.check();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			this.inf_view.refresh();
			break;
		default:
			break;
		}
	}
}
