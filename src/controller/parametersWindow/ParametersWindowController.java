package controller.parametersWindow;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.PublicTransportCenter;
import model.Road;
import model.Route;
import model.Semaphore;
import model.Station;
import util.Alert;
import util.CleanData;
import util.IOFiles;
import util.Util;
import view.windowParameters.ButtonsJP;
import view.windowParameters.MainJP;
import view.windowParameters.ParametersWindowJF;
import controller.CentralSystem;

/**
 * @author Alexis Cuero Losada
 * Abstract class for control the {@link ParametersWindowJF} and set the initial values to the {@link CentralSystem}.
 */
public class ParametersWindowController {

	private ParametersWindowJF parametersWindow;
	
	/**
	 * Constructor that creates the a {@link ParametersWindowJF} instance.
	 */
	public ParametersWindowController() {
		parametersWindow = new ParametersWindowJF();
	}
	
	/**
	 * Sets the MouseListener to the JButtons and JTextFields in the {@link ButtonsJP} and {@link MainJP}.
	 */
	public void setMouseListener()
	{
		ParametersWindowJButtonsML  parametersWindowJButtonML = new ParametersWindowJButtonsML();
		ParametersWindowJTextFieldML parametersWindowJTextFieldML = new ParametersWindowJTextFieldML();
		
		parametersWindow.setButtonsMouseListener(parametersWindowJButtonML);
		parametersWindow.setJTextFieldMouseListener(parametersWindowJTextFieldML);

//		parametersWindowJButtonML.loadAll();
	}
	
	/**
	 * Open a FileChooser for select the file or files. 
	 * @param text the text to show in the FileChooser
	 * @param extensions the extensions for filter the files
	 * @param multiSelection if more than one file can be selected
	 * @return if the multiSeleccion is false return the file else the absolutePaths of files.
	 */
	public String openFileChooser(String text, String extensions, boolean multiSelection)
	{
		int seletedOption = 0;
		
		JFileChooser fileChooser = new JFileChooser();
		FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter(text, extensions);
		fileChooser.setFileFilter(extensionFilter);
		fileChooser.setMultiSelectionEnabled(multiSelection);
		seletedOption = fileChooser.showOpenDialog(parametersWindow);
		
		if(seletedOption == JFileChooser.APPROVE_OPTION)
		{
			if(multiSelection)
			{
				return Util.getAbsolutePaths(fileChooser.getSelectedFiles());
			}
			else
			{
				return fileChooser.getSelectedFile().getAbsolutePath();
			}
		}
		
		return "";
	}
	
	/**
	 * Method for clean and load the initial files, routes, stations, roads and semaphore.
	 * Returns true if all was load successfully, else false. 
	 * @param roadsPath the pathName of the file with extension .kml for clean the file and then load the roads.
	 * @param stationsPath the pathName of the file with extension .kml for clean the file and then load the stations.
	 * @param semaphoresPath the pathName of the file with extension .kml for clean the file and then load the semaphores.
	 * @param routesPaths the pathsNames of the files with extension .text for clean the files and then load all routes.
	 * @return true if all was load successfully, else false.
	 */
	public boolean loadFiles(String roadsPath, String stationsPath, String semaphoresPath, String[] routesPaths)
	{
		boolean valGeneral = true;
		boolean valRoads = true;
		boolean valSemaphores = true;
		boolean valStations = true;
		boolean valRoutes = true;
		String error = "";
		
		valRoads = CleanData.getRoadsData(roadsPath);
		valStations = CleanData.getStationsData(stationsPath);
		valSemaphores = CleanData.getSemaphoresData(semaphoresPath);
		valRoutes = CleanData.getRoutesData(routesPaths, "cleanData/routesData.txt");
		
		if(valStations)
		{
			ArrayList<Station> stations = IOFiles.loadStations("cleanData/stationsData.txt");
			PublicTransportCenter.getPublicTransportCenter().setStations(stations);
			System.out.println("Stations loaded.");
		}
		else
		{
			error = "Stations could not be loaded.";
			System.out.println("Stations could not be loaded.");
		}
		
		if(valRoads)
		{
			ArrayList<Road> roads = IOFiles.loadRoads("cleanData/roadsData.txt");
			PublicTransportCenter.getPublicTransportCenter().setRoads(roads);
			System.out.println("Roads loaded.");
		}
		else
		{
			error += "\nRoads could not be loaded.";
			System.out.println("Roads could not be loaded.");
		}
		
		if(valSemaphores)
		{
			ArrayList<Semaphore> semaphores = IOFiles.loadSemaphores("cleanData/semaphoresData.txt");
			PublicTransportCenter.getPublicTransportCenter().setSemaphores(semaphores);
			System.out.println("Semaphores loaded.");
		}
		else
		{
			error += "\nSemaphores could not be loaded.";
			System.out.println("Semaphores could not be loaded.");
		}
		
		if(valSemaphores && valStations && valRoads)
		{
			PublicTransportCenter.getPublicTransportCenter().loadNodesAttached();
			PublicTransportCenter.getPublicTransportCenter().addListNode();
			PublicTransportCenter.getPublicTransportCenter().addListBidirectionalEdge();

			System.out.println("Nodes loaded.");
		}
		else
		{
			error += "\nNodes could not be loaded.";
			System.out.println("Nodes could not be loaded.");
		}
		
		if(valRoutes)
		{
			
			ArrayList<Route> routes = IOFiles.loadRoute("cleanData/routesData.txt");
			if(routes != null)
			{
				PublicTransportCenter.getPublicTransportCenter().setRoutes(routes);
			}
			else
			{
				error += "\nRoutes could not be loaded.";
				valGeneral = false;
			}
			
		}
		
		if(valSemaphores && valStations && valRoads && valRoutes && valGeneral)
		{
			return true;
		}
		
		Alert.launchErrorMessage(error, parametersWindow);
		
		return false;
	}
	
	/**
	 * Returns the {@link ParametersWindowJF} instance created in this class.
	 * @return the {@link ParametersWindowJF} instance;
	 */
	public ParametersWindowJF getWindowParameters() {
		return parametersWindow;
	}
}
