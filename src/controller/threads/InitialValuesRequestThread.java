package controller.threads;

import model.connection.InitialValuesConnectionCenter;

/**
 * @author Alexis Cuero Losada
 * This class extends of Thread for handle the initial values request from clients.
 */
public class InitialValuesRequestThread extends Thread {

	private InitialValuesConnectionCenter initialValuesConnectionCenter;
	
	/**
	 * Constructor that creates a {@link InitialValuesConnectionCenter} instance.  
	 */
	public InitialValuesRequestThread() {
		initialValuesConnectionCenter = new InitialValuesConnectionCenter();
	}
	
	/**
	 * Returns the {@link InitialValuesConnectionCenter} instance.
	 * @return the {@link InitialValuesConnectionCenter} instance
	 */
	public InitialValuesConnectionCenter getServerCenter()
	{
		return initialValuesConnectionCenter;
	}
	
	/**
	 * Starts to listen the initialValues request. 
	 */
	public void startlistenInitialValuesRequest()
	{
		initialValuesConnectionCenter.turnOnListenInitialValuesRequest();
	}
	
	/**
	 * Interrupts of listen the initialValues request. 
	 */
	public void pauselistenInitialValuesRequest()
	{
		initialValuesConnectionCenter.turnOffListenInitialValuesRequest();
	}
	
	@Override
	public void run() {
		initialValuesConnectionCenter.startServer();
	}
}
