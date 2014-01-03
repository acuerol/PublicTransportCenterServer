package model;

import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class Semaphore implements Serializable {
	/**
	 * The Semaphore serialVersionUID
	 */
	private static final long serialVersionUID = 8286679660561586733L;
	private ArrayList<Object> children;
	private String id;
	private Point2D.Double position;
	private boolean state;
	private int time;
	private int timeGreen;
	private int timeRed;
	
	/**
	 * Creates a Semaphore instance with all parameters.
	 * @param id the semaphore id.
	 * @param timeRed the time until the red light change to green
	 * @param timeGreen the time until the green light change to red
	 * @param position the point associated to the node in the graph.
	 */
	public Semaphore(String id, int timeRed, int timeGreen, Point2D.Double position)
	{
		this.id = id;
		state = true;
		this.position = position;
		this.timeRed = timeRed;
		this.timeGreen = timeGreen;
	}
	
    public String getId() {
		return id;
	}

    public void setState(boolean state) {
		this.state = state;
	}

	public void setTime(int time) {
		this.time = time;
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
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		
		Semaphore other = (Semaphore) obj;
		
		if(id == null)
		{
			if(other.id != null)
				return false;
		}
		else
		{
			if(!id.equals(other.id))
				return false;
		}

		return true;
	}
	
    /**
	 * Returns a ArrayList with the node cildren.
	 * @return ArrayList<Object> that contains the child nodes. 
	 */
	public ArrayList<Object> getChildNodes()
	{
		return children;
	}
	
	/**
     * Returns the semaphore id.
     * @return the semaphore id. 
     */
    public String getID()
	{
		return id;
	}
	
	/**
	 * Returns the semaphore position.
	 * @return the semaphore position.
	 */
	public Point2D.Double getPosition()
	{
		return position;
	}
	
	/**
     * Return the semaphore state, if the state is true the ligt is green else it is red. 
     * @return the state of the ligth.
     */
	public boolean getState()
	{
		return state;
	}
	
	/**
     * Returns the semaphore stopwatch time.
     * @return the semaphore stopwatch time. 
     */
    public int getTime()
	{
		return time;
	}
	
	/**
	 * Return the time until the light change to red.
	 * @return the time until the light change to red.
	 */
	public int getTimeGreen()
	{
		return timeGreen;
	}

	/**
	 * Return the time until the light change to green.
	 * @return the time until the light change to green.
	 */
	public int getTimeRed()
	{
		return timeRed;
	}
	
	@Override
	public int hashCode()
	{
		return id.hashCode();
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
	
	@Override
	public String toString()
	{
		return "Semaphore [id = " + id + "]";//, children = " + children + "]";
		
		//return "Semaphore [id = " + id + ", state = " + state + ", position = " + position.getX() + "," + position.getY() + ", timeRed = " + timeRed
			//	+ ", timeGreen = " + timeGreen + ", children = " + children + "]";
	}
}
