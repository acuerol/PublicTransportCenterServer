package controller.connectionWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import view.connectionWindow.ButtonsJP;
import contrib.ch.randelshofer.quaqua.colorchooser.ColorWheelChooser;
import controller.CentralSystem;

public class ConnectionWindowJButtonsML implements MouseListener {

	private ConnectionWindowController connectionWindowController;
	private CentralSystem centralSystem;
	private ButtonsJP buttonsJP;
	
	public ConnectionWindowJButtonsML()
	{
		centralSystem = CentralSystem.getCentralSystem();
		connectionWindowController = centralSystem.getControllerConnectionWindow();
		buttonsJP = connectionWindowController.getConnectionWindow().getButtonsJP();
	}

	public void start()
	{
		connectionWindowController.startListenInitialValuesRequest();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		
		JToggleButton sourceJTB = new JToggleButton();
		JButton sourceJB = new JButton();
		
		if(event.getSource() instanceof JButton)
		{
			sourceJB = (JButton) event.getSource();
		}
		else
		{
			if(event.getSource() instanceof JToggleButton)
			{
				sourceJTB = (JToggleButton) event.getSource();
			}
		}
		
		if(sourceJTB.equals(buttonsJP.getStartRequestListenerJTB()) && buttonsJP.getStartRequestListenerJTB().isSelected())
		{
			connectionWindowController.startListenInitialValuesRequest();
		}
		else
		{
			if(sourceJTB.equals(buttonsJP.getStartRequestListenerJTB()) && !buttonsJP.getStartRequestListenerJTB().isSelected())
			{
				connectionWindowController.pauseListenInitialValuesRequest();
			}
			else
			{
				if(sourceJB.equals(buttonsJP.getShowBusesJB()))
				{
					centralSystem.createBusesWindowController();
					centralSystem.createRefreshTableThread();
					centralSystem.startRefreshTableThread();
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
