package controller.busesWindow;

import javax.swing.JTable;

import model.PublicTransportCenter;
import util.Alert;
import view.busesWindow.BusesWindowJF;
import view.busesWindow.BusesWindowJTableModel;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class BusesWindowController {

	private BusesWindowJF busesWindow;
	private PublicTransportCenter pTC;
	
	public BusesWindowController()
	{
		pTC = PublicTransportCenter.getPublicTransportCenter();
		busesWindow = new BusesWindowJF();
	}
	
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
	
	public void setJButtonsMouseListener()
	{
		BusesWindowJButtonsML mouseListener = new BusesWindowJButtonsML();
		busesWindow.getButtonsJP().setButtonsMouseListener(mouseListener);
	}
	
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
	
	public BusesWindowJF getBusesWindow() {
		return busesWindow;
	}
	
	public void refreshBuses()
	{
		refreshTable(-1);
	}

	public void sendBusSelected()
	{
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
	
	public void stopBusSelected() {
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
