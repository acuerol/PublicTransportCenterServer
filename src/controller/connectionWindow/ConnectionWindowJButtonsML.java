package controller.connectionWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JToggleButton;

import view.connectionWindow.ButtonsJP;
import view.connectionWindow.ConnectionWindowJF;
import controller.CentralSystem;

/**
 * @author Alexis Cuero Losada
 * This class implements MouseListener for handle the event on the JButtons in the {@link ConnectionWindowJF}.  
 */
public class ConnectionWindowJButtonsML implements MouseListener {

	private ConnectionWindowController connectionWindowController;
	
	/**
	 * Creates a listener for the buttons on ConnectionWindowJF.
	 */
	public ConnectionWindowJButtonsML() {
		connectionWindowController = CentralSystem.getCentralSystem().getControllerConnectionWindow();
	}

	/**
	 * Test.
	 * Method for start the connection automatically.
	 */
	public void start() {
		connectionWindowController.startListenInitialValuesRequest();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		CentralSystem centralSystem = CentralSystem.getCentralSystem();
		JToggleButton sourceJTB = new JToggleButton();
		JButton sourceJB = new JButton();
		ButtonsJP buttonsJP = connectionWindowController.getConnectionWindow().getButtonsJP();
		
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
