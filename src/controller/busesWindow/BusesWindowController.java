package controller.busesWindow;

import javax.swing.JTable;

import model.PublicTransportCenter;
import util.Alert;
import view.busesWindow.BusesWindowJF;
import view.busesWindow.BusesWindowJTableModel;

/**
 * @author Alexis Cuero Losada
 * Abstact Class that controle the BusesWindowJF and the methods for get and set information in the JTable
 * for visualize the buses information.
 */
public class BusesWindowController {

	private BusesWindowJF busesWindow;
	private PublicTransportCenter pTC;
	
	/**
	 * Empty contructor for the class, creates the busesWindowJF for show the information in a JTable above a JFrame.
	 */
	public BusesWindowController()
	{
		busesWindow = new BusesWindowJF();
	}
	
	/**
	 * Returns a Object array with the update data of all buses in the system.
	 * @return a Object array with the update data of all buses.
	 */
	public Object[][] generateData()
	{
		pTC = PublicTransportCenter.getPublicTransportCenter();
		int numBuses = pTC.getBuses().size();
		Object[][] data = new Object[numBuses][8];
		
		for(int i = 0 ; i < numBuses ; i++)
		{
			data[i] = pTC.getBuses().get(i).toArray();
		}
		
		return data;
	}
	
	/**
	 * Sets the MouseListener for the JButtons at the panel in the JFrame which contains. 
	 */
	public void setJButtonsMouseListener()
	{
		BusesWindowJButtonsML mouseListener = new BusesWindowJButtonsML();
		busesWindow.getButtonsJP().setButtonsMouseListener(mouseListener);
	}
	
	/**
	 * Updates the JTable in the JFrame with the most recent buses data. 
	 * @param selectedRow the selected row in the JTable, for maintain the same row selected. 
	 */
	public void refreshTable(int selectedRow)
	{			
		JTable busesJT = busesWindow.getTableJP().getBusesJT();
		BusesWindowJTableModel tableModel = busesWindow.getTableJP().getTableModel();
		Object[][] data = generateData();
		
		tableModel.setData(data);
		busesJT.repaint();
		
		busesJT.setColumnSelectionInterval(0, 0);
		
		if(selectedRow >= 0)
		{
			busesJT.setRowSelectionInterval(selectedRow, selectedRow);
		}
	}
	
	/**
	 * Retruns the BusesWindowJF instance creates in the contructor.
	 * @return the BusesWindowJF instance
	 */
	public BusesWindowJF getBusesWindow() {
		return busesWindow;
	}

	/**
	 * Sends the selected bus, this method change the state of the selected bus to "true" (starts) and refresh
	 * the system.
	 */
	public void sendBusSelected()
	{
		pTC = PublicTransportCenter.getPublicTransportCenter();
		String id = busesWindow.getTableJP().getSelectedBusID();
		
		if(!id.equals(""))
		{
			if(!pTC.getBusByID(id).getState())
			{
				pTC.getBusByID(id).setState(true);
			}
			else
			{
				Alert.launchErrorMessage("The bus is running.", busesWindow);
			}
		}
		else
		{
			Alert.launchErrorMessage("Please select a bus.", busesWindow);
		}
	}
	
	/**
	 * Stops the selected bus, this method change the state of the selected bus to "false" (stop) and refresh
	 * the system.
	 */
	public void stopBusSelected() {
		pTC = PublicTransportCenter.getPublicTransportCenter();
		String id = busesWindow.getTableJP().getSelectedBusID();
		
		if(!id.equals(""))
		{
			if(pTC.getBusByID(id).getState())
			{
				pTC.getBusByID(id).setState(false);
			}
			else
			{
				Alert.launchErrorMessage("The bus is stopped.", busesWindow);
			}
		}
		else
		{
			Alert.launchErrorMessage("Please select a bus.", busesWindow);
		}
	}
}
