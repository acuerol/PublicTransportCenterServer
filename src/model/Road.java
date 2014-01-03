package model;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class Road implements Serializable {
	
	/**
	 * The Road serialVersionUID.
	 */
	private static final long serialVersionUID = 1978281887330643800L;
	private double distance;
	private Point2D.Double finalPoint;
	private String name;
	private ArrayList<Object> nodesAttached;
	private Point2D.Double startPoint;
	
	/**
	 * Creates the Road instance with all parameters. 
	 * @param name the road name
	 * @param startPoint the road start point.
	 * @param finalPoint the road final point.
	 * @param distance the road length.
	 */
	public Road(String name, Point2D.Double startPoint, Point2D.Double finalPoint,
			double distance) {
		this.name = name;
		this.startPoint = startPoint;
		this.finalPoint = finalPoint;
		this.distance = distance;
		nodesAttached = new ArrayList<Object>(2);
	}
	
	/**
	 * Sets all nodes attached.
	 * @param nodes the nodes attached.
	 */
	public void addNodesAttached(ArrayList<Object> nodes)
	{
		for (Object object : nodes) {
			nodesAttached.add(object);
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Road other = (Road) obj;
		if (finalPoint == null) {
			if (other.finalPoint != null)
				return false;
		} else if (!finalPoint.equals(other.finalPoint))
			return false;
		if (!nodesAttached.equals(other.nodesAttached))
			return false;
		if (startPoint == null) {
			if (other.startPoint != null)
				return false;
		} else if (!startPoint.equals(other.startPoint))
			return false;
		return true;
	}
	
	/**
	 * Returns the adition of all points distance in the middle.
	 * @return the distance of road.
	 */
	public double getDistance() {
		return distance;
	}
	
	/**
	 * Returns the final point coordinates.
	 * @return the final point coordinates.
	 */
	public Point2D.Double getFinalPoint() {
		return finalPoint;
	}
	
	/**
	 * Returns the road name. 
	 * @return the road name.
	 */
	public String getName() {
		return name;
	}
	
	//Revisar si se deben añadir de a uno o todo el arreglo de una.
	
	/**
	 * Returns the nodes attached.
	 * @return the nodes attached.
	 */
	public ArrayList<Object> getNodesAttached() {
		return nodesAttached;
	}
	
	/**
	 * Returns the start point coordinates.
	 * @return the start point coordinates.
	 */
	public Point2D.Double getStartPoint() {
		return startPoint;
	}
	
	@Override
	public int hashCode() {
		String str = distance + ";" + finalPoint.getX() + "," + finalPoint.getY();
 		return str.hashCode();
	}

	@Override
	public String toString() {
		
		return "Road [name = " + name + ", nodesAttached = " + nodesAttached + "]";
		
		//return "Road [name = " + name + ", startPoint = " + startPoint.getX() + "," + startPoint.getY()
	//			+ ", finalPoint = " + finalPoint.getX() + "," + finalPoint.getY() + ", distance = " + distance
//				+ ", nodesAttached = " + nodesAttached + "]";
	}
}
