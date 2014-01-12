package controller.parametersWindow;

import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.PublicTransportCenter;
import model.Road;
import model.Route;
import model.Semaphore;
import model.Station;
import model.Way;
import util.Alert;
import util.CleanData;
import util.IOFiles;
import util.Util;
import view.windowParameters.ParametersWindowJF;
import controller.CentralSystem;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class ParametersWindowController {

	private ParametersWindowJF parametersWindow;
	
	public ParametersWindowController() {
		parametersWindow = new ParametersWindowJF();
	}
	
	public void setMouseListener()
	{
		ParametersWindowJButtonsML  parametersWindowJButtonML = new ParametersWindowJButtonsML();
		ParametersWindowJTextFieldML parametersWindowJTextFieldML = new ParametersWindowJTextFieldML();
		
		parametersWindow.setButtonsMouseListener(parametersWindowJButtonML);
		parametersWindow.setJTextFieldMouseListener(parametersWindowJTextFieldML);

		parametersWindowJButtonML.loadAll();
	}

	public ParametersWindowJF getWindowParameters() {
		return parametersWindow;
	}
	
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
	
	public boolean loadFiles(String roadsPath, String stationsPath, String semaphoresPath, String[] routesPaths)
	{
		PublicTransportCenter pTC = PublicTransportCenter.getPublicTransportCenter();
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
			//Alert.launchInfoMessage("The system was loaded.", parametersWindow);
			
//			for (Route route: pTC.getRoutes()) {
//				Way way = route.getWay();
//				for (Object obj : way.getNodes()) {
//					System.out.println();
//					if(obj instanceof Station)
//					{
//						System.out.print(((Station)(obj)).getName());
//						System.out.print("  -  ");
//					}
//					else
//					{
//						System.out.print(((Semaphore)(obj)).getID());
//						System.out.print("  -  ");
//					}
//				}
//				System.out.println();
//			}
//			PublicTransportCenter.setPublicTransportCenter(pTC);
			return true;
		}
		
		Alert.launchErrorMessage(error, parametersWindow);
		
		return false;
	}
}
