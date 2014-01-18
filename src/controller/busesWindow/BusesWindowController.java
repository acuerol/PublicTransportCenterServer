package controller.busesWindow;

import javax.swing.JTable;

import model.PublicTransportCenter;
import model.Route;
import util.Alert;
import view.busesWindow.BusesWindowJF;
import view.busesWindow.BusesWindowJTableModel;

/**
 * @author Alexis Cuero Losada
 * Abstract Class that controls the BusesWindowJF and the methods for get and set information in the JTable
 * for visualize the buses information.
 */
public class BusesWindowController {

	private BusesWindowJF busesWindowJF;
	
	/**
	 * Empty constructor for the class, creates the busesWindowJF for show the information in a JTable above a JFrame.
	 */
	public BusesWindowController()
	{
		busesWindowJF = new BusesWindowJF();
	}
	
	/**
	 * Returns a Object array with the update data of all buses in the system.
	 * @return a Object array with the update data of all buses.
	 */
	public Object[][] generateData() {
		PublicTransportCenter pTC = PublicTransportCenter.getPublicTransportCenter();
		int numBuses = pTC.getBuses().size();
		String item = (String) busesWindowJF.getToolsJP().getRoutesJCB().getSelectedItem();

		if(item != "All routes")
		{
			Route route = pTC.getRouteByName(item);
			numBuses = pTC.getBusesByRoute(route).size();
			Object[][] data = new Object[numBuses][8];
			
			for (int i = 0; i < numBuses; i++) {
				data[i] = pTC.getBusesByRoute(route).get(i).toArray();
			}
			
			return data;
		}
		
		Object[][] data = new Object[numBuses][8];

		for (int i = 0; i < numBuses; i++) {
			data[i] = pTC.getBuses().get(i).toArray();
		}

		return data;
	}
	
	/**
	 * Updates the JTable in the JFrame with the most recent buses data. 
	 * @param selectedRow the selected row in the JTable, for maintain the same row selected. 
	 */
	public void refreshTable(int selectedRow) {
		JTable busesJT = busesWindowJF.getTableJP().getBusesJT();
		BusesWindowJTableModel tableModel = busesWindowJF.getTableJP()
				.getTableModel();
		Object[][] data = generateData();

		tableModel.setData(data);
		busesJT.repaint();

		busesJT.setColumnSelectionInterval(0, 0);

		if (selectedRow >= 0) {
			busesJT.setRowSelectionInterval(selectedRow, selectedRow);
		}
	}
	
	/**
	 * Sends the selected bus, this method change the state of the selected bus to "true" (starts) and refresh
	 * the system.
	 */
	public void sendBusSelected()
	{
		PublicTransportCenter pTC = PublicTransportCenter.getPublicTransportCenter();
		String id = busesWindowJF.getTableJP().getSelectedBusID();
		
		if(!id.equals(""))
		{
			if(!pTC.getBusByID(id).getState())
			{
				pTC.getBusByID(id).setState(true);
			}
			else
			{
				Alert.launchErrorMessage("The bus is running.", busesWindowJF);
			}
		}
		else
		{
			Alert.launchErrorMessage("Please select a bus.", busesWindowJF);
		}
	}
	
	/**
	 * Stops the selected bus, this method change the state of the selected bus to "false" (stop) and refresh
	 * the system.
	 */
	public void stopBusSelected() {
		PublicTransportCenter pTC = PublicTransportCenter.getPublicTransportCenter();
		String id = busesWindowJF.getTableJP().getSelectedBusID();
		
		if(!id.equals(""))
		{
			if(pTC.getBusByID(id).getState())
			{
				pTC.getBusByID(id).setState(false);
			}
			else
			{
				Alert.launchErrorMessage("The bus is stopped.", busesWindowJF);
			}
		}
		else
		{
			Alert.launchErrorMessage("Please select a bus.", busesWindowJF);
		}
	}
	
	/**
	 * Sets the MouseListener for the JButtons at the panel in the JFrame which contains. 
	 */
	public void setJButtonsMouseListener()
	{
		BusesWindowJButtonsML mouseListener = new BusesWindowJButtonsML();
		busesWindowJF.getButtonsJP().setButtonsMouseListener(mouseListener);
	}
	
	/**
	 * Returns the BusesWindowJF instance creates in the constructor.
	 * @return the BusesWindowJF instance
	 */
	public BusesWindowJF getBusesWindow() {
		return busesWindowJF;
	}
}
