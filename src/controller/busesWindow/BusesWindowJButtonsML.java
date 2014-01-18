package controller.busesWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import view.busesWindow.ButtonsJP;
import controller.CentralSystem;

/**
 * @author Alexis Cuero Losada.
 * This class implements MouseListener for handle the mouse event of the buttons in the BusesWindowJF.
 */
public class BusesWindowJButtonsML implements MouseListener {
	
	@Override
	public void mouseClicked(MouseEvent event) {
		CentralSystem centralSystem = CentralSystem.getCentralSystem();
		BusesWindowController busesWindowController = centralSystem.getBusesWindowController();
		ButtonsJP buttonsJP = busesWindowController.getBusesWindow().getButtonsJP();
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
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {	
	}

}
