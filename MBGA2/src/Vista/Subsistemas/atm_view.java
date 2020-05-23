package Vista.Subsistemas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.*;

import Controladores.atm_controller;

@SuppressWarnings("serial")
public class atm_view extends JFrame {

	private JPanel panel;
	private JButton searchPlane;
	private JButton searchPath;
	private JButton searchAuthority;
	private JTextField fieldPlane;
	private JTextField fieldPath;
	private JTable dataTable;
	private JTable sarTable;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableSar;
	private JScrollPane table;
	private JScrollPane tableSarPane;
	private JDialog dialog;
	private atm_controller atm_ctrl;

	private JButton crashButton;
	private JTextField crashField;
	private JButton delayButton;
	private JTextField delayField;

	private JSlider rCrash;
	private JSlider rDelay;
	private JLabel pCrash;
	private JLabel pDelay;

	public atm_view(atm_controller atm_controller) {
		this.atm_ctrl = atm_controller;

		this.panel = new JPanel();
		this.panel.setLayout(new GridBagLayout());

		this.searchPlane = new JButton("Buscar Avion");
		this.searchPath = new JButton("Buscar Ruta");
		this.searchAuthority = new JButton("Mostrar SAR");
		this.fieldPlane = new JTextField(20);
		this.fieldPath = new JTextField(20);

		this.crashButton = new JButton("Estrellar Avion");
		this.crashField = new JTextField(20);
		this.delayButton = new JButton("Retrasar Avion");
		this.delayField = new JTextField(20);

		this.dialog = new JDialog();

		this.rCrash = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		this.rDelay = new JSlider(JSlider.HORIZONTAL, 0, 100, 10);
		this.pCrash = new JLabel("Probabilidad de Accidente");
		this.pDelay = new JLabel("Probabilidad de Retraso");

		this.tableSar = new DefaultTableModel() {

			String[] planeInfo = { "ID", "Tipo", "Estado", "Avion Rescatado", "Alerta", "Retraso" };

			@Override
			public int getColumnCount() {
				return planeInfo.length;
			}

			@Override
			public String getColumnName(int index) {
				return planeInfo[index];
			}
		};

		this.tableModel = new DefaultTableModel() {
			List<Color> rowColours = Arrays.asList(Color.RED, Color.GREEN, Color.CYAN);

			@SuppressWarnings("unused")
			public void setRowColour(int row, Color c) {
				rowColours.set(row, c);
				fireTableRowsUpdated(row, row);
			}

			@SuppressWarnings("unused")
			public Color getRowColour(int row) {
				return rowColours.get(row);
			}

			String[] planeInfo = { "ID", "Destino", "Salida", "Llegada", "Escala", "Compa�ia", "Estado", "Alerta",
					"Retraso" };

			@Override
			public int getColumnCount() {
				return planeInfo.length;
			}

			@Override
			public String getColumnName(int index) {
				return planeInfo[index];
			}
		};
		this.dataTable = new JTable(this.tableModel);
		this.table = new JScrollPane(this.dataTable);
		DefaultTableCellRenderer bw = new DefaultTableCellRenderer();
		bw.setHorizontalAlignment(JLabel.CENTER);
		bw.setForeground(Color.RED);
		bw.setBackground(Color.BLACK);
		this.dataTable.getColumnModel().getColumn(7).setCellRenderer(bw);
		this.table.setPreferredSize(new Dimension(900, 600));
		this.table.setViewportView(dataTable);
		this.getContentPane().add(this.panel);
		this.sarTable = new JTable(this.tableSar);
		this.tableSarPane = new JScrollPane(this.sarTable);
		this.rCrash.setMajorTickSpacing(50);
		this.rCrash.setMinorTickSpacing(10);
		this.rCrash.setPaintTicks(true);
		this.rCrash.setPaintLabels(true);
		this.rDelay.setMajorTickSpacing(50);
		this.rDelay.setMinorTickSpacing(10);
		this.rDelay.setPaintTicks(true);
		this.rDelay.setPaintLabels(true);
		this.pCrash.setLabelFor(this.rCrash);
		this.pDelay.setLabelFor(this.rDelay);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; // El �rea de texto empieza en la columna cero.
		constraints.gridy = 0; // El �rea de texto empieza en la fila cero
		constraints.gridwidth = 1; // El �rea de texto ocupa dos columnas.
		constraints.gridheight = 1; // El �rea de texto ocupa 2 filas.
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new Insets(5, 15, 5, 0);
		this.panel.add(this.searchPlane, constraints);
		constraints.weighty = 1.0;
		constraints.gridy = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		this.panel.add(this.searchPath, constraints);
		constraints.weighty = 1.0;
		constraints.gridy = 2;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		this.panel.add(this.searchAuthority, constraints);
		constraints.gridwidth = 2;
		constraints.weighty = 1.0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		this.panel.add(this.rCrash, constraints);
		constraints.weighty = 1.0;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		this.panel.add(this.rDelay, constraints);
		constraints.weighty = 1.0;
		constraints.gridy = 3;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		this.panel.add(this.pCrash, constraints);
		constraints.weighty = 1.0;
		constraints.gridy = 4;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		this.panel.add(this.pDelay, constraints);
		constraints.gridwidth = 1;
		constraints.weighty = 1.0;
		constraints.gridy = 0;
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.SOUTH;
		this.panel.add(this.fieldPlane, constraints);
		constraints.weighty = 1.0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		this.panel.add(this.fieldPath, constraints);
		constraints.weighty = 1.0;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 5;
		this.panel.add(this.table, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 115, 5, 0);
		constraints.ipady = 40;
		constraints.weighty = 1.0;
		constraints.gridx = 2;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panel.add(this.crashButton, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(0, 115, 0, 0);
		constraints.ipady = 0;
		constraints.weighty = 1.0;
		constraints.gridx = 2;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panel.add(this.crashField, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(10, 235, 5, 0);
		constraints.ipady = 40;
		constraints.weighty = 1.0;
		constraints.gridx = 4;
		constraints.gridy = 6;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panel.add(this.delayButton, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(0, 235, 0, 0);
		constraints.ipady = 0;
		constraints.weighty = 1.0;
		constraints.gridx = 4;
		constraints.gridy = 7;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panel.add(this.delayField, constraints);
		this.atm_ctrl.addModels(this.tableModel, this.tableSar);
		this.dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = dataTable.rowAtPoint(evt.getPoint());
				if (row >= 0) {
					crashField.setText((String) dataTable.getValueAt(row, 0));
					delayField.setText((String) dataTable.getValueAt(row, 0));
					fieldPlane.setText((String) dataTable.getValueAt(row, 0));
					fieldPath.setText((String) dataTable.getValueAt(row, 0));
				}
			}
		});
		this.searchPlane.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atm_ctrl.searchPlane(crashField.getText());
			}
		});

		this.searchPath.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atm_ctrl.searchPath(crashField.getText());
			}
		});

		this.searchAuthority.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
				dialog.setTitle("Lista de aviones de salvamento");
				dialog.setContentPane(tableSarPane);
				dialog.setSize(500, 200);
				dialog.setLocation(500, 500);
			}
		});

		this.crashButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atm_ctrl.crashPlane(crashField.getText());
			}
		});

		this.delayButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				atm_ctrl.delayPlane(delayField.getText());
			}
		});

		this.rCrash.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting())
					atm_ctrl.randomCrash(source.getValue());
			}
		});

		this.rDelay.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting())
					atm_ctrl.randomDelay(source.getValue());
			}
		});
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
		dataTable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(dataTable.getColumnCount());
		for (int i = 0; i < dataTable.getColumnCount(); i++)
			sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
	}

	public JPanel getMainPanel() {
		return this.panel;
	}
}
