package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import db.DataAccessException;
import model.Person;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
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
	private JTextField txtEmployee;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu(null);
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
	public Menu(Person p) {
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
		btnNewButton.setBounds(334, 29, 195, 45);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.addActionListener(e -> {
			try {
				orderClicked(p);
			} catch (DataAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		panel_1.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Lagersystem");
		btnNewButton_1.setBounds(330, 121, 199, 45);
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.addActionListener(e -> storageClicked());
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Salgsstatistik");
		btnNewButton_2.addActionListener( e-> statisticsClicked());
		btnNewButton_2.setBounds(64, 217, 195, 45);
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("Indstillinger");
		btnNewButton_3.addActionListener(e -> settingClicked());
		btnNewButton_3.setBounds(64, 121, 195, 45);
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Borde");
		btnNewButton_4.addActionListener(e -> openTableGui());
		btnNewButton_4.setBounds(64, 29, 195, 45);
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_1.add(btnNewButton_4);

		JButton btnNewButton_5 = new JButton("Log af");
		btnNewButton_5.setBounds(330, 217, 199, 45);
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_5.addActionListener(e -> backToWelcomePage());
		panel_1.add(btnNewButton_5);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setForeground(Color.WHITE);
		menuPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("Logged in:");
		panel_2.add(lblNewLabel_1, BorderLayout.WEST);

		txtEmployee = new JTextField();
		txtEmployee.setEditable(false);
		panel_2.add(txtEmployee, BorderLayout.CENTER);
		txtEmployee.setColumns(10);
		
		init(p);
	}


	private void init(Person p) {
		setEmployeeID(p);
	}

	private void orderClicked(Person p) throws DataAccessException {
		ChooseTableWindow ctw = new ChooseTableWindow(p);
		ctw.setVisible(true);
	}

	private void backToWelcomePage() {
		WelcomePage welcomePage = new WelcomePage();
		welcomePage.setVisible(true);
		cancelClicked();
	}

	public void setEmployeeID(Person p) {
		txtEmployee.setText(p.getEmail());
	}
	
	private void cancelClicked() {
		setVisible(false);
		dispose();
	}
	
	private void storageClicked() {
		comingSoon();
	}
	
	private void openTableGui() {
		comingSoon();
	}
	
	protected void statisticsClicked() {
		comingSoon();
	}
	
	protected void settingClicked() {
		comingSoon();
	}
	private void comingSoon() {
		ComingSoonWindow csw = new ComingSoonWindow();
		csw.setVisible(true);
	}
}
