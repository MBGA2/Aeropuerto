package Vista.Subsistemas;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

import Controladores.tor_controller;

import java.awt.event.ActionListener;


import java.awt.event.ActionEvent;

public class tor_view extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public tor_view(tor_controller cont) {
		
		
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
		
		JLabel lblNewLabel_1 = new JLabel("N\u00FAmero de vuelo a asignar: ");
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*flight search */
			}
		});
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setForeground(Color.BLACK);
		layeredPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		layeredPane.setBackground(Color.BLACK);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(230)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 189, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(26)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)))
					.addGap(61))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(91)
									.addComponent(btnNewButton))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(60)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(30)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(layeredPane, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE)))
					.addGap(53))
		);
		
		JLabel lblNewLabel_2 = new JLabel("Informaci\u00F3n Vuelo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(28, 11, 111, 14);
		layeredPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Introducir n\u00FAmero de vuelo ");
		lblNewLabel_3.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_3.setBounds(10, 35, 142, 14);
		layeredPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("asociado: ");
		lblNewLabel_4.setBounds(10, 49, 101, 14);
		layeredPane.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(39, 71, 86, 20);
		layeredPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*flight search*/
			}
		});
		btnNewButton_1.setBounds(39, 102, 89, 23);
		layeredPane.add(btnNewButton_1);
		contentPane.setLayout(gl_contentPane);
	}

	public JPanel getMainPanel() {
		return contentPane;
	}

}
