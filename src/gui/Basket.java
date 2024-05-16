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

public class Basket extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JLabel lblNewLabel = new JLabel("Indkøbskurv");
			lblNewLabel.setForeground(Color.BLACK);
			lblNewLabel.setBackground(Color.BLACK);
			lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(lblNewLabel, BorderLayout.NORTH);
		}
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			contentPanel.add(panel, BorderLayout.WEST);
			panel.setLayout(new MigLayout("", "[]", "[][][][][][]"));
			{
				JLabel lblNewLabel_3 = new JLabel(".");
				lblNewLabel_3.setForeground(Color.WHITE);
				panel.add(lblNewLabel_3, "cell 0 0");
			}
			{
				JButton btnNewButton = new JButton("Betalingskort");
				btnNewButton.setHorizontalAlignment(SwingConstants.RIGHT);
				btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
				panel.add(btnNewButton, "cell 0 1,alignx center");
			}
			{
				JLabel lblNewLabel_1 = new JLabel(".");
				lblNewLabel_1.setForeground(Color.WHITE);
				panel.add(lblNewLabel_1, "cell 0 2");
			}
			{
				JButton btnNewButton_1 = new JButton("Mobilepay");
				btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
				btnNewButton_1.setHorizontalAlignment(SwingConstants.RIGHT);
				btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 17));
				panel.add(btnNewButton_1, "cell 0 3,alignx center");
			}
			{
				JLabel lblNewLabel_2 = new JLabel(".");
				lblNewLabel_2.setForeground(Color.WHITE);
				panel.add(lblNewLabel_2, "cell 0 4");
			}
			{
				JButton btnNewButton_2 = new JButton("Kontant");
				btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 17));
				panel.add(btnNewButton_2, "cell 0 5,alignx center");
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.EAST);
			panel.setLayout(new BorderLayout(0, 0));
			{
				textField = new JTextField();
				textField.setEditable(false);
				panel.add(textField, BorderLayout.SOUTH);
				textField.setColumns(10);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				panel.add(scrollPane, BorderLayout.CENTER);
				{
					JList list = new JList();
					scrollPane.setViewportView(list);
				}
			}
		}
		{
		    JPanel buttonPane1 = new JPanel();
		    buttonPane1.setBackground(Color.WHITE);
		    buttonPane1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		    getContentPane().add(buttonPane1, BorderLayout.SOUTH);
		    {
		        JButton tilføjButton = new JButton("Køb");
		        buttonPane1.add(tilføjButton); // Add tilføjButton to buttonPane1, not buttonPane1 itself
		        tilføjButton.addActionListener(e -> {
		            dispose();
		        });
		    }
		    {
		        JButton cancelButton = new JButton("Annuller");
		        cancelButton.setActionCommand("Cancel");
		        buttonPane1.add(cancelButton); // Add cancelButton to buttonPane1
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
