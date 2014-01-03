package controller.busesWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import view.busesWindow.BusesWindowButtonsJP;
import view.busesWindow.BusesWindowTableJP;
import controller.CentralSystem;

public class BusesWindowJButtonsML implements MouseListener {

	private BusesWindowButtonsJP buttonsJP;
	private CentralSystem centralSystem;
	private BusesWindowController busesWindowController;
	
	public BusesWindowJButtonsML() {
		centralSystem = CentralSystem.getCentralSystem();
		busesWindowController = centralSystem.getBusesWindowController();
		buttonsJP = busesWindowController.getBusesWindow().getButtonsJP();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		JButton source = (JButton) event.getSource();
		
		if(source.equals(buttonsJP.getSendBusJB()))
		{
			busesWindowController.sendBusSelected();
		}
		else
		{
			if(source.equals(buttonsJP.getStopBusJB()))
			{
				busesWindowController.stopBusSelected();
			}
			else
			{
				if(source.equals(buttonsJP.getCloseJB()))
				{
					centralSystem.getBusesWindowController().getBusesWindow().dispose();
					centralSystem.interruptRefreshTableThread();
				}
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
