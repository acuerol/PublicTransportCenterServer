package model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class Station implements Serializable {
	
	/**
	 * The Station serialVersionUID
	 */
	private static final long serialVersionUID = 4925363765119719623L;
	private ArrayList<Object> children;
	private String name;
	private Point2D.Double position;
	private boolean state;
	
	/**
	 * Creates a Station instance with all parameters.
	 * @param name thestation name.
	 * @param position the station point that represent the position in the graph.
	 * @param state the station state, if it is nice or blocked. 
	 */
	public Station(String name, Point2D.Double position, boolean state)
	{
		this.name = name;
		this.position = position;
		this.state = state;
	}

	/**
	 * Add a node to the child nodes.
	 * @param node the node to be added yo children
	 */
	public void addChild(Object node)
	{
		if(children == null)
		{
			children = new ArrayList<Object>();
		}
		
		children.add(node);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Station other = (Station) obj;

		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (state != other.state)
			return false;
		return true;
	}

	//No sé si sea necesario.
	/**
	 * Returns a ArrayList with the node cildren.
	 * @return ArrayList<Object> that contains the child nodes. 
	 */
	public ArrayList<Object> getChildNodes()
	{
		return children;
	}

	/**
	 * Returns the station name.
	 * @return the station name.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Return the point that represent the station position in the graph.
	 * @return the coordinates of station position. 
	 */
	public Point2D.Double getPosition()
	{
		return position;
	}
	
	/**
	 * Returns the station state, if this is false the station is damaged else it is nice.
	 * @return the station state
	 */
	public boolean getState()
	{
		return state;
	}

	@Override
	public int hashCode() {
		return position.hashCode();
	}

	/**
	 * Returns true if child is content int childNodes, else return false.
	 * @param child the node probably content in childNodes.
	 * @return true if childNodes contains child.
	 */
	public boolean isChild(Object child)
	{
		return children.contains(child);
	}

	/**
	 * Sets the station state, if state is false the station is damaged else it is nice.
	 * @param state the station state.
	 */
	public void setState(boolean state)
	{
		this.state = state;
	}

	@Override
	public String toString()
	{
		return "Station [name = " + name + "]";//, children = " + children + "]";
		
//		return "Station [name = " + name + ", position = " + position.getX() + "," + position.getY() + ", state = "
	//			+ state + ", children = " + children + "]";
	}
}
