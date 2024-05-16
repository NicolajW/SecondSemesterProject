package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel menuPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("Duoro > LogIn > Menu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 536);
		menuPane = new JPanel();
		menuPane.setBackground(Color.WHITE);
		menuPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(menuPane);
		menuPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		menuPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/baggrund_2_35.jpg"));
		lblNewLabel.setIcon(img);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		menuPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("Salgssystem");
		btnNewButton.setBounds(50, 42, 195, 45);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(e -> orderClicked());
        panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lagersystem");
		btnNewButton_1.setBounds(343, 42, 199, 45);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(e -> storageClicked());
        panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Salgsstatistik");
		btnNewButton_2.setBounds(50, 139, 203, 45);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Indstillinger");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_3.setBounds(343, 139, 189, 45);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Administration");
		btnNewButton_4.setBounds(48, 236, 223, 45);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Log af");
		btnNewButton_5.setBounds(343, 236, 157, 45);
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_5.addActionListener(e -> backToWelcomePage());
		panel_1.add(btnNewButton_5);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setForeground(Color.WHITE);
		menuPane.add(panel_2, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Logged in:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.WEST;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		panel_2.add(textField, gbc_textField);
		textField.setColumns(10);
	}
	
	private void orderClicked() {
		setVisible(false);
		dispose();
		CreateOrder co = new CreateOrder();
		co.setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	private void storageClicked() {
		setVisible(false);
		dispose();
		Storage stor = new Storage(null);
		stor.setVisible(true);
	}

	private void backToWelcomePage() {
	    setVisible(false);
	    dispose();
	    WelcomePage welcomePage = new WelcomePage(); 
	    welcomePage.setVisible(true);
	}
	
}



