package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Basket extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Basket dialog = new Basket();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Basket() {
		setTitle("\"Duoro > LogIn > Menu > Kassesystem > Indkøbskurv\"");
		setBounds(100, 100, 290, 270);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[121px]", "[24px][][][]"));
		{
			btnNewButton_1 = new JButton("Mobilepay");
			btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
			
			
		}
		{
			lblNewLabel = new JLabel("Betal");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
			contentPanel.add(lblNewLabel, "cell 0 0,alignx center,aligny center");
		}
		contentPanel.add(btnNewButton_1, "cell 0 1,alignx center,aligny center");
		{
			btnNewButton = new JButton("Betalingskort");
			btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
			contentPanel.add(btnNewButton, "cell 0 2,alignx center,aligny center");
		}
		{
			btnNewButton_2 = new JButton("Kontant");
			btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 17));
			contentPanel.add(btnNewButton_2, "cell 0 3,alignx center,aligny center");
		}
		{
		    JPanel buttonPane1 = new JPanel();
		    buttonPane1.setBackground(Color.WHITE);
		    buttonPane1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		    getContentPane().add(buttonPane1, BorderLayout.SOUTH);
		    {
		        JButton cancelButton = new JButton("Annuller");
		        cancelButton.setActionCommand("Cancel");
		        buttonPane1.add(cancelButton); 
		        cancelButton.addActionListener(e -> goToCreateOrder());
		    }
		}
	}
		private void goToCreateOrder() {
		    dispose();
		    CreateOrder createOrder = new CreateOrder();
		    createOrder.setVisible(false);
		}
}
