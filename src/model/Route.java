package model;

import java.io.Serializable;

/**
 * 
 * @author Alexis Cuero Losada
 *
 */
public class Route implements Serializable {
	/**
	 * The Route serialVersionUID
	 */
	private static final long serialVersionUID = 1292163716323103404L;
	private String name;
	private Way way;
	
	public Way getWay() {
		return way;
	}

	/**
	 * Creates a route with all parameters.
	 * @param name the route name.
	 * @param way the Way associated to the Route. 
	 */
	public Route(String name, Way way) {
		this.name = name;
		this.way = way;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Route other = (Route) obj;

		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Returns the route name.
	 * @return the route name.
	 */
	public String getName()
	{
		return name;
	}
	
	
	//Pediente
	
	/**
	 * Returns the route distance. 
	 * @return the route distance.
	 */
	/*
	public double getDistance()
	{
		return distance;
	}
	*/
	
	@Override
	public int hashCode()
	{
		return way.hashCode();
	}
	
	/**
	 * Sets the route name.
	 * @param name the route name.
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Route [name = " + name + ", way = " + way + "]";
	}
}
