package Vista.Subsistemas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

import Controladores.tor_controller;
import DAOs.tor_dao;

import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;

public class tor_view extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private tor_dao dao;
	private tor_controller tor;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public tor_view(tor_controller cont) {
		
		this.tor = cont;
		this.dao = tor.getDao();
		//this.tor.addModelObserver(this);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Torre de Control");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("ASIGNAR PUERTAS");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dao.addGates();
			}
		});
	this.contentPane.add(btnNewButton);

	this.contentPane.add(lblNewLabel);

	}
		
		
		
	

	public JPanel getMainPanel() {
		return contentPane;
	}

}