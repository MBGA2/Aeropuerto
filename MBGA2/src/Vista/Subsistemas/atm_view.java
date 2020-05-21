package Vista.Subsistemas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Controladores.atm_controller;

@SuppressWarnings("serial")
public class atm_view extends JFrame{

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
	private JDialog dialog;
	private atm_controller atm_ctrl;

	private JButton crashButton;
	private JTextField crashField;
	private JButton delayButton;
	private JTextField delayField;

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

		this.tableSar = new DefaultTableModel() {

			String[] planeInfo = { "ID", "Tipo", "Estado", "Avion Rescatado", "Alerta",
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
		this.table.setPreferredSize(new Dimension(900, 600));
		this.table.setViewportView(dataTable);
		this.getContentPane().add(this.panel);
		
		this.sarTable = new JTable(this.tableSar);
		

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; // El �rea de texto empieza en la columna cero.
		constraints.gridy = 0; // El �rea de texto empieza en la fila cero
		constraints.gridwidth = 1; // El �rea de texto ocupa dos columnas.
		constraints.gridheight = 1; // El �rea de texto ocupa 2 filas.
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new Insets(5, 15, 5, 0);
		// constraints.ipady = 40;
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

		constraints.weighty = 1.0;
		constraints.gridy = 0;
		constraints.gridx = 1;
		constraints.anchor = GridBagConstraints.SOUTH;
		this.panel.add(this.fieldPlane, constraints);
		constraints.weighty = 1.0;
		constraints.gridy = 1;
		constraints.anchor = GridBagConstraints.CENTER;
		this.panel.add(this.fieldPath, constraints);
//		constraints.weighty = 1.0;
//		constraints.gridy = 2;
//		constraints.anchor = GridBagConstraints.NORTH;
//		this.panel.add(this.fieldAuthority, constraints);

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

//		this.dao.tableFill(tableModel);
		this.atm_ctrl.addModels(this.tableModel,this.tableSar);

		this.dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = dataTable.rowAtPoint(evt.getPoint());
				if (row >= 0) {
					crashField.setText((String) dataTable.getValueAt(row, 0));
					delayField.setText((String) dataTable.getValueAt(row, 0));
				}
			}
		});

		this.searchPlane.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}

		});
		
		this.searchAuthority.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(true);
				dialog.setTitle("Lista de aviones de salvamento");
				dialog.setContentPane(sarTable);
				dialog.setSize(500, 200);
				dialog.setLocation(500,500);
			}

		});

		this.crashButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				for (Flight f : dao.getAirport().getFligths())
//					if (f.getID().equalsIgnoreCase(crashField.getText()))
//						dao.getAirport().notifyAllO(new NotifyData(NTYPE.ATM_CRASH, f));
				atm_ctrl.crashPlane(crashField.getText());
			}

		});

		this.delayButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

//				for (Flight f : dao.getAirport().getFligths())
//					if (f.getID().equalsIgnoreCase(delayField.getText()))
//						dao.getAirport().notifyAllO(new NotifyData(NTYPE.ATM_DAMAGED, f));
				atm_ctrl.delayPlane(delayField.getText());
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

//	private void tableRefresh() {
//		this.tableModel.setRowCount(0);
//		this.dao.tableFill(this.tableModel);
//		this.tableModel.fireTableDataChanged();
//	}

//	@Override
//	public void update(NotifyData n) {
//		switch (n.getN()) {
//		case ATM_REFRESH:
//			//this.atm.addFlight((Flight) n.getData());
//			this.tableRefresh();
//			break;
//		case ATM_CRASH:
//			this.atm.planeCrash((Flight) n.getData());
//			this.tableRefresh();
//			break;
//		case ATM_DAMAGED:
//			this.atm.planeDamaged((Flight) n.getData());
//			this.tableRefresh();
//			break;
//		case REFRESH:
//			this.tableRefresh();
//			break;
//		default:
//			break;
//		}
//	}
}
