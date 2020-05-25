package Vista.Subsistemas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

import javax.swing.*;
import javax.swing.table.*;

import Controladores.adu_controller;



@SuppressWarnings("serial")
public class adu_view extends JFrame {

	private adu_controller adu_ctrl;
	
	private JPanel panel;
	
	private JButton searchFormID;
	private JButton addForms;
	
	private JTextField formID;
	private JTextField IDpass;
	
	private JTable dataTable;
	
	private DefaultTableModel tableModel;
	
	private JScrollPane table;

	private JButton acceptForm;
	private JButton NegForm;
	private JTextField neg;
	private JTextField ace;
	



	public adu_view(adu_controller adu_controller) {
		this.adu_ctrl = adu_controller;

		this.panel = new JPanel();
		
		this.panel.setLayout(new GridBagLayout());

		this.searchFormID = new JButton("Buscar ID");
		this.addForms = new JButton("Meter Formularios");
		
		this.formID = new JTextField(20);
		this.IDpass = new JTextField(20);
		

		this.acceptForm = new JButton("Aceptar peticion");
		this.ace = new JTextField(20);
		this.NegForm = new JButton("Denegar peticion");
		this.neg = new JTextField(20);
		


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

			String[] formInfo = { "ID", "VUELO", "Pasajero", "Contacto",  "Destino", "Peso(Kg.)",  "Descripcion", "Impuesto", "ESTADO" };

			@Override
			public int getColumnCount() {
				return formInfo.length;
			}

			@Override
			public String getColumnName(int index) {
				return formInfo[index];
			}
		};
		this.dataTable = new JTable(this.tableModel);
		this.table = new JScrollPane(this.dataTable);
		
		this.table.setPreferredSize(new Dimension(800,600));
		this.table.setViewportView(dataTable);
		this.getContentPane().add(this.panel);
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0; // El �rea de texto empieza en la columna cero.
		constraints.gridy = 0; // El �rea de texto empieza en la fila cero
		constraints.gridwidth = 1; // El �rea de texto ocupa dos columnas.
		constraints.gridheight = 1; // El �rea de texto ocupa 2 filas.
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.SOUTH;
		constraints.insets = new Insets(5, 15, 5, 0);
		this.panel.add(this.searchFormID, constraints);
		constraints.weighty = 1.0;
		constraints.gridy = 0;
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.SOUTH;
		///
		this.panel.add(this.formID, constraints);
		constraints.weighty = 1.0;
		constraints.gridy = 2;
		constraints.gridx = 0;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.NORTH;
		
		this.panel.add(this.addForms, constraints);
		constraints.gridwidth = 1;
		constraints.weighty = 1.0;
		constraints.gridy = 1;
		constraints.gridx= 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weighty = 1.0;
		constraints.gridx = 2;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 3;

		this.adu_ctrl.addTables(this.tableModel);
		this.panel.add(this.table, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.ipady = 40;
		constraints.weighty = 1.0;
		constraints.gridx = 2;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panel.add(this.acceptForm, constraints);
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.ipady = 40;
		constraints.weighty = 1.0;
		constraints.gridx = 3;
		constraints.gridy = 4;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panel.add(this.NegForm, constraints);
		
		
		constraints.anchor = GridBagConstraints.CENTER;
		//constraints.insets = new Insets(0, 235, 0, 0);
		constraints.ipady = 0;
		constraints.weighty = 1.0;
		constraints.gridx = 3;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panel.add(this.neg, constraints);
		constraints.ipady = 0;
		constraints.weighty = 1.0;
		constraints.gridx = 2;
		constraints.gridy = 5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		this.panel.add(this.ace, constraints);
		
		
		this.dataTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = dataTable.rowAtPoint(evt.getPoint());
				if (row >= 0) {
					ace.setText((String) dataTable.getValueAt(row, 0));
					neg.setText((String) dataTable.getValueAt(row, 0));
					formID.setText((String) dataTable.getValueAt(row, 0));
					IDpass.setText((String) dataTable.getValueAt(row, 0));
				}
			}
		});
		

		

		this.addForms.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String n = JOptionPane.showInputDialog("Inserta un numero de forms");
				
				initF(n);
				JOptionPane.showConfirmDialog(null, "Forms anadidos", "", JOptionPane.DEFAULT_OPTION);		}
		});
		
		this.searchFormID.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adu_ctrl.cout(formID.getText());
			}
		});

		this.acceptForm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adu_ctrl.changeStateAce(ace.getText());
			}
		});

		this.NegForm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				adu_ctrl.changeStateDen(neg.getText());
			}
		});

		
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableModel);
		dataTable.setRowSorter(sorter);
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(dataTable.getColumnCount());
		for (int i = 0; i < dataTable.getColumnCount(); i++)
			sortKeys.add(new RowSorter.SortKey(i, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		
	
		
	}

	protected void initF(String num) {
		int n = Integer.parseInt(num);
		try {
			this.adu_ctrl.generateForm(n);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		;
	}

	public JPanel getMainPanel() {
		return this.panel;
	}
}