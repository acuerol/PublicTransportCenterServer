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

	private static final long serialVersionUID = 2465918592855998873L;
	private ArrayList<Double> distances;
	private ArrayList<Object> nodes;

	/**
	 * Creates a Way instance with all parameters.
	 * @param distances the distances of every node.
	 * @param nodes the way nodes 
	 */
	public Way(ArrayList<Object> nodes, ArrayList<Double> distances) {
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

	/**
	 * Returns a String representation of all nodes with the distances in the middle.
	 * @return a String representation of all nodes
	 */
	public String toStringNodesDistances()
	{
		String way = "";
		for (int i = 0 ; i < nodes.size() ; i++)
		{
			if(nodes.get(i) instanceof Station)
			{
				way += (" --" + UtilCalc.round(distances.get(i), 2) + "-- " + ((Station)(nodes.get(i))).getName());
			}
			else
			{
				way += (" --" + UtilCalc.round(distances.get(i), 2) + "-- " + ((Semaphore)(nodes.get(i))).getId());
			}
		}
		
		return way;
	}
	
	@Override
	public String toString() {
		return "Way [ distances = " + distances + ", nodes = "
				+ nodes + "]";
	}
}
