package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel menuPane;

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
		setTitle("Duoro application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 650);
		menuPane = new JPanel();
		menuPane.setBackground(new Color(105, 105, 105));
		menuPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(menuPane);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderClicked();
			}
		});
		btnOrder.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JButton btnNewButton_1 = new JButton("Lagersystem");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StorageClicked();
			}
		});
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel lblNewLabel = new JLabel("DUORO WINE BAR");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 40));
		GroupLayout gl_menuPane = new GroupLayout(menuPane);
		gl_menuPane.setHorizontalGroup(
			gl_menuPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_menuPane.createSequentialGroup()
					.addGap(190)
					.addComponent(btnOrder, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addGap(222))
				.addGroup(gl_menuPane.createSequentialGroup()
					.addContainerGap(367, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addGap(331))
		);
		gl_menuPane.setVerticalGroup(
			gl_menuPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuPane.createSequentialGroup()
					.addGap(74)
					.addComponent(lblNewLabel)
					.addGap(141)
					.addGroup(gl_menuPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnOrder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
					.addContainerGap(239, Short.MAX_VALUE))
		);
		menuPane.setLayout(gl_menuPane);
	}

	private void orderClicked() {
		setVisible(false);
		dispose();
		CreateOrder co = new CreateOrder();
		co.setVisible(true);
	}

	private void StorageClicked() {
		setVisible(false);
		dispose();
		Storage stor = new Storage(null);
		stor.setVisible(true);
	}

}
