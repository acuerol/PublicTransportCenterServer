package view.busesWindow;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * @author Alexis Cuero Losada
 * Class that extends of JPanel for contain the JTable of JFrame.
 */
public class TableJP extends JPanel {
	
	private static final long serialVersionUID = -6177183054257994205L;
	
	private JTable busesJT;
	private JScrollPane scrollJSP;
	private BusesWindowJTableModel tableModel;

	/**
	 * Constructor that initialize the element into the JPanel.
	 */
	public TableJP() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		setElements();
	}
	
	private void setElements()
	{
		setBorder(BorderFactory.createTitledBorder("Buses Information"));
		
		Object[][] data = new Object[1][8];
		String[] columnsNames = {"id", "Driver", "Plate", "Route", "Next Stop Station","State","Speed", "Position"};
		
		tableModel = new BusesWindowJTableModel(columnsNames, data);
		busesJT = new JTable(tableModel);
		scrollJSP = new JScrollPane(busesJT);
		busesJT.setFillsViewportHeight(true);
		
		add(scrollJSP, BorderLayout.CENTER);
	}
	
	/**
	 * Returns the JTable of the panel.
	 * @return the JTable of the panel
	 */
	public JTable getBusesJT() {
		return busesJT;
	}
	
	/**
	 * Returns the JTableModel of the JTable in the panel.
	 * @return the JTableModel of the JTable in the panel
	 */
	public BusesWindowJTableModel getTableModel() {
		return tableModel;
	}

	/**
	 * Returns the ID of the bus selected in the JTable.
	 * @return the ID of the bus selected in the JTable
	 */
	public String getSelectedBusID() {
		int selectedRow = busesJT.getSelectedRow();

		if (selectedRow >= 0) {
			String id = (String) busesJT.getModel().getValueAt(selectedRow, 0);
			return id;
		}

		return "";
	}
}
