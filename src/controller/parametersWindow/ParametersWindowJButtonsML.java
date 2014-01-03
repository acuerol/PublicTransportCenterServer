package controller.parametersWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import controller.CentralSystem;
import controller.connectionWindow.ConnectionWindowController;
import util.Alert;
import view.windowParameters.ButtonsJP;
import view.windowParameters.MainJP;

/**
 * @author Alexis Cuero Losada
 *
 */
public class ParametersWindowJButtonsML implements MouseListener {

	private CentralSystem centralSystem;
	private ParametersWindowController parametersWindowController;
	private ConnectionWindowController connectionWindowController;
	private ButtonsJP buttonsJP;
	private MainJP mainJP;
	private boolean isLoad;
	
	public ParametersWindowJButtonsML() {
		centralSystem = CentralSystem.getCentralSystem();
		parametersWindowController = centralSystem.getWindowParametersController();
	}
	
	public void loadAll()
	{
		mainJP = parametersWindowController.getWindowParameters().getDivisionJSP().getMainJP();
		buttonsJP = parametersWindowController.getWindowParameters().getButtonsJP();
		
		String roads = mainJP.getRoadsPathText();
		String stations = mainJP.getStationsPathText();
		String semaphores = mainJP.getSemaphoresPathText();
		String[] routes = mainJP.getRoutesPathsText().split(",");		
		
		isLoad = parametersWindowController.loadFiles(roads, stations, semaphores, routes);
		
		centralSystem.createControllerConnectionWindow();
		centralSystem.createListenReportConnectionThread();
		parametersWindowController.getWindowParameters().dispose();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		mainJP = parametersWindowController.getWindowParameters().getDivisionJSP().getMainJP();
		buttonsJP = parametersWindowController.getWindowParameters().getButtonsJP();
		
		if(event.getSource().equals(buttonsJP.getLoadParametersJB()))
		{
			String roads = mainJP.getRoadsPathText();
			String stations = mainJP.getStationsPathText();
			String semaphores = mainJP.getSemaphoresPathText();
			String[] routes = mainJP.getRoutesPathsText().split(",");		
			
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
