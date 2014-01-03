package view.busesWindow;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import controller.busesWindow.BusesWindowController;
import model.PublicTransportCenter;

/**
 * @author Alexis Cuero Losada
 *
 */
public class BusesWindowJF extends JFrame {

	public static final String EXIT = "Exit";
	private BusesWindowTableJP tableJP;
	private BusesWindowButtonsJP buttonsJP;
	
	public BusesWindowJF()
	{
		setTitle("Buses Information");
		setSize(400, 400);
		setApareance();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	private void setApareance()
	{
		setLayout(new BorderLayout());
		tableJP = new BusesWindowTableJP();
		buttonsJP = new BusesWindowButtonsJP();
		
		add(tableJP, BorderLayout.CENTER);
		add(buttonsJP, BorderLayout.SOUTH);
	}
	
	public BusesWindowTableJP getTableJP()
	{
		return tableJP;
	}
	
	public BusesWindowButtonsJP getButtonsJP()
	{
		return buttonsJP;
	}	
}
