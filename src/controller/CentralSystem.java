package controller;

import javax.swing.JFrame;

import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.skin.BusinessBlackSteelSkin;

import controller.busesWindow.BusesWindowController;
import controller.connectionWindow.ConnectionWindowController;
import controller.parametersWindow.ParametersWindowController;
import controller.threads.InitialValuesRequestThread;
import controller.threads.ListenReportConnectionThread;
import controller.threads.RefreshTableThread;
import controller.threads.SemaphoresStateThread;
import util.UpdateBuses;
import model.Bus;
import model.PublicTransportCenter;

/**
 * @author Alexis Cuero Losada
 * CentralSystem is the abstract class, this is the central controller, contains all sub controllers.
 * Implment Singleton for ensure have the same instances along of all program.
 */
public class CentralSystem {
	
	private InitialValuesRequestThread connectionThread;
	private ParametersWindowController parametersWindowController;
	private ConnectionWindowController controllerConnectionWindow;
	private ListenReportConnectionThread listenReportConnectionThread;
	private BusesWindowController busesWindowController;
	private RefreshTableThread refreshTableThread;
	private SemaphoresStateThread semaphoresStateThread;
	
	private static CentralSystem centralSystem;
	
	/**
	 * CentralSystem constructor with the JFrame apareance.
	 */
	private CentralSystem() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
	}
	
	/**
	 * Returns the centralSystem if this was created, else create a new instance of this.
	 * Ensure that always the get instance will be the same.
	 * @return the CentralSystem instance
	 */
	public static synchronized CentralSystem getCentralSystem() {
		if(centralSystem == null)
		{
			centralSystem = new CentralSystem();
		}
		
		return centralSystem;
	}
	
	/**
	 * Creates and starts the thread that update the semaphore state.
	 */
	public void createSemaphoresStateThread() {
		semaphoresStateThread = new SemaphoresStateThread();
		semaphoresStateThread.start();
	}
	
	/**
	 * Creates and starts a thread that handles the report of PublicTransportCenterClient, for update the system
	 * and return the system with the recommendations.
	 */
	public void createListenReportConnectionThread() {
		listenReportConnectionThread = new ListenReportConnectionThread();
		listenReportConnectionThread.start();
		listenReportConnectionThread.startToReport();
	}
	
	/**
	 * Creates the controller and set up the JFrame for get the initial parameters. 
	 */
	public void createParametersWindowController() {
		parametersWindowController = new ParametersWindowController();
		parametersWindowController.setMouseListener();
	}
	
	/**
	 * Returns the parametersWindowController with this can get the data from the JFrame with the reference 
	 * to initial parameters. 
	 * @return the parametersWindowController
	 */
	public ParametersWindowController getWindowParametersController() {	
		return parametersWindowController;
	}
	
	/**
	 * Creates and starts the thread that handles the first connection and send the initial values for the server
	 * (the roads, routes and semaphores).
	 */
	public void createConnectionThread() {
		connectionThread = new InitialValuesRequestThread();
		connectionThread.start();
	}
	
	/**
	 * Start the initial values request from the connectionThread, listen request of client programs.
	 */
	public void startListenInitialValuesRequest() {
		connectionThread.startlistenInitialValuesRequest();
	}
	
	/**
	 * Pause the listen of initial value request from client programs. 
	 */
	public void pauseListenInitialValuesRequest() {
		connectionThread.pauselistenInitialValuesRequest();
		
	}
	
	/**
	 * Creates and set up the JFrame for start and stop the initial values request.
	 */
	public void createControllerConnectionWindow() {
		controllerConnectionWindow = new ConnectionWindowController();
		controllerConnectionWindow.getConnectionWindow().setJButtonsMouseListeners();
	}
	
	/**
	 * Returns the controller of the connection window, this allow get the control of text area for reporting
	 * the connection state.
	 * @return the controller of the connection window.
	 */
	public ConnectionWindowController getControllerConnectionWindow() {
		return controllerConnectionWindow;
	}
	
	/**
	 * Creates the controller for the window that allows wath the information of the buses in the system in an JTable. 
	 */
	public void createBusesWindowController() {
		busesWindowController = new BusesWindowController();
		busesWindowController.setJButtonsMouseListener();
	}
	
	/**
	 * Creates the thread that handle the updating of the busesWindow JTable for show the information about
	 * the buses in the system. 
	 */
	public void createRefreshTableThread() {
		refreshTableThread = new RefreshTableThread();
	}
	
	/**
	 * Starts the thread that handle the updating of the busesWindow JTable.
	 */
	public void startRefreshTableThread() {
		refreshTableThread.start();
	}
	
	/**
	 * Interrupts the refreshTableThread.
	 */
	public void interruptRefreshTableThread() {
		refreshTableThread.setInterrupt();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return getCentralSystem();
	}

	/**
	 * This is the core of the program, generates the newSystem based in the busesPositions and returns a new instance
	 * with the correction above the system. 
	 * @return a new instance with the correction above
	 */
	public PublicTransportCenter generateNewSystem() {
		
		PublicTransportCenter pTC = PublicTransportCenter.getPublicTransportCenter();
		
		for (Bus bus : pTC.getBuses())
		{
			bus.setNextNode(UpdateBuses.getNextNode(bus));
			bus.setNextStopStation(UpdateBuses.getNextStopStation(bus));
			
			double acceleration = UpdateBuses.getOptimalAcceleration(bus);
			
			if(acceleration != Double.NaN)
			{
				bus.setAcceleration(acceleration);
			}
		}

		return pTC;
	}

	/**
	 * Returns the controller of BusesWindow for visualize the buses in teh system in a JTable. 
	 * @return the controller of BusesWindow
	 */
	public BusesWindowController getBusesWindowController() {
		return busesWindowController;
	}
}
