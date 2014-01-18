package view.busesWindow;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * @author Alexis Cuero Losada
 * This JFrame allow integrated several panels for offer option for visualize and determiner the state of buses in
 * in the system.
 */
public class BusesWindowJF extends JFrame {

	private static final long serialVersionUID = 6356755331782398247L;

	private TableJP tableJP;
	private ButtonsJP buttonsJP;
	private ToolsJP toolsJP;
	
	/**
	 * The constructor of JFrame, sets the main attributes of busesWindowJF.
	 */
	public BusesWindowJF()
	{
		setTitle("Buses Information Server");
		setSize(600, 400);
		setBackground(Color.WHITE);
		setApareance();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void setApareance()
	{
		setLayout(new BorderLayout());
		tableJP = new TableJP();
		buttonsJP = new ButtonsJP();
		toolsJP = new ToolsJP();
		
		add(toolsJP, BorderLayout.NORTH);
		add(tableJP, BorderLayout.CENTER);
		add(buttonsJP, BorderLayout.SOUTH);
	}
	
	/**
	 * Returns the panel that contains the JTable.
	 * @return the panel that contains the JTable
	 */
	public TableJP getTableJP() {
		return tableJP;
	}

	/**
	 * Returns the panel that contains the JButtons for send and stop buses.
	 * @return the panel that contains the JButtons for send and stop buses
	 */
	public ButtonsJP getButtonsJP() {
		return buttonsJP;
	}

	/**
	 * Returns the panel that contains the filter options.
	 * @return the panel that contains the filter options
	 */
	public ToolsJP getToolsJP() {
		return toolsJP;
	}	
}
