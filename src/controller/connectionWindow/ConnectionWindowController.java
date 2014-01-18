package controller.connectionWindow;

import view.connectionWindow.ConnectionWindowJF;
import controller.CentralSystem;

/**
 * @author Alexis Cuero Losada
 * Controller for the connection window.
 */
public class ConnectionWindowController {

	private ConnectionWindowJF connectionWindowJF;

	/**
	 * Creates a ConnectionWindowController instance for manage the
	 * mainWindowbuttons listener.
	 */
	public ConnectionWindowController() {
		CentralSystem.getCentralSystem().createConnectionThread();
		connectionWindowJF = new ConnectionWindowJF();
	}
	
	/**
	 * Return the {@link ConnectionWindowJF} instance create in this class.
	 * @return the {@link ConnectionWindowJF} instance
	 */
	public ConnectionWindowJF getConnectionWindow()
	{
		return connectionWindowJF;
	}
	
	/**
	 * Starts the thread that handle the request for initial values.
	 */
	public void startListenInitialValuesRequest()
	{
		CentralSystem.getCentralSystem().startListenInitialValuesRequest();
	}

	/**
	 * Interrupts the thread that handle the request for initial values.
	 */
	public void pauseListenInitialValuesRequest() {
		CentralSystem.getCentralSystem().pauseListenInitialValuesRequest();
	}
}
