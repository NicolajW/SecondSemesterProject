package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.SaleOrderController;
import db.DataAccessException;
import db.TableDB;
import model.Person;
import model.Product;
import model.Table;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChooseTableWindow extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable tblTables;
	private TableSeatingTableModel ttm;
	private SaleOrderController soc;
	private TableDB tdb;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ChooseTableWindow dialog = new ChooseTableWindow(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws DataAccessException 
	 */
	public ChooseTableWindow(Person p) throws DataAccessException {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Vælg bord");
				panel.add(lblNewLabel);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				tblTables = new JTable();
				scrollPane.setViewportView(tblTables);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(e -> {
						try {
							okClicked(p);
						} catch (DataAccessException e1) {
							e1.printStackTrace();
						}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(e -> cancelClicked());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		init(p);
	}


	private void init(Person p) throws DataAccessException {
		displayTables();
	}
	
	
	public void okClicked(Person p) throws DataAccessException {
		int selectedRowIndex = tblTables.getSelectedRow();
		if (selectedRowIndex != -1) {
			Table selectedTable = ttm.getTableAt(selectedRowIndex);
			SaleOrderWindow sow = new SaleOrderWindow(p, selectedTable);
			sow.setVisible(true);
			
			cancelClicked();
		}
	}


	private void displayTables() throws DataAccessException {
		tdb = new TableDB();
		ttm = new TableSeatingTableModel(new ArrayList<>());
		soc = new SaleOrderController();
		
        List<Table> tables = tdb.findAllTables();
        ttm = new TableSeatingTableModel(tables);
        tblTables.setModel(ttm);
	}
	
	protected void cancelClicked() {
		setVisible(false);
		dispose();
	}
}
