package controller.connectionWindow;

import java.awt.event.ActionListener;

import controller.CentralSystem;
import view.connectionWindow.ConnectionWindowJF;

public class ConnectionWindowController {

	private CentralSystem centralSystem;
	private ConnectionWindowJF connectionWindow;
	
	public ConnectionWindowController() {
		centralSystem = CentralSystem.getCentralSystem();
		centralSystem.createConnectionThread();
		connectionWindow = new ConnectionWindowJF();
	}
	
	public void startListenInitialValuesRequest()
	{
		centralSystem.startListenInitialValuesRequest();
	}
	
	public ConnectionWindowJF getConnectionWindow()
	{
		return connectionWindow;
	}

	public void pauseListenInitialValuesRequest() {
		centralSystem.pauseListenInitialValuesRequest();
	}
}
