package view.busesWindow;

import javax.swing.table.AbstractTableModel;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class BusesWindowTableModel extends AbstractTableModel {
	private String[] columnNames; 
	private Object[][] data;
	
	public BusesWindowTableModel(String[] columnNames, Object[][] data)
	{
		this.columnNames = columnNames;
		this.data = data;
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return data[row][column];
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
