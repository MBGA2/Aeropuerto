package Vista.Subsistemas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controladores.inf_controller;
import DAOs.inf_dao;
import Datos.Flight;
import Observer.Observer;
import Utils.NotifyData;
import Vista.MainView;

public class inf_view implements Observer {
	private Boolean flag;
	private JPanel mainInfo;
	private JPanel search;
	private JTable miTabla;
	private JScrollPane miBarra;
	private JTable miTabla2;
	private JScrollPane miBarra2;
	private inf_dao dao;
	private inf_controller cont;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTextField searchBar;
	private Object token;
	private int selectedCol;

	public inf_view(inf_controller inf_controller) {
		inf_controller.addModelObserver(this);
		//inf_controller.aero
		cont = inf_controller;
		flag = false;
		
		this.setMainInfo(new JPanel());
		mainInfo.setLayout(new BorderLayout());
		salidas(inf_controller);
		llegadas(inf_controller);
		busqueda(inf_controller);
		JLabel arrivals = new JLabel(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/arrivals.png"))
				.getImage().getScaledInstance(200, 50, java.awt.Image.SCALE_SMOOTH)));
		JLabel departures = new JLabel(new ImageIcon(new ImageIcon(MainView.class.getResource("/Iconos/departures.png"))
				.getImage().getScaledInstance(200, 50, java.awt.Image.SCALE_SMOOTH)));
		miBarra.setViewportView(miTabla);
		miBarra2.setViewportView(miTabla2);
		JPanel subPanel2 = new JPanel();
		subPanel2.setLayout(new BorderLayout());
		subPanel2.add(arrivals, BorderLayout.EAST);
		subPanel2.add(departures, BorderLayout.WEST);
		JPanel subPanel = new JPanel();
		subPanel.add(miBarra);
		subPanel.add(miBarra2);
		mainInfo.add(subPanel2, BorderLayout.NORTH);
		mainInfo.add(subPanel, BorderLayout.CENTER);
		mainInfo.add(search, BorderLayout.SOUTH);
	}

	private void busqueda(inf_controller inf_controller) {
		search = new JPanel();
		JLabel statusInfo = new JLabel("Busqueda de vuelos =>");
		statusInfo.setFont(new Font("Arial Unicode MS", Font.PLAIN, 50));
		this.search.add(statusInfo, BorderLayout.WEST);
		searchBar = new JTextField();
		searchBar.setPreferredSize(new Dimension(1000, 100));
		searchBar.setFont(new Font("Arial Unicode MS", Font.PLAIN, 50));
		this.search.add(searchBar);
		searchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					flag = false;
					model.setRowCount(0);
					model2.setRowCount(0);
					token = searchBar.getText();
					inf_controller.search();
					if (model.getRowCount() == 0) {
						int option = JOptionPane.showOptionDialog(null,
								"No existe ningun vuelo: '" + searchBar.getText() + "' Mostrar todos los vuelos?",
								"Busqueda de vuelo", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
								null);
						if (option == 0) {
							
							flag = true;
							inf_controller.addAll();
						}
					}
				}
			}
		});
	}

	@SuppressWarnings("serial")
	private void llegadas(inf_controller inf_controller) {
		miBarra2 = new JScrollPane();
		miBarra2.setPreferredSize(new Dimension(900, 600));
		model2 = new DefaultTableModel() {
		 public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		miTabla2 = new JTable();
		miTabla2.setFont(new Font("Test", Font.PLAIN, 18));
		miTabla2.setModel(model2);
		model2.addColumn("Origen");
		model2.addColumn("Hora Llegada");
		model2.addColumn("ID");
		model2.addColumn("Compania");
		model2.addColumn("Estado");
		DefaultTableCellRenderer d = new DefaultTableCellRenderer();
		d.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < 5; i++) {
			miTabla2.getColumnModel().getColumn(i).setCellRenderer(d);
		}
		DefaultTableCellRenderer bw = new DefaultTableCellRenderer();
		bw.setHorizontalAlignment(JLabel.CENTER);
		bw.setForeground(Color.YELLOW);
		bw.setBackground(Color.BLACK);
		miTabla2.getColumnModel().getColumn(4).setCellRenderer(bw);
		miTabla2.getTableHeader().setReorderingAllowed(false);
		this.dao = inf_controller.getDao();
		miTabla2.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = miTabla2.rowAtPoint(evt.getPoint());
				selectedCol = miTabla2.columnAtPoint(evt.getPoint());
				if (row >= 0 && selectedCol >= 0) {
					token = miTabla2.getValueAt(row, selectedCol);
					flag = false;
					inf_controller.search();
				}
			}
		});
	}

	private void salidas(inf_controller inf_controller) {
		miBarra = new JScrollPane();
		miBarra.setPreferredSize(new Dimension(900, 600));
		
		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
			       return false;
			    }
			};
		miTabla = new JTable();
		miTabla.setFont(new Font("Test", Font.PLAIN, 18));
		miTabla.setModel(model);
		model.addColumn("Destino");
		model.addColumn("Hora Salida");
		model.addColumn("Hora Llegada");
		model.addColumn("Puerta");
		model.addColumn("ID");
		model.addColumn("Embarque");
		model.addColumn("Compa�ia");
		model.addColumn("Estado");
		DefaultTableCellRenderer d = new DefaultTableCellRenderer();
		d.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < 8; i++) {
			miTabla.getColumnModel().getColumn(i).setCellRenderer(d);
		}
		DefaultTableCellRenderer bw = new DefaultTableCellRenderer();
		bw.setHorizontalAlignment(JLabel.CENTER);
		bw.setForeground(Color.YELLOW);
		bw.setBackground(Color.BLACK);
		miTabla.getColumnModel().getColumn(7).setCellRenderer(bw);
		miTabla.getTableHeader().setReorderingAllowed(false);
		this.dao = inf_controller.getDao();
		miTabla.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = miTabla.rowAtPoint(evt.getPoint());
				selectedCol = miTabla.columnAtPoint(evt.getPoint());
				if (row >= 0 && selectedCol >= 0) {
					token = miTabla.getValueAt(row, selectedCol);
					flag = false;
					inf_controller.search();
				}
			}
		});
	}

	public void refresh() {
		try {
			dao.refreshDate(model, model2, flag);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		model.fireTableDataChanged();
		model2.fireTableDataChanged();
		
	}

	public JPanel getMainInfo() {
		return mainInfo;
	}

	public void setMainInfo(JPanel mainInfo) {
		this.mainInfo = mainInfo;
	}

	public JTable getMiTabla() {
		return this.miTabla;
	}

	public inf_controller getInfCont() {
		return this.cont;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(NotifyData n) {
		switch (n.getN()) {
		case INF_ADD:
			flag = true;
			model.setRowCount(0);
			model2.setRowCount(0);
			dao.buscarVuelosTableModel(model,model2, (List<Flight>) n.getData());
			model.fireTableDataChanged();
			break;
		
		case INF_S_ARR:
			model2.setRowCount(0);
			dao.StringArrVuelosTableModel(model2, token, (List<Flight>) n.getData());
			model2.fireTableDataChanged();
			break;
		case INF_S_DEP:
			model.setRowCount(0);
			dao.StringVuelosTableModel(model, token,(List<Flight>) n.getData());
			model.fireTableDataChanged();
			break;
			
		default:
			break;
		}
	}
}
