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
import util.Alert;
import view.connectionWindow.ConnectionWindowJF;
import model.Bus;
import model.PublicTransportCenter;
import model.connection.InitialValuesConnectionCenter;

public class CentralSystem {
	
	private InitialValuesRequestThread connectionThread;
	private ParametersWindowController parametersWindowController;
	private ConnectionWindowController controllerConnectionWindow;
	private ListenReportConnectionThread listenReportConnectionThread;
	private BusesWindowController busesWindowController;
	private RefreshTableThread refreshTableThread;
	private SemaphoresStateThread semaphoresStateThread;
	private PublicTransportCenter pTC;
	
	private static CentralSystem centralSystem;
	
	private CentralSystem() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		SubstanceLookAndFeel.setSkin(new BusinessBlackSteelSkin());
		pTC = PublicTransportCenter.getPublicTransportCenter();
	}
	
	public static synchronized CentralSystem getCentralSystem()
	{
		if(centralSystem == null)
		{
			centralSystem = new CentralSystem();
		}
		
		return centralSystem;
	}
	
	public void createSemaphoresStateThread()
	{
		semaphoresStateThread = new SemaphoresStateThread();
		semaphoresStateThread.start();
	}
	
	public void createListenReportConnectionThread()
	{
		listenReportConnectionThread = new ListenReportConnectionThread();
		listenReportConnectionThread.start();
		listenReportConnectionThread.startToReport();
	}
	
	public void createParametersWindowController()
	{
		parametersWindowController = new ParametersWindowController();
		parametersWindowController.setMouseListener();
	}
	
	public ParametersWindowController getWindowParametersController()
	{	
		return parametersWindowController;
	}
	
	public void createConnectionThread()
	{
		connectionThread = new InitialValuesRequestThread();
		connectionThread.start();
	}
	
	public void startListenInitialValuesRequest()
	{
		connectionThread.startlistenInitialValuesRequest();
	}
	
	public void createControllerConnectionWindow()
	{
		controllerConnectionWindow = new ConnectionWindowController();
		controllerConnectionWindow.getConnectionWindow().setJButtonsMouseListeners();
	}
	
	public void createBusesWindowController()
	{
		busesWindowController = new BusesWindowController();
		busesWindowController.setJButtonsMouseListener();
	}
	
	public ConnectionWindowController getControllerConnectionWindow()
	{
		return controllerConnectionWindow;
	}
	
	public void createRefreshTableThread()
	{
		refreshTableThread = new RefreshTableThread();
	}
	
	public void startRefreshTableThread()
	{
		refreshTableThread.start();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return getCentralSystem();
	}

	public void pauseListenInitialValuesRequest() {
		connectionThread.pauselistenInitialValuesRequest();
		
	}

	public PublicTransportCenter generateNewSystem() {
		pTC = PublicTransportCenter.getPublicTransportCenter();
		
		for (Bus bus : pTC.getBuses()) {
			bus.setNextNode(bus.getRoute().getWay().getNextNode(bus.getPosition()));
			bus.setNextStopStation(bus.getRoute().getWay().getNextStopStation(bus.getPosition()));
		}
		
		for (Bus bus : pTC.getBuses()) {
			if(bus.getNextStopStation() != null)
			{
				System.out.println(bus.getId() + " -- " + bus.getNextStopStation().getName());
			}
		}
		
		return pTC;
	}

	public BusesWindowController getBusesWindowController() {
		return busesWindowController;
	}

	public void interruptRefreshTableThread() {
		refreshTableThread.setInterrupt();
	}
}
