package util;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import model.PublicTransportCenter;
import model.Road;
import model.Route;
import model.Semaphore;
import model.Station;
import model.Way;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class IOFiles {
	
	/**
	 * Load a file with a specific format and return a list with all roads readed in the file.
	 * @param pathName the file path in the disk.
	 * @return the list of all roads.
	 * @exception IOException error reading the file.
	 */
	public static ArrayList<Road> loadRoads(String pathName)
	{
		ArrayList<Road> roads = new ArrayList<Road>();
		File file = new File(pathName);
		String line = null;
		StringTokenizer tokenizer;
		
		String name;
		String[] coordinates = null;
		Point2D.Double startPoint;
		Point2D.Double finalPoint;
		
		double distance;
		
		try
		{
			if(!file.exists())
			{
				System.err.println("Roads: The path " + pathName + "don't exist.");
				return null;
			}
			
			String[] tempPoint;
			BufferedReader bufferedR = new BufferedReader(new FileReader(file));

			while ((line = bufferedR.readLine()) != null)
			{
				tokenizer = new StringTokenizer(line, ";");
				name = tokenizer.nextToken();
				coordinates = tokenizer.nextToken().split(" ");
				
				tempPoint = coordinates[0].split(",");
				startPoint = new Point2D.Double(java.lang.Double.parseDouble(tempPoint[0]), java.lang.Double.parseDouble(tempPoint[1]));
				
				tempPoint = coordinates[coordinates.length-1].split(",");;
				finalPoint = new Point2D.Double(java.lang.Double.parseDouble(tempPoint[0]), java.lang.Double.parseDouble(tempPoint[1]));
				
				distance = UtilCalc.distanceArrayGeoPoint(coordinates);

				roads.add(new Road(name, startPoint, finalPoint, distance));
			}
			
			bufferedR.close();
		} catch (IOException e) {
			System.err.println("Error loading Roads file.");
			e.printStackTrace();
			return null;
		}
		
		return roads;
	}
	
	/**
	 * Load a file with a specific format and return a list with all routes readed in the file.
	 * @param pathName the file path in the disk.
	 * @param ptc The PublicTransportCenter instance for get the stations.
	 * @return the list of all routes.
	 */
	public static ArrayList<Route> loadRoute(String pathName, PublicTransportCenter ptc)
	{
		ArrayList<Route> routes = new ArrayList<Route>();
		ArrayList<Station> stopStations = new ArrayList<Station>();
		File file = new File(pathName);
		StringTokenizer tokenizer;
		String line = null;
		String name;
		String tempStationName;
		
		try
		{
			if(!file.exists())
			{
				System.err.println("Route: The path " + pathName + "don't exist.");
				return null;
			}
			
			BufferedReader bufferedR = new BufferedReader(new FileReader(file));

			while ((line = bufferedR.readLine()) != null)
			{
				stopStations= new ArrayList<Station>();
				tokenizer = new StringTokenizer(line, ";");
				name = tokenizer.nextToken();
				
				while(tokenizer.hasMoreTokens())
				{
					tempStationName = tokenizer.nextToken();
					stopStations.add(ptc.getStationByName(tempStationName));
				}
				
				routes.add(new Route(name, loadWay(stopStations, ptc)));
			}
			
			bufferedR.close();
		} catch (IOException e) {
			System.err.println("Error loading Stations file.");
			e.printStackTrace();
			return null;
		}
		
		return routes;
	}
	
	/**
	 * Load a file with a specific format and return a list with all semaphores readed in the file.
	 * @param pathName the file path in the disk.
	 * @return the list of all semaphores.
	 * @exception IOException error reading the file.
	 */
	public static ArrayList<Semaphore> loadSemaphores(String pathName)
	{
		ArrayList<Semaphore> semaphores = new ArrayList<Semaphore>();
		File file = new File(pathName);
		String line = null;
		StringTokenizer tokenizer;
		
		String name;
		int timeRed;
		int timeGreen;
		Point2D.Double position;
		double x;
		double y;
		
		try
		{
			if(!file.exists())
			{
				System.err.println("Semaphores: The path " + pathName + "don't exist.");
				return null;
			}
			
			BufferedReader bufferedR = new BufferedReader(new FileReader(file));

			while ((line = bufferedR.readLine()) != null)
			{
				tokenizer = new StringTokenizer(line, ";");
				name = tokenizer.nextToken();
				timeRed = (int)java.lang.Double.parseDouble(tokenizer.nextToken());
				timeGreen = (int)java.lang.Double.parseDouble(tokenizer.nextToken());
				
				x = java.lang.Double.parseDouble(tokenizer.nextToken());
				y = java.lang.Double.parseDouble(tokenizer.nextToken());
				position = new Point2D.Double(x, y);
				
				semaphores.add(new Semaphore(name, timeRed, timeGreen, position));
			}
			
			bufferedR.close();
		} catch (IOException e) {
			System.err.println("Error loading Semaphores file.");
			e.printStackTrace();
			return null;
		}
		
		return semaphores;
	}
	
	/**
	 * Load a file with a specific format and return a list with all stations readed in the file.
	 * @param pathName the file path in the disk.
	 * @return the list of all stations.
	 * @exception IOException error reading the file. 
	 */
	public static ArrayList<Station> loadStations(String pathName)
	{
		ArrayList<Station> stations = new ArrayList<Station>();
		File file = new File(pathName);
		String line = null;
		StringTokenizer tokenizer;
		
		String name;
		boolean state;
		Point2D.Double position;
		double x;
		double y;
		
		try
		{
			if(!file.exists())
			{
				System.err.println("Stations: The path " + pathName + "don't exist.");
				return null;
			}
			
			BufferedReader bufferedR = new BufferedReader(new FileReader(file));

			while ((line = bufferedR.readLine()) != null)
			{
				tokenizer = new StringTokenizer(line, ";");
				name = tokenizer.nextToken();
				
				x = java.lang.Double.parseDouble(tokenizer.nextToken());
				y = java.lang.Double.parseDouble(tokenizer.nextToken());
				position = new Point2D.Double(x, y);
				
				state = Boolean.parseBoolean(tokenizer.nextToken());
				
				stations.add(new Station(name, position, state));
			}
			
			bufferedR.close();
		} catch (IOException e) {
			System.err.println("Error loading Stations file.");
			e.printStackTrace();
			return null;
		}
		
		return stations;
	}
	
	private static Way loadWay(ArrayList<Station> stopStations, PublicTransportCenter ptc)
	{
		ArrayList<Object> pathRoute =  new ArrayList<Object>();
		ArrayList<Double> distances = new ArrayList<Double>();
		
		ptc.getSearch().search(ptc.getGraph(), stopStations.get(0), stopStations.get(stopStations.size() - 1), null);
		
		pathRoute =  ptc.getSearch().selectByStopStationsPath(stopStations);

		for (int i = 0; i < pathRoute.size(); i++) 
		{
			distances.add(ptc.getDistanceAB(new ArrayList<Object>(pathRoute.subList(0, i))));
		}
		
		return new Way(distances, pathRoute);
	}
		
	/**
	 * Write a file from a ArrayList<String>.
	 * @param lines The lines to be write in the file. 
	 * @param pathName The file route in the disk.
	 * @return true if the file was writed succefully.
	 */
	public static boolean writeFile(ArrayList<String> lines, String pathName)
	{
		File file = new File(pathName);
		
		try 
		{
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			BufferedWriter bufferedW = new BufferedWriter(new FileWriter(file));
			
			for (String line : lines) {
				bufferedW.append(line);
				bufferedW.newLine();
			}
			
			bufferedW.close();
		} catch (IOException e) {
			System.err.println("Error writing file.");
		}
		
		return true;
	}
	
	/**
	 * Write a file with the clean roads data extracted of tke kml file, this create a new format for the data reading.
	 * @param pathName the file path in the disk.
	 * @param names the list with all roads names.
	 * @param coordinates the list with all reads coordinates.
	 * @exception IOException error writing the file.
	 * @return true if the file was writing succefully.
	 */
	public static boolean writeRoadsFile(String pathName, ArrayList<String> names, ArrayList<String> coordinates)
	{
		File file = new File(pathName);
		
		try {
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			BufferedWriter bufferedW = new BufferedWriter(new FileWriter(file));
			
			for (int i = 0 ; i < names.size() ; i++)
			{
				bufferedW.append(names.get(i) + ";" + coordinates.get(i));
				bufferedW.newLine();
			}
			
			bufferedW.close();
		} catch (IOException e) {
			System.err.println("Error writing Roads file.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * Write a file with the clean semaphores data extracted of tke kml file, this create a new format for the data reading.
	 * @param pathName the file path in the disk.
	 * @param names the list with all semaphores names.
	 * @param times the vector with the two semaphores times, timeGreen and timeRed.
	 * @param coordinates the list with all semaphores coordinates.
	 * @exception IOException error writing the file.
	 * @return true if the file was writing succefully.
	 */
	public static boolean writeSemaphoresFile(String pathName, ArrayList<String> names, ArrayList<String[]> times, ArrayList<String[]> coordinates)
	{
		File file = new File(pathName);
		
		try {
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			BufferedWriter bufferedW = new BufferedWriter(new FileWriter(file));
			
			for (int i = 0; i < names.size(); i++) {
				bufferedW.append(names.get(i) + ";" + times.get(i)[0] + ";" + times.get(i)[1] + ";" + coordinates.get(i)[0] + ";" + coordinates.get(i)[1]);
				bufferedW.newLine();
			}
			
			bufferedW.close();
		} catch (IOException e) {
			System.err.println("Error writing Semaphores file.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Write a file with the clean stations data extracted of tke kml file, this create a new format for the data reading. 
	 * @param pathName the file path in the disk.
	 * @param sNames the list with the all stations names.
	 * @param sCoordinates the list with the all stations coordinates.
	 * @param sStates the list with the all stations states.
	 * @exception IOException error writing the file.
	 * @return true if the file was writing succefully.
	 */
	public static boolean writeStationsFile(String pathName, ArrayList<String> sNames, ArrayList<String[]> sCoordinates, ArrayList<String> sStates)
	{
		File file = new File(pathName);
		
		try {
			if(!file.exists())
			{
				file.createNewFile();
			}
			
			BufferedWriter bufferedW = new BufferedWriter(new FileWriter(file));
			
			String name;
			String[] coordinates = new String[2];
			String state;
			for (int i = 0 ; i < sNames.size() ; i++)
			{
				name = sNames.get(i);
				coordinates = sCoordinates.get(i);
				state = sStates.get(i);
				bufferedW.append(name + ";" + coordinates[0] + ";" + coordinates[1] + ";" + state);
				bufferedW.newLine();
			}
			
			bufferedW.close();
		} catch (IOException e) {
			System.err.println("Error writing Stations file.");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static ArrayList<String> readFile(String pathName)
	{
		File file = new File(pathName);
		ArrayList<String> lines = new ArrayList<String>();
		String line = "";
		
		try
		{
			if(!file.exists())
			{
				System.err.println("File path " + pathName + "don't exist.");
				return null;
			}
			
			BufferedReader bufferedR = new BufferedReader(new FileReader(file));

			while ((line = bufferedR.readLine()) != null)
			{
				lines.add(line);
			}
			
			bufferedR.close();
		} catch (IOException e) {
			System.err.println("Error loading Semaphores file.");
			e.printStackTrace();
			return null;
		}
		
		return lines;
	}
}