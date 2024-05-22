package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.JButton;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import db.DataAccessException;
import model.Product;
import controller.SaleProductController;
import controller.SaleOrderController;


public class UpdatedCreateOrder extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTextField textField_2;
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private String employeeID;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				UpdatedCreateOrder frame = new UpdatedCreateOrder(null);
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public UpdatedCreateOrder(String employeeID) {
		this.employeeID = employeeID;
		setTitle("Duoro > LogIn > Menu > Kassesystem ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 605);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, BorderLayout.NORTH);
		

		JLabel lblNewLabel = new JLabel("Kassesystem");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
		panel.add(lblNewLabel, "cell 0 0,alignx left,aligny center");

		JLabel lblNewLabel_1 = new JLabel("Logged in:");
		panel.add(lblNewLabel_1, "cell 3 0,alignx center,aligny center");

		textField = new JTextField(employeeID);
		textField.setEnabled(false);
		textField.setEditable(false);
		panel.add(textField, "cell 4 0,alignx center,growy");
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] { { "1", "Product1", "10.00", "Type1", "Description1" },
						{ "2", "Product2", "20.00", "Type2", "Description2" },

				}, new String[] { "ID:", "Navn:", "Pris:", "Type:", "Beskrivelse:" });
		table.setModel(tableModel);

		TableColumn idColumn = table.getColumnModel().getColumn(0);
		idColumn.setPreferredWidth(50);

		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);
		panel_3.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("Produktliste");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblNewLabel_2, BorderLayout.NORTH);

		JLabel lblNewLabel_4 = new JLabel("Fremsøg produkt:");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(lblNewLabel_4, BorderLayout.WEST);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.LEFT);
		panel_3.add(textField_1, BorderLayout.CENTER);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("       Søg         ");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		panel_3.add(btnNewButton, BorderLayout.EAST);
		btnNewButton.addActionListener(e -> {
			String searchId = textField_1.getText();
			searchTableById(searchId);
		});

		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.SOUTH);

		JButton btnNewButton_1 = new JButton("Tilføj ");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel_4.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Fjern");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel_4.add(btnNewButton_2);
		btnNewButton_2.addActionListener(e -> removeProductFromList());

		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane_1 = new JScrollPane();
		panel_2.add(scrollPane_1, BorderLayout.CENTER);

		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		scrollPane_1.setViewportView(list);

		JLabel lblNewLabel_3 = new JLabel("Kurv");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		panel_2.add(lblNewLabel_3, BorderLayout.NORTH);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_2.add(panel_5, BorderLayout.SOUTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_5 = new JLabel("Total:");
		lblNewLabel_5.setBackground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		panel_5.add(lblNewLabel_5, BorderLayout.WEST);

		JButton btnNewButton_3 = new JButton("Betal");
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		panel_5.add(btnNewButton_3, BorderLayout.SOUTH);
		btnNewButton_3.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Betalt");
		});

		textField_2 = new JTextField();
		textField_2.setBackground(Color.WHITE);
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_5.add(textField_2, BorderLayout.CENTER);
		textField_2.setColumns(10);

		btnNewButton_1.addActionListener(e -> addSelectedProductToList());

		btnNewButton_3.addActionListener(e -> {
			calculateTotalPrice();
		});
	}

	private void searchTableById(String searchId) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		boolean found = false;
		for (int i = 0; i < model.getRowCount(); i++) {
			String id = model.getValueAt(i, 0).toString();
			if (id.equals(searchId)) {
				table.setRowSelectionInterval(i, i);
				table.scrollRectToVisible(table.getCellRect(i, 0, true));
				found = true;
				break;
			}
		}
		if (!found) {
			JOptionPane.showMessageDialog(this, "Product not found.");
		}
	}

	private void addSelectedProductToList() {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			String productName = model.getValueAt(selectedRow, 1).toString();
			String productPrice = model.getValueAt(selectedRow, 2).toString();

			String productDetails = String.format("%-20s %10s DK", productName, productPrice);
			listModel.addElement(productDetails);

			calculateTotalPrice();
		} else {
			JOptionPane.showMessageDialog(this, "No product selected.");
		}
	}

	private void removeProductFromList() {
		int selectedIndex = list.getSelectedIndex();
		if (selectedIndex != -1) {
			listModel.remove(selectedIndex);
			calculateTotalPrice();
		} else {
			JOptionPane.showMessageDialog(this, "No product selected to remove.");
		}
	}

	private void calculateTotalPrice() {
		double totalPrice = 0.0;
		for (int i = 0; i < listModel.getSize(); i++) {
			String item = listModel.getElementAt(i);
			String[] parts = item.split("\\s+");
			double price = Double.parseDouble(parts[1]);
			totalPrice += price;
		}
		textField_2.setText(String.format("%.2f DK", totalPrice));
	}

	public void setEmployeeID(String employeeID) {
		textField.setText(employeeID);
	}
	
	
}
