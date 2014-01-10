package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class CleanData {
	/**
	 * Return true if the roads data was sucefully extrated of the kml file, else return false.
	 * @param pathName the file path in the disk.
	 * @return if the operation was success.
	 */
	public static boolean getRoadsData(String pathName)
	{
		DocumentBuilderFactory docBuilderFactory;
		DocumentBuilder docBuilder;
		Document document = null;
		try {
			 
			docBuilderFactory  = DocumentBuilderFactory.newInstance();
		    docBuilder = docBuilderFactory.newDocumentBuilder();
		    
		    document = docBuilder.parse(new File(pathName));
		    document.getDocumentElement().normalize();
		    
		} catch (ParserConfigurationException e) {
			System.err.println(pathName + " XML Format error.");
			return false;
		}catch (IOException e) {
			System.err.println(pathName + " Write/Read error.");
			return false;
		} catch (SAXException e) {
			System.err.println(pathName +  " SAXException.");
			return false;
		}

	    NodeList names = document.getElementsByTagName("name");
	    NodeList coordinates = document.getElementsByTagName("coordinates");
	    
	    ArrayList<String> rNames = new ArrayList<String>();
	    ArrayList<String> rCoordinates = new ArrayList<String>();
	    
	    int j = 0;
	    
	    for (int i = 0; i < names.getLength(); i++)
	    {
	    	if(names.item(i).getParentNode().getNodeName() == "Placemark" || coordinates.item(i).getParentNode().getNodeName() == "Placemark")
	    	{
	    		rNames.add(names.item(i).getTextContent());
	    		rCoordinates.add(coordinates.item(j).getTextContent().replaceAll(",0.0", ""));
	    		j++;
	    	}
		}	    	    
	    return IOFiles.writeRoadsFile("cleanData/roadsData.txt", rNames, rCoordinates);
	}
	
	/**
	 * Return true if the routes data was sucefully extrated of the text files, else return false.
	 * @param paths the file paths in the disk.
	 * @return if the operation was success.
	 */
	public static boolean getRoutesData(String[] paths, String pathName)
	{
		ArrayList<String> routes = new ArrayList<String>();
		for (String path : paths)
		{
			File file = new File(path);
			String name = "";
			String line = "";
			String lines = "";
			
			if(!file.exists())
			{
				System.err.println("the " + path + " don't exist.");
				return false;
			}
			
			try 
			{
				BufferedReader bufferedR = new BufferedReader(new FileReader(file));
				
				if(path.contains("\\"))
				{
					name = path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf("."));
				}
				else
				{
					name = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
				}
				
//				System.out.println(name);
				while((line = bufferedR.readLine()) != null)
				{
					lines += line + ";";
				} 
				
				lines = lines.substring(0, lines.length() - 1);
				
				routes.add(name + ";" + lines);
				bufferedR.close();

				IOFiles.writeFile(routes, pathName);
				
			} catch (IOException e)
			{
				System.err.println("Error reading files.");
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Return true if the semaphores data was sucefully extrated of the kml file, else return false.
	 * @param pathName the file path in the disk.
	 * @return if the operation was success.
	 */
	public static boolean getSemaphoresData(String pathName)
	{
		DocumentBuilderFactory docBuilderFactory;
		DocumentBuilder docBuilder;
		Document document = null;
		
		try
		{
			 
			docBuilderFactory  = DocumentBuilderFactory.newInstance();
		    docBuilder = docBuilderFactory.newDocumentBuilder();
		    
		    document = docBuilder.parse(new File(pathName));
		    document.getDocumentElement().normalize();
		    
		} catch (ParserConfigurationException e) 
		{
			System.err.println("XML Format error.");
			return false;
		} catch (IOException e) {
			System.err.println("Write/Read error.");
			return false;
		} catch (SAXException e) {
			System.err.println("SAXException.");
			return false;
		}

	    NodeList names = document.getElementsByTagName("name");
	    NodeList times = document.getElementsByTagName("value");
	    NodeList coordinates = document.getElementsByTagName("coordinates");
	    
	    ArrayList<String> sNames = new ArrayList<String>();
	    ArrayList<String[]> sTimes = new ArrayList<String[]>();
	    ArrayList<String[]> sCoordinates = new ArrayList<String[]>();
	    
	    int j = 0;
	    int k = 0;
	    
	    for (int i = 0; i < names.getLength(); i++)
	    {
	    	if(names.item(i).getParentNode().getNodeName() == "Placemark" || coordinates.item(i).getParentNode().getNodeName() == "Placemark")
	    	{
	    		sNames.add(names.item(i).getTextContent());
	    		sCoordinates.add(coordinates.item(j).getTextContent().split(","));
	    		sTimes.add(new String[] {times.item(k).getTextContent(), times.item(++k).getTextContent()});
	    		
	    		if(sTimes.get(sTimes.size()-1)[0].equals("") || sTimes.get(sTimes.size()-1)[0].equals("1"))
	    		{
	    			sTimes.get(sTimes.size()-1)[0] = "30.0";
	    		}
	    		
	    		if(sTimes.get(sTimes.size()-1)[1].equals("") || sTimes.get(sTimes.size()-1)[1].equals("1"))
	    		{
	    			sTimes.get(sTimes.size()-1)[1] = "30.0";
	    		}
	    		
	    		k++;
	    		j++;
	    	}
		}	    	    
	    return IOFiles.writeSemaphoresFile("cleanData/semaphoresData.txt", sNames, sTimes, sCoordinates);
	}
	
	/**
	 * Return true if the station data was sucefully extrated of the kml file, else return false.
	 * @param pathName the file path in the disk.
	 * @return if the operation was success.
	 */
	public static boolean getStationsData(String pathName)
	{
		DocumentBuilderFactory docBuilderFactory;
		DocumentBuilder docBuilder;
		Document document = null;
		try {
			 
			docBuilderFactory  = DocumentBuilderFactory.newInstance();
		    docBuilder = docBuilderFactory.newDocumentBuilder();
		    
		    document = docBuilder.parse(new File(pathName));
		    document.getDocumentElement().normalize();
		    
		} catch (ParserConfigurationException e) {
			System.err.println("XML Format error.");
			return false;
		}catch (IOException e) {
			System.err.println("Write/Read error.");
			return false;
		} catch (SAXException e) {
			System.err.println("SAXException.");
			return false;
		}

	    NodeList names = document.getElementsByTagName("name");
	    NodeList coordinates = document.getElementsByTagName("coordinates");
	    NodeList state = document.getElementsByTagName("value");
	    
	    ArrayList<String> sNames = new ArrayList<String>();
	    ArrayList<String[]> sCoordinates = new ArrayList<String[]>();
	    ArrayList<String> sStates = new ArrayList<String>();
	    String tempStr;
	    int j = 0;
	    
	    for (int i = 0; i < names.getLength(); i++)
	    {
	    	if(names.item(i).getParentNode().getNodeName() == "Placemark" || coordinates.item(i).getParentNode().getNodeName() == "Placemark")
	    	{
	    		sNames.add(names.item(i).getTextContent());
	    		tempStr = coordinates.item(j).getTextContent().replaceAll("0.0", "");
	    		sCoordinates.add(tempStr.split(","));
	    		
	    		tempStr = state.item(j).getTextContent();
	    		
	    		if(tempStr.equals("1"))
	    		{
	    			sStates.add("true");
	    		}
	    		else
	    		{
	    			sStates.add("false");
	    		}
	    		
	    		j++;
	    	}
		}

	    return IOFiles.writeStationsFile("cleanData/stationsData.txt", sNames, sCoordinates, sStates);
	}
}
