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

import Controladores.main_controller;
import Vista.Subsistemas.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	private main_controller ctrl;
	// PANEL PRINCIPAL
	public JPanel mainPanel;
	private JPanel mainPanel_1;
	private JPanel subPanel;
	// CENTER PANEL
	private JPanel centerPanel;
	private JPanel ATMpanel;
	private JPanel INFOpanel;
	private JPanel TORpanel;
	private JPanel SEGpanel;
	private JPanel MAPpanel;

	SimpleDigitalClock clock1;
	private JButton btnInfo;
	private JButton btnAduanas;
	private JButton btnSeguridad;
	private JButton btnAtm;
	private JButton btnControl;

	// BOTTOM STATUS BAR
	private JPanel statusBar;
	private JLabel statusInfo;
	private JLabel picLabel;

	private atm_view atm_view;
	private map_view map_view;
	private seg_view seg_view;
	private inf_view inf_view;
	private tor_view tor_view;
	private adu_view adu_view;
	private JPanel ADUPanel;

	public MainView(main_controller ctrl) {

		super("MBGA");
		this.ctrl = ctrl;
		this.initGUI();
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.inf_view = new inf_view(this.ctrl.getInf());
		this.INFOpanel = inf_view.getMainInfo();
		this.map_view = new map_view(this.ctrl.getMap());
		this.MAPpanel = map_view.getMainInfo();
		this.atm_view = new atm_view(this.ctrl.getAtm());
		this.ATMpanel = atm_view.getMainPanel();
		this.seg_view = new seg_view(this.ctrl.getSeg());
		this.SEGpanel = seg_view.getMainPanel();
		this.tor_view = new tor_view(this.ctrl.getTor());
		this.TORpanel = tor_view.getMainPanel();
		this.adu_view = new adu_view(this.ctrl.getAdu());
        this.ADUPanel =  adu_view.getMainPanel();
	}

	private void initGUI() {
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowListener() {
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
		});
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		this.setContentPane(mainPanel);
		this.createTopPanel();
		this.createStatusBar();

	}

	private void createTopPanel() {

		mainPanel_1 = new JPanel();
		mainPanel_1.setLayout(new BorderLayout());

		subPanel = new JPanel();
		clock1 = new SimpleDigitalClock(this.ctrl);
		JButton btnGeneral = new JButton("");
		btnGeneral.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/inicio.png")).getImage()
				.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		btnGeneral.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnGeneral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				centerPanel.removeAll();
				centerPanel.add(MAPpanel);
				centerPanel.repaint();
				visualizar();

			}

		});

		btnInfo = new JButton("");
		btnInfo.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/infoLogo.png")).getImage()
				.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		btnInfo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				centerPanel.removeAll();
				ctrl.getInf().resetFlag();
				centerPanel.add(INFOpanel);
				centerPanel.repaint();
				visualizar();

			}

		});

		btnAduanas = new JButton("");
		btnAduanas.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/aduanasLogo.png")).getImage()
				.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		btnAduanas.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnAduanas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				centerPanel.removeAll();
				centerPanel.add(ADUPanel);
				centerPanel.repaint();
				visualizar();
			}

		});

		btnSeguridad = new JButton("");
		btnSeguridad.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/seguridadLogo.png"))
				.getImage().getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		btnSeguridad.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnSeguridad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				centerPanel.removeAll();
				centerPanel.add(SEGpanel);
				centerPanel.repaint();
				visualizar();
			}

		});
		btnAtm = new JButton("");
		btnAtm.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/atmLogo.png")).getImage()
				.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));

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
		btnControl.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/controlLogo.png")).getImage()
				.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));
		btnControl.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		btnControl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				centerPanel.removeAll();
				centerPanel.add(TORpanel);
				centerPanel.repaint();
				visualizar();
			}

		});

		JPanel masmenos = new JPanel(new BorderLayout());
		JButton mas = new JButton("");
		mas.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/mas.png")).getImage()
				.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		mas.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		mas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clock1.setModifier(60 + clock1.getModifier());
			}

		});
		JButton menos = new JButton("");
		menos.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/menos.png")).getImage()
				.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
		menos.setFont(new Font("Arial Unicode MS", Font.PLAIN, 14));
		menos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (clock1.getModifier() != 1) {
					clock1.setModifier(clock1.getModifier() - 60);
				}
			}

		});
		masmenos.add(mas, BorderLayout.NORTH);
		masmenos.add(menos, BorderLayout.SOUTH);
		subPanel.add(btnGeneral);
		subPanel.add(btnInfo);
		subPanel.add(btnAduanas);
		subPanel.add(btnSeguridad);
		subPanel.add(btnAtm);
		subPanel.add(btnControl);
		subPanel.add(clock1);
		subPanel.add(masmenos);
		mainPanel_1.add(subPanel, BorderLayout.CENTER);
		this.centerPanel = new JPanel();
		picLabel = new JLabel(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/MBGA.png")).getImage()
				.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH)));
		this.centerPanel.add(picLabel);
		mainPanel.add(this.centerPanel);
		mainPanel.add(mainPanel_1, BorderLayout.NORTH);
	}

	private void createStatusBar() {
		this.statusBar = new JPanel();
		this.statusInfo = new JLabel("Make Barajas Great Again, tu aeropuerto de confianza");
		statusInfo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 30));
		this.statusBar.add(statusInfo);
		JButton btnSalir = new JButton("");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quit();
			}
		});

		btnSalir.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/salir.png")).getImage()
				.getScaledInstance(200, 80, java.awt.Image.SCALE_SMOOTH)));
		btnSalir.setFont(new Font("Arial Unicode MS", Font.PLAIN, 50));
		JButton btnBorrar = new JButton("");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				del();
				JOptionPane.showConfirmDialog(null, "Vuelos borrados", "", JOptionPane.DEFAULT_OPTION);
			}
		});
		btnBorrar.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/borrar.png")).getImage()
				.getScaledInstance(200, 80, java.awt.Image.SCALE_SMOOTH)));
		btnBorrar.setFont(new Font("Arial Unicode MS", Font.PLAIN, 50));

		JButton btnAnadir = new JButton("");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String n = JOptionPane.showInputDialog("Inserta un numero de vuelos");
				init(n);
				JOptionPane.showConfirmDialog(null, "Vuelos anadidos", "", JOptionPane.DEFAULT_OPTION);
			}
		});
		btnAnadir.setIcon(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/anadir.png")).getImage()
				.getScaledInstance(200, 80, java.awt.Image.SCALE_SMOOTH)));
		btnAnadir.setFont(new Font("Arial Unicode MS", Font.PLAIN, 50));
		statusBar.add(btnAnadir);
		statusBar.add(btnBorrar);
		statusBar.add(btnSalir);
		mainPanel.add(statusBar, BorderLayout.SOUTH);
	}

	public void quit() {
		int option = JOptionPane.showOptionDialog(null, "¿Seguro que quieres salir?", "Salir",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (option == 0) {
			main_controller.quit();
		}
	}

	private void visualizar() {
		this.setVisible(true);
	}

	private void init(String num) {
		int n = Integer.parseInt(num);
		try {
			this.ctrl.generateFlight(n);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		;
	}

	private void del() {
		try {
			this.ctrl.deleteAll();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
