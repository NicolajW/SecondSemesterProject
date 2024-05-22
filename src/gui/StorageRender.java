package gui;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import model.Product;

public class StorageRender implements ListCellRenderer<Product> {

	private DefaultListCellRenderer dcell = new DefaultListCellRenderer();
	@Override
	public Component getListCellRendererComponent(JList<? extends Product> list, Product value, int index,
			boolean isSelected, boolean cellHasFocus) {
		JLabel rendered = (JLabel) dcell.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		return rendered;
	}

}
