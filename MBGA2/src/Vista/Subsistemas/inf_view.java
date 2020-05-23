package Vista.Subsistemas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.table.*;

import Controladores.inf_controller;
import Vista.MainView;

public class inf_view {
	private JPanel mainInfo;
	private JPanel search;
	private JTable miTabla;
	private JScrollPane miBarra;
	private JTable miTabla2;
	private JScrollPane miBarra2;
	private DefaultTableModel model;
	private DefaultTableModel model2;
	private JTextField searchBar;
	private Object token;
	private int selectedCol;
	private inf_controller inf_controller;

	public inf_view(inf_controller i) {
		this.inf_controller = i;

		this.setMainInfo(new JPanel());
		mainInfo.setLayout(new BorderLayout());
		salidas();
		llegadas();
		busqueda();
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
		this.inf_controller.addTables(model, model2);
	}

	private void busqueda() {
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
					model.setRowCount(0);
					model2.setRowCount(0);
					token = searchBar.getText();
					inf_controller.search((String) token);
					if (model.getRowCount() == 0) {
						int option = JOptionPane.showOptionDialog(null,
								"No existe ningun vuelo: '" + searchBar.getText() + "' Mostrar todos los vuelos?",
								"Busqueda de vuelo", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, null,
								null);
						if (option == 0) {
							inf_controller.resetFlag();
						}
					}
				}
			}
		});
	}

	@SuppressWarnings("serial")
	private void llegadas() {
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
		model2.addColumn("Incidencias");
		DefaultTableCellRenderer d = new DefaultTableCellRenderer();
		d.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < 6; i++) {
			miTabla2.getColumnModel().getColumn(i).setCellRenderer(d);
		}
		DefaultTableCellRenderer bw = new DefaultTableCellRenderer();
		bw.setHorizontalAlignment(JLabel.CENTER);
		bw.setForeground(Color.YELLOW);
		bw.setBackground(Color.BLACK);
		miTabla2.getColumnModel().getColumn(4).setCellRenderer(bw);
		miTabla2.getTableHeader().setReorderingAllowed(false);
		miTabla2.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = miTabla2.rowAtPoint(evt.getPoint());
				selectedCol = miTabla2.columnAtPoint(evt.getPoint());
				if (row >= 0 && selectedCol >= 0) {
					token = miTabla2.getValueAt(row, selectedCol);
					inf_controller.search((String) token);
				}
			}
		});
	}

	private void salidas() {
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
		model.addColumn("Compañia");
		model.addColumn("Estado");
		model.addColumn("Incidencias");
		DefaultTableCellRenderer d = new DefaultTableCellRenderer();
		d.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < 9; i++) {
			miTabla.getColumnModel().getColumn(i).setCellRenderer(d);
		}
		DefaultTableCellRenderer bw = new DefaultTableCellRenderer();
		bw.setHorizontalAlignment(JLabel.CENTER);
		bw.setForeground(Color.YELLOW);
		bw.setBackground(Color.BLACK);
		miTabla.getColumnModel().getColumn(7).setCellRenderer(bw);
		miTabla.getTableHeader().setReorderingAllowed(false);
		miTabla.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = miTabla.rowAtPoint(evt.getPoint());
				selectedCol = miTabla.columnAtPoint(evt.getPoint());
				if (row >= 0 && selectedCol >= 0) {
					token = miTabla.getValueAt(row, selectedCol);
					inf_controller.search((String) token);
				}
			}
		});
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
		return this.inf_controller;
	}

}
