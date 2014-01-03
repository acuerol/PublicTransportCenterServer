package controller.threads;

import model.connection.InitialValuesConnectionCenter;
import view.connectionWindow.ConnectionWindowJF;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class InitialValuesRequestThread extends Thread {

	private InitialValuesConnectionCenter serverConnectionCenter;
	
	public InitialValuesRequestThread() {
		serverConnectionCenter = new InitialValuesConnectionCenter();
	}
	
	public InitialValuesConnectionCenter getServerCenter()
	{
		return serverConnectionCenter;
	}
	
	public void startlistenInitialValuesRequest()
	{
		serverConnectionCenter.turnOnListenInitialValuesRequest();
	}
	
	public void pauselistenInitialValuesRequest()
	{
		serverConnectionCenter.turnOffListenInitialValuesRequest();
	}
	
	@Override
	public void run() {
		serverConnectionCenter.startServer();
	}
}
