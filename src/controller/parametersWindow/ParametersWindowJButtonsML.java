package controller.parametersWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.CentralSystem;
import util.Alert;
import view.windowParameters.ButtonsJP;
import view.windowParameters.MainJP;
import view.windowParameters.ParametersWindowJF;

/**
 * @author Alexis Cuero Losada
 * This class implements MouseListener for handle the event on the JButtons in the {@link ParametersWindowJF}.  
 */
public class ParametersWindowJButtonsML implements MouseListener {

	private ParametersWindowController parametersWindowController;
	private ButtonsJP buttonsJP;
	private MainJP mainJP;
	private boolean isLoad;
	
	/**
	 * Constructor that obtains the {@link ParametersWindowController} instance of CentralSystem.
	 */
	public ParametersWindowJButtonsML() {
		parametersWindowController = CentralSystem.getCentralSystem().getWindowParametersController();
	}
	
	/**
	 * Test
	 * Load all automatically. 
	 */
	public void loadAll()
	{
		CentralSystem centralSystem = CentralSystem.getCentralSystem();
		mainJP = parametersWindowController.getWindowParameters().getDivisionJSP().getMainJP();
		buttonsJP = parametersWindowController.getWindowParameters().getButtonsJP();
		
		String roads = mainJP.getRoadsPathJTF().getText();
		String stations = mainJP.getStationsPathJTF().getText();
		String semaphores = mainJP.getSemaphoresPathJTF().getText();
		String[] routes = mainJP.getRoutesPathsJTF().getText().split(",");		
		
		isLoad = parametersWindowController.loadFiles(roads, stations, semaphores, routes);
		
		centralSystem.createControllerConnectionWindow();
		centralSystem.createListenReportConnectionThread();
		centralSystem.createSemaphoresStateThread();
		parametersWindowController.getWindowParameters().dispose();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		CentralSystem centralSystem = CentralSystem.getCentralSystem();
		mainJP = parametersWindowController.getWindowParameters().getDivisionJSP().getMainJP();
		buttonsJP = parametersWindowController.getWindowParameters().getButtonsJP();
		
		if(event.getSource().equals(buttonsJP.getLoadParametersJB()))
		{
			String roads = mainJP.getRoadsPathJTF().getText();
			String stations = mainJP.getStationsPathJTF().getText();
			String semaphores = mainJP.getSemaphoresPathJTF().getText();
			String[] routes = mainJP.getRoutesPathsJTF().getText().split(",");		
			
			isLoad = parametersWindowController.loadFiles(roads, stations, semaphores, routes);
		}
		else
		{
			if(event.getSource().equals(buttonsJP.getRunSystemJB()))
			{
				if(isLoad)
				{
					centralSystem.createControllerConnectionWindow();
					centralSystem.createListenReportConnectionThread();
					centralSystem.createSemaphoresStateThread();
					parametersWindowController.getWindowParameters().dispose();
				}
				else
				{
					Alert.launchErrorMessage("The system isn't loaded", parametersWindowController.getWindowParameters());
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
}
