package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import util.Util;


/**
 * @author Alexis Cuero Losada 
 * Adaptation of this thread write by Casey Watson:
 * http://stackoverflow.com/questions/58306/graph-algorithm-to-find-all-connections-between-two-arbitrary-vertices
 */
public class Search implements Serializable {
	
	/**
	 * The Search serialVersionUID.
	 */
	private static final long serialVersionUID = -3780788245763790235L;
	private ArrayList<ArrayList<Object>> paths;
	
	/**
	 * Search constructor, instance paths.
	 */
	public Search() {
		
		paths = new ArrayList<ArrayList<Object>>();
	}
	
	/**
	 * Erase all elements at the list.
	 */
	public void cleanPaths()
	{
		paths.clear();
	}
	
	/**
	 * Returns a ArrayList with all paths for found the searched node.
	 * @return all paths for found the searched node.
	 */
	public ArrayList<ArrayList<Object>> getPaths()
	{
		return paths;
	}
	
	/**
	 * Prints all paths in the Search instance.
	 */
	public void printPaths()
	{
		if(paths != null)
		{
			for (ArrayList<Object> nodes : paths)
			{
				System.out.print("Path " + (paths.indexOf(nodes) + 1) + " = ");
				for (Object node : nodes) {
					if (node instanceof Station) {
						System.out.print(((Station)(node)).getName() + " --> ");
					}
					else
					{
						System.out.print(((Semaphore)(node)).getId() + " --> ");
					}
				}
				System.out.print(" End");
				System.out.println();
			}
		}
	}
	
	/**
	 * Start to search in the graph and clear the paths. 
	 * @param graph the graph contains all nodes. 
	 * @param start the start node.
	 * @param searched the searhed node.
	 * @param visited the list of visit nodes.
	 */
	public void search(Graph graph, Object start, Object searched, ArrayList<Object> visited)
	{
		paths.clear();
		searchPaths(graph, start, searched, visited);
	}
	
	/**
	 * Sets a ArrayList<ArrayList<Nodes>> with all paths for go to B from A. 
	 * @param graph the graph contains all nodes. 
	 * @param start the start node.
	 * @param searched the searhed node.
	 * @param visited the list of visit nodes.
	 */
	private void searchPaths(Graph graph, Object start, Object searched, ArrayList<Object> visited)
	{
		if(visited == null)
		{
			visited = new ArrayList<Object>();
			visited.add(start);
		}
		
		ArrayList<Object> nodes;
		
		if (visited.get(visited.size() - 1) instanceof Station) {
			nodes = ((Station)(visited.get(visited.size() - 1))).getChildNodes();
		}
		else
		{
			nodes = ((Semaphore)(visited.get(visited.size() - 1))).getChildNodes();
		}
		
		if(nodes == null)
		{
			nodes = new ArrayList<Object>();
		}
		
		for (Object node : nodes)
		{
			if(visited.contains(node)) {
				continue;
			}
			
			if(node.equals(searched))
			{
				visited.add(node);
				paths.add(setNewPath(visited));
				visited.remove(visited.size() - 1);
				break;
			}
		}
		
		for (Object node : nodes)
		{
			if(visited.contains(node) || node.equals(searched))
			{
				continue;
			}
			
			visited.add(node);
			searchPaths(graph, start, searched, visited);
			visited.remove(visited.size() - 1);
		}
	}
	
	/**
	 * Returns the select path that contains all stopStations. 
	 * @param stopStations the stop stations for filter paths.
	 * @return the select path.
	 */
	public ArrayList<Object> selectByStopStationsPath(ArrayList<Station> stopStations)
	{
		if(paths.size() == 0)
		{
//			System.out.println("Path not found.");
		}
		else
		{
			if(paths.size() == 1)
			{
//				System.out.println("One path founded.");
				printPaths();
				return paths.get(0);
			}
			else
			{
				ArrayList<Object> validPath = new ArrayList<Object>();
				boolean firstCont = false;
//				System.out.println("Several paths founded.");
				
				for (ArrayList<Object> path : paths)
				{
					if(path.containsAll(stopStations))
					{
						if(!firstCont)
						{
							validPath = path;
							firstCont = true;
						}
						
						if(path.size() < validPath.size())
						{
							validPath = path;
						}
					}
				}
				
				return validPath;
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the select path. 
	 * @return the select path.
	 */
	public ArrayList<Object> selectPath()
	{
		if(paths.size() == 0)
		{
			System.out.println("Path not found.");
		}
		else
		{
			if(paths.size() == 1)
			{
				System.out.println("One path founded.");
				printPaths();
				return paths.get(0);
			}
			else
			{
				Scanner scanner = new Scanner(System.in);
				boolean val = true;
				String opt = "";
				int selected = 0;
				System.out.println("Several paths founded, select one: ");
				printPaths();
				
				do
				{
					val = true;
					System.out.println("Write the number of path: ");
				
					opt = scanner.nextLine();
					
					if(Util.isInteger(opt))
					{
						selected = Integer.parseInt(opt);
						
						if(selected < 1 || selected > paths.size())
						{
							System.err.println("Out range number. Try again.");
							val = false;
						}
					}
					else
					{
						System.err.println("The number isn't a integer. Try again.");
						val = false;
					}
				}while(!val);
				
				scanner.close();
				return paths.get(selected - 1);
			}
		}
		
		return null;
	}
	
	/**
	 * Return a ArrayList<Node> with all nodes in the path. 
	 * @param nodes the visited nodes.
	 * @return all nodes befores found the seraced node.
	 */
	public ArrayList<Object> setNewPath(ArrayList<Object> nodes)
	{
		ArrayList<Object> path = new ArrayList<Object>();
		
		for (Object object : nodes) {
			path.add(object);
		}
		
		return path;
	}
}
