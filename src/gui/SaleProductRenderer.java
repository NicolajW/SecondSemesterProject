package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.SaleProduct;

public class SaleProductRenderer implements ListCellRenderer<SaleProduct> {
	
	private DefaultListCellRenderer dlcr = new DefaultListCellRenderer();

	@Override
	public Component getListCellRendererComponent(JList<? extends SaleProduct> list, SaleProduct value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		String res = value.getName() + " Price: " + value.getPrice() + " - Type: "+ value.getType();
		
		return dlcr.getListCellRendererComponent(list, res, index, isSelected, cellHasFocus);
	}
	
}