package view.busesWindow;

import javax.swing.table.AbstractTableModel;

/**
 * @author Alexis Cuero Losada
 * The tableModel for the JTable of busesWindow, this allow refresh the JTable of secure form, so this 
 * defined a style for show the text in JTable and limit actions.
 */
public class BusesWindowJTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -9203135560452331900L;
	
	private String[] columnNames; 
	private Object[][] data;
	
	/**
	 * Constructor with parameters for initialize the column names and JTable data. 
	 * @param columnNames the name of columns
	 * @param data the data of JTable
	 */
	public BusesWindowJTableModel(String[] columnNames, Object[][] data)
	{
		this.columnNames = columnNames;
		this.data = data;
	}
	
	/**
	 * This method allow change the data in the JTable of secure form.
	 * @param data the data update 
	 */
	public void setData(Object[][] data) {
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
