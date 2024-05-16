package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.Font;
import javax.swing.JTextPane;

public class AddToBasketPopUp extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddToBasketPopUp dialog = new AddToBasketPopUp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AddToBasketPopUp() {
		setTitle("\"Duoro > LogIn > Menu > Kassesystem > TilføjTilKurv\"");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 498, 389);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("");
			contentPanel.add(lblNewLabel, BorderLayout.CENTER);
			ImageIcon img = new ImageIcon(this.getClass().getResource("/SaoLourencoHvid.jpg"));
			lblNewLabel.setIcon(img);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new MigLayout("", "[29px,grow]", "[11px][][grow][][][][][][]"));
			{
				JLabel lblNewLabel_1 = new JLabel("Sao Lourenco Hvid");
				lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
				panel.add(lblNewLabel_1, "cell 0 0,alignx left,aligny top");
			}
			{
				JTextPane txtpnSoLourenoHvidvin = new JTextPane();
				txtpnSoLourenoHvidvin.setEditable(false);
				txtpnSoLourenoHvidvin.setText(
						"São Lourenço hvidvin af Quinta Colinas de São Lourenço produceret af Chardonnay, Sauvignon Blanc og Arinto druer sorter i regionen Bairrada");
				panel.add(txtpnSoLourenoHvidvin, "cell 0 1,grow");
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Pris pr. flaske = 300,-");
				panel.add(lblNewLabel_2, "cell 0 3,alignx left,aligny center");
			}
			{
				textField = new JTextField();
				panel.add(textField, "cell 0 4,growx");
				textField.setColumns(10);
			}
			{
				JLabel lblNewLabel_5 = new JLabel("");
				panel.add(lblNewLabel_5, "cell 0 5");
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Pris pr. glas = 60,-");
				panel.add(lblNewLabel_4, "cell 0 6");
			}
			{
				textField_1 = new JTextField();
				panel.add(textField_1, "cell 0 7,growx");
				textField_1.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			JButton tilføjButton = new JButton("Tilføj");
			buttonPane.add(tilføjButton);
			tilføjButton.addActionListener(e -> {
				tilføjClicked();
			    dispose();
			    
			    });
			
			{
				JButton cancelButton = new JButton("Annuller");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				buttonPane.add(cancelButton);

				
				cancelButton.addActionListener(e -> goToCreateOrder());
			}
		}
	}

	private void tilføjClicked() {
		CreateOrder order = new CreateOrder();
		order.setVisible(true);
	}

	private void goToCreateOrder() {
		dispose();
		CreateOrder createOrder = new CreateOrder();
		createOrder.setVisible(false);
	}
	
	
	

}
