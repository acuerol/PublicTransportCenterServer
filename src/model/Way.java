package model;

import java.io.Serializable;
import java.util.ArrayList;

import util.UtilCalc;

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

	public String toString(String name)
	{
		String str = name + ": ";
		for (int i = 0 ; i < nodes.size() ; i++)
		{
			if(nodes.get(i) instanceof Station)
			{
				str += (" --" + UtilCalc.round(distances.get(i), 2) + "-- " + ((Station)(nodes.get(i))).getName());
			}
			else
			{
				str += (" --" + UtilCalc.round(distances.get(i), 2) + "-- " + ((Semaphore)(nodes.get(i))).getID());
			}
		}
		
		return str;
	}
	
	@Override
	public String toString() {
		return "Way [ distances = " + distances + ", nodes = "
				+ nodes + "]";
	}
	
	
}
