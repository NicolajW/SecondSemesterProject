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

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Kassesystem");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JButton btnNewButton_1 = new JButton("Lagersystem");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JLabel lblNewLabel = new JLabel("DUORO WINE BAR");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.BOLD, 40));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(190)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 239, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)
					.addGap(222))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(367, Short.MAX_VALUE)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addGap(331))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(74)
					.addComponent(lblNewLabel)
					.addGap(141)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
					.addContainerGap(239, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

}
