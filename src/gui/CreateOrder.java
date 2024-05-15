package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.ScrollPane;
import javax.swing.JScrollBar;
import java.awt.Panel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Button;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JProgressBar;
import java.awt.Label;
import javax.swing.border.LineBorder;
import java.awt.List;
import javax.swing.ScrollPaneConstants;

public class CreateOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateOrder frame = new CreateOrder();
					 frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
	public CreateOrder() {
		setBackground(new Color(255, 255, 255));
		setTitle("Duoro > LogIn > Menu > Kassesystem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 1000);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	
		
	        
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("DUORO PRODUCTS");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		Button button = new Button("Kurv");
		panel_1.add(button);
		
		JPanel productPanel = new JPanel();
		productPanel.setBackground(Color.WHITE);
		contentPane.add(productPanel, BorderLayout.CENTER);
		productPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JPanel firstWine = new JPanel();
		firstWine.setBorder(new LineBorder(new Color(0, 0, 0)));
		firstWine.setBackground(Color.WHITE);
		productPanel.add(firstWine);
		firstWine.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Tilføj til Kurv");
		firstWine.add(btnNewButton, BorderLayout.SOUTH);
		
		JLabel lblRose = new JLabel("<html>Sao Lourenco Hvid<br>Pris: 300,-<br>Pris pr glas 60,-</html>");
		lblRose.setHorizontalAlignment(SwingConstants.CENTER);
		firstWine.add(lblRose, BorderLayout.CENTER);
		
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		firstWine.add(lblNewLabel_2, BorderLayout.NORTH);
		ImageIcon img2 = new ImageIcon(this.getClass().getResource("/SaoLourencoHvid.jpg"));
		lblNewLabel_2.setIcon(img2);
	
		
		JPanel secondWine = new JPanel();
		secondWine.setBorder(new LineBorder(new Color(0, 0, 0)));
		secondWine.setBackground(Color.WHITE);
		productPanel.add(secondWine);
		secondWine.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("<html>Carqueijal Rose<br>Pris pr flaske: 150,-<br>Pris pr glas 35,-</html>");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		secondWine.add(lblNewLabel_1, BorderLayout.CENTER);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		secondWine.add(lblNewLabel_3, BorderLayout.NORTH);
		ImageIcon img3 = new ImageIcon(this.getClass().getResource("/CarqueijalRose.jpg"));
		lblNewLabel_3.setIcon(img3);
		
		JButton btnNewButton_1 = new JButton("Tilføj til Kurv");
		secondWine.add(btnNewButton_1, BorderLayout.SOUTH);
		
		JPanel thirdWine = new JPanel();
		thirdWine.setBorder(new LineBorder(new Color(0, 0, 0)));
		thirdWine.setBackground(Color.WHITE);
		productPanel.add(thirdWine);
		thirdWine.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton_2 = new JButton("Tilføj til Kurv");
		thirdWine.add(btnNewButton_2, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_4 = new JLabel("<html>Seara d'ORDENS 10<br>Pris: 400,-<br>Pris pr glas 70,-</html>");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		thirdWine.add(lblNewLabel_4, BorderLayout.CENTER);
//		ImageIcon img4 = new ImageIcon(this.getClass().getResource("/vin.jpg"));
//		lblNewLabel_4.setIcon(img4);
	
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		thirdWine.add(lblNewLabel_5, BorderLayout.NORTH);
		ImageIcon img5 = new ImageIcon(this.getClass().getResource("/SearaDordens10.jpg"));
		lblNewLabel_5.setIcon(img5);
		
		
		
		JPanel hvidvin = new JPanel();
		hvidvin.setBorder(new LineBorder(new Color(0, 0, 0)));
		hvidvin.setBackground(Color.WHITE);
		productPanel.add(hvidvin);
		hvidvin.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_6 = new JLabel("<html>Seara d'Ordens Vintage<br>Pris: 500,-<br>Pris pr glas 85,-</html>");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		hvidvin.add(lblNewLabel_6, BorderLayout.CENTER);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		hvidvin.add(lblNewLabel_7, BorderLayout.NORTH);
		ImageIcon img7 = new ImageIcon(this.getClass().getResource("/SearaDordensVintage2009.jpg"));
		lblNewLabel_7.setIcon(img7);
		
		
		JButton btnNewButton_3 = new JButton("Tilføj til Kurv");
		hvidvin.add(btnNewButton_3, BorderLayout.SOUTH);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_8 = new JLabel("");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_8, BorderLayout.NORTH);
		ImageIcon img8 = new ImageIcon(this.getClass().getResource("/ValladoHvid.jpg"));
		lblNewLabel_8.setIcon(img8);
		
		JLabel lblNewLabel_15 = new JLabel("<html>Vallado Hvidvin<br>Pris: 200,-<br>Pris pr glas 45,-</html>");
		lblNewLabel_15.setForeground(Color.WHITE);
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_15, BorderLayout.CENTER);
		
		JButton btnNewButton_4 = new JButton("Tilføj til Kurv");
		panel_2.add(btnNewButton_4, BorderLayout.SOUTH);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_9 = new JLabel("");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_9, BorderLayout.NORTH);
		
		JLabel lblNewLabel_14 = new JLabel("");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_14, BorderLayout.CENTER);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_10 = new JLabel("");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_10, BorderLayout.NORTH);
		ImageIcon img9 = new ImageIcon(this.getClass().getResource("/ValladoHvid.jpg"));
		lblNewLabel_9.setIcon(img9);
		
		JLabel lblNewLabel_13 = new JLabel("");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_4.add(lblNewLabel_13, BorderLayout.CENTER);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_11 = new JLabel("");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_11, BorderLayout.NORTH);
		
		JLabel lblNewLabel_12 = new JLabel("");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_5.add(lblNewLabel_12, BorderLayout.CENTER);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_16 = new JLabel("New label");
		panel_6.add(lblNewLabel_16, BorderLayout.NORTH);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_17 = new JLabel("New label");
		panel_7.add(lblNewLabel_17, BorderLayout.NORTH);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.add(panel_8);
		panel_8.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("New label");
		panel_8.add(label, BorderLayout.NORTH);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		productPanel.add(panel_9);
		panel_9.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane(productPanel);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.getVerticalScrollBar().setBlockIncrement(100);
	}
	
}

