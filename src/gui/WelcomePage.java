package gui;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;

public class WelcomePage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomePage frame = new WelcomePage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WelcomePage() {
		setForeground(Color.WHITE);
		setTitle("Duoro > LogIn");
		setBackground(new Color(0, 0, 102));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 444, 560);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);

		setContentPane(contentPane);
		ImageIcon img = new ImageIcon(this.getClass().getResource("/logomedtekst_5_10.jpg"));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/logomedtekst_5_10.jpg"));
		lblNewLabel.setIcon(img2);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		
		contentPane.add(panel_1, BorderLayout.CENTER);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setBackground(Color.WHITE);
		panel_1.add(panel_1_1);
		
		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 10));
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Medarbejder ID:");
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		passwordField = new JPasswordField();
		passwordField.setBackground(Color.WHITE);
		
		JLabel lblNewLabel_4 = new JLabel("Adgangskode:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		JButton btnNewButton = new JButton("Log In ");
		
		btnNewButton.addActionListener(e -> logInClicked());
		
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		btnNewButton.setBackground(UIManager.getColor("Button.darkShadow"));
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(22)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addGap(29))
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(41)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel_1_1.createSequentialGroup()
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1_1.createSequentialGroup()
									.addComponent(lblNewLabel_3)
									.addContainerGap())
								.addGroup(gl_panel_1_1.createSequentialGroup()
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(49, Short.MAX_VALUE))))))
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(24)
					.addComponent(lblNewLabel_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		panel_1_1.setLayout(gl_panel_1_1);
	}
	

	
	private void logInClicked() {
		setVisible(false);
		dispose();
		Menu menu = new Menu();
		menu.setVisible(true);
	}
}
