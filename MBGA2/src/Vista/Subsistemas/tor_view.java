package Vista.Subsistemas;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import Controladores.tor_controller;
import SA.tor_SA;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class tor_view extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private tor_SA sa;
	private tor_controller tor;
	private DefaultTableModel tableModel;
	private JTable table;
	private JButton button;

	public tor_view(tor_controller cont) {
		this.tor = cont;
		this.sa = tor.getSA();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		textField = new JTextField();
		textField.setColumns(10);
		table = new JTable();
		table.setForeground(Color.BLACK);
		this.tableModel = new DefaultTableModel(
				new Object[][] {
						{ "#", "Destino", "Hora Salida", "Hora Llegada", "Puerta", "ID", "Compania", "Estado" }, },
				new String[] { "#", "Destino", "Hora Salida", "Hora Llegada", "Puerta", "ID", "Compania", "Estado" });
		table.setModel(tableModel);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		JLabel lblNewLabel = new JLabel("Ver vuelos cancelados");
		JLabel lblNewLabel_1 = new JLabel("Asignar puertas");
		button = new JButton("Asignar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sa.addGates(tableModel);
			}
		});
		JLabel lblNewLabel_2 = new JLabel("TORRE DE CONTROL");
		lblNewLabel_2.setFont(new Font("Georgia", Font.BOLD, 22));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap(24, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addComponent(lblNewLabel_1).addComponent(lblNewLabel))
										.addGap(36).addComponent(button))
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_2).addComponent(table, GroupLayout.PREFERRED_SIZE,
												379, GroupLayout.PREFERRED_SIZE)))
						.addGap(21)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(
				gl_contentPane.createSequentialGroup().addContainerGap().addComponent(lblNewLabel_2).addGap(20)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(lblNewLabel_1)
								.addComponent(button))
						.addGap(53).addComponent(lblNewLabel).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(20, Short.MAX_VALUE)));
		contentPane.setLayout(gl_contentPane);
	}

	public JPanel getMainPanel() {
		return contentPane;
	}

}