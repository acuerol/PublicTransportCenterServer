package model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class Way implements Serializable {
	/**
	 * The Way serialVersionUID.
	 */
	private static final long serialVersionUID = 2465918592855998873L;
	private ArrayList<Double> distances;
	private ArrayList<Object> nodes;
	// ver si no es mejro con un TreeMap.
	/**
	 * Creates a Way instance with all parameters.
	 * @param distances the distances of every node.
	 * @param nodes the way nodes 
	 */
	public Way(ArrayList<Double> distances, ArrayList<Object> nodes) {
		this.distances = distances;
		this.nodes = nodes;
	}
	
	public Object getNextNode(double distance)
	{
//		System.out.println("----------------------");
		for (int i = 0 ; i < distances.size() - 1 ; i++)
		{
//			System.out.println("node Examined: " + nodes.get(i) + "--" - distances.get(i));
			
			//Ver si es adecuado comparar double de esta manera.
			if(distances.get(i) <= distance && distances.get(i+1) > distance)
			{
				return nodes.get(i+1);
			}
		}
		
		return new Station("Unknowed", new Point2D.Double(), false);
	}
	
	public Station getNextStopStation(double distance)
	{		
		
		double maxDistance = 0.0;
		boolean validRange = false;
		
		for (int i = 0 ; i < distances.size() - 1 ; i++)
		{
			if(distances.get(i) <= distance && distances.get(i+1) > distance)
			{
				maxDistance = distances.get(i);
				validRange = true;
				continue;
			}
			
			if(validRange)
			{
				if(nodes.get(i) instanceof Station)
				{
					return (Station) nodes.get(i);
				}
			}
		}

		System.out.println("----------------------");
		System.out.println("---- " + distance + " ----");
		
		for (Route route : PublicTransportCenter.getPublicTransportCenter().getRoutes()) {
			if(route.getWay().equals(this))
			{
				System.out.println(route.getName());
			}
		}
		
		for (Object node : nodes) {
			if(node instanceof Station)
			{
				System.out.println(nodes.indexOf(node));
				System.out.println(((Station)(node)).getName() + " - " + distances.get(nodes.indexOf(node)));
			}
			else
			{
				System.out.println(nodes.indexOf(node));
				System.out.println(((Semaphore)(node)).getID() + " - " + distances.get(nodes.indexOf(node)));
			}
		}
		System.out.println("----------------------");
		
		return new Station("Unknowed", new Point2D.Double(), false);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Way other = (Way) obj;
		if (nodes == null) {
			if (other.nodes != null)
				return false;
		} else if (!nodes.equals(other.nodes))
			return false;
		
		return true;
	}
	
	/**
	 * Returns the distance of every node.
	 * @return the distance of every node
	 */
	public ArrayList<Double> getDistances() {
		return distances;
	}
	
	/**
	 * Returns all nodes of the way.
	 * @return the way nodes.
	 */
	public ArrayList<Object> getNodes() {
		return nodes;
	}
	
	@Override
	public int hashCode() {
		return distances.hashCode();
	}

	/**
	 * Sets the nodes distance.
	 * @param distances the nodes distance
	 */
	public void setDistance(ArrayList<Double> distances) {
		this.distances = distances;
	}

	/**
	 * Sets all way nodes .
	 * @param nodes way nodes.
	 */
	public void setNode(ArrayList<Object> nodes) {
		this.nodes = nodes;
	}

	@Override
	public String toString() {
		return "Way [ distances = " + distances + ", nodes = "
				+ nodes + "]";
	}
	
	
}
